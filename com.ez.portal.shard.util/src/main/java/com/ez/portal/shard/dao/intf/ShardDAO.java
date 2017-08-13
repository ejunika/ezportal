package com.ez.portal.shard.dao.intf;

import java.util.List;

import com.ez.portal.shard.entity.UserSpace;

public interface ShardDAO {
    
    UserSpace getUserSpace(Long userSpaceId) throws Exception;
    
    UserSpace getUserSpace(String shardKey) throws Exception;
    
    List<UserSpace> getAllUserSpaces() throws Exception;
    
    List<UserSpace> getAllUserSpacesByEmailId(String  emailId) throws Exception;
    
    UserSpace addUserSpace(UserSpace userSpace) throws Exception;
    
}
