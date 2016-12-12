package cz.muni.fi.pa165.activeye.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by spriadka on 12/12/16.
 */

@Path("/sample")
public class Sample {

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWorld(){
        return "Hello World";
    }
}
