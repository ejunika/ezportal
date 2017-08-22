package com.ez.portal.core.rest.service.impl;

import com.ez.portal.core.response.PortalSessionResponse;
import com.ez.portal.core.rest.manager.PortalSessionServiceManager;
import com.ez.portal.core.rest.service.intf.PortalSessionService;

/**
 * @author azaz.akhtar
 *
 */
public class PortalSessionServiceImpl implements PortalSessionService {

	/**
	 * 
	 */
	private PortalSessionServiceManager portalSessionServiceManager;
	
	/**
	 * 
	 */
	private PortalSessionResponse portalSessionResponse;
	
	/**
	 * @return the portalSessionResponse
	 */
	public PortalSessionResponse getPortalSessionResponse() {
		return portalSessionResponse;
	}

	/**
	 * @return
	 */
	public PortalSessionServiceManager getPortalSessionServiceManager() {
		return portalSessionServiceManager;
	}

	/**
	 * @param portalSessionServiceManager
	 */
	public void setPortalSessionServiceManager(PortalSessionServiceManager portalSessionServiceManager) {
		this.portalSessionServiceManager = portalSessionServiceManager;
	}

	/**
	 * @param portalSessionResponse the portalSessionResponse to set
	 */
	public void setPortalSessionResponse(PortalSessionResponse portalSessionResponse) {
		this.portalSessionResponse = portalSessionResponse;
	}

	@Override
	public PortalSessionResponse checkPortalSession(String portalSessionToken) throws Exception {
		portalSessionResponse.resetResponse();
		if (portalSessionServiceManager.checkPortalSession(portalSessionToken)) {
			portalSessionResponse.setAuthenticationToken(portalSessionToken);
			portalSessionResponse.setUser(portalSessionServiceManager.getActiveUser(portalSessionToken));
			portalSessionResponse.setMessage("Session is active");
			portalSessionResponse.setStatus(true);
		} else {
			portalSessionResponse.setAuthenticationToken(null);
			portalSessionResponse.setUser(null);
			portalSessionResponse.setMessage("Session is not active");
			portalSessionResponse.setStatus(true);
		}
		return portalSessionResponse;
	}

	@Override
	public PortalSessionResponse logout(String portalSessionToken) throws Exception {
		portalSessionResponse.resetResponse();
		if (portalSessionServiceManager.makeSessionOut(portalSessionToken)) {
			portalSessionResponse.setAuthenticationToken(null);
			portalSessionResponse.setUser(null);
			portalSessionResponse.setMessage("Logout successfully");
			portalSessionResponse.setStatus(true);
		} else {
			portalSessionResponse.setAuthenticationToken(portalSessionToken);
			portalSessionResponse.setUser(portalSessionServiceManager.getActiveUser(portalSessionToken));
			portalSessionResponse.setMessage("Session is active");
			portalSessionResponse.setStatus(true);
		}
		return portalSessionResponse;
	}

}
