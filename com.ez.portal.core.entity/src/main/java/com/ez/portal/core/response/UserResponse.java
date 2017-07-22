package com.ez.portal.core.response;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.entity.User;

@XmlRootElement (name = "userRequest")
@XmlAccessorType (XmlAccessType.FIELD)
public class UserResponse extends AbstractResponse {
    private static final long serialVersionUID = 1L;

    private List<User> users;
    
    private String message;
    
    private Boolean status;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public void resetResponse() {
        this.setMessage(null);
        this.setStatus(false);
        this.setUsers(null);
    }
    
}
