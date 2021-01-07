package com.ejbank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ejbank_account")
@NamedQueries({
        @NamedQuery(name = "AccountEntity.getAccountsByCustomer",
                query = "SELECT a FROM AccountEntity a WHERE a.customer.id = :userId"),
        @NamedQuery(name = "AccountEntity.getAccountsByAdvisor",
                query = "SELECT a FROM AccountEntity as a " +
                        "WHERE a.customer.id IN (" +
                            "SELECT c.id from CustomerEntity AS c WHERE c.advisor_id = :userId)")
} )
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_type_id")
    private AccountTypeEntity accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column(name = "balance")
    private double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountTypeEntity getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEntity accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
