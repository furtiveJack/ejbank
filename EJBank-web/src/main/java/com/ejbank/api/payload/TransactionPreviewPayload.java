package com.ejbank.api.payload;

public class TransactionPreviewPayload {
    private boolean result;
    private double before;
    private double after;
    private String message;
    private String error;

    public TransactionPreviewPayload(boolean result, double before, double after, String message, String error) {
        this.result = result;
        this.before = before;
        this.after = after;
        this.message = message;
        this.error = error;
    }

    public boolean isResult() {
        return result;
    }

    public double getBefore() {
        return before;
    }

    public double getAfter() {
        return after;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}
