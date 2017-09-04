package com.ez.portal.core.dao.intf;

import java.util.List;

import com.ez.portal.core.entity.DBServer;
import com.ez.portal.core.entity.UserSpace;

/**
 * @author azaz.akhtar
 *
 */
public interface UserSpaceDAO extends CommonDAO<UserSpace, Long> {
	
	List<UserSpace> getAllUserSpaces() throws Exception;
	
	UserSpace createUserSpace(UserSpace userSpace, DBServer dbServer) throws Exception;
	
}
