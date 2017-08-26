package com.ez.portal.core.dao.manager.intf;

import com.ez.portal.core.dao.intf.AccessPermissionDAO;
import com.ez.portal.core.dao.intf.PasswordDAO;
import com.ez.portal.core.dao.intf.PortalSessionDAO;
import com.ez.portal.core.dao.intf.UserDAO;
import com.ez.portal.core.dao.intf.UserRoleDAO;
import com.ez.portal.core.dao.intf.UserSpaceDAO;

public interface DAOManager {

	/**
	 * @return
	 */
	UserDAO getUserDAO();

	/**
	 * @return
	 */
	UserSpaceDAO getUserSpaceDAO();

	/**
	 * @return
	 */
	PortalSessionDAO getPortalSessionDAO();

	/**
	 * @return
	 */
	PasswordDAO getPasswordDAO();

	/**
	 * @return
	 */
	UserRoleDAO getUserRoleDAO();

	/**
	 * @return
	 */
	AccessPermissionDAO getAccessPermissionDAO();
	
}
