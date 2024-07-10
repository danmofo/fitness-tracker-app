package com.dmoffat.fitnesstracker.controller;

import com.dmoffat.fitnesstracker.model.response.ApiResponse;
import com.dmoffat.fitnesstracker.model.response.ErrorCode;
import com.dmoffat.fitnesstracker.model.response.ErrorResponse;
import com.dmoffat.fitnesstracker.model.response.LoginSuccessResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<ApiResponse> handleLogin(
            @Valid @RequestBody LoginRequest loginRequest,
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

            return ResponseEntity.ok(new LoginSuccessResponse());
        } catch (AuthenticationException ex) {
            ErrorResponse body = new ErrorResponse(ErrorCode.INVALID_CREDENTIALS);
            return new ResponseEntity<>(body, HttpStatusCode.valueOf(401));
        }
    }

    public record LoginRequest(
        @NotEmpty String email,
        @NotEmpty String password) {}
}
