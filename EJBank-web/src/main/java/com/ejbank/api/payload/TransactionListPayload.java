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

    public String getError() {
        return error;
    }

    public int getTotal() {
        return total;
    }
}
