package com.ejbank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ejbank_advisor")
public class Advisor extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @Column(name = "id")
    @Id
    private User user;
}
