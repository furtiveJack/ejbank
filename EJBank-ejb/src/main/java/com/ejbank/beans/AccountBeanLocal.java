package com.ejbank.beans;

import com.ejbank.model.AccountEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface AccountBeanLocal {

    List<AccountEntity> getAccountsByUser(int id);
}
