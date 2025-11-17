# EcomBackend

Spring JPA


# Security Overview

This document describes the Spring Security setup used in this project.

## Purpose
Provide stateless JWT-based authentication and role-based authorization for REST endpoints.

## Main components
- `src/main/java/com/ecom/security/SecurityConfig.java`  
  Configures the security filter chain:
  - CSRF disabled
  - Custom authentication entry point via `CustomAuthEntryPoint`
  - Public endpoints: `\`/api/login\``, `\`/api/register\``
  - Role-protected endpoint example: `\`/endpoint\`` requires authority `USER`
  - All other requests require authentication
  - Session management is stateless (JWT)
  - Registers `JwtAuthFilter` before `UsernamePasswordAuthenticationFilter`
  - Logout is permitted to all

# Spring Security Authorization Flow
<img width="701" height="383" alt="image" src="https://github.com/user-attachments/assets/987b0a13-f497-4141-8370-29d838c38709" />

- `src/main/java/com/ecom/security/JwtAuthFilter.java`  
  Extracts the JWT from the `Authorization: Bearer <token>` header, validates it using `JwtUtils`, and sets the `Authentication` in the security context when valid.

- `src/main/java/com/ecom/security/JwtUtils.java`  
  Utility for creating, signing, parsing, and validating JWT tokens (expiration, signature, claims).

- `src/main/java/com/ecom/security/CustomAuthEntryPoint.java`  
  Handles unauthorized access attempts and returns a `401 Unauthorized` response (typically JSON message) for unauthenticated requests.

- `src/main/java/com/ecom/security/UserSecurityConfig.java`  
  Any additional user/role-related security configuration (if present).

- `src/main/java/com/ecom/config/AppConfig.java`  
  Application-level beans and configuration (e.g., JWT secret, expiration configuration). Check this file for properties your `JwtUtils` depends on.

## How it works (quick)
1. Client calls `\`/api/login\`` with credentials.
2. Server authenticates and returns a JWT (signed).
3. Client sends requests with header:  
   `Authorization: Bearer <token>`
4. `JwtAuthFilter` validates the token and populates the security context.
5. Access to endpoints is granted or denied based on the configured authorization rules in `SecurityConfig.java`.

## Authentication Flow 
Using the Method level annotation @EnableMethodSecurity hence the authorize at the method reason.
