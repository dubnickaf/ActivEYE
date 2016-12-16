package cz.muni.fi.pa165.activeye.rest;

import cz.muni.fi.pa165.activeye.dto.NotAuthenticatedUserDTO;
import cz.muni.fi.pa165.activeye.dto.StatisticsOfUserDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.dto.UserWithRecordsDTO;
import cz.muni.fi.pa165.activeye.facades.UserFacade;

import javax.inject.Inject;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * User REST controller
 * @author Filip Dubnička <445647>
 */

@Path("/users")
public class UserRESTService {
    @Inject
    private UserFacade userFacade;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public final boolean authenticate(NotAuthenticatedUserDTO user){
        return userFacade.authenticate(user);
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public final void register(UserDTO userDTO,@QueryParam("password") String password){
        userFacade.registerUser(userDTO,password);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public final Collection<UserDTO> getAllUsers(){
        return userFacade.getAllUsers();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{userId}")
    public final UserDTO findUserById(@PathParam("userId") String id){
        return userFacade.findUserById(Long.valueOf(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public final UserDTO findUserByEmail(@QueryParam("email")String email){
        return userFacade.findUserByEmail(email);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getWRecords/{userId}")
    public final UserWithRecordsDTO findUserWithRecordsById(@PathParam("userId") String id){
        return userFacade.findUserWithRecordsById(Long.valueOf(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getWRecords")
    public final UserWithRecordsDTO findUserWithRecordsByEmail(@QueryParam("email")String email){
        return userFacade.findUserWithRecordsByEmail(email);
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
     public final void updateUser(UserDTO userDTO){
        userFacade.updateUser(userDTO);

    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public final void deleteUser(UserDTO userDTO){
        userFacade.deleteUser(userDTO);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getStats")
    public final StatisticsOfUserDTO getStatistics(@QueryParam("id")String id){
        UserDTO userDTO = userFacade.findUserById(Long.parseLong(id));
        return userFacade.getStatistics(userDTO);
    }
}
