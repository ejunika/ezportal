package com.ez.portal.core.rest.service.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.core.request.UserRoleRequest;
import com.ez.portal.core.response.UserRoleResponse;

/**
 * @author azaz.akhtar
 *
 */
@WebService
@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public interface UserRoleService {

	@GET
	@Path("get-user-role/{userRoleId}")
	UserRoleResponse getUserRole(@PathParam("userRoleId") Long userRoleId) throws Exception;
	
	@GET
	@Path("activate-user-role/{userRoleId}")
	UserRoleResponse activateUserRole(@PathParam("userRoleId") Long userRoleId) throws Exception;
	
	@GET
	@Path("delete-user-role/{userRoleId}")
	UserRoleResponse deleteUserRole(@PathParam("userRoleId") Long userRoleId) throws Exception;
	
	@GET
	@Path("block-user-role/{userRoleId}")
	UserRoleResponse blockUserRole(@PathParam("userRoleId") Long userRoleId) throws Exception;
	
	@GET
	@Path("get-all-user-role")
	UserRoleResponse getAllUserRole() throws Exception;
	
	@POST
	@Path("create-user-role")
	UserRoleResponse createUserRole(UserRoleRequest userRoleRequest) throws Exception;
	
	@POST
	@Path("update-user-role")
	UserRoleResponse updateUserRole(UserRoleRequest userRoleRequest) throws Exception;
	
}
