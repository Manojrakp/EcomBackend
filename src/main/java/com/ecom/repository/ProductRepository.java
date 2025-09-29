package com.ecom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ecom.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // you can add custom query methods here later if needed
}
