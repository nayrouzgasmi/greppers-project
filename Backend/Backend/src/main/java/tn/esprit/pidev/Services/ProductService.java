package tn.esprit.pidev.Services;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import tn.esprit.pidev.Entities.Store;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Entities.Tag;
import tn.esprit.pidev.Repositories.ProductRepository;
import tn.esprit.pidev.Repositories.StoreRepository;
import tn.esprit.pidev.Repositories.TagRepository;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
	AmazonS3 s3Client;
    // @Value("${do.space.bucket}")
	private String doSpaceBucket="green-bubble";

	String FOLDER = "products/";

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            // existingProduct.setStore(updatedProduct.getStore());
            existingProduct.setCompositions(updatedProduct.getCompositions());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setAvailable(updatedProduct.isAvailable());
            existingProduct.setBioScore(updatedProduct.getBioScore());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    public void deleteProductWithReference(Long productId) {
        // Retrieve the product that you want to delete
        Product product = productRepository.findById(productId).orElse(null);
        // product.getImageUrls().clear();

        if (product != null) {
            // Retrieve the stores that reference the product
            List<Store> stores = storeRepository.findByProducts_Id(productId);

            // Remove the product from the set of products in the stores
            for (Store store : stores) {
                store.getProducts().remove(product);
                storeRepository.save(store);
            }
            // product.getTags().forEach(tag->product.getTags().remove(tag));
            product.getTags().clear();
            product.getImageUrls().clear();
            // product.getImageUrls().forEach(image->product.getImageUrls().remove(image));
            // Delete the product
            productRepository.delete(product);
        }
    }

    public Product createProductAndAssignToStore(Long storeId, Product productDto,List<MultipartFile> multipartFiles) throws IOException {
        // Retrieve the store from the repository
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + storeId));

        // Create the product entity from the DTO
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        if (productDto.getQuantity() > 0)
            product.setAvailable(true);
        else
            product.setAvailable(false);
        product.setBioScore(productDto.getBioScore());
        if (product.getImageUrls() == null) {
            product.setImageUrls(new HashSet<String>());
        }
        multipartFiles.forEach(image -> {
            try {
                String imgUrl=saveFileAlone((MultipartFile) image);
                product.getImageUrls().add(imgUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        product.setStore(store);
        if (product.getTags() == null) {
            product.setTags(new HashSet<Tag>());
        }
        // Assign existing or create new tags for the product
        for (Tag tag : productDto.getTags()) {
            Tag existingTag = tagRepository.findByName(tag.getName());
            if (existingTag != null) {
                // Use existing tag if found in the database
                product.getTags().add(existingTag);
            } else {
                // Create a new tag if not found in the database
                Tag newTag = new Tag();
                newTag.setName(tag.getName());
                newTag.setIcon(tag.getIcon());
                tagRepository.save(newTag);
                product.getTags().add(newTag);
            }
        }

        // Add the product to the store's products set
        store.getProducts().add(product);

        // Save the store (which will cascade save the product)
        storeRepository.save(store);

        return product;
    }
    @Override
	public void saveFile(MultipartFile multipartFile,Product product) throws IOException {
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		String imgName = FilenameUtils.removeExtension(multipartFile.getOriginalFilename());
		String key = FOLDER + imgName + "." + extension;
		saveImageToServer(multipartFile, key);
        product.getImageUrls().add(key);
        return;
	}
    public String saveFileAlone(MultipartFile multipartFile) throws IOException {
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		String imgName = FilenameUtils.removeExtension(multipartFile.getOriginalFilename());
		String key = FOLDER + imgName + "." + extension;
        System.out.println(key);
		saveImageToServer(multipartFile, key);
        return "https://green-bubble.fra1.digitaloceanspaces.com/"+key;
	}
    private void saveImageToServer(MultipartFile multipartFile, String key) throws IOException {
        System.out.println("inside save image");
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(multipartFile.getInputStream().available());
		if (multipartFile.getContentType() != null && !"".equals(multipartFile.getContentType())) {
			metadata.setContentType(multipartFile.getContentType());
		}

		s3Client.putObject(new PutObjectRequest(doSpaceBucket, key, multipartFile.getInputStream(), metadata)
				.withCannedAcl(CannedAccessControlList.PublicReadWrite));
        System.out.println("here");

	}

}
