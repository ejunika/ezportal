package com.ez.portal.core.rest.service.impl;

import com.ez.portal.core.dao.intf.LoginDAO;
import com.ez.portal.core.dao.manager.LoginManager;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.request.SignUpRequest;
import com.ez.portal.core.request.UserRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.response.UserResponse;
import com.ez.portal.core.rest.service.intf.LoginService;
import com.ez.portal.shard.request.ShardRequest;
import com.ez.portal.shard.response.ShardResponse;

public class LoginServiceImpl implements LoginService {
    
    private LoginManager loginManager;

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public void setLoginManager(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Override
    public UserResponse signUp(SignUpRequest signUpRequest) {
        return loginManager.signUp(signUpRequest);
    }

    @Override
    public UserResponse getUser(Long userId) {
        return getLoginManager().getUser(userId);
    }

    @Override
    public LoginResponse doLogin(LoginRequest loginRequest) {
        return getLoginManager().doLogin(loginRequest);
    }

    @Override
    public UserResponse getAllUser() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ShardResponse getAllUserSpaces(ShardRequest request) throws Exception {
        return getLoginManager().getAllUserSpaces(request);
    }

    @Override
    public UserResponse getUserByAuthenticationToken(String authenticationToken) throws Exception {
        return getLoginManager().getUserByAuthenticationToken(authenticationToken);
    }

    @Override
    public LoginResponse logout(String authenticationToken) throws Exception {
        return getLoginManager().logout(authenticationToken);
    }

}
