/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.activeye.rest;

import cz.muni.fi.pa165.activeye.facades.RecordFacade;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author xdubnick
 */
@Path("/records")
public class RecordRESTService {
    @Inject
    private RecordFacade recordFacade;
    
    
}
