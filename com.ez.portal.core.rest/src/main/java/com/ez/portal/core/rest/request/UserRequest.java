package com.ez.portal.core.rest.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.entity.User;
import com.ez.portal.core.util.rest.request.AbstractRequest;

@XmlRootElement (name = "userRequest")
@XmlAccessorType (XmlAccessType.FIELD)
public class UserRequest extends AbstractRequest {
    private static final long serialVersionUID = 1L;
    
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
