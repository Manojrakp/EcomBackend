package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.entity.PreLoginAds;
import com.ecom.repository.PreloginHomeRepositary;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j

@Service
public class PreLoginHomeServices {
	private final  PreloginHomeRepositary preloginHomeRepositary;
	//@PostConstruct
	public List<PreLoginAds> getActivePreloginAds() {
	    List<PreLoginAds> activeAds = preloginHomeRepositary.findValidAds();
	   log.debug("Active Prelogin Ads fetched: " + activeAds);
	    return activeAds;
	}

}
