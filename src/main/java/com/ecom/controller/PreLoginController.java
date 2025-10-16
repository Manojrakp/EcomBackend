package com.ecom.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.entity.PreLoginAds;
import com.ecom.entity.Product;
import com.ecom.repository.PreloginHomeRepositary;
import com.ecom.service.PreLoginHomeServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor


@RestController
@RequestMapping("/home")
@Slf4j
public class PreLoginController {

	private final PreLoginHomeServices preLoginHomeServices;
	
	   @PostMapping("/prelogin")
	    public List<PreLoginAds> GetAllProduct() {
		   log.debug("demo");
	    	return preLoginHomeServices.getActivePreloginAds();
	    	 
	    }
	
}
