package com.ejbank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ejbank_transaction")
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    private int id;

    @Column(name = "account_id_from", columnDefinition = "INT")
    @ManyToOne
    private User account_id_from;

    @Column(name = "account_id_to", columnDefinition = "INT")
    @ManyToOne
    private User account_id_to;

    @Column(name = "author", columnDefinition = "id")
    @ManyToOne
    private User author;

    @Column(name = "amount", columnDefinition = "DECIMAL(10, 0)")
    private float amount;

    @Column(name = "comment")
    private String comment;

    @Column(name = "applied", columnDefinition = "TINYINT(1)")
    private boolean applied;

    @Column(name = "date", columnDefinition = "DATETIME")
    private Date date;

}
