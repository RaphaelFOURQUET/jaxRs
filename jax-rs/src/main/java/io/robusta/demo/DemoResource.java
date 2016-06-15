package io.robusta.demo;

import io.robusta.business.UserBusiness;
import io.robusta.domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.Field;

@Path("hello")
public class DemoResource {

	

	@GET
	public String hello() {
		return "hello";
	}

	@GET
	@Path("param")
	public String helloAdmin(@QueryParam("admin") boolean isAdmin) {
		return "isAdmin ? " + isAdmin;
	}

	@POST
	// application/x-www-form-urlencoded
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_XML)
	@Path("param")
	public User createUser(@FormParam("admin") boolean isAdmin,
			@FormParam("name") String name, @FormParam("email") String email,
			@FormParam("male") boolean male) {
		return new UserBusiness().createUser(email, name, isAdmin, male);
	}

	@POST
	// application/x-www-form-urlencoded
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("easy")
	public User createEasy(User user) {
		// return new UserBusiness().createUser(email, name, isAdmin, male);
		return user;
	}

	@GET
	@Path("{name}/{property}")
	public String getByProperty(@PathParam("name") String name,
			@PathParam("property") String property)
			throws IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, SecurityException {
		User u = new UserBusiness().findByName(name);
		Field f = u.getClass().getDeclaredField(property);
		f.setAccessible(true);
		return f.get(u).toString();

	}

	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_XML)
	@Path("content")
	@POST
	public String readContent(
			@HeaderParam("Authorization") String authorization,
			@CookieParam("JSESSIONID") String name, String content) {

		return content.toLowerCase().replaceAll("penny", name);

	}

	@GET
	@Path("find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getByProperty(@PathParam("id") Long id) {
		User u = new UserBusiness().findById(id);
		return u;
	}
	
	@PUT	
	@Consumes(MediaType.APPLICATION_JSON)
	public User update(User u) {		
		return u;
	}
	

}
