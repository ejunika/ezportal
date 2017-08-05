package com.ez.portal.core.dao.manager;

import com.ez.portal.core.dao.intf.UserInfoDAO;

public class UserInfoDAOManager {

    private UserInfoDAO userInfoDAO;

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public void setUserInfoDAO(UserInfoDAO userInfoDAO) {
        this.userInfoDAO = userInfoDAO;
    }
}
