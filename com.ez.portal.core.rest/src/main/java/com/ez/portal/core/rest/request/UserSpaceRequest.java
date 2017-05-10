package com.ez.portal.core.rest.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.util.rest.request.AbstractRequest;
import com.ez.portal.shard.entity.UserSpace;

@XmlRootElement(name = "userSpaceRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSpaceRequest extends AbstractRequest {

    private static final long serialVersionUID = 1L;
    
    private UserSpace userSpace;

    public UserSpace getUserSpace() {
        return userSpace;
    }

    public void setUserSpace(UserSpace userSpace) {
        this.userSpace = userSpace;
    }

}
