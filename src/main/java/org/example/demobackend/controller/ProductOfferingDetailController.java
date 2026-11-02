package org.example.demobackend.controller;

import org.example.demobackend.dto.request.AssignProductDetailReq;
import org.example.demobackend.entity.ProductOffering;
import org.example.demobackend.entity.ProductOfferingDetail;
import org.example.demobackend.service.ProductOfferingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductOfferingDetailController {

    @Autowired
    private ProductOfferingDetailService productOfferingDetailService;

    @PostMapping("/assign-product-detail")
    public ResponseEntity<ProductOffering> assignProductDetail(@RequestBody AssignProductDetailReq request) {
        ProductOffering productOffering = productOfferingDetailService.assignProductDetail(request);
        return ResponseEntity.ok(productOffering);
    }
}
