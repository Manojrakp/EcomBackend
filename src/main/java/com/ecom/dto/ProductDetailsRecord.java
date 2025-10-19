package com.ecom.dto;

import com.ecom.entity.PreLoginAds;
import com.ecom.entity.Product;
import com.ecom.entity.ProductDtls;

import java.util.List;


public record ProductDetailsRecord<T>(String respCode,
                                String message,
                                      List<T> ProductDetailsforID
        ) {
}



