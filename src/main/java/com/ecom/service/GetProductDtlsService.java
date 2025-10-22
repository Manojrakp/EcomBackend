package com.ecom.service;

import java.util.List;

import com.ecom.dto.ProductDetailsRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.ecom.entity.ProductDtls;
import com.ecom.repository.ProductDtlsRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetProductDtlsService {
    
    private final ProductDtlsRepository productDtlsRepository;

    public List<ProductDtls> getAllUsers() {
        return productDtlsRepository.findAll();
    }

    public ProductDtls saveProduct(ProductDtls productDtls) {
        return productDtlsRepository.save(productDtls);
    }

//    public List<ProductDtls> getProductDtlsByProductId(Long productId) {
//        return productDtlsRepository.findByProductId(productId);
//    }

    public ProductDetailsRecord getProductDtlsByProductId(Long productId) {
        List<ProductDtls> productDtlsByID = productDtlsRepository.findByProductId(productId);
        log.info("Fetched productDtlsByID --->{}" , productDtlsByID.toString());
        return new ProductDetailsRecord("S", "product Dtls By ID fetched successfully", productDtlsByID);
    }



}
