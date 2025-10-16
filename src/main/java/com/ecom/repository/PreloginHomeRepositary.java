package com.ecom.repository;



import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.entity.PreLoginAds;
@Repository
public interface PreloginHomeRepositary  extends JpaRepository<PreLoginAds, Long> {
	 @Query("""
		        SELECT p 
		        FROM PreLoginAds p 
		        WHERE p.active = true 
		    """)
		    List<PreLoginAds> findValidAds();
}
