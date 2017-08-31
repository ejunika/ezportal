package com.ez.portal.core.rest.manager;

import com.ez.portal.core.dao.manager.intf.DAOManager;

/**
 * @author azaz.akhtar
 *
 */
public abstract class AbstractServiceManager {

	/**
	 * 
	 */
	protected DAOManager daoManager;

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
