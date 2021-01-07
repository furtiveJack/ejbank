package com.ejbank.api.payload;

import java.util.Objects;

public class AccountPayload {

    private String id;
    private String type;
    private Double amount;

    public AccountPayload(String id, String type, Double amount) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(type);
        Objects.requireNonNull(amount);
        this.id = id;
        this.type = type;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }
}