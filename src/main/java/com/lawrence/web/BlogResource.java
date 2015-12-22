package com.lawrence.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.lawrence.web.db.Blog;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

@Path("/blogs")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class BlogResource {
	
    private JacksonDBCollection<Blog, String> collection;
    
    public BlogResource(JacksonDBCollection<Blog, String> blogs) {
        System.out.println("=========BlogResource=========>"+blogs);
        this.collection = blogs;
    }
    
    @POST
    @Timed
    public Response publishNewBlog(@Valid Blog blog) {
    	System.out.println("publishNewBlog: "+blog);
        collection.insert(blog);
        return Response.noContent().build();
    }
    
    @GET
    @Timed
    public List<Blog> index() {
        DBCursor<Blog> dbCursor = collection.find();
        List<Blog> blogs = new ArrayList<Blog>();
        while (dbCursor.hasNext()) {
            Blog blog = dbCursor.next();
            blogs.add(blog);
        }
        return blogs;
    }
}
