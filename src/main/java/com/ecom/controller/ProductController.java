package com.ecom.controller;

import java.util.List;
import java.util.Map;

import com.ecom.dto.PreloginResponse;
import com.ecom.dto.ProductDetailsRecord;
import com.ecom.service.GetProductDtlsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.ecom.entity.Product;
import com.ecom.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final GetProductDtlsService getProductDtlsService;

    @PostMapping("/saveWithDetails")
    public Product saveProductWithDetails(@RequestBody Product product) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonProduct = mapper.writeValueAsString(product);
            System.out.println("In saveAll Controller --> " + jsonProduct);

            Product savedProduct = productService.saveProductWithDetails(product);
            String jsonResponse = mapper.writeValueAsString(savedProduct);
            System.out.println("In saveAll Controller resp --> " + jsonResponse);

            return savedProduct;
        } catch (Exception e) {
        	 e.printStackTrace(); 
        	    throw e; 
        }
    }
    
    @PostMapping("/allProducts")
    public List<Product> GetAllProduct() {
    	return productService.getAllProducts();
    	 
    }

    @PostMapping("/getByproductById")
//    public ProductDetailsRecord  getProductById(@RequestBody Map<String, Long> requestBody) {
//        Long productId = requestBody.get("productId");
//        log.debug(getProductDtlsService.getProductDtlsByProductId(productId));
//        return getProductDtlsService.getProductDtlsByProductId(productId);
//    }
    public ProductDetailsRecord getProductById(@RequestBody Map<String, Long> requestBody) {
        // 1. Log the start of the method and the received input
        log.info("Request received to fetch product details. Request body: {}", requestBody);

        Long productId = requestBody.get("productId");

        ProductDetailsRecord productDetails = getProductDtlsService.getProductDtlsByProductId(productId);

        log.debug("Product details retrieved for ID {}: {}", productId, productDetails);

        return productDetails;
    }

}
