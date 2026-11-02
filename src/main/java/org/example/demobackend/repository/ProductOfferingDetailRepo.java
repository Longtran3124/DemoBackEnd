package org.example.demobackend.repository;

import org.example.demobackend.entity.ProductOfferingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOfferingDetailRepo extends JpaRepository<ProductOfferingDetail, Long> {
}
