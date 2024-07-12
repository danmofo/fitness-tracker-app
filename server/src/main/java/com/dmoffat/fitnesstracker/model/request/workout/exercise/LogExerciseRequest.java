package com.dmoffat.fitnesstracker.model.request.workout.exercise;

import com.dmoffat.fitnesstracker.model.response.ApiResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LogExerciseRequest (
    @NotNull Integer workoutId,
    @NotNull Integer exerciseId,
    @Min(1) Integer weight,
    @Min(1) Integer sets,
    Integer reps,
    String notes,
    List<String> equipment
) implements ApiResponse {}
