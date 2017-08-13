package com.ez.portal.core.entity;

import java.io.Serializable;

public interface Shardable extends Serializable {
    String getShardKey();
}
