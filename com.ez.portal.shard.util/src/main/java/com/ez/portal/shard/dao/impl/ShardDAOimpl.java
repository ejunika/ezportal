package com.ez.portal.shard.dao.impl;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.shard.dao.intf.ShardDAO;
import com.ez.portal.shard.entity.UserSpace;
import com.ez.portal.shard.util.PortalHibernateUtil;

public class ShardDAOimpl implements ShardDAO {

    @Override
    public UserSpace getUserSpace(Long userSpaceId) {
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

}
