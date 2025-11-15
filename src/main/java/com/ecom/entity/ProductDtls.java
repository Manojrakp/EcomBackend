package com.ecom.entity;

import com.ecom.utils.StringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ECOM_PRODUCT_DTLS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "product"}) //Jackson to skip the proxy internals  -- to bytebuddy.ByteBuddyInterceptor

public class ProductDtls {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Product_dtl_seq")
    @SequenceGenerator(name = "Product_dtl_seq", sequenceName = "PRODUCT_DTL_SEQ", allocationSize = 1)

    private Long id;
       
    @Column(nullable = false, unique = true, length = 50)
    private String sku;

    @Column(length = 500)
    private String productNameDesc;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID") // Maps to the PRODUCT_ID column in ECOM_PRODUCT_DTLS
    private Product product;

    @Lob     
    private String description;  

    @Lob
    private String specifications; 

    @Lob
    @Convert(converter = StringListConverter.class)
    private List<String> imageGalleryUrls;

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
