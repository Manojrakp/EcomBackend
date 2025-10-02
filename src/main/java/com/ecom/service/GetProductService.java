package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.entity.Product;
import com.ecom.repository.ProductRepository;


@Service
public class GetProductService {
    
    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository){
        this.productRepository =productRepository;
    }

    public List<Product> getAllUsers() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
}
