package com.ez.portal.core.dao.manager;

import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.dao.intf.LoginDAO;
import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.request.SignUpRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.response.UserResponse;
import com.ez.portal.core.security.UserAuthentication;
import com.ez.portal.core.util.PortalUtils;
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

    public UserResponse signUp(SignUpRequest signUpRequest) {
        User createdBy = null, newUser;
        Password password = null;
        Boolean created = false;
        try {
            createdBy = loginDAO.get(signUpRequest.getCreatedBy());
            newUser = new User();
            newUser.setCreatedBy(createdBy);
            newUser.setUpdatedBy(createdBy);
            newUser.setUserType(signUpRequest.getUserType());
            newUser.setEmailId(signUpRequest.getEmailId());
            newUser.setUsername(signUpRequest.getUsername());
            password = new Password();
            password.setPasswordHash(new UserAuthentication().getPasswordHash(signUpRequest.getPassword()));
            password.setCreatedBy(createdBy);
            password.setUpdatedBy(createdBy);
            created = loginDAO.createUser(newUser, password);
            if (created) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserResponse();
    }

    public UserResponse getUser(Long userId) {
        UserResponse response = new UserResponse();
        List<User> users = new ArrayList<>();
        User user;
        try {
            user = loginDAO.get(userId);
            if (user != null) {
                users.add(user);
                response.setUsers(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public UserResponse getAllUser() {
        UserResponse response = new UserResponse();
        List<User> users;
        try {
            users = loginDAO.getAll();
            response.setUsers(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public LoginResponse doLogin(LoginRequest request) {
        String emailId = request.getEmailId();
        String password = request.getPassword();
        Byte userType = request.getUserType();
        getLoginResponse().resetResponse();
        if (userType == PortalUtils.SUPER_USER) {
            setLoginResponse(doSuLogin(emailId, password));
        } else {
            setLoginResponse(doLogin(emailId, password));
        }
        return getLoginResponse();
    }

    public LoginResponse doLogin(String emailId, String password) {
        Boolean authorised = false;
        User user = null;
        String authenticationToken = null;
        try {
            user = loginDAO.getUserInfoByEmailId(emailId);
            if (user != null) {
                authorised = getUserAuthentication().authenticateUser(user, password);
                if (authorised) {
                    authenticationToken = getUserAuthentication().createSession(user, password);
                    getLoginResponse().setAuthenticationToken(authenticationToken);
                    getLoginResponse().setMessage("Logged in successful");
                    getLoginResponse().setStatus(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            getLoginResponse().setMessage("Logged in un-successful");
            getLoginResponse().setStatus(false);
        }
        return getLoginResponse();
    }

    public LoginResponse doSuLogin(String emailId, String password) {
        Boolean authorised = false;
        User user = null;
        String authenticationToken = null;
        try {
            user = loginDAO.getSuperUserByEmailId(emailId);
            if (user != null) {
                authorised = getUserAuthentication().authenticateSuperUser(user, password);
                if (authorised) {
                    authenticationToken = getUserAuthentication().createSuperUserSession(user, password);
                    getLoginResponse().setAuthenticationToken(authenticationToken);
                    getLoginResponse().setMessage("Logged in successful");
                    getLoginResponse().setStatus(true);
                }
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
