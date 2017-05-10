package com.ez.portal.core.dao.manager;

import com.ez.portal.core.dao.intf.UserSpaceDAO;
import com.ez.portal.shard.entity.UserSpace;

public class UserSpaceManager {
    
    private UserSpaceDAO userSpaceDAO;

    public UserSpaceDAO getUserSpaceDAO() {
        return userSpaceDAO;
    }

    public void setUserSpaceDAO(UserSpaceDAO userSpaceDAO) {
        this.userSpaceDAO = userSpaceDAO;
    }
    
    public UserSpace createUserSpace(UserSpace userSpace) {
        return userSpaceDAO.add(userSpace);
    }
}
