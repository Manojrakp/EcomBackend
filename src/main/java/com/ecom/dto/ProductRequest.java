package com.ecom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String sku;
    private String name;
    private Double price;
    private Integer stockQuantity;
    private String category;
    private String brand;
    private String thumbnailUrl;
}
