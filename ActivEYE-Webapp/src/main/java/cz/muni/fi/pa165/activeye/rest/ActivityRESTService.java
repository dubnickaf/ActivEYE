package cz.muni.fi.pa165.activeye.rest;

import cz.muni.fi.pa165.activeye.dto.ActivityDTO;
import cz.muni.fi.pa165.activeye.facades.ActivityFacade;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   14-12-2016
 */

@Path("/activities")
public class ActivityRESTService {

    @Inject
    private ActivityFacade activityFacade;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public final void createActivity(ActivityDTO activityDTO) {
        activityFacade.createActivity(activityDTO);
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public final void updateActivity(ActivityDTO activityDTO) {
        activityFacade.updateActivity(activityDTO);
    }

    @DELETE
    @Path("/delete/{activityId}")
    public final void deleteActivity(@PathParam("activityId") String id) {
        activityFacade.deleteActivity(Long.valueOf(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{activityId}")
    public final ActivityDTO findById(@PathParam("activityId") String id) {
        return activityFacade.findById(Long.valueOf(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public final ActivityDTO findByName(@QueryParam("name") String name) {
        return activityFacade.findByName(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/all")
    public final List<ActivityDTO> findAll() {
        return activityFacade.findAll();
    }
}
