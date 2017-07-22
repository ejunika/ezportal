package com.ez.portal.core.rest.service.impl;

import com.ez.portal.core.dao.manager.UserSpaceManager;
import com.ez.portal.core.rest.service.intf.InstallService;

public class InstallServiceImpl implements InstallService {

    private UserSpaceManager userSpaceManager;

    public UserSpaceManager getUserSpaceManager() {
        return userSpaceManager;
    }

    public void setUserSpaceManager(UserSpaceManager userSpaceManager) {
        this.userSpaceManager = userSpaceManager;
    }
}
