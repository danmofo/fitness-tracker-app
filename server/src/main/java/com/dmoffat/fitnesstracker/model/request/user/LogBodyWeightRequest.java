package com.dmoffat.fitnesstracker.model.request.user;

import com.dmoffat.fitnesstracker.model.request.ApiRequest;
import jakarta.validation.constraints.NotNull;

public record LogBodyWeightRequest(@NotNull Double weight) implements ApiRequest {
}
