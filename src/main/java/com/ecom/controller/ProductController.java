package com.ecom.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.Product;
import com.ecom.repository.ProductDtlsRepository;
import com.ecom.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ProductDtlsRepository productDtlsRepository;
    

    public ProductController(ProductService getProductService,
		      ProductDtlsRepository productDtlsRepository) {
		this.productService = getProductService;
		this.productDtlsRepository = productDtlsRepository;
		}

//    @PostMapping("/save")
//    public Product SaveProduct(@RequestBody Product dtls){
//        System.out.println("In the Controller------>"+ dtls);
//        return getProductService.saveProduct(dtls);
//    }
//
//    @PostMapping("Details/save")
//    public ProductDtls createProductDtls(@RequestBody ProductDtls productDtls) {
//        return productDtlsRepository.save(productDtls);
//    }
    
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

}
