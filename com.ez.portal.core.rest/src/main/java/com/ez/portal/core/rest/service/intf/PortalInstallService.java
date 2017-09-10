package com.ez.portal.core.rest.service.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.core.response.PortalInstallResponse;

@WebService
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PortalInstallService {

	@GET
	@Path("install")
	PortalInstallResponse installPortal() throws Exception;
	
	@GET
	@Path("install/{tenantName}")
	PortalInstallResponse installPortal(@PathParam("tenantName") String tenantName) throws Exception;
	
}
