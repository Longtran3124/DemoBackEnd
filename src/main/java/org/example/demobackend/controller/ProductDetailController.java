package org.example.demobackend.controller;

import lombok.Getter;
import org.example.demobackend.dto.request.ProductDetailCreateReq;
import org.example.demobackend.entity.ProductDetail;
import org.example.demobackend.service.ProductDetailService;
import org.example.demobackend.service.ProductOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/product-detail")
    public ResponseEntity<List<ProductDetail>> getAll() {
        List<ProductDetail> productDetails = productDetailService.getAll();
        return ResponseEntity.ok(productDetails);
    }

    @PostMapping("/product-detail")
    public ResponseEntity<ProductDetail> create(@RequestBody ProductDetailCreateReq request) {
        ProductDetail productDetail = productDetailService.create(request);
        return ResponseEntity.ok(productDetail);
    }

}
