package com.ez.portal.core.dao.manager;

import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.dao.intf.LoginDAO;
import com.ez.portal.core.entity.Login;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.response.UserResponse;

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
    
    public UserResponse getUser(Long userId) {
        UserResponse response = new UserResponse();
        List<User> users = new ArrayList<>();
        User user = loginDAO.getUser(userId);
        users.add(user);
        response.setUsers(users);
        return response;
    }
    
    public UserResponse getAllUser() {
        UserResponse response = new UserResponse();
        List<User> users = loginDAO.getAllUsers();
        response.setUsers(users);
        return response;
    }
    
    public Login doLogin(String emailId, String password) {
        User user = loginDAO.getUserInfoByEmailId(emailId);
        System.out.println(user.getPassword());
        return null;
    }
    
}
