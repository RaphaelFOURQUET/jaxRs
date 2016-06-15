package io.robusta.demo.response;

import io.robusta.business.EtagBusiness;
import io.robusta.business.UserBusiness;
import io.robusta.demo.providers.NotFoundException;
import io.robusta.domain.User;
import io.robusta.rra.representation.implementation.GsonRepresentation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("")
public class ResponseResource {

	@Inject
	UserBusiness userBusiness;
	
	@Inject
	EtagBusiness etagBusiness;
	
	@POST
	@Path("user/")
	public Response createUser(@PathParam("name") String name) {
	    
	    User entity = userBusiness.createUser(name);
	    String json = new Gson().toJson(entity);
	    return Response.ok(json, MediaType.APPLICATION_JSON)
	    	.header("ETag", entity.getEtag()).status(201).build();
	}
	

	@GET
	public String test(){
		return "test";
	}
	
	@GET
	@Path("{id}")
	public Response getUser(@PathParam("id") long id){
		
		User u = userBusiness.getUserById(id);
		if (u != null){
			return Response.ok(u).build();
		}else{
			throw new WebApplicationException("no user found with id : "+id,404);
		}
		
	}
	
	@GET
	@Path("throw/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public GsonRepresentation getThrowableUser(@PathParam("id") long id){
		
		User u = userBusiness.getUserById(id);
		if (u != null){
			return new GsonRepresentation(u);
		}else{
			throw new NotFoundException();
		}
	}
	
	
}
