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
                "AND t.applied = false ")
})
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id_from", columnDefinition = "INT")
    private UserEntity account_id_from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id_to", columnDefinition = "INT")
    private UserEntity account_id_to;

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
    private Date date;

}
