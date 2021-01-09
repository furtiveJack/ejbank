package com.ejbank.api.payload;

public class AccountDetailsPayload {
    private String owner;
    private String advisor;
    private int rate;
    private double interest;
    private double amount;
    private String error;

    public AccountDetailsPayload(String owner, String advisor, int rate, double interest, double amount, String error) {
        this.owner = owner;
        this.advisor = advisor;
        this.rate = rate;
        this.interest = interest;
        this.error = error;
        this.amount = amount;
    }

    public String getOwner() {
        return owner;
    }

    public String getAdvisor() {
        return advisor;
    }

    public int getRate() {
        return rate;
    }

    public double getInterest() {
        return interest;
    }

    public String getError() {
        return error;
    }

    public double getAmount() {
        return amount;
    }
}
