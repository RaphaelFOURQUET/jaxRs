package io.robusta.demo.providers;

import io.robusta.rra.representation.implementation.GsonRepresentation;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class RepresentationException extends WebApplicationException {

	
	public RepresentationException(int status, GsonRepresentation representation) {
		super(Response.status(status).entity(representation).build());
	}
	
	public RepresentationException(String message, int status) {
		super(message, status);
	}
	
	public RepresentationException(Throwable throwable, int status) {
		this(throwable.getMessage(), status);
	}
}

