package com.dmoffat.fitnesstracker.model.request.workout;

import com.dmoffat.fitnesstracker.model.request.ApiRequest;

public record FinishWorkoutRequest (String notes) implements ApiRequest {}
