package com.ejbank.api;

import com.ejbank.api.payload.AccountPayload;
import com.ejbank.api.payload.AccountsPayload;
import com.ejbank.beans.AccountBeanLocal;
import com.ejbank.model.AccountEntity;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountAPI {
    @EJB
    private AccountBeanLocal accountBeanLocal;

    @GET
    @Path("/{user_id}")
    public AccountsPayload getUserAccounts(@PathParam("user_id") Integer user_id) {
        List<AccountEntity> accounts = accountBeanLocal.getAccountsByUser(user_id);
        List<AccountPayload> payloads = new ArrayList<>();
        for (AccountEntity account : accounts) {
            payloads.add(new AccountPayload(""+account.getId(), account.getAccountType().getName(), account.getBalance()));
        }
        return new AccountsPayload(payloads);
    }
}