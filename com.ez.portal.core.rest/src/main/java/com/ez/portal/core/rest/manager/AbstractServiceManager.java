package com.ez.portal.core.rest.manager;

import com.ez.portal.core.dao.manager.DAOManager;

/**
 * @author azaz.akhtar
 *
 */
public abstract class AbstractServiceManager {

	/**
	 * 
	 */
	private DAOManager daoManager;

	/**
	 * @return
	 */
	public DAOManager getDaoManager() {
		return daoManager;
	}

	/**
	 * @param daoManager
	 */
	public void setDaoManager(DAOManager daoManager) {
		this.daoManager = daoManager;
	}
	
}
