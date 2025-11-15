package com.ecom.dto;

import com.ecom.entity.Product;

import java.util.List;


public record ProductDetailsRecord<T>(String status,
                                String message,
                                      List<T> ProductDetailsforID,
                                      Product product) {
}




