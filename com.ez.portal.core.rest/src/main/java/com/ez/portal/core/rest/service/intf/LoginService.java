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
import com.ez.portal.core.request.UserRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.response.UserResponse;

@WebService
@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public interface LoginService {
    
    @POST
    @Path ("/sign-up")
    UserResponse signUp(UserRequest userRequest) throws Exception;
    
    @POST
    @Path ("/do-login")
    LoginResponse doLogin(LoginRequest loginRequest) throws Exception;
    
    @GET
    @Path ("/get-user/{userId}")
    UserResponse getUser(@PathParam ("userId") Long userId) throws Exception;
    
    @GET
    @Path ("/get-all-user")
    UserResponse getAllUser() throws Exception;
        
}
