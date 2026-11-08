package org.example.demobackend.repository;

import org.example.demobackend.entity.ProductDetail;
import org.example.demobackend.entity.ProductOffering;
import org.example.demobackend.entity.ProductOfferingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductOfferingDetailRepo extends JpaRepository<ProductOfferingDetail, Long> {
    Optional<ProductOfferingDetail> findByProductOfferingAndProductDetail(ProductOffering productOffering, ProductDetail productDetail);

    // HQL
    @Modifying // -> tất cả các lệnh liên quan đến tác động dữ liệu như delete hoặc update thì mình phải sử dụng anotation này
    @Query("delete from ProductOfferingDetail p where p.id = :id")
    void deleteById(Long id);

    // Native Query
    @Modifying
    @Query(value = "delete from product_offering_detail where id = :id", nativeQuery = true)
    void deleteById2(Long id);
}
