package com.ez.portal.core.dao.manager;

import java.security.MessageDigest;

import com.ez.portal.core.dao.intf.PasswordDAO;
import com.ez.portal.core.dao.intf.UserDAO;
import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.util.PortalUtils;

public class UserDAOManager {

    private UserDAO userDAO;

    private PasswordDAO passwordDAO;

    private PortalSessionDAOManager portalSessionDAOManager;

    private LoginResponse loginResponse;

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
        // Boolean authorised = false;
        // User user = null;
        // String authenticationToken = null;
        // try {
        // user = loginDAO.getSuperUserByEmailId(emailId);
        // if (user != null) {
        // authorised = getUserAuthentication().authenticateSuperUser(user,
        // password);
        // if (authorised) {
        // authenticationToken =
        // getUserAuthentication().createSuperUserSession(user, password);
        // getLoginResponse().setAuthenticationToken(authenticationToken);
        // getLoginResponse().setMessage("Logged in successful");
        // getLoginResponse().setStatus(true);
        // }
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // getLoginResponse().setMessage("Logged in un-successful");
        // getLoginResponse().setStatus(false);
        // }
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
