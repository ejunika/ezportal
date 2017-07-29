package com.ez.portal.core.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "loginResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginResponse extends AbstractResponse {

    private static final long serialVersionUID = 1L;
    
    private String authenticationToken;

    public LoginResponse() {
        super();
    }
    
    public LoginResponse(String message, Boolean status) {
        super();
        setMessage(message);
        setStatus(status);
    }
    
    @Override
    public void resetResponse() {
        super.resetResponse();
        this.authenticationToken = null;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

}
