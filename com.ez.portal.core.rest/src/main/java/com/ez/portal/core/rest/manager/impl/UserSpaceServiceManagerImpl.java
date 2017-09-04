package com.ez.portal.core.rest.manager.impl;

import java.util.ArrayList;
import java.util.List;

import com.ez.portal.core.dao.manager.intf.DAOManager;
import com.ez.portal.core.entity.UserSpace;
import com.ez.portal.core.request.UserSpaceRequest;
import com.ez.portal.core.response.UserSpaceResponse;
import com.ez.portal.core.rest.manager.intf.UserSpaceServiceManager;

/**
 * @author azaz.akhtar
 *
 */
public class UserSpaceServiceManagerImpl implements UserSpaceServiceManager {

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
	 * @param userSpaceResponse
	 *            the userSpaceResponse to set
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
	 * @param daoManager
	 *            the daoManager to set
	 */
	public void setDaoManager(DAOManager daoManager) {
		this.daoManager = daoManager;
	}

	@Override
	public UserSpaceResponse createUserSpace(UserSpaceRequest userSpaceRequest) {
		List<UserSpace> userSpaces = null;
		UserSpace userSpace = userSpaceRequest.getUserSpace();
		String shardKey = generateShardKey();
		userSpaceResponse.resetResponse();
		if (shardKey != null) {
			userSpace.setShardKey(shardKey);
			try {
				userSpace = daoManager.getUserSpaceDAO().add(userSpace);
				if (userSpace != null) {
					userSpaces = new ArrayList<>();
					userSpaces.add(userSpace);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			
		}
		return userSpaceResponse;
	}

	public String generateShardKey() {
		List<UserSpace> userSpaces = null;
		UserSpace userSpace = null;
		String lastShardKeyUsed = null;
		String newShardKey = null;
		Integer lastShardKeyIntValue = 1000;
		try {
			userSpaces = daoManager.getUserSpaceDAO().getAllUserSpaces();
			Integer newShardKeyIntValue = 1;
			if (userSpaces != null && !userSpaces.isEmpty()) {
				userSpace = userSpaces.get(0);
				if (userSpace != null) {
					lastShardKeyUsed = userSpace.getShardKey();
					lastShardKeyIntValue = Integer.parseInt(lastShardKeyUsed);
					newShardKey = newShardKeyIntValue.toString();
				}
			}
			newShardKeyIntValue += lastShardKeyIntValue;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newShardKey;
	}

}
