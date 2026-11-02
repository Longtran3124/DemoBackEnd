package org.example.demobackend.service.iml;

import org.example.demobackend.dto.request.ProductDetailCreateReq;
import org.example.demobackend.entity.ProductDetail;
import org.example.demobackend.entity.ProductOffering;
import org.example.demobackend.repository.ProductDetailRepo;
import org.example.demobackend.repository.ProductOfferingRepo;
import org.example.demobackend.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImp implements ProductDetailService {

    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Override
    public List<ProductDetail> getAll() {
        return productDetailRepo.findAll();
    }

    @Override
    public ProductDetail create(ProductDetailCreateReq request) {
        if (request.getWeight() == null || request.getFeature() == null) {
            throw new RuntimeException("khong duoc de trong");
        }

        ProductDetail  productDetail = new ProductDetail();
        productDetail.setWeight(request.getWeight());
        productDetail.setFeature(request.getFeature());
        productDetail.setPower(request.getPower());
        productDetail.setBrand(request.getBrand());
        productDetail.setImage(request.getImage());
        productDetail.setVideo(request.getVideo());

        return productDetailRepo.save(productDetail);
    }
}
