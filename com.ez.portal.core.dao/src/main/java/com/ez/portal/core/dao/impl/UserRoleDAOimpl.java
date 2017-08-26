package com.ez.portal.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ez.portal.core.dao.intf.UserRoleDAO;
import com.ez.portal.core.entity.UserRole;

/**
 * @author azaz.akhtar
 *
 */
public class UserRoleDAOimpl extends CommonDAOimpl<UserRole, Long> implements UserRoleDAO {

	@Override
	@SuppressWarnings("null")
	public List<UserRole> createUserRoles(List<UserRole> userRoles) throws Exception {
		Session session = null;
		Transaction transaction = null;
		List<UserRole> newUserRoles = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if (userRoles != null) {
				newUserRoles = new ArrayList<>();
				for (UserRole userRole : userRoles) {
					userRole.setShardKey(getEzShardUtil().getShardKey());
					session.save(userRole);
					newUserRoles.add(userRole);
					session.flush();
				}
				transaction.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return newUserRoles;
	}

}
