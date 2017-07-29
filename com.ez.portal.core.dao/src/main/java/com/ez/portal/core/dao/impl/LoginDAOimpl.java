package com.ez.portal.core.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.core.dao.intf.LoginDAO;
import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.status.PortalSessionStatus;
import com.ez.portal.core.util.dao.impl.CommonDAOimpl;

public class LoginDAOimpl extends CommonDAOimpl<User, Long> implements LoginDAO {

    @Override
    public User getUserInfoByEmailId(String emailId) throws Exception {
        return (User) getSessionFactory().openSession().createCriteria(User.class)
                .add(Restrictions.eq("emailId", emailId)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getUsersByEmailId(String emailId) throws Exception {
        List<User> users = null;
        Session session = null;
        Criteria criteria = null;
        try {
            session = getSessionFactory().openSession();
            criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("emailId", emailId));
            users = criteria.list();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public Password getActivePassword(User user) throws Exception {
        Password password = null;
        Session session = null;
        Criteria criteria = null;
        try {
            session = getSessionFactory().openSession();
            criteria = session.createCriteria(Password.class);
            criteria.add(Restrictions.eq("createdBy", user)).add(Restrictions.eq("passwordStatus", (byte) 1));
            password = (Password) criteria.uniqueResult();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return password;
    }

    @Override
    public Boolean createSession(PortalSession portalSession) throws Exception {
        Boolean created = false;
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(portalSession);
            transaction.commit();
            created = true;
        } catch(Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return created;
    }

    @Override
    public User getUserByAuthenticationToken(String authenticationToken) throws Exception {
        User user = null;
        PortalSession portalSession = null;
        Session session = null;
        Criteria criteria = null;
        try {
            session = getSessionFactory().openSession();
            criteria = session.createCriteria(PortalSession.class);
            criteria.add(Restrictions.eq("portalSessionToken", authenticationToken));
            criteria.add(Restrictions.eq("portalSessionStatus", PortalSessionStatus.ACTIVE_SESSION));
            portalSession = (PortalSession) criteria.uniqueResult();
            if (portalSession != null) {
                user = portalSession.getCreatedBy();
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public Boolean makePortalSessionInActive(String authenticationToken) throws Exception {
        Boolean result = false;
        PortalSession portalSession = null;
        Session session = null;
        Criteria criteria = null;
        Transaction transaction = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            criteria = session.createCriteria(PortalSession.class);
            criteria.add(Restrictions.eq("portalSessionToken", authenticationToken));
            criteria.add(Restrictions.eq("portalSessionStatus", PortalSessionStatus.ACTIVE_SESSION));
            portalSession = (PortalSession) criteria.uniqueResult();
            if (portalSession != null) {
                portalSession.setPortalSessionStatus(PortalSessionStatus.IN_ACTIVE_SESSION);
                portalSession.setUpdatedAt(new Date());
                session.saveOrUpdate(portalSession);
                transaction.commit();
                result = true;
            }
        } catch(Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return result;
    }

}
