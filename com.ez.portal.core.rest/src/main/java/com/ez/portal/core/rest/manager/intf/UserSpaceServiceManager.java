package com.ez.portal.core.rest.manager.intf;

import com.ez.portal.core.request.UserSpaceRequest;
import com.ez.portal.core.response.UserSpaceResponse;

/**
 * @author azaz.akhtar
 *
 */
public interface UserSpaceServiceManager {

	/**
	 * @param userSpaceRequest
	 * @return
	 */
	UserSpaceResponse createUserSpace(UserSpaceRequest userSpaceRequest);
	
}
