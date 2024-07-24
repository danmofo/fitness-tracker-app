package com.dmoffat.fitnesstracker.model.response.workout;

import com.dmoffat.fitnesstracker.model.ExerciseWithCompletedSets;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;

public class ListWorkoutExerciseResponse implements ApiResponse {
    private final ExerciseWithCompletedSets exercises;

    public ListWorkoutExerciseResponse(ExerciseWithCompletedSets exercises) {
        this.exercises = exercises;
    }

    public ExerciseWithCompletedSets getExercises() {
        return exercises;
    }
}
