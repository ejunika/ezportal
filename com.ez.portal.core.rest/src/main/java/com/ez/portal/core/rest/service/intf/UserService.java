package com.ez.portal.core.rest.service.intf;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.core.request.UserRequest;
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
	
	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("remove-user/{userId}")
	UserResponse removeUser(@PathParam("userId") Long userId) throws Exception;
	
	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("block-user/{userId}")
	UserResponse blockUser(@PathParam("userId") Long userId) throws Exception;
	
	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("unblock-user/{userId}")
	UserResponse unblockUser(@PathParam("userId") Long userId) throws Exception;
	
	/**
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("activate-user/{userId}")
	UserResponse activateUser(@PathParam("userId") Long userId) throws Exception;
	
	/**
	 * @param userRequest
	 * @return
	 * @throws Exception
	 */
	@PUT
	@Path("add-user")
	UserResponse addUser(UserRequest userRequest) throws Exception;
	
	/**
	 * @param userRequest
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("update-user")
	UserResponse updateUser(UserRequest userRequest) throws Exception;
	
}
