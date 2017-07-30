package com.ez.portal.core.request;

import java.io.Serializable;

public abstract class AbstractRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String shardKey;

    public String getShardKey() {
        return shardKey;
    }

    public void setShardKey(String shardKey) {
        this.shardKey = shardKey;
    }
    
}
