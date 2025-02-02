package com.ejbank.beans;

import com.ejbank.model.AccountEntity;
import com.ejbank.model.TransactionEntity;
import com.ejbank.model.UserEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        return transactions != null ? transactions.size() : 0;
    }

    @Override
    public List<TransactionEntity> getTransactionsByAccount(int accountId, int offset) {
        return em.createNamedQuery("TransactionEntity.getByAccount", TransactionEntity.class)
                .setParameter("accountId", accountId)
                .setFirstResult(offset)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public int getNbTransactionsByAccount(int accountId) {
        List<TransactionEntity> transactions = em.createNamedQuery("TransactionEntity.getByAccount", TransactionEntity.class)
                .setParameter("accountId", accountId)
                .getResultList();
        return transactions != null ? transactions.size() : 0;
    }

    @Override
    public int createTransaction(AccountEntity srcAccount, AccountEntity dstAccount, double amount,
                                  String comment, UserEntity author,
                                  boolean applied) {
        try {
            TransactionEntity transaction = new TransactionEntity();
            transaction.setAccount_id_from(srcAccount);
            transaction.setAccount_id_to(dstAccount);
            transaction.setAmount(amount);
            transaction.setApplied(applied);
            transaction.setComment(comment);
            transaction.setAuthor(author);
            transaction.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            em.persist(transaction);
            em.flush();
            return transaction.getId();
        } catch(Exception e) {
            return -1;
        }
    }

    @Override
    public boolean applyTransaction(int id) {
        try {
            TransactionEntity transaction = em.find(TransactionEntity.class, id);
            AccountEntity srcAccount = transaction.getAccount_id_from();
            AccountEntity dstAccount = transaction.getAccount_id_to();
            srcAccount.setBalance(srcAccount.getBalance() - transaction.getAmount());
            dstAccount.setBalance(dstAccount.getBalance() + transaction.getAmount());
            transaction.setApplied(true);
            em.persist(srcAccount);
            em.persist(dstAccount);
            em.persist(transaction);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public TransactionEntity getById(int id) {
        return em.find(TransactionEntity.class, id);
    }

    @Override
    public List<TransactionEntity> getTransactionsByMonth(int accountId, int year, int month) {
        String startDate = year + '-' + month + "-01";
        String endDate = year + '-' + month + "-31";
        return em.createNamedQuery("TransactionEntity.getByMonth", TransactionEntity.class)
                .setParameter("accountId", accountId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

    @Override
    public boolean removeTransaction(int id) {
        try {
            TransactionEntity t = getById(id);
            em.remove(t);
            em.flush();
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
