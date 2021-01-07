package com.ejbank.api.payload;

import java.util.List;

public class AccountsPayload<T> {

    private List<T> accounts;
    private String error;

    public AccountsPayload(List<T> accounts, String error) {
        this.accounts = accounts;
        this.error = error;
    }

    public List<T> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
