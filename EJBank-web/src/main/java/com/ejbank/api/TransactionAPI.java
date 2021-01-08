package com.ejbank.api;

import com.ejbank.api.payload.TransactionListPayload;
import com.ejbank.api.payload.TransactionPayload;
import com.ejbank.api.payload.TransactionPreviewPayload;
import com.ejbank.api.payload.TransactionPreviewRequestPayload;
import com.ejbank.beans.AccountBeanLocal;
import com.ejbank.beans.TransactionBeanLocal;
import com.ejbank.beans.UserBeanLocal;
import com.ejbank.model.AccountEntity;
import com.ejbank.model.TransactionEntity;
import com.ejbank.model.UserEntity;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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
            validation += transactionBeanLocal.getNbTransactionsToValidateForAccount(account.getId());
        }
        return validation;
    }

    @GET
    @Path("/list/{account_id}/{offset}/{user_id}")
    public TransactionListPayload getTransactionsFromAccount(@PathParam("account_id") Integer accountId,
                                           @PathParam("offset") Integer offset,
                                           @PathParam("user_id") Integer userId) {
        boolean isCustomer = userBeanLocal.isCustomer(userId);
        List<AccountEntity> accounts = isCustomer
                ? accountBeanLocal.getAccountsByCustomer(userId)
                : accountBeanLocal.getAccountsByAdvisor(userId);
        if (accounts.stream().noneMatch(account -> account.getId() == accountId)) {
            return new TransactionListPayload(new ArrayList<>(),
                    "You're not allowed to access this account", 0);
        }
        List<TransactionEntity> transactions = transactionBeanLocal.getTransactionsByAccount(accountId, offset);
        List<TransactionPayload> payloads = new ArrayList<>();
        for (TransactionEntity t : transactions) {
            UserEntity destUser = userBeanLocal.getById(t.getAccount_id_to().getCustomer().getId());
            String state = t.isApplied()
                    ? "APPLIED"
                    : isCustomer ? "WAITING_APPROVE" : "TO_APPROVE";
            payloads.add(new TransactionPayload(t.getId(), t.getDate(),
                    t.getAccount_id_from().getAccountType().getName(),
                    t.getAccount_id_to().getAccountType().getName(),
                    destUser.getFirstname() + " " + destUser.getLastname(),
                    t.getAmount(), t.getAuthor().getFirstname() + " " + t.getAuthor().getLastname(),
                    t.getComment(), state));
        }
        int total = transactionBeanLocal.getNbTransactionsByAccount(accountId);
        return new TransactionListPayload(payloads, null, total);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/preview")
    public TransactionPreviewPayload getTransactionPreview(TransactionPreviewRequestPayload payload) {
        AccountEntity srcAccount = accountBeanLocal.getById(Integer.parseInt(payload.getSource()));
        AccountEntity dstAccount = accountBeanLocal.getById(Integer.parseInt(payload.getDestination()));
        int overdraft = srcAccount.getAccountType().getOverdraft();
        double amount = Integer.parseInt(payload.getAmount());
        double before = srcAccount.getBalance() - amount;
        double after = dstAccount.getBalance() + amount;
        boolean result = before + overdraft >= 0;
        String message = result ? "" : "Vous ne disposez pas d'un solde suffisant";
        return new TransactionPreviewPayload(result, before, after,
                message, null);
    }
}