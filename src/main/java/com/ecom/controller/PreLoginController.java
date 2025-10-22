package com.ecom.controller;

import java.util.List;

import com.ecom.dto.PreloginResponse;
import com.ecom.entity.PreLoginAds;
import com.ecom.service.PreLoginHomeServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor


@RestController
@RequestMapping("api/home")
@Slf4j
public class PreLoginController {

	private final PreLoginHomeServices preLoginHomeServices;
	
	   @PostMapping("/prelogin")
	    public PreloginResponse GetAllProduct() {
	    	return preLoginHomeServices.getPreloginHomeData();
	    }
	
}
