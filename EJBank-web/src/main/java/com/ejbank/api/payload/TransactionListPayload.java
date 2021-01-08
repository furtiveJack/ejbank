package com.ejbank.api.payload;

import java.util.List;
import java.util.Objects;

public class TransactionListPayload {
    private List<TransactionPayload> transactions;
    private String error;
    private int total;

    public TransactionListPayload(List<TransactionPayload> transactions, String error, int total) {
        assert(total >= 0);
        this.transactions = transactions;
        this.error = error;
        this.total = total;
    }

    public List<TransactionPayload> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionPayload> transactions) {
        this.transactions = transactions;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
