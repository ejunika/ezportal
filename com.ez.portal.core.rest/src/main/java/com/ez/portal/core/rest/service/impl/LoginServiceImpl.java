package com.ez.portal.core.rest.service.impl;

import com.ez.portal.core.dao.manager.CoreDAOManager;
import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.request.SignUpRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.response.UserResponse;
import com.ez.portal.core.rest.service.intf.LoginService;
import com.ez.portal.shard.request.ShardRequest;
import com.ez.portal.shard.response.ShardResponse;

public class LoginServiceImpl implements LoginService {

    private CoreDAOManager coreDAOManager;

    public CoreDAOManager getCoreDAOManager() {
        return coreDAOManager;
    }

    public void setCoreDAOManager(CoreDAOManager coreDAOManager) {
        this.coreDAOManager = coreDAOManager;
    }

    @Override
    public UserResponse signUp(SignUpRequest signUpRequest) {
        return coreDAOManager.getUserDAOManager().signUp(signUpRequest);
    }

    @Override
    public LoginResponse doLogin(LoginRequest loginRequest) {
        return coreDAOManager.getUserDAOManager().doLogin(loginRequest);
    }

    @Override
    public ShardResponse getAllUserSpaces(ShardRequest request) throws Exception {
        return coreDAOManager.getUserDAOManager().getAllUserSpacesByEmailId(request.getEmailId());
    }

    @Override
    public UserResponse getUserByAuthenticationToken(String authenticationToken) throws Exception {
        return coreDAOManager.getUserDAOManager().getUserByAuthenticationToken(authenticationToken);
    }

    @Override
    public LoginResponse logout(String authenticationToken) throws Exception {
        return coreDAOManager.getUserDAOManager().doLogout(authenticationToken);
    }

}
