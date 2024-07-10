package com.dmoffat.fitnesstracker.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailController {
    @GetMapping("/auth")
    public String protectedRoute(@AuthenticationPrincipal User user) {
        return "{\"user\": \"" + user.getUsername() + "\"}";
    }
}
