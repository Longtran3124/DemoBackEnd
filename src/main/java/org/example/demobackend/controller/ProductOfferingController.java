package org.example.demobackend.controller;

import org.example.demobackend.common.StatusEnum;
import org.example.demobackend.dto.request.ProductOfferingCreateReq;
import org.example.demobackend.entity.ProductOffering;
import org.example.demobackend.service.ProductOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // được phép khai báo api ở đây
@RequestMapping("/api/v1/products")
public class ProductOfferingController {

    @Autowired
    private ProductOfferingService productOfferingService;

    // GET http://localhost:8081/api/v1/products/filter?name=&minPrice=&maxPrice=&color=&status=
    @GetMapping("/filter")
    // RequestParam -> mục đích để truyền các dữ liệu này vào tham số của api
    // required = false -> bắt buộc dữ liệu truyền vào không được để trống
    public ResponseEntity<List<ProductOffering>> filter(@RequestParam(name = "name", required = false) String name,
                                                        @RequestParam(name = "minPrice", required = false) Long minPrice,
                                                        @RequestParam(name = "maxPrice", required = false) Long maxPrice,
                                                        @RequestParam(name = "color", required = false) String color,
                                                        @RequestParam(name = "status", required = false) String status){
        List<ProductOffering> productOfferingList = productOfferingService.filter(name, minPrice, maxPrice, color, status);
        return ResponseEntity.ok(productOfferingList);
    }

    // GET http://localhost:8081/api/v1/products/{id}   (vd: /api/v1/products/1)
    @GetMapping("/{id}")
    // class ResponseEntity dùng để trả dữ liệu về
    public ResponseEntity<ProductOffering> getById(@PathVariable Long id){
        ProductOffering p = productOfferingService.getById(id);
        return ResponseEntity.ok(p);
    }

    // GET http://localhost:8081/api/v1/products/getAll
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductOffering>> getAll(){
        return ResponseEntity.ok(productOfferingService.getAll());
    }

    // GET http://localhost:8081/api/v1/products/search?name=
    @GetMapping("/search")
    public ResponseEntity<List<ProductOffering>> findByNameContaining(String name){
        return ResponseEntity.ok(productOfferingService.findByNameContaining(name));
    }

    // GET http://localhost:8081/api/v1/products/searchs?name=&price=
    @GetMapping("searchs")
    public ResponseEntity<List<ProductOffering>> findByNameContainingAndPrice(String name, Long price){
        return ResponseEntity.ok(productOfferingService.findByNameContainingAndPrice(name, price));
    }


    // POST http://localhost:8081/api/v1/products/productss   (body: ProductOfferingCreateReq)
    @PostMapping("productss")
    public ResponseEntity<ProductOffering> create(@RequestBody ProductOfferingCreateReq request){
        return ResponseEntity.ok(productOfferingService.createProductOffering(request));
    }

    // PUT http://localhost:8081/api/v1/products/productss/{id}   (body: ProductOffering)
    @PutMapping("productss/{id}")
    public ResponseEntity<ProductOffering> update(@PathVariable Long id, @RequestBody ProductOffering productOffering){
        return ResponseEntity.ok(productOfferingService.updateProductOffering(id, productOffering));
    }

    // GET http://localhost:8081/api/v1/products/products/by-brand?brand=Apple&status=ACTIVE
    @GetMapping("/products/by-brand")
    // RequestParam -> lấy giá trị từ phần query string trên url (sau dấu ?)
    public ResponseEntity<List<ProductOffering>> findByBrandAndStatus(@RequestParam String brand,
                                                                      @RequestParam StatusEnum status){
        return ResponseEntity.ok(productOfferingService.findByBrandAndStatus(brand, status));
    }
}
