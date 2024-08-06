package com.dmoffat.fitnesstracker.controller.workout;

import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.request.workout.FinishWorkoutRequest;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;
import com.dmoffat.fitnesstracker.model.response.workout.FinishWorkoutResponse;
import com.dmoffat.fitnesstracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FinishWorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public FinishWorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/api/v1/workout/{workoutId}/finish")
    public ResponseEntity<ApiResponse> handleFinishWorkout(
            @PathVariable Integer workoutId,
            @RequestBody FinishWorkoutRequest request,
            @AuthenticationPrincipal User user) {

        boolean result = workoutService.finishWorkout(user, workoutId, request.notes());

        return ResponseEntity.ok(new FinishWorkoutResponse(result));
    }
}
