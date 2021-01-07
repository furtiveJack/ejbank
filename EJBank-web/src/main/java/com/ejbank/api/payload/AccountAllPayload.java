package com.ejbank.api.payload;

import java.util.Objects;

public class AccountAllPayload extends AccountPayload {
    private String user;

    public AccountAllPayload(String id, String type, Double amount, String user) {
        super(id, type, amount);
        Objects.requireNonNull(user);
        this.user = user;
    }
}
