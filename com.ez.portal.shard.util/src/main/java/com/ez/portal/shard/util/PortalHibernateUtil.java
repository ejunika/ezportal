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
    
    public void initShard() {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(UserSpace.class);
        @SuppressWarnings("unchecked")
        List<UserSpace> spaces = criteria.list();
        for (UserSpace space : spaces) {
            System.out.println(space.getUserSpaceName());
        }
        getEzShardUtil().buildShard(spaces);
    }
    
}
