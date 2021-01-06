package com.ejbank.api;

import com.ejbank.beans.TransactionBeanLocal;
import com.ejbank.model.TransactionEntity;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.StringJoiner;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class TransactionAPI {
    @EJB
    private TransactionBeanLocal transactionBeanLocal;

    @GET
    @Path("/validation/notification/{user_id}")
    public String getTransactionsToBeValidated(@PathParam("user_id") Integer user_id) {
        return "" + transactionBeanLocal.getTransactionsToValidate(user_id);
    }
}