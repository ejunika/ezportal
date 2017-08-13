package com.ez.portal.core.dao.manager;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.dao.intf.PasswordDAO;
import com.ez.portal.core.dao.intf.UserDAO;
import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.request.SignUpRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.response.UserResponse;
import com.ez.portal.core.util.PortalUtils;
import com.ez.portal.shard.dao.intf.ShardDAO;
import com.ez.portal.shard.entity.UserSpace;
import com.ez.portal.shard.response.ShardResponse;

public class UserDAOManager {

    private UserDAO userDAO;

    private ShardDAO shardDAO;

    private PasswordDAO passwordDAO;

    private PortalSessionDAOManager portalSessionDAOManager;

    private LoginResponse loginResponse;

    private ShardResponse shardResponse;

    public ShardDAO getShardDAO() {
        return shardDAO;
    }

    public void setShardDAO(ShardDAO shardDAO) {
        this.shardDAO = shardDAO;
    }

    public ShardResponse getShardResponse() {
        return shardResponse;
    }

    public void setShardResponse(ShardResponse shardResponse) {
        this.shardResponse = shardResponse;
    }

    public PasswordDAO getPasswordDAO() {
        return passwordDAO;
    }

    public void setPasswordDAO(PasswordDAO passwordDAO) {
        this.passwordDAO = passwordDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public LoginResponse getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
    }

    public PortalSessionDAOManager getPortalSessionDAOManager() {
        return portalSessionDAOManager;
    }

    public void setPortalSessionDAOManager(PortalSessionDAOManager portalSessionDAOManager) {
        this.portalSessionDAOManager = portalSessionDAOManager;
    }

    public String getPasswordHash(String password) {
        MessageDigest md = null;
        StringBuffer hexString = null;
        if (password != null) {
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (Exception e) {
                e.printStackTrace();
            }
            byte[] defaultBytes = password.getBytes();
            md.reset();
            md.update(defaultBytes);
            byte messageDigest[] = md.digest();
            hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
        }
        return hexString.toString();
    }

    public Boolean authenticateUser(User user, String password) {
        Boolean authority = false;
        Password activePassword = null;
        try {
            activePassword = passwordDAO.getActivePasswordByUser(user);
            if (activePassword != null && getPasswordHash(password).equals(activePassword.getPasswordHash())) {
                authority = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authority;
    }
    
    public Boolean authenticateSuperUser(User user, String password) {
        Boolean authority = false;
        Password activePassword = null;
        try {
            activePassword = passwordDAO.getActivePasswordBySuperUser(user);
            if (activePassword != null && getPasswordHash(password).equals(activePassword.getPasswordHash())) {
                authority = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authority;
    }

    public UserResponse getUserByAuthenticationToken(String authenticationToken) {
        UserResponse response = new UserResponse();
        User user = null;
        List<User> users = new ArrayList<>();
        try {
            user = userDAO.getUserByAuthenticationToken(authenticationToken);
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

    public ShardResponse getAllUserSpacesByEmailId(String emailId) {
        List<User> users = null;
        List<UserSpace> userSpaces = null;
        UserSpace userSpace = null;
        try {
            users = userDAO.getUsersByEmailIdInAllUserSpaces(emailId);
            if (users != null) {
                userSpaces = new ArrayList<>();
                for (User user : users) {
                    userSpace = shardDAO.getUserSpace(user.getShardKey());
                    userSpace.setHibernateProperties(null);
                    userSpaces.add(userSpace);
                }
                shardResponse.setUserSpaces(userSpaces);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shardResponse;
    }

    public UserResponse signUp(SignUpRequest signUpRequest) {
        User createdBy = null, newUser;
        Password password = null;
        try {
            createdBy = userDAO.get(signUpRequest.getCreatedBy());
            newUser = new User();
            newUser.setCreatedBy(createdBy);
            newUser.setUpdatedBy(createdBy);
            newUser.setUserType(signUpRequest.getUserType());
            newUser.setEmailId(signUpRequest.getEmailId());
            newUser.setUsername(signUpRequest.getUsername());
            password = new Password();
            password.setPasswordHash(PortalUtils.getPasswordHash(signUpRequest.getPassword()));
            password.setCreatedBy(createdBy);
            password.setUpdatedBy(createdBy);
            newUser = userDAO.createUser(newUser, password);
            if (newUser != null) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new UserResponse();
    }

    public LoginResponse doLogin(LoginRequest request) {
        loginResponse.resetResponse();
        if (request != null) {
            if (request.getUserType() == PortalUtils.SUPER_USER) {
                loginResponse = doSuLogin(request.getEmailId(), request.getPassword());
            } else {
                loginResponse = doLogin(request.getEmailId(), request.getPassword());
            }
        }
        return loginResponse;
    }

    public LoginResponse doLogout(String portalSessionToken) {
        loginResponse.resetResponse();
        if (portalSessionDAOManager.makeSessionOut(portalSessionToken)) {
            loginResponse.setAuthenticationToken(null);
            loginResponse.setMessage("Logout successfully!");
            loginResponse.setStatus(true);
        } else {
            loginResponse.setAuthenticationToken(portalSessionToken);
            loginResponse.setMessage("Error");
            loginResponse.setStatus(false);
        }
        return loginResponse;
    }

    public LoginResponse doSuLogin(String emailId, String password) {
        Boolean authority = false;
        User user = null;
        String authenticationToken = null;
        try {
            user = userDAO.getSuperUserByEmailId(emailId);
            if (user != null) {
                authority = authenticateSuperUser(user, password);
                if (authority) {
                    authenticationToken = portalSessionDAOManager.createSuperUserSession(user);
                    loginResponse.setAuthenticationToken(authenticationToken);
                    loginResponse.setMessage("Logged in successful");
                    loginResponse.setStatus(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            getLoginResponse().setMessage("Logged in un-successful");
            getLoginResponse().setStatus(false);
        }
        return loginResponse;
    }

    public LoginResponse doLogin(String emailId, String password) {
        loginResponse.resetResponse();
        Boolean authority = false;
        User user = null;
        String authenticationToken = null;
        try {
            user = userDAO.getUserByEmailId(emailId);
            if (user != null) {
                authority = authenticateUser(user, password);
                if (authority) {
                    authenticationToken = portalSessionDAOManager.createSession(user);
                    loginResponse.setAuthenticationToken(authenticationToken);
                    loginResponse.setMessage("Logged in successful");
                    loginResponse.setStatus(true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            loginResponse.setMessage("Logged in un-successful");
            loginResponse.setStatus(false);
        }
        return loginResponse;
    }

}
