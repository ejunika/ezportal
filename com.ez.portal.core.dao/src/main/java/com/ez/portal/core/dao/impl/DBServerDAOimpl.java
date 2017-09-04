package com.ez.portal.core.dao.impl;

import com.ez.portal.core.dao.intf.DBServerDAO;
import com.ez.portal.core.entity.DBServer;

/**
 * @author azaz.akhtar
 *
 */
public class DBServerDAOimpl extends CommonDAOimpl<DBServer, Long> implements DBServerDAO {

	/**
	 * 
	 */
	public DBServerDAOimpl() {
		super(true);
	}
	
}
