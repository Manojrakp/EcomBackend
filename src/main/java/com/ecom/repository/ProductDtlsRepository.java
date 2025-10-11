package com.ecom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.ecom.entity.ProductDtls;

public interface ProductDtlsRepository extends JpaRepository<ProductDtls, Long> {
}
