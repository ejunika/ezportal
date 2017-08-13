package com.ez.portal.core.response;

import java.io.Serializable;


public abstract class AbstractResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String message;
    
    private Boolean status;
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Boolean getStatus() {
        return status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public void resetResponse() {
        this.message = null;
        this.status = null;
    }
    
}
