package com.ez.portal.core.rest.service.intf;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.core.response.PortalSessionResponse;

/**
 * @author azaz.akhtar
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PortalSessionService {

	/**
	 * @param portalSessionToken
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("check-portal-session/{portalSessionToken}")
	PortalSessionResponse checkPortalSession(@PathParam("portalSessionToken") String portalSessionToken)
			throws Exception;

}
