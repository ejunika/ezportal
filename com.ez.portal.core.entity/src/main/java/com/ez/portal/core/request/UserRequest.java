package com.ez.portal.core.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserInfo;

@XmlRootElement (name = "userRequest")
@XmlAccessorType (XmlAccessType.FIELD)
public class UserRequest extends AbstractRequest {
    private static final long serialVersionUID = 1L;
    
    private User user;
    
    private String password;
    
    private List<UserInfo> userInfos;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
