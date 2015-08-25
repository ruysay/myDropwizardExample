package com.lawrence.web;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.lawrence.web.db.DAOResponse;
import com.lawrence.web.db.UserDAO;


@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	
	private UserDAO mUserDAO = null;
	
    public UserResource(UserDAO dao) {
    	mUserDAO = dao;
    }

    @GET
    @Timed
    public DAOResponse getId(@QueryParam("id") Optional<String> name) {
        System.out.println("=========getId=========");
//    	final String value = String.format("hello", name.or("defaultID"));
        System.out.println("mUserDAO: "+mUserDAO);
    	return new DAOResponse(0, "TEST");
    }
    
//    @GET
//    @Timed
//    public Saying sayHello(@QueryParam("name") Optional<String> name) {
//        final String value = String.format(template, name.or(defaultName));
//        return new Saying(counter.incrementAndGet(), value);
//    }
}