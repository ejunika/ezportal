package com.ez.portal.core.request;

import java.util.List;

import com.ez.portal.core.entity.UserRole;

/**
 * @author azaz.akhtar
 *
 */
public class UserRoleRequest extends AbstractRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private List<UserRole> userRoles;

	/**
	 * @return the userRoles
	 */
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
