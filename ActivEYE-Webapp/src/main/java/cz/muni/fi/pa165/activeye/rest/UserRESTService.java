package cz.muni.fi.pa165.activeye.rest;

import cz.muni.fi.pa165.activeye.dto.NotAuthenticatedUserDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.facades.UserFacade;

import javax.inject.Inject;
import javax.ws.rs.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/login/{nauDto}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final boolean authenticate(NotAuthenticatedUserDTO user){
        return userFacade.authenticate(user);
    }

    @RequestMapping(value = "/register/{password}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void register(UserDTO userDTO,@PathParam("password")String password){
        userFacade.registerUser(userDTO,password);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserDTO> getAllUsers(){
        return userFacade.getAllUsers();
    }

    @RequestMapping(value = "/byId/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO findUserById(@PathParam("id")String id){
        return userFacade.findUserById(Long.valueOf(id));
    }

    @RequestMapping(value = "/byEmail/{email}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO findUserByEmail(@PathParam("email")String email){
        return userFacade.findUserByEmail(email);
    }

    @RequestMapping(value = "/update/{user}",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
     public final void updateUser(@PathParam("user")UserDTO userDTO){
        userFacade.updateUser(userDTO);

    }

    @RequestMapping(value = "/update/{user}",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteUser(@PathParam("user")UserDTO userDTO){
        userFacade.deleteUser(userDTO);
    }
}
