package com.ecom.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Preloginads")
@Getter
@Setter	
public class PreLoginAds {	

	@Id
	@SequenceGenerator(name = "ads_seq", sequenceName = "ADS_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ads_seq")
	private Long id;

    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "redirect_url")
    private String redirectUrl;

    private boolean active;

    @Column(name = "valid_till")
    private LocalDate validTill;

}
