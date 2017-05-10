package com.ez.portal.core.rest.service.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.core.rest.request.LoginRequest;
import com.ez.portal.core.rest.request.UserRequest;
import com.ez.portal.core.rest.response.LoginResponse;
import com.ez.portal.core.rest.response.UserResponse;

@WebService
@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public interface LoginService {
    
    @POST
    @Path ("/sign_up")
    UserResponse signUp(UserRequest userRequest);
    
    @POST
    @Path ("/do_login")
    LoginResponse doLogin(LoginRequest loginRequest);
    
    @GET
    @Path ("/get_user/{userId}")
    UserResponse getUser(@PathParam ("userId") Long userId);
    
}
