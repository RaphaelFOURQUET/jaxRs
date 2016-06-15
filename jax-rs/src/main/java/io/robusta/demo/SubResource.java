package io.robusta.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class SubResource {
   
	@GET
	public String world(){
		return "world";
	}
	
	@GET
	@Path("conflict")
	public String conflict(){
		return "get conflict as a SubResource";
	}
	
}