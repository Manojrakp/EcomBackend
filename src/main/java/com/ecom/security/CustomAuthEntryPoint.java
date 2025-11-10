package com.ecom.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        // Format timestamp similar to your log style
        String timestamp = OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        // Prepare response body dynamically
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", timestamp);
        body.put("level", "DEBUG");
        body.put("thread", Thread.currentThread().getName());
        body.put("class", "DaoAuthenticationProvider");
        body.put("message", authException.getMessage()); // <-- dynamically taken from exception

        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}
