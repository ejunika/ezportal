package com.ez.portal.core.rest.service.impl;

import com.ez.portal.core.response.PortalInstallResponse;
import com.ez.portal.core.rest.manager.intf.PortalInstallServiceManager;
import com.ez.portal.core.rest.service.intf.PortalInstallService;

public class PortalInstallServiceImpl implements PortalInstallService {

	private PortalInstallServiceManager portalInstallServiceManager;
	
	public PortalInstallServiceManager getPortalInstallServiceManager() {
		return portalInstallServiceManager;
	}

	public void setPortalInstallServiceManager(PortalInstallServiceManager portalInstallServiceManager) {
		this.portalInstallServiceManager = portalInstallServiceManager;
	}

	@Override
	public PortalInstallResponse installPortal() throws Exception {
		return portalInstallServiceManager.install();
	}

	@Override
	public PortalInstallResponse installPortal(String tenantName) throws Exception {
		return portalInstallServiceManager.install(tenantName);
	}

}
