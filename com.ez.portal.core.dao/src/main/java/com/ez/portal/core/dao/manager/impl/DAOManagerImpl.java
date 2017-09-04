package com.ez.portal.core.dao.manager.impl;

import com.ez.portal.core.dao.intf.AccessPermissionDAO;
import com.ez.portal.core.dao.intf.DBServerDAO;
import com.ez.portal.core.dao.intf.PasswordDAO;
import com.ez.portal.core.dao.intf.PortalSessionDAO;
import com.ez.portal.core.dao.intf.UserDAO;
import com.ez.portal.core.dao.intf.UserRoleDAO;
import com.ez.portal.core.dao.intf.UserSpaceDAO;
import com.ez.portal.core.dao.manager.intf.DAOManager;

public class DAOManagerImpl implements DAOManager {
	
	/**
	 * 
	 */
	private UserDAO userDAO;
	
	/**
	 * 
	 */
	private UserSpaceDAO userSpaceDAO;
	
	/**
	 * 
	 */
	private PasswordDAO passwordDAO;
	
	/**
	 * 
	 */
	private PortalSessionDAO portalSessionDAO;
	
	/**
	 * 
	 */
	private UserRoleDAO userRoleDAO;
	
	/**
	 * 
	 */
	private DBServerDAO dbServerDAO;
	
	/**
	 * 
	 */
	private AccessPermissionDAO accessPermissionDAO;

	@Override
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserSpaceDAO getUserSpaceDAO() {
		return userSpaceDAO;
	}

	/**
	 * @param userSpaceDAO
	 */
	public void setUserSpaceDAO(UserSpaceDAO userSpaceDAO) {
		this.userSpaceDAO = userSpaceDAO;
	}

	@Override
	public PortalSessionDAO getPortalSessionDAO() {
		return portalSessionDAO;
	}

	/**
	 * @param portalSessionDAO
	 */
	public void setPortalSessionDAO(PortalSessionDAO portalSessionDAO) {
		this.portalSessionDAO = portalSessionDAO;
	}

	@Override
	public PasswordDAO getPasswordDAO() {
		return passwordDAO;
	}

	/**
	 * @param passwordDAO the passwordDAO to set
	 */
	public void setPasswordDAO(PasswordDAO passwordDAO) {
		this.passwordDAO = passwordDAO;
	}

	@Override
	public UserRoleDAO getUserRoleDAO() {
		return userRoleDAO;
	}

	/**
	 * @param userRoleDAO the userRoleDAO to set
	 */
	public void setUserRoleDAO(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}

	@Override
	public AccessPermissionDAO getAccessPermissionDAO() {
		return accessPermissionDAO;
	}

	/**
	 * @param accessPermissionDAO the accessPermissionDAO to set
	 */
	public void setAccessPermissionDAO(AccessPermissionDAO accessPermissionDAO) {
		this.accessPermissionDAO = accessPermissionDAO;
	}

	/**
	 * @return the dbServerDAO
	 */
	@Override
	public DBServerDAO getDbServerDAO() {
		return dbServerDAO;
	}

	/**
	 * @param dbServerDAO the dbServerDAO to set
	 */
	public void setDbServerDAO(DBServerDAO dbServerDAO) {
		this.dbServerDAO = dbServerDAO;
	}
}
