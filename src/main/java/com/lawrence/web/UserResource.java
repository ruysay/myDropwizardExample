package com.lawrence.web;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response findNameById(@QueryParam("id") Optional<String> name) {
//        System.out.println("=========getId=========");
////    	final String value = String.format("hello", name.or("defaultID"));
//        System.out.println("mUserDAO: "+mUserDAO);
//        final String _id = String.format("hello", name.or("defaultID"));;
//        mUserDAO.findNameById(Integer.valueOf(_id));
//    	return new DAOResponse(0, "TEST");
    	mUserDAO.hashCode();
        return Response
       		 .ok( new DAOResponse( 12345, "findNameById") )
       		 .build();
    }
    
    @POST
    @Timed
    public Response createUser() {
        System.out.println("=========createUser=========");
        //TODO get data from payload
//        mUserDAO.insert(id, name);
        
        return Response
        		 .ok( new DAOResponse( 12345, "createUser") )
        		 .build();
//    	return new DAOResponse(0, "TEST");
    }
    
    @PUT
    @Timed
    public Response updateUser(@QueryParam("id") Optional<String> name) {
        System.out.println("=========getId=========");
//        System.out.println("mUserDAO: "+mUserDAO);
//        final String _id = String.format("hello", name.or("defaultID"));;
//        mUserDAO.findNameById(Integer.valueOf(_id));
//    	return new DAOResponse(0, "TEST");
        
        return Response
       		 .ok( new DAOResponse( 12345, "updateUser") )
       		 .build();
    }
    
    @DELETE
    @Timed
    public Response deleteUser(@QueryParam("id") Optional<String> name) {
        System.out.println("=========getId=========");
////    	final String value = String.format("hello", name.or("defaultID"));
//        System.out.println("mUserDAO: "+mUserDAO);
//        final String _id = String.format("hello", name.or("defaultID"));;
//        mUserDAO.findNameById(Integer.valueOf(_id));
//    	return new DAOResponse(0, "TEST");
        return Response
          		 .ok( new DAOResponse( 12345, "deleteUser") )
          		 .build();
    }
//    @GET
//    @Timed
//    public Saying sayHello(@QueryParam("name") Optional<String> name) {
//        final String value = String.format(template, name.or(defaultName));
//        return new Saying(counter.incrementAndGet(), value);
//    }
}