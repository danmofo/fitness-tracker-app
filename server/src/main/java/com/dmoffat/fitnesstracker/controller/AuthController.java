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
import org.springframework.security.core.AuthenticationException;
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

        var authentication =
            UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(), loginRequest.password());

        try {
            var authResponse = authenticationManager.authenticate(authentication);
            var securityContext = securityContextHolderStrategy.createEmptyContext();
            securityContext.setAuthentication(authResponse);
            securityContextHolderStrategy.setContext(securityContext);
            securityContextRepository.saveContext(securityContext, req, res);

            return ResponseEntity.ok(new LoginSuccessResponse());
        } catch (AuthenticationException ex) {
            var body = new ErrorResponse(ErrorCode.INVALID_CREDENTIALS);
            return new ResponseEntity<>(body, HttpStatusCode.valueOf(401));
        }
    }

    public record LoginRequest(
        @NotEmpty String email,
        @NotEmpty String password) {}
}
