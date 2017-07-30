package com.ez.portal.core.request;

import java.util.List;

import com.ez.portal.core.entity.UserInfo;

public class SignUpRequest extends AbstractRequest {
    
    private static final long serialVersionUID = 1L;

    private String emailId;
    
    private String username;
    
    private String password;
    
    private Byte userType;
    
    private Long createdBy;
    
    private List<UserInfo> userInfos;

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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
