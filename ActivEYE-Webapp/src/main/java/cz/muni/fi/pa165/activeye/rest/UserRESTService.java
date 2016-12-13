package cz.muni.fi.pa165.activeye.rest;

import cz.muni.fi.pa165.activeye.dto.NotAuthenticatedUserDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.facades.UserFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by spriadka on 12/12/16.
 */

@Path("/users")
public class UserRESTService {
    @Inject
    private UserFacade userFacade
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(NotAuthenticatedUserDTO user){
            return userFacade.authenticate(user) ? Response.ok().build() : Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("/register/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(UserDTO userDTO,@PathParam("password")String password){
        userFacade.registerUser(userDTO,password);
        return Response.ok().build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UserDTO> getAllUsers(){
        return userFacade.getAllUsers();
    }

}
