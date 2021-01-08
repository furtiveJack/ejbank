package com.ejbank.beans;

import com.ejbank.model.AccountEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccountBeanLocal {

    List<AccountEntity> getAccountsByCustomer(int id);

    List<AccountEntity> getAccountsByAdvisor(int id);

    AccountEntity getById(int id);
}
