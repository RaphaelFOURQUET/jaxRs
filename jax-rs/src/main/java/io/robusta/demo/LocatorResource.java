package io.robusta.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/hello")
public class LocatorResource {

	
	@Path("/world")
	public SubResource getSubResource() {
		return new SubResource();
	}
	
	@Path("{digit : \\d+}")
	@GET
	public String getDigit(@PathParam("digit") Long digits){		
		return digits.toString(); 
	}
	
	@Path("{param}/conflict")
	@GET
	public String getConflict(@PathParam("param") String param){		
		return "conflit gives '"+param+"' as a param"; 
	}
	
	@Path("conflict/conflict")
	@GET
	public String getAnotherConflict(@PathParam("digit") Long digits){		
		return "conflict as a strong string"; 
	}
		
	
	//@Path("conflict/conflict")
	//@GET
	public String getAThirdConflict(@PathParam("digit") Long digits){		
		return "conflict as a thrid string"; 
	}

	@Path("conflict")
	public SubResource getConflictSubResource() {
		return new SubResource();
	}
	
}
