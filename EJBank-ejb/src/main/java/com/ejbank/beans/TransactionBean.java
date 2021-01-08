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
    public int getNbTransactionsToValidateForAccount(int accountId) {
        List<TransactionEntity> transactions = em
                .createNamedQuery("TransactionEntity.getNBTransactionsForAccount", TransactionEntity.class)
                .setParameter("accountId", accountId)
                .getResultList();
        return transactions.size();
    }

    @Override
    public List<TransactionEntity> getTransactionsByAccount(int accountId, int offset) {
        List<TransactionEntity> transactions = em.createNamedQuery("TransactionEntity.getByAccount", TransactionEntity.class)
                .setParameter("accountId", accountId)
                .setFirstResult(offset)
                .setMaxResults(10)
                .getResultList();
        return transactions;
    }

    @Override
    public int getNbTransactionsByAccount(int accountId) {
        return em.createNamedQuery("TransactionEntity.getByAccount", TransactionEntity.class)
                .setParameter("accountId", accountId)
                .getResultList()
                .size();
    }
}
