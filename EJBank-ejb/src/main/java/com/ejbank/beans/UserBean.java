package com.ejbank.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class UserBean implements UserBeanLocal {

    @Override
    public String test() {
        return "Hello from EJB";
    }
}
