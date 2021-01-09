package com.ejbank.api.payload;

public class TransactionValidationPayload {
    private boolean result;
    private String message;
    private String error;

    public TransactionValidationPayload(boolean result, String message, String error) {
        this.result = result;
        this.message = message;
        this.error = error;
    }

    public boolean getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}
