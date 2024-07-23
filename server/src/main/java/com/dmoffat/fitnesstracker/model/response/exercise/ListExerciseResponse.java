package com.dmoffat.fitnesstracker.model.response.exercise;

import com.dmoffat.fitnesstracker.model.Exercise;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;

import java.util.List;

public class ListExerciseResponse implements ApiResponse {
    private final List<Exercise> exercises;

    public ListExerciseResponse(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
