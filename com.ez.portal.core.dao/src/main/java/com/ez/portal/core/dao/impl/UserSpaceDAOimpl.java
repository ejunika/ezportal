package com.ez.portal.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.ez.portal.core.dao.intf.UserSpaceDAO;
import com.ez.portal.core.entity.DBServer;
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

	@Override
	@SuppressWarnings("unchecked")
	public List<UserSpace> getAllUserSpaces() throws Exception {
		List<UserSpace> userSpaces = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getSessionFactory(true).openSession();
			criteria = session.createCriteria(UserSpace.class);
			criteria.addOrder(Order.desc("shardKey"));
			userSpaces = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userSpaces;
	}

	@Override
	public UserSpace createUserSpace(UserSpace userSpace, DBServer dbServer) throws Exception {
		Session session = null;
		try {
			session = getSessionFactory(true).openSession();
			session.beginTransaction();
			if (dbServer != null && userSpace != null) {
				session.save(dbServer);
				userSpace.setDbServer(dbServer);
				session.save(userSpace);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return userSpace;
	}
	
}
