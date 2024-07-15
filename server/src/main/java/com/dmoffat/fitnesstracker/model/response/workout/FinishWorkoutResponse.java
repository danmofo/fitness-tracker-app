package com.dmoffat.fitnesstracker.model.response.workout;

import com.dmoffat.fitnesstracker.model.response.ApiResponse;

public record FinishWorkoutResponse(boolean success) implements ApiResponse {
}
