package com.ejbank.beans;

import com.ejbank.model.UserEntity;

import javax.ejb.Local;

@Local
public interface UserBeanLocal {

    UserEntity getById(int id);
}
