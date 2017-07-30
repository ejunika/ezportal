package com.ez.portal.core.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loginRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginRequest extends AbstractRequest {

    private static final long serialVersionUID = 1L;

    private String emailId;
    
    private String password;
    
    private Byte userType;
    
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

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }
    
}
