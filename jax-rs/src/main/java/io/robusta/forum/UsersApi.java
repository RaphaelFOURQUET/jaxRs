/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.robusta.forum;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.robusta.ForaDataSource;
import io.robusta.business.UserBusiness;
import io.robusta.domain.User;

/**
 *
 * @author INTI-0332
 */
@Path("users")
public class UsersApi {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getUsers(@QueryParam("term") String term) {
		List<User> users = ForaDataSource.getInstance().getUsers();
		if(term != null) {
			//for() Si contient term
			List<User> matchingUsers = new ArrayList<User>();
			
			//Une autre facon de faire ma boucle.
			//return users.stream().filter(u -> u.getName().toLowerCase().contains(term.toLowerCase())).collect(Collectors.toList());
			
			for(User currentUser : users) {
				if(currentUser.getName().toLowerCase().contains(term.toLowerCase())) {
					matchingUsers.add(currentUser);
				}
			}
			return matchingUsers;
		}
		return users;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") long userId) {
		UserBusiness userBusiness = new UserBusiness();
		return userBusiness.findById(userId);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_XML)
	public User update(User user) {	//Comment recuperer User ? Seulement avec le @Consumes et l'ordre des parametres.. OK
		UserBusiness userBusiness = new UserBusiness();
		return userBusiness.updateUser(user);
	}

	@DELETE
	@Path("{id}")
	public String deleteUser(@PathParam("id") long userId) {
		UserBusiness userBusiness = new UserBusiness();
		User user = userBusiness.findById(userId);
		userBusiness.deleteUser(user);
		return "deleted";
	}
}
