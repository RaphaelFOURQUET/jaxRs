package io.robusta.demo.jaxb;

import io.robusta.rra.representation.implementation.GsonRepresentation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("rra")
public class RraResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String asString(){
		
		GsonRepresentation rep = new GsonRepresentation(new Data());				
		rep.set("rra", "Robusta code Rest Adapter");
		rep.remove("password");
		return rep.toString();
	}
	
	
	@GET
	@Path("gson")
	@Produces(MediaType.APPLICATION_JSON)
	public GsonRepresentation gson(){
		
		GsonRepresentation rep = new GsonRepresentation(new Data());				
		rep.set("rra", "Robusta code Rest Adapter");
		rep.remove("password");
		return rep;
	}
	
}


class Data{
	
	String hello = "world";
	String password = "my-password-2015";
	
}