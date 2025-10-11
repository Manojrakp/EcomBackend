package com.ecom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ECOM_PRODUCT_DTLS")
public class ProductDtls {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Product_dtl_seq")
    @SequenceGenerator(name = "Product_dtl_seq", sequenceName = "PRODUCT_DTL_SEQ", allocationSize = 1)

    private Long id;
    
    
    @Column(nullable = false, unique = true, length = 50)
    private String sku; 
 
    
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID") // Maps to the PRODUCT_ID column in ECOM_PRODUCT_DTLS
    private Product product;

    @Lob     
    private String description;  

    @Lob
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
