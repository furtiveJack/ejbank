package com.ejbank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ejbank_transaction")
@NamedQueries({
        @NamedQuery(name = "TransactionEntity.getNBTransactionsForAccount",
        query = "SELECT DISTINCT t FROM TransactionEntity AS t " +
                "WHERE (t.account_id_from.id = :accountId or t.account_id_to.id = :accountId) " +
                "AND t.applied = false "),
        @NamedQuery(name = "TransactionEntity.getByAccount",
        query = "SELECT t FROM TransactionEntity AS t " +
                "WHERE (t.account_id_from.id = :accountId or t.account_id_to.id = :accountId) " +
                "ORDER BY t.date DESC")
})
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id_from", columnDefinition = "INT")
    private AccountEntity account_id_from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id_to", columnDefinition = "INT")
    private AccountEntity account_id_to;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", columnDefinition = "id")
    private UserEntity author;

    @Column(name = "amount", columnDefinition = "DECIMAL(10, 0)")
    private double amount;

    @Column(name = "comment")
    private String comment;

    @Column(name = "applied", columnDefinition = "TINYINT(1)")
    private boolean applied;

    @Column(name = "date", columnDefinition = "DATETIME")
    private String date;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public AccountEntity getAccount_id_from() {
        return account_id_from;
    }

    public AccountEntity getAccount_id_to() {
        return account_id_to;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public double getAmount() {
        return amount;
    }

    public String getComment() {
        return comment;
    }

    public boolean isApplied() {
        return applied;
    }

    public String getDate() {
        return date;
    }
}
