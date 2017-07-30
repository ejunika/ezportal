package com.ez.portal.shard.util;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.ez.portal.shard.entity.UserSpace;

public class PortalHibernateUtil {
    
    public static SessionFactory sessionFactory;
      
    private EZShardUtil ezShardUtil;

    public PortalHibernateUtil() {
        super();
    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        PortalHibernateUtil.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return ezShardUtil.getSessionFactory();
    }

    public EZShardUtil getEzShardUtil() {
        return ezShardUtil;
    }

    public void setEzShardUtil(EZShardUtil ezShardUtil) {
        this.ezShardUtil = ezShardUtil;
    }
    
    @SuppressWarnings("unchecked")
    public void initShard() {
        Session session = null;
        Criteria criteria = null;
        List<UserSpace> spaces = null;
        try {
            session = sessionFactory.openSession();
            criteria = session.createCriteria(UserSpace.class);
            spaces = criteria.list();
            if (spaces != null) {
                getEzShardUtil().buildShard(spaces);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
}
