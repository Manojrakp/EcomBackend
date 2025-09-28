package com.ecom.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") 
public class ReqController {

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        return "Fetching user with ID: " + id;
    }

    @GetMapping("/search")
    public String searchUsers() {
        return "Searching for users named" ;
    }

    
 

}