package com.dmoffat.fitnesstracker.model.response.workout;

import com.dmoffat.fitnesstracker.model.response.ApiResponse;

public class CreateWorkoutResponse implements ApiResponse {
    private final Integer workoutId;

    public CreateWorkoutResponse(Integer workoutId) {
        this.workoutId = workoutId;
    }

    public Integer getWorkoutId() {
        return workoutId;
    }
}
