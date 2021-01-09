package com.ejbank.api;

import com.ejbank.api.payload.*;
import com.ejbank.beans.AccountBeanLocal;
import com.ejbank.beans.TransactionBeanLocal;
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

    @EJB
    private TransactionBeanLocal transactionBeanLocal;

    @GET
    @Path("/{user_id}")
    public AccountsPayload<AccountPayload> getCustomerAccounts(@PathParam("user_id") Integer userId) {
        if (! userBeanLocal.isCustomer(userId)) {
            return new AccountsPayload<>(new ArrayList<>(), "You are not a customer");
        }
        List<AccountEntity> accounts = accountBeanLocal.getAccountsByCustomer(userId);
        if (accounts == null) {
            return new AccountsPayload<>(new ArrayList<>(), "You don't have any account");
        }
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
    public AccountsPayload<AccountAttachedPayload> getAdvisorAttachedAccounts(@PathParam("user_id") Integer userId) {
        if (userBeanLocal.isCustomer(userId)) {
            return new AccountsPayload<>(new ArrayList<>(), "You are not an advisor");
        }
        List<AccountEntity> accounts = accountBeanLocal.getAccountsByAdvisor(userId);
        if (accounts == null) {
            return new AccountsPayload<>(new ArrayList<>(), "You don't have any attached account");
        }
        List<AccountAttachedPayload> payloads = new ArrayList<>();
        for (AccountEntity account : accounts) {
            UserEntity user = userBeanLocal.getById(account.getCustomer().getId());
            int validation = transactionBeanLocal.getNbTransactionsToValidateForAccount(account.getId());
            payloads.add(new AccountAttachedPayload(""+account.getId(),
                    account.getAccountType().getName(),
                    account.getBalance(),
                    user.getFirstname() + " " + user.getLastname(),
                    validation));
        }
        return new AccountsPayload<>(payloads, null);
    }

    @GET
    @Path("/all/{user_id}")
    public AccountsPayload<AccountAllPayload> getUserAccounts(@PathParam("user_id") Integer userId) {
        List<AccountAllPayload> payloads = new ArrayList<>();
        List<AccountEntity> accounts = userBeanLocal.isCustomer(userId)
                ? accountBeanLocal.getAccountsByCustomer(userId)
                : accountBeanLocal.getAccountsByAdvisor(userId);
        if (accounts == null) {
            return new AccountsPayload<>(new ArrayList<>(), "No account for this user");
        }
        for (AccountEntity account : accounts) {
            UserEntity user = userBeanLocal.getById(account.getCustomer().getId());
            payloads.add(new AccountAllPayload(
                    account.getId()+"",
                    account.getAccountType().getName(),
                    account.getBalance(),
                    user.getFirstname() + " " + user.getLastname()));
        }
        return new AccountsPayload<>(payloads, null);
    }
}