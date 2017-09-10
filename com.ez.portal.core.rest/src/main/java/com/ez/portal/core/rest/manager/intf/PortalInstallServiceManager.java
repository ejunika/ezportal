package com.ez.portal.core.rest.manager.intf;

import com.ez.portal.core.response.PortalInstallResponse;

public interface PortalInstallServiceManager {

	PortalInstallResponse install();

	PortalInstallResponse install(String tenantName);
	
}
