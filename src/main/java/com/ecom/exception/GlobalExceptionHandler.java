package com.ecom.exception;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
    public GlobalExceptionHandler() {
	        log.debug("GlobalExceptionHandler loaded successfully by Spring Boot!");
	    }
  
    // Handle unique constraint or foreign key constraint errors
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        String message = "Database constraint violated";
        if (ex.getMessage() != null && ex.getMessage().contains("ORA-00001")) {
            message = "Duplicate entry: the record already exists.";
        } else if (ex.getMessage() != null && ex.getMessage().contains("foreign key")) {
            message = "Cannot delete or update because of a related record (foreign key constraint).";
        }

        Map<String, Object>  body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CONFLICT.value());
        body.put("error", HttpStatus.CONFLICT.getReasonPhrase());
        body.put("message", message);
        body.put("details", ex.getMostSpecificCause().getMessage());

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    // Fallback for any other exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String,String> > handleBadCredentialsException(BadCredentialsException ex) {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("timestamp", OffsetDateTime.now().toString());
        body.put("error", "Unauthorized");
        body.put("details", ex.getLocalizedMessage());
        body.put("message", "Invalid username or password");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

}
