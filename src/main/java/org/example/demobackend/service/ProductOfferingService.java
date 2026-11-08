package org.example.demobackend.service;

import org.example.demobackend.common.StatusEnum;
import org.example.demobackend.dto.request.ProductOfferingCreateReq;
import org.example.demobackend.entity.ProductOffering;

import java.util.List;

public interface ProductOfferingService {

    ProductOffering getById(Long id);

    List<ProductOffering> getAll();

    List<ProductOffering> findByNameContaining(String name);

    List<ProductOffering> findByNameContainingAndPrice(String name, Long price);

    ProductOffering createProductOffering(ProductOfferingCreateReq request);

    ProductOffering updateProductOffering(Long id, ProductOffering productOffering);

    List<ProductOffering> findByBrandAndStatus(String brand, StatusEnum status);

    List<ProductOffering> filter(String name, Long minPrice, Long maxPrice, String color, String status);
}
