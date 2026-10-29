package org.example.demobackend.service.iml;

import org.example.demobackend.entity.ProductOffering;
import org.example.demobackend.repository.ProductOfferingRepo;
import org.example.demobackend.service.ProductOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOfferingServiceIml implements ProductOfferingService {

    @Autowired // thay vì phải new thì dùng Autowired
    private ProductOfferingRepo productOfferingRepo;

    @Override
    public ProductOffering getById(Long id) {
        Optional<ProductOffering> product = productOfferingRepo.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException("Khong tim thay san pham");
        } else {
            return product.get();
        }
    }

    @Override
    public List<ProductOffering> getAll() {
        return productOfferingRepo.findAll();
    }

    @Override
    public List<ProductOffering> findByNameContaining(String name) {
        return productOfferingRepo.findByNameContaining(name);
    }

    @Override
    public List<ProductOffering> findByNameContainingAndPrice(String name, Long price) {
        return productOfferingRepo.findByNameContainingAndPrice(name, price);
    }

    @Override
    public ProductOffering createProductOffering(ProductOffering productOffering) {
        if (productOffering.getId() != null) {
            throw new RuntimeException("Khong duoc phep truyen id vao");
        }
        return productOfferingRepo.save(productOffering);
    }

    @Override
    public ProductOffering updateProductOffering(Long id, ProductOffering productOffering) {
        getById(id);
        productOffering.setId(id);
        return productOfferingRepo.save(productOffering);
    }


}
