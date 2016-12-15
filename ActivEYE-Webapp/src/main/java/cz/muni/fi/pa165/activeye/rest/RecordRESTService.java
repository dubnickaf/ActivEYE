/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.activeye.rest;

import cz.muni.fi.pa165.activeye.dto.RecordDTO;
import cz.muni.fi.pa165.activeye.facades.RecordFacade;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author  Matej Vasko 422713
 * @version 1.0
 * @since   15-12-2016
 */
@Path("/records")
public class RecordRESTService {
    @Inject
    private RecordFacade recordFacade;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public final void createRecord(RecordDTO recordDTO) {
        recordFacade.createRecord(recordDTO);
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public final void updateRecord(RecordDTO recordDTO) {
        recordFacade.updateRecord(recordDTO);
    }

    @DELETE
    @Path("/delete/{recordId}")
    public final void deleteRecord(@PathParam("recordId") String id) {
        RecordDTO recordDTO = recordFacade.findById(Long.valueOf(id));
        recordFacade.deleteRecord(recordDTO);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{recordId}")
    public final RecordDTO findById(@PathParam("recordId") String id) {
        return recordFacade.findById(Long.valueOf(id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/all")
    public final List<RecordDTO> getAllRecords() {
        return recordFacade.getAllRecords();
    }

}
