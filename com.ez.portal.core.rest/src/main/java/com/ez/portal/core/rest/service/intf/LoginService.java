package com.ez.portal.core.rest.service.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.response.LoginResponse;

/**
 * @author azaz.akhtar
 *
 */
@WebService
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LoginService {

	/**
	 * @param loginRequest
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/do-login")
	LoginResponse doLogin(LoginRequest loginRequest) throws Exception;

}
