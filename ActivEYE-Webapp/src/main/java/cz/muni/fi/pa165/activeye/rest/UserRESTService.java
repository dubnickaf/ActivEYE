package cz.muni.fi.pa165.activeye.rest;
import cz.muni.fi.pa165.activeye.entities.User;
import cz.muni.fi.pa165.activeye.exceptions.ActiveyeDataAccessException;
import cz.muni.fi.pa165.activeye.service.UserService;

import javax.inject.Inject;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by spriadka on 12/12/16.
 */

@Path("/users")
public class UserRESTService {
    @Inject
    private UserService userService;

    @POST
    @Path("/{email}/{password}")
    public Response authenticate(@PathParam("email") String email, @PathParam("password") String password){
        try{
            User user = userService.findUserByEmail(email);
            return userService.authenticate(user,password) ? Response.ok().build() : Response.status(Response.Status.FORBIDDEN).build();
        }
        catch (ActiveyeDataAccessException ex){
            return Response.serverError().build();
        }
    }
}
