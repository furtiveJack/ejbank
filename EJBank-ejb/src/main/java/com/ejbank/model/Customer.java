package com.ejbank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ejbank_customer")
public class Customer extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @ManyToOne
    private User user;

    @Column(name = "advisor_id")
    @ManyToOne
    private Advisor advisor;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
}
