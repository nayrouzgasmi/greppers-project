package tn.esprit.pidev.Services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Entities.Store;
import tn.esprit.pidev.Repositories.ProductRepository;
import tn.esprit.pidev.Repositories.StoreRepository;

@Service
public class StoreService implements IStoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
	AmazonS3 s3Client;
    // @Value("${do.space.bucket}")
	private String doSpaceBucket="green-bubble";
	String FOLDER = "stores/";


    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<Store> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Store createOrUpdateStore(Store store, MultipartFile logo, MultipartFile storeImage) {
        try {
            String logoUrl=saveFileAlone((MultipartFile) logo);
            store.setLogo(logoUrl);
            String storeImageUrl=saveFileAlone((MultipartFile) storeImage);
            store.setStoreImage(storeImageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storeRepository.save(store);
    }

    @Override
    public void deleteStoreById(Long id) {
        storeRepository.deleteById(id);
    }

    @Override
    public Store updateStore(Long id, Store store) {
        Optional<Store> optionalStore = storeRepository.findById(id);
    
        if (optionalStore.isPresent()) {
            Store existingStore = optionalStore.get();
            existingStore.setName(store.getName());
            existingStore.setDescription(store.getDescription());
            existingStore.setVendor(store.getVendor());
            // existingStore.setProducts(store.getProducts());
            existingStore.setStoreImage(store.getStoreImage());
            existingStore.setLogo(store.getLogo());
            existingStore.setApproved(store.isApproved());
            return storeRepository.save(existingStore);
        } else {
            return null;
        }
    }

    public Store assignProductToStore(long id, Product product) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with ID: "));
        store.getProducts().add(product);
        return storeRepository.save(store);
    }

    public Store assignExistingProductToStore(long storeiId, long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Cart not found with ID: "));;
        Store store = storeRepository.findById(storeiId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with ID: "));
        store.getProducts().add(product);
        return storeRepository.save(store);
    }

    public Store saveStore(Store store) {
        return storeRepository.save(store);
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
