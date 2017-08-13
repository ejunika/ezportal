package com.ez.portal.shard.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.shard.entity.UserSpace;

@XmlRootElement(name = "shardRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShardRequest {
    
    private String emailId;
    
    private List<UserSpace> userSpaces;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<UserSpace> getUserSpaces() {
        return userSpaces;
    }

    public void setUserSpaces(List<UserSpace> userSpaces) {
        this.userSpaces = userSpaces;
    }
    
}
