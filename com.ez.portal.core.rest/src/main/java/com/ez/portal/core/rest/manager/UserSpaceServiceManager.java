package com.ez.portal.core.rest.manager;

import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.entity.DBServer;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserSpace;
import com.ez.portal.core.request.UserSpaceRequest;
import com.ez.portal.core.response.UserSpaceResponse;

/**
 * @author azaz.akhtar
 *
 */
public class UserSpaceServiceManager extends AbstractServiceManager {

	private UserSpaceResponse userSpaceResponse;

	/**
	 * @return the userSpaceResponse
	 */
	public UserSpaceResponse getUserSpaceResponse() {
		return userSpaceResponse;
	}

	/**
	 * @param userSpaceResponse
	 *            the userSpaceResponse to set
	 */
	public void setUserSpaceResponse(UserSpaceResponse userSpaceResponse) {
		this.userSpaceResponse = userSpaceResponse;
	}

	/**
	 * @param portalUserSpaceRequest
	 * @return
	 */
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

	/**
	 * @return
	 */
	public UserSpaceResponse getUserSpaces() {
		userSpaceResponse.resetResponse();
		List<UserSpace> userSpaces = null;
		try {
			userSpaces = daoManager.getUserSpaceDAO().getAll();
			if (userSpaces != null) {
				for (UserSpace userSpace : userSpaces) {
					userSpace.setHibernateProperties(null);
				}
				userSpaceResponse.setUserSpaces(userSpaces);
				userSpaceResponse.setMessage(userSpaces.size() + " userSpaces found");
				userSpaceResponse.setStatus(true);
			} else {
				
			}
		} catch (Exception e) {
			userSpaceResponse.setMessage(e.getMessage());
			userSpaceResponse.setStatus(false);
		}
		return userSpaceResponse;
	}

	/**
	 * @param portalUserSpaceRequest
	 * @return
	 */
	public UserSpaceResponse createUserSpace(UserSpaceRequest portalUserSpaceRequest) {
		UserSpace userSpace = portalUserSpaceRequest.getUserSpace();
		DBServer dbServer = userSpace.getDbServer();
		userSpaceResponse.resetResponse();
		List<UserSpace> userSpaces = null;
		try {
			userSpace = daoManager.getUserSpaceDAO().createUserSpace(userSpace, dbServer);
			if (userSpace != null) {
				userSpaces = new ArrayList<>();
				userSpaces.add(userSpace);
				userSpaceResponse.setUserSpaces(userSpaces);
				userSpaceResponse.setMessage("User Space saved successfully");
				userSpaceResponse.setStatus(true);
			}
		} catch (Exception e) {
			userSpaceResponse.setMessage(e.getMessage());
			userSpaceResponse.setStatus(false);
		}
		return userSpaceResponse;
	}

}
