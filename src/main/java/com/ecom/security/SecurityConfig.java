package com.ecom.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private MyJwtAuthenticationConverter myJwtAuthenticationConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated() // Require authentication for all other requests
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                         .jwt(jwt -> jwt
                                 .jwkSetUri("https://manojkumar.uk.auth0.com/api/v2/")
                                .jwtAuthenticationConverter(myJwtAuthenticationConverter)
                        ))

                .formLogin(form -> form
                        .loginPage("/login") // Specify custom login page
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll());
        return http.build();
    }
}