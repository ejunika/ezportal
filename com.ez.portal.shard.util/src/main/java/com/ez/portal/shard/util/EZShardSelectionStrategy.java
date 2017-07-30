package com.ez.portal.shard.util;

import org.hibernate.shards.ShardId;
import org.hibernate.shards.strategy.selection.ShardSelectionStrategy;

import com.ez.portal.shard.Shardable;

public class EZShardSelectionStrategy implements ShardSelectionStrategy {

    @Override
    public ShardId selectShardIdForNewObject(Object entity) {
        if (entity instanceof Shardable) {
            return getShardId(((Shardable) entity).getShardKey());
        }
        return null;
    }
    
    private ShardId getShardId(String shardKey) {
        ShardId shardId = null;
        try {
            shardId = new ShardId(Integer.parseInt(shardKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shardId;
    }

}
