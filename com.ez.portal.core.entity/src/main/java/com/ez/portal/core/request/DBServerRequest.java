package com.ez.portal.core.request;

import com.ez.portal.core.entity.DBServer;

public class DBServerRequest extends AbstractRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DBServer dbServer;

	/**
	 * @return the dbServer
	 */
	public DBServer getDbServer() {
		return dbServer;
	}

	/**
	 * @param dbServer the dbServer to set
	 */
	public void setDbServer(DBServer dbServer) {
		this.dbServer = dbServer;
	}
	
}
