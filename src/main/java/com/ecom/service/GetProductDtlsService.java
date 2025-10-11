package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.entity.ProductDtls;
import com.ecom.repository.ProductDtlsRepository;


@Service
public class GetProductDtlsService {
    
    private final ProductDtlsRepository productDtlsRepository;

    public GetProductDtlsService(ProductDtlsRepository productDtlsRepository){
        this.productDtlsRepository = productDtlsRepository;
    }

    public List<ProductDtls> getAllUsers() {
        return productDtlsRepository.findAll();
    }

    public ProductDtls saveProduct(ProductDtls productDtls) {
        return productDtlsRepository.save(productDtls);
    }
    
 
    
}
