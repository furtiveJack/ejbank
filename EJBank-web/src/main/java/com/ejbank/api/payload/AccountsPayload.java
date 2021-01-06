package com.ejbank.api.payload;

import java.util.List;

public class AccountsPayload {

    private List<AccountPayload> accounts;
    private String error = null;

    public AccountsPayload(List<AccountPayload> accounts) {
        this.accounts = accounts;
    }

    public List<AccountPayload> getAccounts() {
        return accounts;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
