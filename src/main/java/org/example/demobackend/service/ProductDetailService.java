package org.example.demobackend.service;

import org.example.demobackend.dto.request.ProductDetailCreateReq;
import org.example.demobackend.entity.ProductDetail;
import org.example.demobackend.entity.ProductOffering;

import java.util.List;

public interface ProductDetailService {

    List<ProductDetail> getAll();

    ProductDetail create(ProductDetailCreateReq request);
}
