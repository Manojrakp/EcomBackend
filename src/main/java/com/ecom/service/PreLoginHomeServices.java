package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.dto.PreloginResponse;
import com.ecom.entity.PreLoginAds;
import com.ecom.entity.Product;
import com.ecom.repository.PreloginHomeRepositary;
import com.ecom.repository.ProductRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j

@Service
public class PreLoginHomeServices {
	private final  PreloginHomeRepositary preloginHomeRepositary;
	
	private final ProductRepository productRepository;

	//@PostConstruct
//	public List<PreLoginAds> getActivePreloginAds() {
//	    List<PreLoginAds> activeAds = preloginHomeRepositary.findValidAds();
//	   log.debug("Active Prelogin Ads fetched: " + activeAds);
//	    return activeAds;
//	}
	
	  public List<Product> getAllUsers() {
	        return productRepository.findAll();
	    }
	  public PreloginResponse getPreloginHomeData() {
	        log.debug("Fetching valid prelogin ads...");
	        List<PreLoginAds> ads = preloginHomeRepositary.findValidAds();

	        log.debug("Fetching all products...");
	        List<Product> products = productRepository.findAll();

	        log.info("Fetched {} ads and {} products", ads.size(), products.size());

	        return new PreloginResponse("S", "Data fetched successfully", ads, products);
	    }

}