package com.ejbank.api.payload;

import java.util.Objects;

public class AttachedAccountPayload extends AccountPayload {
    private String user;
    private int validation;

    public AttachedAccountPayload(String id, String type, Double amount, String user, int validation) {
        super(id, type, amount);
        Objects.requireNonNull(user);
        assert(validation >= 0);
        this.user = user;
        this.validation = validation;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getValidation() {
        return validation;
    }

    public void setValidation(int validation) {
        this.validation = validation;
    }
}
