package com.ejbank.beans;

import com.ejbank.model.AccountEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class AccountBean implements AccountBeanLocal {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    @Override
    public List<AccountEntity> getAccountsByCustomer(int userId) {
        return em.createNamedQuery("AccountEntity.getAccountsByCustomer", AccountEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<AccountEntity> getAccountsByAdvisor(int id) {
        return em.createNamedQuery("AccountEntity.getAccountsByAdvisor", AccountEntity.class)
                .setParameter("userId", id)
                .getResultList();
    }
}
