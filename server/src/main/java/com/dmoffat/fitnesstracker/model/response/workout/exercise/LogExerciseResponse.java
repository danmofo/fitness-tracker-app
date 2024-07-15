package com.dmoffat.fitnesstracker.model.response.workout.exercise;

import com.dmoffat.fitnesstracker.model.response.ApiResponse;

public class LogExerciseResponse implements ApiResponse {
    private final boolean success;
    private Integer workoutExerciseId;

    public LogExerciseResponse(boolean success) {
        this.success = success;
    }

    public LogExerciseResponse(boolean success, Integer workoutExerciseId) {
        this.success = success;
        this.workoutExerciseId = workoutExerciseId;
    }

    public boolean isSuccess() {
        return success;
    }

    public Integer getWorkoutExerciseId() {
        return workoutExerciseId;
    }
}
