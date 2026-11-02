package org.example.demobackend.service;

import org.example.demobackend.dto.request.AssignProductDetailReq;
import org.example.demobackend.entity.ProductOffering;

public interface ProductOfferingDetailService {

    ProductOffering assignProductDetail(AssignProductDetailReq request);
}
