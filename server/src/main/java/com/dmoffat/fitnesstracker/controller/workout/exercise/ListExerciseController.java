package com.dmoffat.fitnesstracker.controller.workout.exercise;

import com.dmoffat.fitnesstracker.model.Exercise;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;
import com.dmoffat.fitnesstracker.model.response.exercise.ListExerciseResponse;
import com.dmoffat.fitnesstracker.service.ExerciseSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListExerciseController {
    private final ExerciseSearchService exerciseSearchService;

    @Autowired
    public ListExerciseController(ExerciseSearchService exerciseSearchService) {
        this.exerciseSearchService = exerciseSearchService;
    }

    @GetMapping("/api/v1/workout/exercise")
    public ResponseEntity<ApiResponse> handleListExercises() {
        List<Exercise> allExercises = exerciseSearchService.search();
        ListExerciseResponse response = new ListExerciseResponse(allExercises);
        return ResponseEntity.ok(response);
    }
}
