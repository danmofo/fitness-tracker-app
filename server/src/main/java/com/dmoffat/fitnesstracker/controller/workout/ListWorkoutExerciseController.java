package com.dmoffat.fitnesstracker.controller.workout;

import com.dmoffat.fitnesstracker.model.User;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;
import com.dmoffat.fitnesstracker.model.response.workout.ListCompletedSetsForExercise;
import com.dmoffat.fitnesstracker.model.response.workout.ListWorkoutExerciseResponse;
import com.dmoffat.fitnesstracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListWorkoutExerciseController {
    private final WorkoutService workoutService;

    @Autowired
    public ListWorkoutExerciseController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping("/api/v1/workout/{workoutId}/exercise")
    public ResponseEntity<ApiResponse> handleListWorkoutExercises(
            @PathVariable Integer workoutId, @AuthenticationPrincipal User user) {
        var exercises = workoutService.listExercisesForWorkoutId(user, workoutId);
        return ResponseEntity.ok(new ListWorkoutExerciseResponse(exercises));
    }

    @GetMapping("/api/v1/workout/{workoutId}/exercise/{exerciseId}")
    public ResponseEntity<ApiResponse> handleListCompletedSetsForExercise(
        @PathVariable Integer workoutId, @PathVariable Integer exerciseId, @AuthenticationPrincipal User user) {
        var completedSets = workoutService.listCompletedSetsForExercise(user, exerciseId, workoutId);

        return ResponseEntity.ok(new ListCompletedSetsForExercise(completedSets));
    }
}
