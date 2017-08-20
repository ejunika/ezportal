package com.ez.portal.core.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author azaz.akhtar
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractResponse implements Serializable {
    
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 
     */
    private String message;
    
    /**
     * 
     */
    private Boolean status;
    
    /**
     * @return
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * @return
     */
    public Boolean getStatus() {
        return status;
    }
    
    /**
     * @param status
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    /**
     * 
     */
    public void resetResponse() {
        this.message = null;
        this.status = null;
    }
    
}
