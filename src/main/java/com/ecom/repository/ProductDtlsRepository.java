package com.ecom.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.entity.ProductDtls;

import java.util.List;

@Repository
public interface ProductDtlsRepository extends JpaRepository<ProductDtls, Long> {

    @Query("""
    SELECT p
    FROM ProductDtls p
    WHERE p.product.id = :id
""")
    List<ProductDtls> findByProductId(@Param("id") Long productId);

}


