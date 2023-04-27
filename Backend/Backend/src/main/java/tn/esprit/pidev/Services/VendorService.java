package tn.esprit.pidev.Services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.Entities.Store;
import tn.esprit.pidev.Entities.Vendor;
import tn.esprit.pidev.Repositories.StoreRepository;
import tn.esprit.pidev.Repositories.VendorRepository;

@Service
public class VendorService implements IVendorService {

    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Vendor> getAllVendors() {
        try {
            return (List<Vendor>) vendorRepository.findAll();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<Vendor> getVendorById(Long id) {
        return vendorRepository.findById(id);
    }

    @Override
    public Vendor saveVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor assignStoreToVendor(Long id, Store store) {

        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found with ID: "));
        store.setVendor(vendor);
        // storeRepository.save(store);
        vendor.getStores().add(store);
        return vendorRepository.save(vendor);

    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public Vendor updateVendor(Long id, Vendor vendor) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);

        if (optionalVendor.isPresent()) {
            Vendor existingVendor = optionalVendor.get();
            existingVendor.setName(vendor.getName());
            existingVendor.setLastName(vendor.getLastName());
            existingVendor.setProfileImageLink(vendor.getProfileImageLink());
            existingVendor.setStores(vendor.getStores());
            return vendorRepository.save(existingVendor);
        } else {
            return null;
        }
    }

}