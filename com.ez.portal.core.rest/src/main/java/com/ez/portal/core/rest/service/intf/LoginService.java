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
import com.ez.portal.core.request.UserRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.response.UserResponse;
import com.ez.portal.shard.request.ShardRequest;
import com.ez.portal.shard.response.ShardResponse;

@WebService
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LoginService {

    @POST
    @Path("/sign-up")
    UserResponse signUp(SignUpRequest signUpRequest) throws Exception;

    @POST
    @Path("/get-all-user-spaces")
    ShardResponse getAllUserSpaces(ShardRequest request) throws Exception;

    @POST
    @Path("/do-login")
    LoginResponse doLogin(LoginRequest loginRequest) throws Exception;

    @GET
    @Path("/get-user/{userId}")
    UserResponse getUser(@PathParam("userId") Long userId) throws Exception;

    @GET
    @Path("/get-all-user")
    UserResponse getAllUser() throws Exception;
    
    @GET
    @Path("/logout/{authenticationToken}")
    LoginResponse logout(@PathParam("authenticationToken") String authenticationToken) throws Exception;

    @GET
    @Path("/get-user-by-authentication-token/{authenticationToken}")
    UserResponse getUserByAuthenticationToken(@PathParam("authenticationToken") String authenticationToken)
            throws Exception;

}
