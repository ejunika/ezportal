package com.ez.portal.core.rest.manager.intf;

import com.ez.portal.core.request.UserRoleRequest;
import com.ez.portal.core.response.UserRoleResponse;

/**
 * @author azaz.akhtar
 *
 */
public interface UserRoleServiceManager {

	/**
	 * @param userRoleRequest
	 * @return
	 */
	UserRoleResponse createUserRole(UserRoleRequest userRoleRequest);
	
	/**
	 * @param userRoleRequest
	 * @return
	 */
	UserRoleResponse updateUserRole(UserRoleRequest userRoleRequest);
	
	/**
	 * @param userRoleId
	 * @return
	 */
	UserRoleResponse deleteUserRole(Long userRoleId);
	
	/**
	 * @param userRoleId
	 * @return
	 */
	UserRoleResponse activateUserRole(Long userRoleId);

	/**
	 * @return
	 */
	UserRoleResponse getAllUserRole();
	
}
