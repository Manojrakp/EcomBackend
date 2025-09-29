package com.ecom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PRODUCT_DTLS")
public class ProductDtls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    @Column(columnDefinition = "TEXT")
    private String description;  

    @Column(columnDefinition = "TEXT")
    private String specifications; 

    @Column(length = 255)
    private String imageGalleryUrls;
    @Column(length = 100)
    private String material;

    @Column(length = 100)
    private String dimensions;

    @Column(length = 100)
    private String weight;

    @Column(length = 255)
    private String warrantyInfo;

    @Column(length = 255)
    private String manufacturer;

}
