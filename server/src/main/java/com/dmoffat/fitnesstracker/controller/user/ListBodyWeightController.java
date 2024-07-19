package com.dmoffat.fitnesstracker.controller.user;

import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;
import com.dmoffat.fitnesstracker.model.response.user.ListBodyWeightResponse;
import com.dmoffat.fitnesstracker.service.UserBodyWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListBodyWeightController {
    private final UserBodyWeightService userBodyWeightService;

    @Autowired
    public ListBodyWeightController(UserBodyWeightService userBodyWeightService) {
        this.userBodyWeightService = userBodyWeightService;
    }

    @GetMapping("/api/v1/user/weight/")
    public ResponseEntity<ApiResponse> listBodyWeight(@AuthenticationPrincipal User user) {

        var bodyWeights = userBodyWeightService.findAll(user);

        return ResponseEntity.ok(new ListBodyWeightResponse(bodyWeights));
    }
}
