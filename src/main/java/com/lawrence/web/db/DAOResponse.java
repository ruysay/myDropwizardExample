package com.lawrence.web.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DAOResponse {
	
	private long id = 0;
	private String content = null;
	public DAOResponse() {
		
	}
	
    public DAOResponse(long id, String content) {
        this.id = id;
        this.content = content;
    }
    
    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}