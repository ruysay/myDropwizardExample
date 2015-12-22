package com.lawrence.web;

import org.skife.jdbi.v2.DBI;

import com.lawrence.web.db.Blog;
import com.lawrence.web.db.UserDAO;
import com.lawrence.web.health.MyHealthCheck;
import com.lawrence.web.mongo.MongoDBHealthCheck;
import com.lawrence.web.mongo.MongoManaged;
import com.mongodb.DB;
import com.mongodb.Mongo;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.vz.mongodb.jackson.JacksonDBCollection;

public class HelloWorldApplication extends Application<HelloWorldConfiguration>{
    
    public static void main(String[] args) throws Exception {
//        new HelloWorldApplication().run(args);
        HelloWorldApplication app = new HelloWorldApplication();
        try
        {
        	app.run(args);
        }
        catch(Exception err)
        {
        	System.out.println("Err: " +err);
        }
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
            );
        final MyHealthCheck healthCheck =
                new MyHealthCheck(configuration.getTemplate());
        
        System.out.println("=========RUN START=========");
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        
        System.out.println("Check: "+configuration.getTemplate() + ", "+
        		configuration.getDefaultName());
        
        
        final DBIFactory factory = new DBIFactory();
        System.out.println("factory: "+configuration.getDataSourceFactory());
        
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final UserDAO dao = jdbi.onDemand(UserDAO.class);
        environment.jersey().register(new UserResource(dao));
        System.out.println("=========RUN END=========");
        
        
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.lifecycle().manage(mongoManaged);
        
        environment.healthChecks().register("", new MongoDBHealthCheck(mongo));
        
        DB db = mongo.getDB(configuration.mongodb);
        JacksonDBCollection<Blog, String> blogs = JacksonDBCollection.wrap(db.getCollection("blogs"), Blog.class, String.class);
 
        environment.jersey().register(new BlogResource(blogs));
    }
}
