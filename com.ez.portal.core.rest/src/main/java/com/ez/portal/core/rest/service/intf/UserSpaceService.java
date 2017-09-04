package com.ez.portal.core.rest.service.intf;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.core.request.UserSpaceRequest;
import com.ez.portal.core.response.UserSpaceResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UserSpaceService {

	@GET
	@Path("get-user-spaces")
	UserSpaceResponse getUserSpaces() throws Exception;
	
	@POST
	@Path("get-all-user-spaces")
	UserSpaceResponse getAllUserSpaces(UserSpaceRequest portalUserSpaceRequest) throws Exception;
	
	@PUT
	@Path("create-user-space")
	UserSpaceResponse createUserSpace(UserSpaceRequest portalUserSpaceRequest) throws Exception;
	
}
