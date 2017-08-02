package com.ez.portal.core.security;

import java.security.MessageDigest;
import java.util.Date;
import java.util.UUID;

import com.ez.portal.core.dao.intf.LoginDAO;
import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.status.PortalSessionStatus;

public class UserAuthentication {

    private LoginDAO loginDAO;

    private SessionController sessionController;
    
    private Thread sessionControllerThread;

    public LoginDAO getLoginDAO() {
        return loginDAO;
    }

    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public Boolean authenticateUser(User user, String password) {
        Boolean authorised = false;
        Password activePassword = null;
        if (user != null) {
            try {
                activePassword = loginDAO.getActivePassword(user);
                if (activePassword != null && getPasswordHash(password).equals(activePassword.getPasswordHash())) {
                    authorised = true;
                } else {
                    authorised = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return authorised;
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

    public SessionController getSessionController() {
        return sessionController;
    }

    public Thread getSessionControllerThread() {
        return sessionControllerThread;
    }

    public void setSessionControllerThread(Thread sessionControllerThread) {
        this.sessionControllerThread = sessionControllerThread;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public String generateToken(String emailId, String password) {
        // String token = UUID.randomUUID().toString().toUpperCase() + "|" +
        // emailId + "|" + password;
        // StandardPBEStringEncryptor encryptor = new
        // StandardPBEStringEncryptor();
        // encryptor.setPassword("AuthenticationToken");
        // token = encryptor.encrypt(token);
        String token = UUID.randomUUID().toString().toUpperCase();
        return token;
    }

    public static void main(String[] args) {
        System.out.println(new UserAuthentication().getPasswordHash("admin"));
    }

    public String createSession(User user, String password) {
        String authenticationToken = generateToken(user.getEmailId(), password);
        final PortalSession portalSession = new PortalSession();
        portalSession.setCreatedAt(new Date());
        portalSession.setUpdatedAt(new Date());
        portalSession.setPortalSessionStatus(PortalSessionStatus.ACTIVE_SESSION);
        portalSession.setPortalSessionToken(authenticationToken);
        portalSession.setCreatedBy(user);
        portalSession.setUpdatedBy(user);
        portalSession.setShardKey(user.getShardKey());
        try {
            getLoginDAO().createSession(portalSession);
//            sessionController.setPortalSessionId(portalSession.getPortalSessionId());
//            sessionControllerThread = new Thread(getSessionController());
//            sessionControllerThread.setName("SESSION_CONTROLLER_THREAD");
//            sessionControllerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationToken;
    }

    public Boolean authenticateSuperUser(User user, String password) {
        Boolean authorised = false;
        Password activePassword = null;
        if (user != null) {
            try {
                activePassword = loginDAO.getActivePasswordForSuperUser(user);
                if (activePassword != null && getPasswordHash(password).equals(activePassword.getPasswordHash())) {
                    authorised = true;
                } else {
                    authorised = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return authorised;
    }

    public String createSuperUserSession(User user, String password) {
        String authenticationToken = generateToken(user.getEmailId(), password);
        PortalSession portalSession = new PortalSession();
        portalSession.setCreatedAt(new Date());
        portalSession.setUpdatedAt(new Date());
        portalSession.setPortalSessionStatus(PortalSessionStatus.ACTIVE_SESSION);
        portalSession.setPortalSessionToken(authenticationToken);
        portalSession.setCreatedBy(user);
        portalSession.setUpdatedBy(user);
        try {
            getLoginDAO().createSuperUserSession(portalSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authenticationToken;
    }
}
