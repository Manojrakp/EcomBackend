package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.entity.Product;
import com.ecom.entity.ProductDtls;
import com.ecom.repository.ProductRepository;


@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product saveProductWithDetails(Product product) {

        // ✅ Ensure bidirectional relationship is set
        if (product.getProductInfo() != null) {
            product.getProductInfo().setProduct(product);
        }

        // ✅ Cascade will automatically save ProductDtls
        return productRepository.save(product);
    }
    
 
    
}
