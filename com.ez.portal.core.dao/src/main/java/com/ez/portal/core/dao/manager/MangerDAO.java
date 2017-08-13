package com.ez.portal.core.dao.manager;

import com.ez.portal.core.dao.intf.UserDAO;

public class MangerDAO {
        
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
}
