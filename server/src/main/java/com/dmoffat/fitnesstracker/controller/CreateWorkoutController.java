package com.dmoffat.fitnesstracker.controller;

import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;
import com.dmoffat.fitnesstracker.model.response.workout.CreateWorkoutResponse;
import com.dmoffat.fitnesstracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateWorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public CreateWorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/api/v1/workout/")
    public ResponseEntity<ApiResponse> handleCreateWorkout(@AuthenticationPrincipal User user) {
        var workout = workoutService.createWorkout(user);
        return ResponseEntity.ok(new CreateWorkoutResponse(workout.getId()));
    }
}
