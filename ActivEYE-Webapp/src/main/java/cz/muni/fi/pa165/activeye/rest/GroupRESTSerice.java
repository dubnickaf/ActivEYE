package cz.muni.fi.pa165.activeye.rest;

import cz.muni.fi.pa165.activeye.dto.GroupDTO;
import cz.muni.fi.pa165.activeye.dto.UserDTO;
import cz.muni.fi.pa165.activeye.facades.GroupFacade;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 *
 * @author Branislav Bajuzik; 442772
 */
@Path("/groups")
public class GroupRESTSerice {
    @Inject
    private GroupFacade groupFacade;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public final void createGroup(GroupDTO groupDTO) {
        groupFacade.create(groupDTO);
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public final void updateGroup(GroupDTO groupDTO) {
        groupFacade.update(groupDTO);
    }

    @DELETE
    @Path("/delete/{groupId}")
    public final void deleteGroup(@PathParam("groupId") String id) {
        groupFacade.delete(Long.valueOf(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{groupId}")
    public final GroupDTO findById(@PathParam("groupId") String id) {
        return groupFacade.findById(Long.valueOf(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/all")
    public final Collection<GroupDTO> findAll() {
        return groupFacade.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/all/{groupId}")
    public final Collection<UserDTO> findAllUsers(@PathParam("groupId") String id) {
        return groupFacade.getAllUsers(Long.valueOf(id));
    }

    @PUT
    @Path("/update/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public final void addUser(UserDTO userDTO, @PathParam("groupId") String id) {
        groupFacade.addUser(userDTO, Long.valueOf(id));
    }
}
