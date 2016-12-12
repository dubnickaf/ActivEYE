package cz.muni.fi.pa165.activeye;

import cz.muni.fi.pa165.activeye.rest.UserRESTService;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Created by spriadka on 12/12/16.
 */
public class Webapp extends ResourceConfig {
    public Webapp(){
        register(RequestContextFilter.class);
        register(UserRESTService.class);
        register(JacksonFeature.class);
    }
}
