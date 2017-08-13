package com.ez.portal.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.core.dao.intf.PasswordDAO;
import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.status.PasswordStatus;
import com.ez.portal.shard.util.PortalHibernateUtil;

public class PasswordDAOimpl extends CommonDAOimpl<Password, Long> implements PasswordDAO {

    @Override
    public Password getActivePasswordByUser(User user) throws Exception {
        Password password = null;
        Session session = null;
        Criteria criteria = null;
        try {
            session = getSessionFactory().openSession();
            criteria = session.createCriteria(Password.class);
            criteria.add(Restrictions.eq("user", user));
            criteria.add(Restrictions.eq("passwordStatus", PasswordStatus.ACTIVE_PASSWORD));
            password = (Password) criteria.uniqueResult();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return password;
    }

    @Override
    public Password getActivePasswordBySuperUser(User user) throws Exception {
        Password password = null;
        Session session = null;
        Criteria criteria = null;
        try {
            session = PortalHibernateUtil.sessionFactory.openSession();
            criteria = session.createCriteria(Password.class);
            criteria.add(Restrictions.eq("user", user));
            criteria.add(Restrictions.eq("passwordStatus", PasswordStatus.ACTIVE_PASSWORD));
            password = (Password) criteria.uniqueResult();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return password;
    }

}
