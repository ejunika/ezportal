package com.ez.portal.core.rest.manager;

import java.security.MessageDigest;

import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.util.UserUtil;

/**
 * @author azaz.akhtar
 *
 */
public class LoginServiceManager extends AbstractServiceManager {

	/**
	 * 
	 */
	private LoginResponse loginResponse;
	
	/**
	 * 
	 */
	private PortalSessionServiceManager portalSessionServiceManager;
	
	/**
	 * @return the portalSessionServiceManager
	 */
	public PortalSessionServiceManager getPortalSessionServiceManager() {
		return portalSessionServiceManager;
	}

	/**
	 * @param portalSessionServiceManager the portalSessionServiceManager to set
	 */
	public void setPortalSessionServiceManager(PortalSessionServiceManager portalSessionServiceManager) {
		this.portalSessionServiceManager = portalSessionServiceManager;
	}

	/**
	 * @return
	 */
	public LoginResponse getLoginResponse() {
		return loginResponse;
	}

	/**
	 * @param loginResponse
	 */
	public void setLoginResponse(LoginResponse loginResponse) {
		this.loginResponse = loginResponse;
	}
	
	/**
	 * @param loginRequest
	 * @return
	 */
	public LoginResponse proceedLogin(LoginRequest loginRequest) {
		loginResponse.resetResponse();
		if (loginRequest != null) {
			if (loginRequest.getUserType() == UserUtil.USER_TYPE_SUPER_USER) {
				loginResponse = proceedForSuperUserLogin(loginRequest.getEmailId(), loginRequest.getPassword());
			} else {
				loginResponse = proceedForNormalUserLogin(loginRequest.getEmailId(), loginRequest.getPassword(),
						loginRequest.getShardKey());
			}
		}
		return loginResponse;
	}

	private LoginResponse proceedForNormalUserLogin(String emailId, String password, String shardKey) {
		loginResponse.resetResponse();
        Boolean authority = false;
        User user = null;
        String authenticationToken = null;
        try {
            user = getDaoManager().getUserDAO().getActiveUserByEmailId(emailId, shardKey);
            if (user != null) {
                authority = authenticateUser(user, password);
                if (authority) {
                    authenticationToken = portalSessionServiceManager.createSession(user);
                    loginResponse.setAuthenticationToken(authenticationToken);
                    loginResponse.setUser(user);
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
	
	/**
	 * @param password
	 * @return
	 */
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

	/**
	 * @param user
	 * @param password
	 * @return
	 */
	private Boolean authenticateUser(User user, String password) {
		Boolean isAuthorized = false;
        Password activePassword = null;
        try {
            activePassword = getDaoManager().getPasswordDAO().getActivePasswordByUser(user);
            if (activePassword != null && getPasswordHash(password).equals(activePassword.getPasswordHash())) {
            	isAuthorized = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAuthorized;
	}

	private LoginResponse proceedForSuperUserLogin(String emailId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
