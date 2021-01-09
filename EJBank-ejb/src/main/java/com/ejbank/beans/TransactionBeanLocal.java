package com.ejbank.beans;

import com.ejbank.model.AccountEntity;
import com.ejbank.model.TransactionEntity;
import com.ejbank.model.UserEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TransactionBeanLocal {

    /**
     * Compute the number of transactions waiting for validation for a given account.
     * @param accountId id of the account
     * @return          the number of transactions found
     */
    int getNbTransactionsToValidateForAccount(int accountId);

    /**
     * Retrieves a list of transactions for the given account.
     * @param accountId id of the account
     * @param offset    offset of the first transaction in the list
     * @return          a list of 10 transactions order by time desc
     */
    List<TransactionEntity> getTransactionsByAccount(int accountId, int offset);

    /**
     * Compute the total number of transactions for this account.
     * @param accountId id of the account
     * @return          the number of transactions done on this account.
     */
    int getNbTransactionsByAccount(int accountId);

    /**
     * Create a new transaction in database.
     * @param srcAccount    source account
     * @param dstAccount    destination account
     * @param amount        amount of money to transfer
     * @param comment       optional comment
     * @param author        author of this transaction
     * @param applied       weither the transaction is applied or not
     * @return              id of the created transaction on success, -1 otherwise
     */
    int createTransaction(AccountEntity srcAccount, AccountEntity dstAccount, double amount,
                           String comment, UserEntity author,
                           boolean applied);

    /**
     * Apply a transaction. The accounts concerned by this transaction will see
     * their balance updated correspondingly.
     * @param id    id of the transition
     * @return      true if success, false otherwise
     */
    boolean applyTransaction(int id);

    /**
     * Retrieve a transaction by its id
     * @param id    transaction id
     * @return      correponding transaction (can be null)
     */
    TransactionEntity getById(int id);
}
