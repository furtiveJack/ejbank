package com.ejbank.api.payload;

import com.owlike.genson.annotation.JsonCreator;
import com.owlike.genson.annotation.JsonProperty;

public class TransactionApplyRequestPayload {
    private String source;
    private String destination;
    private String amount;
    private String comment;
    private String author;

    @JsonCreator
    public TransactionApplyRequestPayload(@JsonProperty("source") String source,
                                          @JsonProperty("destination") String destination,
                                          @JsonProperty("amount") String amount,
                                          @JsonProperty("comment") String comment,
                                          @JsonProperty("author") String author) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.comment = comment;
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public String getAuthor() {
        return author;
    }
}
