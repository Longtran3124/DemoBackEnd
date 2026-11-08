package org.example.demobackend.repository;

import org.example.demobackend.common.StatusEnum;
import org.example.demobackend.entity.ProductOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOfferingRepo extends JpaRepository<ProductOffering, Long> {

    List<ProductOffering> findByNameContaining(String name);

    List<ProductOffering> findByNameContainingAndPrice(String name, Long price);

    // lấy ra danh sách product_offering theo detail_id = 1
    // Tự custom 
    @Query(value = "select po from ProductOffering po \n" +
                    " where po.id in (select pod.productOffering.id from ProductOfferingDetail pod where pod.productDetail.id = 1)")

    // nativeQuery = true -> viết câu lệnh query này theo kiểu native (theo kiểu csdl bên dưới mình đang dùng)
    // Không nên dùng cách này vì nó chỉ dụng được csdl cố định
//    @Query(value = "select * from product_offerings where id in (select product_offering_id from product_offering_detail where product_detail_id = 1)", nativeQuery = true)
    List<ProductOffering> findByDetailId(Long id);


    // lấy danh sách ProductOffering có ít nhất 1 productDetail thuộc hãng brand, đồng thời có status đúng với giá trị truyền vào
    @Query(value = "select po from ProductOffering po " +
                    " join ProductOfferingDetail pod on pod.productOffering.id = po.id " +
                    " join ProductDetail pd on pod.productDetail.id = pd.id " +
                    "where pd.brand = :brand and po.statusEnum = :status")
    // Param nối tham số của method với chỗ :tên trong câu query
    List<ProductOffering> findByBrandAndStatus(@Param("brand") String brand,
                                               @Param("status") StatusEnum status);

}
