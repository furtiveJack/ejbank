package com.ejbank.api;


import com.ejbank.beans.UserBeanLocal;

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
public class UserTest {

    @EJB
    private UserBeanLocal testUserBean;

    @GET
    @Path("/test")
    public String testEJB() {
        return testUserBean.test();
    }

    @GET
    @Path("/{userId}")
    public String testUserById(@PathParam("userId") String userId) {
        return testUserBean.test() + userId;
    }

}
