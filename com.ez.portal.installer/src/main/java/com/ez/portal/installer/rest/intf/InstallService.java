package com.ez.portal.installer.rest.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.installer.response.InstallResponse;

@WebService
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface InstallService {
    
    @GET
    @Path("/install")
    InstallResponse install() throws Exception;
}
