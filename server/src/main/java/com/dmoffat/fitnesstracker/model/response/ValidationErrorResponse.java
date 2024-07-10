package com.dmoffat.fitnesstracker.model.response;

import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {
    private final List<ValidationError> validationErrors;

    public ValidationErrorResponse(List<ValidationError> validationErrors) {
        super(ErrorCode.VALIDATION);
        this.validationErrors = validationErrors;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}
