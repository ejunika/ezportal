package com.ez.portal.shard.dao.intf;

import com.ez.portal.shard.entity.UserSpace;

public interface ShardDAO {
    UserSpace getUserSpace(Long userSpaceId);
}
