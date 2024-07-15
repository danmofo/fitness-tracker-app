package com.dmoffat.fitnesstracker.controller.user;

import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.request.user.LogBodyWeightRequest;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;
import com.dmoffat.fitnesstracker.model.response.user.LogBodyWeightResponse;
import com.dmoffat.fitnesstracker.service.UserBodyWeightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogBodyWeightController {
    private final UserBodyWeightService userBodyWeightService;

    @Autowired
    public LogBodyWeightController(UserBodyWeightService userBodyWeightService) {
        this.userBodyWeightService = userBodyWeightService;
    }

    @PostMapping("/api/v1/user/weight/")
    public ResponseEntity<ApiResponse> handleLogBodyWeight(
        @Valid @RequestBody LogBodyWeightRequest request,
        @AuthenticationPrincipal User user) {

        userBodyWeightService.logBodyWeight(user, request.weight());

        return ResponseEntity.ok(new LogBodyWeightResponse(true));
    }
}
