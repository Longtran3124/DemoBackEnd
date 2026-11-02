package org.example.demobackend.repository;

import org.example.demobackend.entity.ProductDetail;
import org.example.demobackend.entity.ProductOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailRepo extends JpaRepository<ProductDetail, Long> {
}
