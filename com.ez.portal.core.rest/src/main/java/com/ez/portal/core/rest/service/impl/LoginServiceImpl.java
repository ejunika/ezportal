package com.ez.portal.core.rest.service.impl;

import com.ez.portal.core.dao.manager.LoginManager;
import com.ez.portal.core.rest.request.LoginRequest;
import com.ez.portal.core.rest.request.UserRequest;
import com.ez.portal.core.rest.response.LoginResponse;
import com.ez.portal.core.rest.response.UserResponse;
import com.ez.portal.core.rest.service.intf.LoginService;

public class LoginServiceImpl implements LoginService {
    
    private LoginManager loginManager;

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public void setLoginManager(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Override
    public UserResponse signUp(UserRequest userRequest) {
        getLoginManager().signUp(userRequest.getUser());
        return null;
    }

    @Override
    public UserResponse getUser(Long userId) {
        getLoginManager().getUser(userId);
        return null;
    }

    @Override
    public LoginResponse doLogin(LoginRequest loginRequest) {
        getLoginManager().doLogin(loginRequest.getEmailId(), loginRequest.getPassword());
        return null;
    }

}
