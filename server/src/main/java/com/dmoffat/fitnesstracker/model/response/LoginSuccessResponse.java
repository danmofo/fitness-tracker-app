package com.dmoffat.fitnesstracker.model.response;

public class LoginSuccessResponse implements ApiResponse {
    private final boolean success = true;

    public boolean isSuccess() {
        return success;
    }
}
