package com.ez.portal.core.rest.manager;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserSpace;
import com.ez.portal.core.request.LoginRequest;
import com.ez.portal.core.request.UserSpaceRequest;
import com.ez.portal.core.response.LoginResponse;
import com.ez.portal.core.response.UserSpaceResponse;
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
	private UserSpaceResponse userSpaceResponse;
	
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

	public UserSpaceResponse getUserSpaceResponse() {
		return userSpaceResponse;
	}
	
	public void setUserSpaceResponse(UserSpaceResponse userSpaceResponse) {
		this.userSpaceResponse = userSpaceResponse;
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
                    loginResponse.setMessage("Logged in successful");
                    loginResponse.setStatus(true);
                    loginResponse.setPortalResponseCode(200);
                } else {
                	loginResponse.setMessage("Invalid Email/Password");
                    loginResponse.setStatus(true);
                    loginResponse.setPortalResponseCode(500);
                }
                loginResponse.setUser(user);
            } else {
            	loginResponse.setMessage("Not authorized to login");
                loginResponse.setStatus(true);
                loginResponse.setPortalResponseCode(401);
            }
        } catch (Exception e) {
            e.printStackTrace();
            loginResponse.setMessage(e.getMessage());
            loginResponse.setStatus(false);
            loginResponse.setPortalResponseCode(500);
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

	public UserSpaceResponse getAllUserSpaces(UserSpaceRequest userSpaceRequest) {
		userSpaceResponse.resetResponse();
		String emailId = userSpaceRequest.getEmailId();
		List<User> users = null;
		List<UserSpace> userSpaces = null;
		String shardKey = null;
		if (emailId != null) {
			try {
				users = getDaoManager().getUserDAO().getUsersByEmailIdInAllUserSpaces(emailId);
				if (users != null) {
					Long userSpaceId = 0l;
					userSpaces = new ArrayList<>();
					UserSpace userSpace = null;
					for (User user : users) {
						shardKey = user.getShardKey();
						try {
							userSpaceId = Long.parseLong(shardKey);
							userSpace = getDaoManager().getUserSpaceDAO().get(userSpaceId);
							userSpace.setHibernateProperties(null);
							if (userSpace != null) {
								userSpaces.add(userSpace);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					userSpaceResponse.setUserSpaces(userSpaces);
					userSpaceResponse.setMessage(userSpaces.size() + " userSpaces found");
					userSpaceResponse.setStatus(true);
				}
			} catch (Exception e) {
				userSpaceResponse.setMessage(e.getMessage());
				userSpaceResponse.setStatus(false);
			}
		}
		return userSpaceResponse;
	}

}
