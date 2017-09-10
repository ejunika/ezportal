package com.ez.portal.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.core.dao.intf.PortalSessionDAO;
import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.UserSpace;

public class PortalSessionDAOimpl extends CommonDAOimpl<PortalSession, Long> implements PortalSessionDAO {

	@Override
	public PortalSession getPortalSessionByPortalSessionToken(String portalSessionToken) throws Exception {
		PortalSession portalSession = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getSessionFactory().openSession();
			criteria = session.createCriteria(PortalSession.class);
			criteria.add(Restrictions.eq("portalSessionToken", portalSessionToken));
			portalSession = (PortalSession) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return portalSession;
	}

	@Override
	public PortalSession createSuperUserSession(PortalSession portalSession) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSessionFactory(true).openSession();
			transaction = session.beginTransaction();
			session.save(portalSession);
			session.flush();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return portalSession;
	}

	@Override
	public void setActiveUserAndShard(PortalSession portalSession) throws Exception {
		if (portalSession != null) {
			getEzShardUtil().setActiveUser(portalSession.getUser());
			getEzShardUtil().setShardKey(portalSession.getShardKey());
			getEzShardUtil().initSessionFactory(portalSession.getShardKey());
		}
	}

	@Override
	public UserSpace getUserSpaceByPortalSessionToken(String portalSessionToken) throws Exception {
		UserSpace userSpace = null;
		PortalSession portalSession = null;
		Session session = null;
		Session masterSession = null;
		try {
			session = getSessionFactory().openSession();
			portalSession = (PortalSession) session.createCriteria(PortalSession.class)
					.add(Restrictions.eq("portalSessionToken", portalSessionToken)).uniqueResult();
			if (portalSession != null) {
				String shardKey = portalSession.getShardKey();
				masterSession = getSessionFactory(true).openSession();
				userSpace = (UserSpace) masterSession.get(UserSpace.class, Long.parseLong(shardKey));
				userSpace.setHibernateProperties(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			masterSession.close();
			session.close();
		}
		return userSpace;
	}

}
