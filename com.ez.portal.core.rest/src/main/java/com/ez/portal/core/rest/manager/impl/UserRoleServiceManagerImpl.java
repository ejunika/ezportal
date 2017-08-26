package com.ez.portal.core.rest.manager.impl;

import java.util.List;

import com.ez.portal.core.dao.manager.intf.DAOManager;
import com.ez.portal.core.entity.UserRole;
import com.ez.portal.core.request.UserRoleRequest;
import com.ez.portal.core.response.UserRoleResponse;
import com.ez.portal.core.rest.manager.intf.UserRoleServiceManager;

/**
 * @author azaz.akhtar
 *
 */
public class UserRoleServiceManagerImpl implements UserRoleServiceManager {

	/**
	 * 
	 */
	private DAOManager daoManager;
	
	/**
	 * 
	 */
	private UserRoleResponse userRoleResponse;
	
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
	 * @return the userRoleResponse
	 */
	public UserRoleResponse getUserRoleResponse() {
		return userRoleResponse;
	}

	/**
	 * @param userRoleResponse the userRoleResponse to set
	 */
	public void setUserRoleResponse(UserRoleResponse userRoleResponse) {
		this.userRoleResponse = userRoleResponse;
	}

	@Override
	public UserRoleResponse createUserRole(UserRoleRequest userRoleRequest) {
		List<UserRole> requestedUserRoles = null;
		List<UserRole> userRoles = null;
		try {
			requestedUserRoles = userRoleRequest.getUserRoles();
			if (requestedUserRoles != null) {
				userRoles = daoManager.getUserRoleDAO().createUserRoles(requestedUserRoles);
				if (userRoles != null) {
					userRoleResponse.setMessage("UserRole created successfully");
					userRoleResponse.setStatus(true);
				} else {
					userRoleResponse.setMessage("Something went wrong");
					userRoleResponse.setStatus(true);
				}
				userRoleResponse.setUserRoles(userRoles);
			}
		} catch (Exception e) {
			userRoleResponse.setUserRoles(userRoles);
			userRoleResponse.setMessage(e.getMessage());
			userRoleResponse.setStatus(false);
		}
		return userRoleResponse;
	}

	@Override
	public UserRoleResponse updateUserRole(UserRoleRequest userRoleRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleResponse deleteUserRole(Long userRoleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleResponse activateUserRole(Long userRoleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleResponse getAllUserRole() {
		List<UserRole> userRoles = null;
		userRoleResponse.resetResponse();
		try {
			userRoles = daoManager.getUserRoleDAO().getAll();
			if (userRoles != null) {
				userRoleResponse.setMessage("Got " + userRoles.size() + " UserRoles successfully");
				userRoleResponse.setStatus(true);
			} else {
				userRoleResponse.setMessage("No UserRole found");
				userRoleResponse.setStatus(true);
			}
			userRoleResponse.setUserRoles(userRoles);
		} catch (Exception e) {
			e.printStackTrace();
			userRoleResponse.setMessage(e.getMessage());
			userRoleResponse.setStatus(false);
		}
		return userRoleResponse;
	}

}
