package com.ez.portal.core.rest.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.util.rest.request.AbstractRequest;

@XmlRootElement(name = "loginRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginRequest extends AbstractRequest {

    private static final long serialVersionUID = 1L;

    private String emailId;
    
    private String password;
    
    public String getEmailId() {
        return emailId;
    }
    
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
}
