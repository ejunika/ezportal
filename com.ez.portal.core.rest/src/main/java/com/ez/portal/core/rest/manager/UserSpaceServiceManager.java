package com.ez.portal.core.rest.manager;

import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.dao.manager.DAOManager;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserSpace;
import com.ez.portal.core.request.UserSpaceRequest;
import com.ez.portal.core.response.UserSpaceResponse;

/**
 * @author azaz.akhtar
 *
 */
public class UserSpaceServiceManager {

	/**
	 * 
	 */
	private DAOManager daoManager;
	
	/**
	 * 
	 */
	private UserSpaceResponse userSpaceResponse;
	
	/**
	 * @return the userSpaceResponse
	 */
	public UserSpaceResponse getUserSpaceResponse() {
		return userSpaceResponse;
	}

	/**
	 * @param userSpaceResponse the userSpaceResponse to set
	 */
	public void setUserSpaceResponse(UserSpaceResponse userSpaceResponse) {
		this.userSpaceResponse = userSpaceResponse;
	}

	/**
	 * @return the daoManager
	 */
	public DAOManager getDaoManager() {
		return daoManager;
	}

	/**
	 * @param daoManager the daoManager to set
	 */
	public void setDaoManager(DAOManager daoManager) {
		this.daoManager = daoManager;
	}

	/**
	 * @param portalUserSpaceRequest
	 * @return
	 */
	public UserSpaceResponse getAllUserSpaces(UserSpaceRequest portalUserSpaceRequest) {
		userSpaceResponse.resetResponse();
		String emailId = portalUserSpaceRequest.getEmailId();
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
