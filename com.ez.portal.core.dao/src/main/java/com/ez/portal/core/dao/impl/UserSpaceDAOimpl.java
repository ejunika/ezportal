package com.ez.portal.core.dao.impl;

import com.ez.portal.core.dao.intf.UserSpaceDAO;
import com.ez.portal.core.entity.UserSpace;

/**
 * @author azaz.akhtar
 *
 */
public class UserSpaceDAOimpl extends CommonDAOimpl<UserSpace, Long> implements UserSpaceDAO {
	
	/**
	 * 
	 */
	public UserSpaceDAOimpl() {
		super(true);
	}
	
}
