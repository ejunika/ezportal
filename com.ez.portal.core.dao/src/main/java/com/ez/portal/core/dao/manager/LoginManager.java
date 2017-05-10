package com.ez.portal.core.dao.manager;

import com.ez.portal.core.dao.intf.LoginDAO;
import com.ez.portal.core.entity.Login;
import com.ez.portal.core.entity.User;

public class LoginManager {

    private LoginDAO loginDAO;

    public LoginDAO getLoginDAO() {
        return loginDAO;
    }

    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public User signUp(User user) {
        return null;
    }
    
    public User getUser(Long userId) {
        return null;
    }
    
    public Login doLogin(String emailId, String password) {
        User user = loginDAO.getUserInfoByEmailId(emailId);
        System.out.println(user.getPassword());
        return null;
    }
    
}
