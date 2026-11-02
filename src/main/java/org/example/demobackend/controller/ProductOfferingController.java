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
public class ProductOfferingController {

    @Autowired
    private ProductOfferingService productOfferingService;

    @GetMapping("/products/{id}")
    // class ResponseEntity dùng để trả dữ liệu về
    public ResponseEntity<ProductOffering> getById(@PathVariable Long id){
        ProductOffering p = productOfferingService.getById(id);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductOffering>> getAll(){
        return ResponseEntity.ok(productOfferingService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductOffering>> findByNameContaining(String name){
        return ResponseEntity.ok(productOfferingService.findByNameContaining(name));
    }

    @GetMapping("searchs")
    public ResponseEntity<List<ProductOffering>> findByNameContainingAndPrice(String name, Long price){
        return ResponseEntity.ok(productOfferingService.findByNameContainingAndPrice(name, price));
    }

//    @PostMapping("productss")
//    public ResponseEntity<ProductOffering> create(@RequestBody ProductOffering productOffering){
//        return ResponseEntity.ok(productOfferingService.createProductOffering(productOffering));
//    }

    @PostMapping("productss")
    public ResponseEntity<ProductOffering> create(@RequestBody ProductOfferingCreateReq request){
        return ResponseEntity.ok(productOfferingService.createProductOffering(request));
    }

    @PutMapping("productss/{id}")
    public ResponseEntity<ProductOffering> update(@PathVariable Long id, @RequestBody ProductOffering productOffering){
        return ResponseEntity.ok(productOfferingService.updateProductOffering(id, productOffering));
    }

    // http://localhost:8081/products/by-brand?brand=Apple&status=ACTIVE
    @GetMapping("/products/by-brand")
    // RequestParam -> lấy giá trị từ phần query string trên url (sau dấu ?)
    public ResponseEntity<List<ProductOffering>> findByBrandAndStatus(@RequestParam String brand,
                                                                      @RequestParam StatusEnum status){
        return ResponseEntity.ok(productOfferingService.findByBrandAndStatus(brand, status));
    }
}
