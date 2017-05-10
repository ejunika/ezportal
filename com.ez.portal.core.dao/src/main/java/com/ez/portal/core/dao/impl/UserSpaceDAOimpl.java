package com.ez.portal.core.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ez.portal.core.dao.intf.UserSpaceDAO;
import com.ez.portal.core.util.dao.impl.CommonDAOimpl;
import com.ez.portal.shard.entity.UserSpace;
import com.ez.portal.shard.util.PortalHibernateUtil;

public class UserSpaceDAOimpl extends CommonDAOimpl<UserSpace, Long> implements UserSpaceDAO {

    private Session session;
    private Transaction transaction;
    
    @Override
    public UserSpace add(UserSpace entity) {
        try {
            session = PortalHibernateUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public UserSpace update(UserSpace entity) {
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public UserSpace addOrUpdate(UserSpace entity) {
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return entity;
    }

    @Override
    public UserSpace get(Long entityId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserSpace> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserSpace delete(UserSpace entity) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
