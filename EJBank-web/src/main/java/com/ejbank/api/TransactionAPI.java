package com.ejbank.api;

import com.ejbank.beans.AccountBeanLocal;
import com.ejbank.beans.TransactionBeanLocal;
import com.ejbank.beans.UserBeanLocal;
import com.ejbank.model.AccountEntity;
import com.ejbank.model.TransactionEntity;
import com.ejbank.model.UserEntity;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TransactionAPI {
    @EJB
    private TransactionBeanLocal transactionBeanLocal;
    @EJB
    private UserBeanLocal userBeanLocal;
    @EJB
    private AccountBeanLocal accountBeanLocal;

    @GET
    @Path("/validation/notification/{user_id}")
    public int getTransactionsToBeValidated(@PathParam("user_id") Integer user_id) {
        if (userBeanLocal.isCustomer(user_id)) {
            return 0;
        }
        int validation = 0;
        List<AccountEntity> accounts = accountBeanLocal.getAccountsByAdvisor(user_id);
        for (AccountEntity account : accounts) {
            validation += transactionBeanLocal.getNbTransactionsForAccount(account.getId());
        }
        return validation;
    }
}