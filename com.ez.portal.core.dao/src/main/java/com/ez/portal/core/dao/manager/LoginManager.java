package com.ez.portal.core.dao.manager;

import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.dao.intf.LoginDAO;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.response.UserResponse;
import com.ez.portal.core.security.UserAuthentication;
import com.ez.portal.shard.dao.intf.ShardDAO;
import com.ez.portal.shard.entity.UserSpace;
import com.ez.portal.shard.request.ShardRequest;
import com.ez.portal.shard.response.ShardResponse;

public class LoginManager {

    private LoginDAO loginDAO;

    private ShardDAO shardDAO;

    private UserAuthentication userAuthentication;
    
    private LoginResponse loginResponse;

    public LoginDAO getLoginDAO() {
        return loginDAO;
    }

    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
    
    public ShardDAO getShardDAO() {
        return shardDAO;
    }

    public void setShardDAO(ShardDAO shardDAO) {
        this.shardDAO = shardDAO;
    }

    public UserAuthentication getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public User signUp(User user) {
        return loginDAO.add(user);
    }

    public UserResponse getUser(Long userId) {
        UserResponse response = new UserResponse();
        List<User> users = new ArrayList<>();
        User user = loginDAO.get(userId);
        users.add(user);
        response.setUsers(users);
        return response;
    }

    public UserResponse getAllUser() {
        UserResponse response = new UserResponse();
        List<User> users = loginDAO.getAll();
        response.setUsers(users);
        return response;
    }

    public LoginResponse doLogin(LoginRequest request) {
        String emailId = request.getEmailId();
        String password = request.getPassword();
        Boolean authorised = false;
        User user = null;
        String authenticationToken = null;
        getLoginResponse().resetResponse();
        try {
            user = loginDAO.getUserInfoByEmailId(emailId);
            authorised = getUserAuthentication().authenticateUser(user, password);
            if (authorised) {
                authenticationToken = getUserAuthentication().createSession(user, password);
                getLoginResponse().setAuthenticationToken(authenticationToken);
                getLoginResponse().setMessage("Logged in successful");
                getLoginResponse().setStatus(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            getLoginResponse().setMessage("Logged in un-successful");
            getLoginResponse().setStatus(false);
        }
        return getLoginResponse();
    }

    public ShardResponse getAllUserSpaces(ShardRequest request) {
        ShardResponse responce = new ShardResponse();
        responce.setUserSpaces(getAllUserSpacesByEmailId(request.getEmailId()));
        return responce;
    }
    
    public UserResponse getUserByAuthenticationToken(String authenticationToken) {
        UserResponse response = new UserResponse();
        User user = null;
        List<User> users = new ArrayList<>();
        try {
            user = loginDAO.getUserByAuthenticationToken(authenticationToken);
            if (user != null) {
                users.add(user);
                response.setUsers(users);
                response.setMessage("User found");
                response.setStatus(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Error :" + e.getMessage());
            response.setStatus(false);
        }
        return response;
    }

    public List<UserSpace> getAllUserSpacesByEmailId(String emailId) {
        List<User> users = null;
        List<UserSpace> userSpaces = null;
        UserSpace userSpace = null;
        try {
            users = loginDAO.getUsersByEmailId(emailId);
            if (users != null) {
                userSpaces = new ArrayList<>();
                for (User user : users) {
                    userSpace = getShardDAO().getUserSpace(Long.parseLong(user.getShardKey()));
                    if (userSpace != null) {
                        userSpace.setHibernateProperties(null);
                        userSpaces.add(userSpace);
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return userSpaces;
    }

    public LoginResponse logout(String authenticationToken) {
        Boolean logoutSuccess = false;
        try {
            logoutSuccess = loginDAO.makePortalSessionInActive(authenticationToken);
            if (logoutSuccess) {
                getLoginResponse().setAuthenticationToken(null);
                getLoginResponse().setMessage("Logout successful");
                getLoginResponse().setStatus(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            getLoginResponse().setMessage("Error :" + e.getMessage());
            getLoginResponse().setStatus(true);
        }
        return getLoginResponse();
    }

}
