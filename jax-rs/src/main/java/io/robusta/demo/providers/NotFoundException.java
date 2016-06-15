package io.robusta.demo.providers;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException{
	
	public NotFoundException() {
		super(Response.status(404).entity("Resource not Found").build());
	}
	
}
