package com.ez.portal.core.rest.manager.intf;

import java.util.List;

import com.ez.portal.core.request.UserRequest;
import com.ez.portal.core.response.UserResponse;

/**
 * @author azaz.akhtar
 *
 */
public interface UserServiceManager {

	/**
	 * @return
	 */
	UserResponse getAllusers();
	
	/**
	 * @param entryStatusList
	 * @return
	 */
	UserResponse getAllUsers(List<String> entryStatusList);

	/**
	 * @param userRequest
	 * @return
	 */
	UserResponse addUser(UserRequest userRequest);

	/**
	 * @param userRequest
	 * @return
	 */
	UserResponse updateUser(UserRequest userRequest);

	/**
	 * @param userId
	 * @return
	 */
	UserResponse blockUser(Long userId);

	/**
	 * @param userId
	 * @return
	 */
	UserResponse unblockUser(Long userId);

	/**
	 * @param userId
	 * @return
	 */
	UserResponse activateUser(Long userId);

	/**
	 * @param userId
	 * @return
	 */
	UserResponse removeUser(Long userId);
	
}
