package com.ejbank.beans;

import com.ejbank.model.AccountEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.StringJoiner;

@Stateless
@LocalBean
public class AccountBean implements AccountBeanLocal {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    @Override
    public List<AccountEntity> getAccountsByUser(int userId) {
        return em.createNamedQuery("AccountEntity.getAccountsByUser", AccountEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
