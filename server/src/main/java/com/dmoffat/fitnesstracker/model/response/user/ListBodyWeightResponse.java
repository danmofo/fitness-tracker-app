package com.dmoffat.fitnesstracker.model.response.user;

import com.dmoffat.fitnesstracker.model.UserBodyWeight;
import com.dmoffat.fitnesstracker.model.response.ApiResponse;

import java.util.List;

public class ListBodyWeightResponse implements ApiResponse {
    private final List<UserBodyWeight> bodyWeights;

    public ListBodyWeightResponse(List<UserBodyWeight> bodyWeights) {
        this.bodyWeights = bodyWeights;
    }

    public List<UserBodyWeight> getBodyWeights() {
        return bodyWeights;
    }
}
