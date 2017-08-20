package com.ez.portal.core.rest.service.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.request.SignUpRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.response.UserResponse;

/**
 * @author azaz.akhtar
 *
 */
@WebService
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LoginService {

	/**
	 * API for new user creation
	 * 
	 * @param signUpRequest
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/sign-up")
	UserResponse signUp(SignUpRequest signUpRequest) throws Exception;

	/**
	 * @param loginRequest
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/do-login")
	LoginResponse doLogin(LoginRequest loginRequest) throws Exception;

	/**
	 * @param authenticationToken
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/logout/{authenticationToken}")
	LoginResponse logout(@PathParam("authenticationToken") String authenticationToken) throws Exception;

}
