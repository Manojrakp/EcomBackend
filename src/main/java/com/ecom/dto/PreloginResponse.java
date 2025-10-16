package com.ecom.dto;

import java.util.List;

import com.ecom.entity.Product;
import com.ecom.entity.PreLoginAds;
public record PreloginResponse<T>(String respCode,
        String message,
        List<PreLoginAds> adsList,
        List<Product> products

        ) {
}


