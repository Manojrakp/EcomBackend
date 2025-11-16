package com.ecom.service;

import java.util.List;
import java.util.Optional;

import com.ecom.dto.ProductDetailsRecord;
import com.ecom.entity.Product;
import com.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.entity.ProductDtls;
import com.ecom.repository.ProductDtlsRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductDtlsService {
    
    private final ProductDtlsRepository productDtlsRepository;
    private final ProductRepository productRepository;


    public List<ProductDtls> getAllProducts() {
        return productDtlsRepository.findAll();
    }

    public ProductDtls saveProduct(ProductDtls productDtls) {
        return productDtlsRepository.save(productDtls);
    }

    public ResponseEntity<ProductDetailsRecord> getProductDtlsByProductId(Long productId) {
        List<ProductDtls> productDtlsByID = productDtlsRepository.findByProductId(productId);

        Product product = productRepository.findById(productId).orElse(null);

        if (productDtlsByID.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ProductDetailsRecord(HttpStatus.NOT_FOUND.toString(), "No product found for given ID", productDtlsByID,product ));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ProductDetailsRecord(HttpStatus.OK.toString(), "Product details fetched successfully", productDtlsByID,product));
    }





}
