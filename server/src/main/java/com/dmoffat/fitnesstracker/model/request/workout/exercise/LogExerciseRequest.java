package com.dmoffat.fitnesstracker.model.request.workout.exercise;

import com.dmoffat.fitnesstracker.model.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LogExerciseRequest (
    @NotNull Integer workoutId,
    @NotNull Integer exerciseId,
    @NotNull @Min(1) Double weight,
    @NotNull @Min(1) Integer sets,
    @NotNull @Min(0) Integer reps,
    String notes,
    List<String> equipment
) implements ApiResponse {}
