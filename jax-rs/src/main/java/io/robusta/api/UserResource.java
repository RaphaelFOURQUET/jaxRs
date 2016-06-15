package io.robusta.api;

import io.robusta.ForaDataSource;
import io.robusta.domain.User;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Path("users")
public class UserResource {

	

	@Path("test")
	@GET
	public String test(){
		return "test";
	}
	
	@GET
	public String helloAdmin(@QueryParam("admin") boolean isAdmin) {
		return "isAdmin ? " + isAdmin;
	}
}
