//package com.ez.portal.core.dao.manager;
//
//import java.security.MessageDigest;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ez.portal.core.dao.intf.PasswordDAO;
//import com.ez.portal.core.dao.intf.UserDAO;
//import com.ez.portal.core.entity.Password;
//import com.ez.portal.core.entity.User;
//import com.ez.portal.core.request.LoginRequest;
//import com.ez.portal.core.request.SignUpRequest;
//import com.ez.portal.core.response.LoginResponse;
//import com.ez.portal.core.response.UserResponse;
//import com.ez.portal.core.util.PortalUtils;
//
///**
// * @author azaz.akhtar
// *
// */
//public class UserDAOManager {
//
//    
//    private LoginResponse loginResponse;
//
//    
//
//    /**
//     * @param password
//     * @return
//     */
//    public String getPasswordHash(String password) {
//        MessageDigest md = null;
//        StringBuffer hexString = null;
//        if (password != null) {
//            try {
//                md = MessageDigest.getInstance("MD5");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            byte[] defaultBytes = password.getBytes();
//            md.reset();
//            md.update(defaultBytes);
//            byte messageDigest[] = md.digest();
//            hexString = new StringBuffer();
//            for (int i = 0; i < messageDigest.length; i++) {
//                String hex = Integer.toHexString(0xFF & messageDigest[i]);
//                if (hex.length() == 1) {
//                    hexString.append('0');
//                }
//                hexString.append(hex);
//            }
//        }
//        return hexString.toString();
//    }
//
//    /**
//     * @param user
//     * @param password
//     * @return
//     */
//    public Boolean authenticateUser(User user, String password) {
//        Boolean authority = false;
//        Password activePassword = null;
//        try {
//            activePassword = passwordDAO.getActivePasswordByUser(user);
//            if (activePassword != null && getPasswordHash(password).equals(activePassword.getPasswordHash())) {
//                authority = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return authority;
//    }
//    
//    /**
//     * @param user
//     * @param password
//     * @return
//     */
//    public Boolean authenticateSuperUser(User user, String password) {
//        Boolean authority = false;
//        Password activePassword = null;
//        try {
//            activePassword = passwordDAO.getActivePasswordBySuperUser(user);
//            if (activePassword != null && getPasswordHash(password).equals(activePassword.getPasswordHash())) {
//                authority = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return authority;
//    }
//
//    /**
//     * @param authenticationToken
//     * @return
//     */
//    public UserResponse getUserByAuthenticationToken(String authenticationToken) {
//        UserResponse response = new UserResponse();
//        User user = null;
//        List<User> users = new ArrayList<>();
//        try {
//            user = userDAO.getUserByAuthenticationToken(authenticationToken);
//            if (user != null) {
//                users.add(user);
//                response.setUsers(users);
//                response.setMessage("User found");
//                response.setStatus(true);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.setMessage("Error :" + e.getMessage());
//            response.setStatus(false);
//        }
//        return response;
//    }
//
//
//    /**
//     * @param signUpRequest
//     * @return
//     */
//    public UserResponse signUp(SignUpRequest signUpRequest) {
//        User createdBy = null, newUser;
//        Password password = null;
//        try {
//            createdBy = userDAO.get(signUpRequest.getCreatedBy());
//            newUser = new User();
//            newUser.setCreatedBy(createdBy);
//            newUser.setUpdatedBy(createdBy);
//            newUser.setUserType(signUpRequest.getUserType());
//            newUser.setEmailId(signUpRequest.getEmailId());
//            newUser.setUsername(signUpRequest.getUsername());
//            password = new Password();
//            password.setPasswordHash(PortalUtils.getPasswordHash(signUpRequest.getPassword()));
//            password.setCreatedBy(createdBy);
//            password.setUpdatedBy(createdBy);
//            newUser = userDAO.createUser(newUser, password);
//            if (newUser != null) {
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new UserResponse();
//    }
//
//    /**
//     * @param request
//     * @return
//     */
//    public LoginResponse doLogin(LoginRequest request) {
//        loginResponse.resetResponse();
//        if (request != null) {
//            if (request.getUserType() == PortalUtils.SUPER_USER) {
//                loginResponse = doSuLogin(request.getEmailId(), request.getPassword());
//            } else {
//                loginResponse = doLogin(request.getEmailId(), request.getPassword(), request.getShardKey());
//            }
//        }
//        return loginResponse;
//    }
//
//    /**
//     * @param portalSessionToken
//     * @return
//     */
//    public LoginResponse doLogout(String portalSessionToken) {
//        loginResponse.resetResponse();
//        if (portalSessionDAOManager.makeSessionOut(portalSessionToken)) {
//            loginResponse.setAuthenticationToken(null);
//            loginResponse.setMessage("Logout successfully!");
//            loginResponse.setStatus(true);
//        } else {
//            loginResponse.setAuthenticationToken(portalSessionToken);
//            loginResponse.setMessage("Error");
//            loginResponse.setStatus(false);
//        }
//        return loginResponse;
//    }
//
//    /**
//     * @param emailId
//     * @param password
//     * @return
//     */
//    public LoginResponse doSuLogin(String emailId, String password) {
//        Boolean authority = false;
//        User user = null;
//        String authenticationToken = null;
//        try {
//            user = userDAO.getSuperUserByEmailId(emailId);
//            if (user != null) {
//                authority = authenticateSuperUser(user, password);
//                if (authority) {
//                    authenticationToken = portalSessionDAOManager.createSuperUserSession(user);
//                    loginResponse.setAuthenticationToken(authenticationToken);
//                    loginResponse.setMessage("Logged in successful");
//                    loginResponse.setStatus(true);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            getLoginResponse().setMessage("Logged in un-successful");
//            getLoginResponse().setStatus(false);
//        }
//        return loginResponse;
//    }
//
//    /**
//     * @param emailId
//     * @param password
//     * @param shardKey
//     * @return
//     */
//    public LoginResponse doLogin(String emailId, String password, String shardKey) {
//        loginResponse.resetResponse();
//        Boolean authority = false;
//        User user = null;
//        String authenticationToken = null;
//        try {
//            user = userDAO.getUserByEmailId(emailId, shardKey);
//            if (user != null) {
//                authority = authenticateUser(user, password);
//                if (authority) {
//                    authenticationToken = portalSessionDAOManager.createSession(user);
//                    loginResponse.setAuthenticationToken(authenticationToken);
//                    loginResponse.setUser(user);
//                    loginResponse.setMessage("Logged in successful");
//                    loginResponse.setStatus(true);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            loginResponse.setMessage("Logged in un-successful");
//            loginResponse.setStatus(false);
//        }
//        return loginResponse;
//    }
//
//}
