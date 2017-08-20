package com.ez.portal.core.request;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author azaz.akhtar
 *
 */
@XmlRootElement(name = "loginRequest")
public class LoginRequest extends AbstractRequest {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String emailId;
    
    /**
     * 
     */
    private String password;
    
    /**
     * 
     */
    private Byte userType;
    
    /**
     * @return
     */
    public String getEmailId() {
        return emailId;
    }
    
    /**
     * @param emailId
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    /**
     * @return
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     */
    public Byte getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(Byte userType) {
        this.userType = userType;
    }
    
}
