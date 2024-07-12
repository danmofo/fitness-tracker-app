package com.dmoffat.fitnesstracker.controller.workout.exercise;

import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.WorkoutExercise;
import com.dmoffat.fitnesstracker.model.request.workout.exercise.LogExerciseRequest;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;
import com.dmoffat.fitnesstracker.service.LogExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogExerciseController {
    private final LogExerciseService logExerciseService;

    @Autowired
    public LogExerciseController(LogExerciseService logExerciseService) {
        this.logExerciseService = logExerciseService;
    }

    @PostMapping("/api/v1/workout/{workoutId}/exercise/")
    public ResponseEntity<ApiResponse> handleLogExercise(
            @RequestBody @Valid LogExerciseRequest request,
            @AuthenticationPrincipal User user) {

        WorkoutExercise loggedExercise = logExerciseService.logExercise(user, request);
        System.out.println(loggedExercise);

        return ResponseEntity.ok(null);
    }
}
