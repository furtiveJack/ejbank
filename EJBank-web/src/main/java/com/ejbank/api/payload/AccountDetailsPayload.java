package com.ejbank.api.payload;

public class AccountDetailsPayload {
    private String owner;
    private String advisor;
    private Double rate;
    private Double interest;
    private Double amount;
    private String error;

    public AccountDetailsPayload(String owner, String advisor, Double rate, Double interest, Double amount, String error) {
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

    public Double getRate() {
        return rate;
    }

    public Double getInterest() {
        return interest;
    }

    public String getError() {
        return error;
    }

    public Double getAmount() {
        return amount;
    }
}
