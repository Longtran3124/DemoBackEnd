package org.example.demobackend.service;

import org.example.demobackend.entity.ProductOffering;

import java.util.List;

public interface ProductOfferingService {

    ProductOffering getById(Long id);

    List<ProductOffering> getAll();

    List<ProductOffering> findByNameContaining(String name);

    List<ProductOffering> findByNameContainingAndPrice(String name, Long price);
}
