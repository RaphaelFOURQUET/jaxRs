/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.robusta.forum;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
    //@Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return ForaDataSource.getInstance().getUsers();
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
