package com.ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Authentication Manager bean to be used in the UserController for authentication
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Authentication Provider that uses the above authentication provider
//    @Bean
//    public ProviderManager providerManager(DaoAuthenticationProvider daoAuthenticationProvider) {
//        return new ProviderManager(daoAuthenticationProvider);
//    }
//    // Authentication Manager that uses the ProviderManager
//    @Bean
//    public AuthenticationManager authenticationManager(ProviderManager providerManager) {
//        return providerManager;
//    }

}
