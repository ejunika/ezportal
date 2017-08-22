package com.ez.portal.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.core.dao.intf.PortalSessionDAO;
import com.ez.portal.core.entity.PortalSession;

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
        } catch(Exception e) {
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

}
