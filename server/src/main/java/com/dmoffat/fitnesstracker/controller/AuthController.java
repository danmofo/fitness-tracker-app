package com.dmoffat.fitnesstracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    // todo: Return proper request/response types instead of an empty object ({})
    @PostMapping("/api/v1/auth/login")
    public String handleLogin(
            @RequestBody LoginRequest loginRequest,
            HttpServletRequest req,
            HttpServletResponse res) {

        UsernamePasswordAuthenticationToken authentication =
            UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(), loginRequest.password());

        try {
            Authentication response = authenticationManager.authenticate(authentication);
            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(response);
            securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, req, res);

            return "{\"success\": true}";
        } catch (AuthenticationException ex) {
            return "{\"error\": \"Wrong credentials\"}";
        }
    }

    public record LoginRequest(String email, String password) {}
}
