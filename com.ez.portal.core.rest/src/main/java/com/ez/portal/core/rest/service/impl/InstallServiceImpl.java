package com.ez.portal.core.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.dao.manager.UserSpaceManager;
import com.ez.portal.core.rest.request.UserSpaceRequest;
import com.ez.portal.core.rest.response.UserSpaceResponse;
import com.ez.portal.core.rest.service.intf.InstallService;
import com.ez.portal.shard.entity.UserSpace;

public class InstallServiceImpl implements InstallService {

    private UserSpaceManager userSpaceManager;

    public UserSpaceManager getUserSpaceManager() {
        return userSpaceManager;
    }

    public void setUserSpaceManager(UserSpaceManager userSpaceManager) {
        this.userSpaceManager = userSpaceManager;
    }
    
    @Override
    public UserSpaceResponse createUserSpace(UserSpaceRequest userSpaceRequest) {
        UserSpace userSpace = userSpaceManager.createUserSpace(userSpaceRequest.getUserSpace());
        UserSpaceResponse userSpaceResponse = new UserSpaceResponse();
        List<UserSpace> userSpaces = new ArrayList<>();
        userSpaces.add(userSpace);
        userSpaceResponse.setUserSpaces(userSpaces);
        return userSpaceResponse;
    }

}
