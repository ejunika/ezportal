package com.ez.portal.rest.service;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@WebService
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface PortalService {
    
    @GET
    @Path("/get/{id}")
    Response get(@PathParam("id") Long id);

    @POST
    @Path("/post")
    Response post(@FormParam("reqData") String reqData);
    
}
