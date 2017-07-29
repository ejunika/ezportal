package com.ez.portal.shard.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.shard.entity.UserSpace;

@XmlRootElement(name = "shardResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShardResponse {

    private List<UserSpace> userSpaces;

    public List<UserSpace> getUserSpaces() {
        return userSpaces;
    }

    public void setUserSpaces(List<UserSpace> userSpaces) {
        this.userSpaces = userSpaces;
    }
    
}
