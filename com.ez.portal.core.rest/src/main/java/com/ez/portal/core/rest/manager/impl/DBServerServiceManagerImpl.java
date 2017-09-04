package com.ez.portal.core.rest.manager.impl;

import com.ez.portal.core.dao.manager.intf.DAOManager;
import com.ez.portal.core.entity.DBServer;
import com.ez.portal.core.request.DBServerRequest;
import com.ez.portal.core.response.DBServerResponse;
import com.ez.portal.core.rest.manager.intf.DBServerServiceManager;

/**
 * @author azaz.akhtar
 *
 */
public class DBServerServiceManagerImpl implements DBServerServiceManager {

	/**
	 * 
	 */
	private DAOManager daoManager;
	
	/**
	 * 
	 */
	private DBServerResponse dbServerResponse;
	
	/**
	 * @return the daoManager
	 */
	public DAOManager getDaoManager() {
		return daoManager;
	}

	/**
	 * @param daoManager the daoManager to set
	 */
	public void setDaoManager(DAOManager daoManager) {
		this.daoManager = daoManager;
	}

	/**
	 * @return the dbServerResponse
	 */
	public DBServerResponse getDbServerResponse() {
		return dbServerResponse;
	}

	/**
	 * @param dbServerResponse the dbServerResponse to set
	 */
	public void setDbServerResponse(DBServerResponse dbServerResponse) {
		this.dbServerResponse = dbServerResponse;
	}

	@Override
	public DBServerResponse createDBServer(DBServerRequest dbServerRequest) {
		DBServer dbServer = dbServerRequest.getDbServer();
		try {
			daoManager.getDbServerDAO().add(dbServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void test() {
		DBServer dbServer = new DBServer((byte) 1, "Localhost:MySQL", "localhost", 3306, "root", "Admin");
		try {
			daoManager.getDbServerDAO().add(dbServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
