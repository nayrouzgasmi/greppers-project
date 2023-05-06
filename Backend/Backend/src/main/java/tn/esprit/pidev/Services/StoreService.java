package tn.esprit.pidev.Services;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private IObjectStorageService objectStorageService;
    // @Value("${do.space.bucket}")
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
        String logoUrl = objectStorageService.saveFileAlone((MultipartFile) logo, FOLDER);
        store.setLogo(logoUrl);
        String storeImageUrl = objectStorageService.saveFileAlone((MultipartFile) storeImage, FOLDER);
        store.setStoreImage(storeImageUrl);
        return storeRepository.save(store);
    }

    @Override
    public Store updateStore(long id, Store store, MultipartFile logo, MultipartFile storeImage) {
        Store storeToEdit = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: "));
        if (logo != null) {
            objectStorageService.deleteFile(storeToEdit.getLogo());
            String logoUrl = objectStorageService.saveFileAlone((MultipartFile) logo, FOLDER);
            storeToEdit.setLogo(logoUrl);
        }
        if (storeImage != null) {
            objectStorageService.deleteFile(storeToEdit.getStoreImage());
            String storeImageUrl = objectStorageService.saveFileAlone((MultipartFile) storeImage, FOLDER);
            storeToEdit.setStoreImage(storeImageUrl);
        }
        storeToEdit.setName(store.getName());
        storeToEdit.setDescription(store.getDescription());
        return storeRepository.save(storeToEdit);
    }

    @Override
    public void deleteStoreById(Long id) {
        Store store = storeRepository.findById(id).get();
        if (store != null && store.getLogo() != null) {
            objectStorageService.deleteFile(store.getLogo());
            objectStorageService.deleteFile(store.getStoreImage());
        }
        storeRepository.deleteById(id);
    }

    // @Override
    // public Store updateStore(Long id, Store store) {
    // Optional<Store> optionalStore = storeRepository.findById(id);

    // if (optionalStore.isPresent()) {
    // Store existingStore = optionalStore.get();
    // existingStore.setName(store.getName());
    // existingStore.setDescription(store.getDescription());
    // existingStore.setVendor(store.getVendor());
    // // existingStore.setProducts(store.getProducts());
    // existingStore.setStoreImage(store.getStoreImage());
    // existingStore.setLogo(store.getLogo());
    // existingStore.setApproved(store.isApproved());
    // return storeRepository.save(existingStore);
    // } else {
    // return null;
    // }
    // }

    public Store assignProductToStore(long id, Product product) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with ID: "));
        store.getProducts().add(product);
        return storeRepository.save(store);
    }

    public Store assignExistingProductToStore(long storeiId, long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with ID: "));
        ;
        Store store = storeRepository.findById(storeiId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with ID: "));
        store.getProducts().add(product);
        return storeRepository.save(store);
    }

    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }
}
