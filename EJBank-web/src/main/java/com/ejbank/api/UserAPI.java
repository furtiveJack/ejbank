package com.ejbank.api;


import com.ejbank.api.payload.UserPayload;
import com.ejbank.beans.UserBeanLocal;
import com.ejbank.model.UserEntity;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserAPI {

    @EJB
    private UserBeanLocal testUserBean;

    @GET
    @Path("/{userId}")
    public UserPayload getUserById(@PathParam("userId") Integer userId) {
        UserEntity user = testUserBean.getById(userId);
        return new UserPayload(user.getFirstname(), user.getLastname());
    }

}
