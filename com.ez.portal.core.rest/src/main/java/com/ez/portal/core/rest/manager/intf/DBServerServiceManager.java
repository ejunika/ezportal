package com.ez.portal.core.rest.manager.intf;

import com.ez.portal.core.request.DBServerRequest;
import com.ez.portal.core.response.DBServerResponse;

/**
 * @author azaz.akhtar
 *
 */
public interface DBServerServiceManager {

	/**
	 * @param dbServerRequest
	 * @return
	 */
	DBServerResponse createDBServer(DBServerRequest dbServerRequest);
	
}
