package com.lawrence.web.db;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import net.vz.mongodb.jackson.Id;

public class Blog {
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	@URL
	@NotBlank
	private String url = null;
	
	@NotBlank
	private String title = null;
    
	private final Date publishedOn = new Date();

	public Blog() {
		
	}
	
	public Blog(String title, String url) {
        super();
        this.title = title;
        this.url = url;
    }
	
	public String getId() {
		return id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Date getPublishedOn() {
		return publishedOn;
	}
}
