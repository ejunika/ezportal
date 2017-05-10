package com.ez.portal.core.rest.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.util.rest.response.AbstractResponse;
import com.ez.portal.shard.entity.UserSpace;

@XmlRootElement(name = "userSpaceResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSpaceResponse extends AbstractResponse {

    private static final long serialVersionUID = 1L;
    
    private List<UserSpace> userSpaces;
    
    public List<UserSpace> getUserSpaces() {
        return userSpaces;
    }

    public void setUserSpaces(List<UserSpace> userSpaces) {
        this.userSpaces = userSpaces;
    }

    @Override
    public void resetResponse() {
        // TODO Auto-generated method stub
    }

}
