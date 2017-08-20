package com.ez.portal.core.rest.service.intf;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.core.response.UserResponse;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UserService {

	/**
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("get-all-users")
	UserResponse getAllUsers() throws Exception;
	
}
