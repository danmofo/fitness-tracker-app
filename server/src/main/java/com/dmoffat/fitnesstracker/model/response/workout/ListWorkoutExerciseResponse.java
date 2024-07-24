package com.dmoffat.fitnesstracker.model.response.workout;

import com.dmoffat.fitnesstracker.model.ExerciseWithCompletedSets;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;

import java.util.List;

public class ListWorkoutExerciseResponse implements ApiResponse {
    private final List<ExerciseWithCompletedSets> exercises;

    public ListWorkoutExerciseResponse(List<ExerciseWithCompletedSets> exercises) {
        this.exercises = exercises;
    }

    public List<ExerciseWithCompletedSets> getExercises() {
        return exercises;
    }
}
