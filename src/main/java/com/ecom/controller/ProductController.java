package com.ecom.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Product;
import com.ecom.service.GetProductService;
@RestController
@RequestMapping("/product")
public class ProductController {
    private final GetProductService getProductService;

    public ProductController(GetProductService getProductService){
        this.getProductService = getProductService;
    }


    @PostMapping("/save")
    public Product SaveProduct(@RequestBody Product dtls){
        System.out.println("In the Controller------>"+ dtls);
        return getProductService.saveProduct(dtls);
    }


    

    // @PostMapping
    // public Product createProduct(@RequestBody ProductRequest request) {
    //     Product product = new Product();
    //     product.setSku(request.getSku());
    //     product.setName(request.getName());
    //     product.setPrice(request.getPrice());
    //     product.setStockQuantity(request.getStockQuantity());
    //     product.setCategory(request.getCategory());
    //     product.setBrand(request.getBrand());
    //     product.setThumbnailUrl(request.getThumbnailUrl());

    //     return getProductService.saveProduct(product);
    // }


    
}
