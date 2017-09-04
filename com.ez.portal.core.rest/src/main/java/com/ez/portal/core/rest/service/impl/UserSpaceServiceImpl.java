package com.ez.portal.core.rest.service.impl;

import com.ez.portal.core.request.UserSpaceRequest;
import com.ez.portal.core.response.UserSpaceResponse;
import com.ez.portal.core.rest.manager.UserSpaceServiceManager;
import com.ez.portal.core.rest.service.intf.UserSpaceService;

/**
 * @author azaz.akhtar
 *
 */
public class UserSpaceServiceImpl implements UserSpaceService {

	/**
	 * 
	 */
	private UserSpaceServiceManager userSpaceServiceManager;
	
	/**
	 * @return the userSpaceServiceManager
	 */
	public UserSpaceServiceManager getUserSpaceServiceManager() {
		return userSpaceServiceManager;
	}

	/**
	 * @param userSpaceServiceManager the userSpaceServiceManager to set
	 */
	public void setUserSpaceServiceManager(UserSpaceServiceManager userSpaceServiceManager) {
		this.userSpaceServiceManager = userSpaceServiceManager;
	}

	@Override
	public UserSpaceResponse getAllUserSpaces(UserSpaceRequest portalUserSpaceRequest) throws Exception {
		return userSpaceServiceManager.getAllUserSpaces(portalUserSpaceRequest);
	}

	@Override
	public UserSpaceResponse getUserSpaces() throws Exception {
		return userSpaceServiceManager.getUserSpaces();
	}

	@Override
	public UserSpaceResponse createUserSpace(UserSpaceRequest portalUserSpaceRequest) throws Exception {
		return userSpaceServiceManager.createUserSpace(portalUserSpaceRequest);
	}

}
