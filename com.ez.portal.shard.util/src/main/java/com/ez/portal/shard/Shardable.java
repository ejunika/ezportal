package com.ez.portal.shard;

import java.io.Serializable;

public interface Shardable extends Serializable {
    String getShardKey();
}
