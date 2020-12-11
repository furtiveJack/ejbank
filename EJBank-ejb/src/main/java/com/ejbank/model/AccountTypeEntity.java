package com.ejbank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ejbank_account_type")
public class AccountTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "rate")
    private float rate;

    @Column(name = "overdraft")
    private int overdraft;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(int overdraft) {
        this.overdraft = overdraft;
    }
}
