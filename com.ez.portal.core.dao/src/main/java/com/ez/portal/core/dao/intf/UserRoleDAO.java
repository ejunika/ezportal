package com.ez.portal.core.dao.intf;

import java.util.List;

import com.ez.portal.core.entity.UserRole;

/**
 * @author azaz.akhtar
 *
 */
public interface UserRoleDAO extends CommonDAO<UserRole, Long> {
	
	List<UserRole> createUserRoles(List<UserRole> userRoles) throws Exception;
	
}
