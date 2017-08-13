package com.ez.portal.shard.rest.service.impl;

import com.ez.portal.shard.manager.ShardManager;
import com.ez.portal.shard.request.ShardRequest;
import com.ez.portal.shard.response.ShardResponse;
import com.ez.portal.shard.rest.service.intf.ShardService;

public class ShardServiceImpl implements ShardService {

    private ShardManager shardManager;
    
    public ShardManager getShardManager() {
        return shardManager;
    }

    public void setShardManager(ShardManager shardManager) {
        this.shardManager = shardManager;
    }

    @Override
    public ShardResponse getAllUserSpace(ShardRequest request) throws Exception {
        return null;
    }

    @Override
    public ShardResponse installUserSpace(ShardRequest request) throws Exception {
        shardManager.createUserSpace(request);
        return null;
    }

    @Override
    public ShardResponse activateAllUserSpaces() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ShardResponse activateUserSpace(Long userSpaceId) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
