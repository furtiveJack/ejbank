package com.ejbank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ejbank_account")
public class AccountEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    private int id;

    @ManyToOne
    private Customer customer;
}
