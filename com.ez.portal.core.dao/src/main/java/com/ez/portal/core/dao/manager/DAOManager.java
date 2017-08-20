package com.ez.portal.core.dao.manager;

import com.ez.portal.core.dao.intf.PasswordDAO;
import com.ez.portal.core.dao.intf.PortalSessionDAO;
import com.ez.portal.core.dao.intf.UserDAO;
import com.ez.portal.core.dao.intf.UserSpaceDAO;

/**
 * @author azaz.akhtar
 *
 */
public class DAOManager {
	
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
	 * @return
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return
	 */
	public UserSpaceDAO getUserSpaceDAO() {
		return userSpaceDAO;
	}

	/**
	 * @param userSpaceDAO
	 */
	public void setUserSpaceDAO(UserSpaceDAO userSpaceDAO) {
		this.userSpaceDAO = userSpaceDAO;
	}

	/**
	 * @return
	 */
	public PortalSessionDAO getPortalSessionDAO() {
		return portalSessionDAO;
	}

	/**
	 * @param portalSessionDAO
	 */
	public void setPortalSessionDAO(PortalSessionDAO portalSessionDAO) {
		this.portalSessionDAO = portalSessionDAO;
	}

	/**
	 * @return the passwordDAO
	 */
	public PasswordDAO getPasswordDAO() {
		return passwordDAO;
	}

	/**
	 * @param passwordDAO the passwordDAO to set
	 */
	public void setPasswordDAO(PasswordDAO passwordDAO) {
		this.passwordDAO = passwordDAO;
	}
	
}
