package com.ejbank.api.payload;

import java.util.Objects;

public class TransactionPayload {
    private int id;
    private String date;
    private String source;
    private String destination;
    private String destination_user;
    private double amount;
    private String author;
    private String comment;
    private String state;

    public TransactionPayload(int id, String date, String source, String destination, String destination_user,
                              double amount, String author, String comment, String state) {
        this.id = id;
        this.date = date;
        this.source = source;
        this.destination = destination;
        this.destination_user = destination_user;
        this.amount = amount;
        this.author = author;
        this.comment = comment;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestination_user() {
        return destination_user;
    }

    public double getAmount() {
        return amount;
    }

    public String getAuthor() {
        return author;
    }

    public String getComment() {
        return comment;
    }

    public String getState() {
        return state;
    }
}
