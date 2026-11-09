package org.example.demobackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.demobackend.dto.request.AssignProductDetailReq;
import org.example.demobackend.entity.ProductOffering;
import org.example.demobackend.entity.ProductOfferingDetail;
import org.example.demobackend.service.ProductDetailService;
import org.example.demobackend.service.ProductOfferingDetailService;
import org.example.demobackend.service.ProductOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductOfferingDetailController {

    // beanFactory
    // applicationContext
    // -> 2 thằng này sẽ tự khởi tạo đối tượng và inject vào cho mình

    //@Autowired // field injection -> sử dụng anotation @Autowired vào trong field mà mình cần inject (field là thuộc tính)
    private final ProductOfferingDetailService productOfferingDetailService;
    private final ProductOfferingService productOfferingService;
    private final ProductDetailService productDetailService;


    // contructor injection -> khai báo thuộc tính  là 1 trong các tham số của constructer,
    // khi mà spring khởi tạo đối tượng thì nó sẽ tự động inject phụ thuộc ấy vaào constructer
    // reflection
    public ProductOfferingDetailController(ProductOfferingDetailService productOfferingDetailService,
                                           ProductOfferingService productOfferingService,
                                           ProductDetailService productDetailService) {
        this.productOfferingDetailService = productOfferingDetailService;
        this.productOfferingService = productOfferingService;
        this.productDetailService = productDetailService;
    }


    // setter injection
//    @Autowired
//    public void setProductOfferingDetailService(ProductOfferingDetailService productOfferingDetailService) {
//        this.productOfferingDetailService = productOfferingDetailService;
//    }

    // POST http://localhost:8081/assign-product-detail   (body: AssignProductDetailReq)
    @PostMapping("/assign-product-detail")
    public ResponseEntity<ProductOffering> assignProductDetail(@RequestBody AssignProductDetailReq request) {
        ProductOffering productOffering = productOfferingDetailService.assignProductDetail(request);
        return ResponseEntity.ok(productOffering);
    }
}
