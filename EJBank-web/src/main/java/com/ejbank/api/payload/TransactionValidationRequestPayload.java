package com.ejbank.api.payload;

import com.owlike.genson.annotation.JsonCreator;
import com.owlike.genson.annotation.JsonProperty;

public class TransactionValidationRequestPayload {
    private String transaction;
    private boolean approve;
    private String author;

    @JsonCreator
    public TransactionValidationRequestPayload(@JsonProperty("transaction") String transaction,
                                               @JsonProperty("approve") boolean approve,
                                               @JsonProperty("author") String author) {
        this.transaction = transaction;
        this.approve = approve;
        this.author = author;
    }

    public String getTransaction() {
        return transaction;
    }

    public boolean isApprove() {
        return approve;
    }

    public String getAuthor() {
        return author;
    }
}
