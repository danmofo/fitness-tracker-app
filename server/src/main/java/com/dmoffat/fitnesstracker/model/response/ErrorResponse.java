package com.dmoffat.fitnesstracker.model.response;

public class ErrorResponse implements ApiResponse {
    private final ErrorCode errorCode;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
