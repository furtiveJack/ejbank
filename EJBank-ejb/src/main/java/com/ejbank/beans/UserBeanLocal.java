package com.ejbank.beans;

import com.ejbank.model.UserEntity;

import javax.ejb.Local;

@Local
public interface UserBeanLocal {

    /**
     * Retrieve a user by its id.
     * @param id    user id
     * @return      The corresponding user (can be null).
     */
    UserEntity getById(int id);

    /**
     * Check weither this user is a customer or an advisor.
     * @param userId    user id
     * @return          true if user is a customer, false if its an advisor.
     */
    boolean isCustomer(int userId);
}
