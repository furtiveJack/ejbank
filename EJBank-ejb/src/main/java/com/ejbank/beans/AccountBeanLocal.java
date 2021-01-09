package com.ejbank.beans;

import com.ejbank.model.AccountEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccountBeanLocal {

    /**
     * Retrieve the account list of the given customer.
     * @param id    id of the customer
     * @return      a list containing all the accounts of this customer (can be null)
     */
    List<AccountEntity> getAccountsByCustomer(int id);

    /**
     * Retrieve the account list of the given advisor.
     * @param id    id of the advisor
     * @return      a list containing all the accounts of this advisor (can be null)
     */
    List<AccountEntity> getAccountsByAdvisor(int id);

    /**
     * Retrieve the account corresponding the the given id.
     * @param id    account id
     * @return      the account retrieved (can be null)
     */
    AccountEntity getById(int id);
}
