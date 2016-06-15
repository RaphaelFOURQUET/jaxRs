/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.robusta.forum;

import io.robusta.ForaDataSource;
import io.robusta.domain.User;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author INTI-0332
 */
@Path("users")
public class UsersApi {
    
    @GET
    public List<User> getUsers() {
        return ForaDataSource.getInstance().getUsers();
    }
}
