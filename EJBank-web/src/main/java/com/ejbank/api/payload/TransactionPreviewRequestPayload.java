package com.ejbank.api.payload;

import com.owlike.genson.annotation.JsonCreator;
import com.owlike.genson.annotation.JsonProperty;

public class TransactionPreviewRequestPayload {

    private String source;
    private String destination;
    private String amount;
    private String author;

    @JsonCreator
    public TransactionPreviewRequestPayload(@JsonProperty("source") String source,
                                            @JsonProperty("destination") String destination,
                                            @JsonProperty("amount") String amount,
                                            @JsonProperty("author") String author) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
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

    public String getAuthor() {
        return author;
    }
}
