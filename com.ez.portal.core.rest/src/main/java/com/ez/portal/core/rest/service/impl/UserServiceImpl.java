package com.ez.portal.core.rest.service.impl;

import java.util.List;

import com.ez.portal.core.request.UserRequest;
import com.ez.portal.core.response.UserResponse;
import com.ez.portal.core.rest.manager.intf.UserServiceManager;
import com.ez.portal.core.rest.service.intf.UserService;

/**
 * @author azaz.akhtar
 *
 */
public class UserServiceImpl implements UserService {

	private UserServiceManager userServiceManager;
	
	/**
	 * @return the userServiceManager
	 */
	public UserServiceManager getUserServiceManager() {
		return userServiceManager;
	}

	/**
	 * @param userServiceManager the userServiceManager to set
	 */
	public void setUserServiceManager(UserServiceManager userServiceManager) {
		this.userServiceManager = userServiceManager;
	}

	@Override
	public UserResponse getAllUsers(List<String> entryStatusList) throws Exception {
//		return userServiceManager.getAllusers();
		return userServiceManager.getAllUsers(entryStatusList);
	}

	@Override
	public UserResponse removeUser(Long userId) throws Exception {
		return userServiceManager.removeUser(userId);
	}

	@Override
	public UserResponse addUser(UserRequest userRequest) throws Exception {
		return userServiceManager.addUser(userRequest);
	}

	@Override
	public UserResponse updateUser(UserRequest userRequest) throws Exception {
		return userServiceManager.updateUser(userRequest);
	}

	@Override
	public UserResponse blockUser(Long userId) throws Exception {
		return userServiceManager.blockUser(userId);
	}

	@Override
	public UserResponse unblockUser(Long userId) throws Exception {
		return userServiceManager.unblockUser(userId);
	}

	@Override
	public UserResponse activateUser(Long userId) throws Exception {
		return userServiceManager.activateUser(userId);
	}

}
