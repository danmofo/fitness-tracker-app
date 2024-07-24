package com.dmoffat.fitnesstracker.model.response.workout;

import com.dmoffat.fitnesstracker.model.CompletedSet;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;

import java.util.List;

public record ListCompletedSetsForExercise(List<CompletedSet> completedSets) implements ApiResponse {
}
