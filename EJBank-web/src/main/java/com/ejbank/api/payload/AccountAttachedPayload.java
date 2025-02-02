package com.ejbank.api.payload;

import java.util.Objects;

public class AccountAttachedPayload extends AccountPayload {
    private String user;
    private int validation;

    public AccountAttachedPayload(String id, String type, Double amount, String user, int validation) {
        super(id, type, amount);
        Objects.requireNonNull(user);
        assert(validation >= 0);
        this.user = user;
        this.validation = validation;
    }

    public String getUser() {
        return user;
    }

    public int getValidation() {
        return validation;
    }
}
