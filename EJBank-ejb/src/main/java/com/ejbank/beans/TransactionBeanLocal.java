package com.ejbank.beans;

import com.ejbank.model.AccountEntity;
import com.ejbank.model.TransactionEntity;
import com.ejbank.model.UserEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TransactionBeanLocal {

    int getNbTransactionsToValidateForAccount(int accountId);

    List<TransactionEntity> getTransactionsByAccount(int accountId, int offset);

    int getNbTransactionsByAccount(int accountId);

    int createTransaction(AccountEntity srcAccount, AccountEntity dstAccount, double amount,
                           String comment, UserEntity author,
                           boolean applied);

    boolean applyTransaction(int id);

    TransactionEntity getById(int id);
}
