package com.ejbank.beans;

import com.ejbank.model.AccountEntity;
import com.ejbank.model.TransactionEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TransactionBeanLocal {

    int getNbTransactionsToValidateForAccount(int accountId);

    List<TransactionEntity> getTransactionsByAccount(int accountId, int offset);

    int getNbTransactionsByAccount(int accountId);
}
