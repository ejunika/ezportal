package com.ez.portal.core.response;

import java.util.List;

import com.ez.portal.core.entity.AccessPermission;

/**
 * @author azaz.akhtar
 *
 */
public class AccessPermissionResponse extends AbstractResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
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
