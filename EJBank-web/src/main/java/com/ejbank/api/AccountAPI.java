package com.ejbank.api;

import com.ejbank.api.payload.AccountPayload;
import com.ejbank.api.payload.AccountsPayload;
import com.ejbank.api.payload.AttachedAccountPayload;
import com.ejbank.beans.AccountBeanLocal;
import com.ejbank.beans.UserBeanLocal;
import com.ejbank.model.AccountEntity;
import com.ejbank.model.UserEntity;

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

    @EJB
    private UserBeanLocal userBeanLocal;

    @GET
    @Path("/{user_id}")
    public AccountsPayload<AccountPayload> getUserAccounts(@PathParam("user_id") Integer userId) {
        if (! userBeanLocal.isCustomer(userId)) {
            return new AccountsPayload<>(new ArrayList<>(), "You are not a customer");
        }
        List<AccountEntity> accounts = accountBeanLocal.getAccountsByCustomer(userId);
        List<AccountPayload> payloads = new ArrayList<>();
        for (AccountEntity account : accounts) {
            payloads.add(new AccountPayload(""+account.getId(),
                    account.getAccountType().getName(),
                    account.getBalance()));
        }
        return new AccountsPayload<>(payloads, null);
    }

    @GET
    @Path("/attached/{user_id}")
    public AccountsPayload<AttachedAccountPayload> getAdvisorAttachedAccounts(@PathParam("user_id") Integer userId) {
        if (userBeanLocal.isCustomer(userId)) {
            return new AccountsPayload<>(new ArrayList<>(), "You are not an advisor");
        }
        List<AccountEntity> accounts = accountBeanLocal.getAccountsByAdvisor(userId);
        List<AttachedAccountPayload> payloads = new ArrayList<>();
        for (AccountEntity account : accounts) {
            UserEntity user = userBeanLocal.getById(account.getCustomer().getId());
            int validation = 1; // TODO : ajouter "validation" = le nombre de transactions à valider sur chaque compte
            payloads.add(new AttachedAccountPayload(""+account.getId(),
                    account.getAccountType().getName(),
                    account.getBalance(),
                    user.getFirstname() + " " + user.getLastname(),
                    validation));
        }
        return new AccountsPayload<>(payloads, null);
    }
}