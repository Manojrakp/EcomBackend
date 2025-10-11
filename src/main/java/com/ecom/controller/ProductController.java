package com.ecom.controller;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ecom.entity.Product;
import com.ecom.entity.ProductDtls;
import com.ecom.repository.ProductDtlsRepository;
import com.ecom.service.GetProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final GetProductService getProductService;
    private final ProductDtlsRepository productDtlsRepository;

    public ProductController(GetProductService getProductService,
		            ProductDtlsRepository productDtlsRepository) {
		this.getProductService = getProductService;
		this.productDtlsRepository = productDtlsRepository;
		}

    @PostMapping("/save")
    public Product SaveProduct(@RequestBody Product dtls){
        System.out.println("In the Controller------>"+ dtls);
        return getProductService.saveProduct(dtls);
    }

    @PostMapping("dtls/save")
    public ProductDtls createProductDtls(@RequestBody ProductDtls productDtls) {
        return productDtlsRepository.save(productDtls);
    }



    
}
