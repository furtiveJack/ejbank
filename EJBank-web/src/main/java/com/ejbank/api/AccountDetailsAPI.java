package com.ejbank.api;

import com.ejbank.api.payload.AccountDetailsPayload;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class AccountDetailsAPI {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{account_id}/{user_id}")
    public AccountDetailsPayload getAccountDetails() {
        return new AccountDetailsPayload("Owner Owner", "Advisor Advisor", 0, 0, 0, null);
    }
}
