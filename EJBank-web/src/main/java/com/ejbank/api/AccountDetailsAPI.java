package com.ejbank.api;

import com.ejbank.api.payload.AccountDetailsPayload;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountDetailsAPI {

    @EJB
    private TransactionBeanLocal transactionBeanLocal;
    @EJB
    private UserBeanLocal userBeanLocal;
    @EJB
    private AccountBeanLocal accountBeanLocal;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{account_id}/{user_id}")
    public AccountDetailsPayload getAccountDetails(@PathParam("account_id") Integer accountId,
                                                   @PathParam("user_id") Integer userId) {
        AccountEntity account = accountBeanLocal.getById(accountId);
        if (account == null) {
            return new AccountDetailsPayload(null, null, null, null, null,
                    "Account not found");
        }
        if (account.getCustomer().getId() != userId && account.getCustomer().getAdvisor_id() != userId) {
            return new AccountDetailsPayload(null, null, null, null, null,
                    "Account access forbidden");
        }
        UserEntity user = userBeanLocal.getById(account.getCustomer().getId());
        UserEntity advisorUser = userBeanLocal.getById(account.getCustomer().getAdvisor_id());
        if (user == null || advisorUser == null) {
            return new AccountDetailsPayload(null, null, null, null, null,
                    "User not found");
        }
        double rate = account.getAccountType().getRate();
        double amount = account.getBalance();
        double interest = computeInterest(account, rate);
        String owner = user.getFirstname() + " " + user.getLastname();
        String advisor = advisorUser.getFirstname() + " " + advisorUser.getLastname();
        return new AccountDetailsPayload(owner, advisor, rate, interest, amount, null);
    }

    private double computeInterest(AccountEntity account, double rate) {
        if (rate == 0) return 0;
        int month = LocalDateTime.now().getMonthValue();
        int year = LocalDateTime.now().getYear();
        double monthlyAmount = account.getBalance();
        double[] interestByMonth = new double[month];
        Arrays.fill(interestByMonth, 0);

        for (int currentMonth = month ; currentMonth >= 1 ; currentMonth--) {
            List<TransactionEntity> transactions = transactionBeanLocal
                    .getTransactionsByMonth(account.getId(), year, month);
            if (transactions == null) {
                interestByMonth[currentMonth-1] = 0;
                continue;
            }
            transactions = transactions.stream()
                    .filter(TransactionEntity::isApplied)
                    .collect(Collectors.toList());
            for (TransactionEntity t : transactions) {
                if (t.getAccount_id_from().getId() == account.getId()) {
                    monthlyAmount -= t.getAmount();
                }
                if (t.getAccount_id_to().getId() == account.getId()) {
                    monthlyAmount += t.getAmount();
                }
            }
            interestByMonth[currentMonth-1] = monthlyAmount * rate / 100;
        }
        return Arrays.stream(interestByMonth).sum();
    }
}
