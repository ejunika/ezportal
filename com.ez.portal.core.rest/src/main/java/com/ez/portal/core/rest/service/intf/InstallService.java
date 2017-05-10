package com.ez.portal.core.rest.service.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.core.rest.request.UserSpaceRequest;
import com.ez.portal.core.rest.response.UserSpaceResponse;

@WebService
@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public interface InstallService {

    @POST
    @Path("/create_user_space")
    UserSpaceResponse createUserSpace(UserSpaceRequest userSpaceRequest);
    
}
