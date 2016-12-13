/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.activeye.rest;

import cz.muni.fi.pa165.activeye.facades.ActivityFacade;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author xdubnick
 */
@Path("/activities")
public class ActivityRESTService {
    @Inject
    private ActivityFacade activityFacade;
    
    
}
