package com.ejbank.beans;

import com.ejbank.model.TransactionEntity;
import com.ejbank.model.UserEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class TransactionBean implements TransactionBeanLocal {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    @Override
    public int getTransactionsToValidate(int userId) {
        UserEntity user = em.find(UserEntity.class, userId);
        if ("customer".equals(user.getType())) {
            return 0;
        }
        return 3;
    }

    @Override
    public int getNbTransactionsForAccount(int accountId) {
        List<TransactionEntity> transactions = em
                .createNamedQuery("TransactionEntity.getNBTransactionsForAccount", TransactionEntity.class)
                .setParameter("accountId", accountId)
                .getResultList();
        return transactions.size();
    }
}
