package com.ez.portal.core.request;

import java.util.List;

import com.ez.portal.core.entity.AccessPermission;

public class AccessPermissionRequest extends AbstractRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AccessPermission> accessPermissions;

	/**
	 * @return the accessPermissions
	 */
	public List<AccessPermission> getAccessPermissions() {
		return accessPermissions;
	}

	/**
	 * @param accessPermissions the accessPermissions to set
	 */
	public void setAccessPermissions(List<AccessPermission> accessPermissions) {
		this.accessPermissions = accessPermissions;
	}

}
