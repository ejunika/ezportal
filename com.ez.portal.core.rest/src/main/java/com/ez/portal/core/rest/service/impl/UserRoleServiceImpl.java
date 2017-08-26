package com.ez.portal.core.rest.service.impl;

import com.ez.portal.core.request.UserRoleRequest;
import com.ez.portal.core.response.UserRoleResponse;
import com.ez.portal.core.rest.manager.intf.UserRoleServiceManager;
import com.ez.portal.core.rest.service.intf.UserRoleService;

/**
 * @author azaz.akhtar
 *
 */
public class UserRoleServiceImpl implements UserRoleService {

	/**
	 * 
	 */
	private UserRoleServiceManager userRoleServiceManager;
	
	/**
	 * @return the userRoleServiceManager
	 */
	public UserRoleServiceManager getUserRoleServiceManager() {
		return userRoleServiceManager;
	}

	/**
	 * @param userRoleServiceManager the userRoleServiceManager to set
	 */
	public void setUserRoleServiceManager(UserRoleServiceManager userRoleServiceManager) {
		this.userRoleServiceManager = userRoleServiceManager;
	}

	@Override
	public UserRoleResponse getUserRole(Long userRoleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleResponse activateUserRole(Long userRoleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleResponse deleteUserRole(Long userRoleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleResponse blockUserRole(Long userRoleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleResponse getAllUserRole() throws Exception {
		return userRoleServiceManager.getAllUserRole();
	}

	@Override
	public UserRoleResponse createUserRole(UserRoleRequest userRoleRequest) throws Exception {
		return userRoleServiceManager.createUserRole(userRoleRequest);
	}

	@Override
	public UserRoleResponse updateUserRole(UserRoleRequest userRoleRequest) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
