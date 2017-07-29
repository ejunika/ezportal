package com.ez.portal.shard.rest.service.intf;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ez.portal.shard.request.ShardRequest;
import com.ez.portal.shard.response.ShardResponse;

@WebService
@Consumes (MediaType.APPLICATION_JSON)
@Produces (MediaType.APPLICATION_JSON)
public interface ShardService {
    
    @POST
    @Path("get_all_user_space")
    ShardResponse getAllUserSpace(ShardRequest request);
}
