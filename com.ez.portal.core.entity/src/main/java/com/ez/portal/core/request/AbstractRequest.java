package com.ez.portal.core.request;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author azaz.akhtar
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractRequest implements Serializable {
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    private String shardKey;

    /**
     * @return
     */
    public String getShardKey() {
        return shardKey;
    }

    /**
     * @param shardKey
     */
    public void setShardKey(String shardKey) {
        this.shardKey = shardKey;
    }
    
}
