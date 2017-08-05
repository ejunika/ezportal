package com.ez.portal.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.core.dao.intf.UserDAO;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.util.dao.impl.CommonDAOimpl;

public class UserDAOimpl extends CommonDAOimpl<User, Long> implements UserDAO {

    @Override
    public User getUserByEmailId(String emailId) throws Exception {
        User user = null;
        Session session = null;
        Criteria criteria = null;
        try {
            session = getSessionFactory().openSession();
            criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("emailId", emailId));
            user = (User) criteria.uniqueResult();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

}
