package org.example.demobackend.repository;

import org.example.demobackend.entity.ProductOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductOfferingRepo extends JpaRepository<ProductOffering, Long> {

    List<ProductOffering> findByNameContaining(String name);

    List<ProductOffering> findByNameContainingAndPrice(String name, Long price);
}
