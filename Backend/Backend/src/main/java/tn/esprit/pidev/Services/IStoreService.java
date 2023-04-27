package tn.esprit.pidev.Services;

import java.util.List;
import java.util.Optional;

import tn.esprit.pidev.Entities.Product;
import tn.esprit.pidev.Entities.Store;

public interface IStoreService {

    List<Store> getAllStores();

    Optional<Store> getStoreById(Long id);

    Store createOrUpdateStore(Store store);

    void deleteStoreById(Long id);

    Store updateStore(Long id, Store store);

    Store assignProductToStore( long id,Product product);
    Store assignExistingProductToStore( long storeId,long productId);

}