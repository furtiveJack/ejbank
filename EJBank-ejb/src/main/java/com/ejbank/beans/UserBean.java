package com.ejbank.beans;

import com.ejbank.model.UserEntity;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UserBean implements UserBeanLocal {

    @PersistenceContext(unitName = "EJBankPU")
    private EntityManager em;

    @Override
    public UserEntity getById(int id) {
        return em.find(UserEntity.class, id);
    }

    @Override
    public boolean isCustomer(int userId) {
        UserEntity user = getById(userId);
        return "customer".equals(user.getType());
    }
}
