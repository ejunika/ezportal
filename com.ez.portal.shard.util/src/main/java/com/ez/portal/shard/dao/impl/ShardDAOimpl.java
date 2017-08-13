package com.ez.portal.shard.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.shard.dao.intf.ShardDAO;
import com.ez.portal.shard.entity.UserSpace;
import com.ez.portal.shard.util.EZShardUtil;
import com.ez.portal.shard.util.PortalHibernateUtil;

public class ShardDAOimpl implements ShardDAO {

    private EZShardUtil ezShardUtil;

    public EZShardUtil getEzShardUtil() {
        return ezShardUtil;
    }

    public void setEzShardUtil(EZShardUtil ezShardUtil) {
        this.ezShardUtil = ezShardUtil;
    }

    @Override
    public UserSpace getUserSpace(Long userSpaceId) throws Exception {
        Session session = null;
        UserSpace userSpace = null;
        try {
            session = PortalHibernateUtil.sessionFactory.openSession();
            userSpace = (UserSpace) session.createCriteria(UserSpace.class)
                    .add(Restrictions.eq("userSpaceId", userSpaceId)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userSpace;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserSpace> getAllUserSpaces() throws Exception {
        Session session = null;
        List<UserSpace> userSpaces = null;
        try {
            session = ezShardUtil.getSessionFactory().openSession();
            userSpaces = session.createCriteria(UserSpace.class).list();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userSpaces;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserSpace> getAllUserSpacesByEmailId(String emailId) throws Exception {
        Session session = null;
        List<UserSpace> userSpaces = null;
        try {
            session = ezShardUtil.getSessionFactory().openSession();
            userSpaces = session.createCriteria(UserSpace.class).add(Restrictions.eq("", emailId)).list();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userSpaces;
    }

    @Override
    public UserSpace getUserSpace(String shardKey) throws Exception {
        Long userSpaceId = 0l;
        UserSpace userSpace = null;
        try {
            userSpaceId = Long.parseLong(shardKey);
            userSpace = getUserSpace(userSpaceId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userSpace;
    }

    @Override
    public UserSpace addUserSpace(UserSpace userSpace) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = PortalHibernateUtil.sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(userSpace);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userSpace;
    }

}
