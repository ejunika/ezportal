package com.ez.portal.core.dao.manager;

import com.ez.portal.core.dao.intf.LoginDAO;
import com.ez.portal.core.dao.intf.UserDAO;

public class MangerDAO {
    
    private LoginDAO loginDAO;
    
    private UserDAO userDAO;

    public LoginDAO getLoginDAO() {
        return loginDAO;
    }

    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
}
