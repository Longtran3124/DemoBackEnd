package org.example.demobackend.service.iml;

import lombok.RequiredArgsConstructor;
import org.example.demobackend.dto.request.AssignProductDetailReq;
import org.example.demobackend.entity.ProductDetail;
import org.example.demobackend.entity.ProductOffering;
import org.example.demobackend.entity.ProductOfferingDetail;
import org.example.demobackend.repository.ProductDetailRepo;
import org.example.demobackend.repository.ProductOfferingDetailRepo;
import org.example.demobackend.repository.ProductOfferingRepo;
import org.example.demobackend.service.ProductOfferingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOfferingDetailServiceImp implements ProductOfferingDetailService {

//    @Autowired
    private final ProductOfferingDetailRepo productOfferingDetailRepo;

//    @Autowired
    private final ProductOfferingRepo productOfferingRepo;

//    @Autowired
    private final ProductDetailRepo productDetailRepo;

    @Override
    public ProductOffering assignProductDetail(AssignProductDetailReq request) {

        List<Long> productDetailIds = new LinkedList<>(request.getProductDetailIds());

        // validate data
        if (request.getProductOfferingId() == null) {
            throw new RuntimeException("ProductOfferingId not null");
        }
        if (request.getProductDetailIds() == null || productDetailIds.isEmpty()) {
            throw new RuntimeException("productDetailIds not null and not empty");
        }

        Optional<ProductOffering> productOfferingOptinal = productOfferingRepo.findById(request.getProductOfferingId());
        if (productOfferingOptinal.isEmpty()) {
            throw new RuntimeException("productOfferingId not exists");
        }

        ProductOffering productOffering = productOfferingOptinal.get();

        List<ProductDetail> productDetails = productDetailRepo.findAllById(productDetailIds);
        if (productDetails.isEmpty()) {
            throw new RuntimeException("productDetails not exists");
        }

        List<ProductOfferingDetail> productOfferingDetails = new ArrayList<>();

        for (int i = 0; i < productDetails.size(); i++) {
            ProductOfferingDetail productOfferingDetail = new ProductOfferingDetail();
            productOfferingDetail.setProductOffering(productOfferingOptinal.get());
            productOfferingDetail.setProductDetail(productDetails.get(i));

            productOfferingDetailRepo.delete(productOfferingDetail);
            productOfferingDetails.add(productOfferingDetail);
        }

        productOfferingDetailRepo.saveAll(productOfferingDetails);
        productOffering.setProductOfferingDetails(productOfferingDetails);
        return productOffering;
    }
}
