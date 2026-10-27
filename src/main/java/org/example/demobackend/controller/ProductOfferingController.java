package org.example.demobackend.controller;

import org.example.demobackend.entity.ProductOffering;
import org.example.demobackend.service.ProductOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // được phép khai báo api ở đây
public class ProductOfferingController {

    @Autowired
    private ProductOfferingService productOfferingService;

    @GetMapping("/products")
    // class ResponseEntity dùng để trả dữ liệu về
    public ResponseEntity<ProductOffering> getById(Long id){
        id = 1L;
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
}
