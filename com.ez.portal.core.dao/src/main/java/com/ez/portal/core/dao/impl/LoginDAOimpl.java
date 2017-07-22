package com.ez.portal.core.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.ez.portal.core.dao.intf.LoginDAO;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.util.dao.impl.CommonDAOimpl;

public class LoginDAOimpl extends CommonDAOimpl<User, Long> implements LoginDAO {

    @Override
    public User getUserInfoByEmailId(String emailId) {
        return (User) getSessionFactory().openSession().createCriteria(User.class)
                .add(Restrictions.eq("emailId", emailId)).uniqueResult();
    }

    @Override
    public User getUser(Long userId) {
        return (User) getSessionFactory().openSession().createCriteria(User.class)
                .add(Restrictions.eq("userId", userId)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        return getSessionFactory().openSession().createCriteria(User.class).list();
    }

}
