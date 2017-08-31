package com.ez.portal.shard.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.ez.portal.core.entity.HibernateProperty;
import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserSpace;
import com.ez.portal.core.util.EntityUtil;
import com.ez.portal.core.util.UserUtil;

/**
 * @author azaz.akhtar
 *
 */
public class PortalHibernateUtil {

    /**
     * 
     */
    public static SessionFactory sessionFactory;

    /**
     * 
     */
    private EZShardUtil ezShardUtil;

    /**
     * 
     */
    public PortalHibernateUtil() {
        super();
    }

    /**
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        PortalHibernateUtil.sessionFactory = sessionFactory;
    }

    /**
     * @return
     */
    public SessionFactory getSessionFactory() {
        return ezShardUtil.getSessionFactory();
    }
    
    /**
     * @param nonShard
     * @return
     */
    public SessionFactory getSessionFactory(Boolean nonShard) {
    	if (nonShard) {
    		return sessionFactory;
    	} else {
    		return getSessionFactory();
    	}
    }
    
    /**
     * @return
     */
    public EZShardUtil getEzShardUtil() {
        return ezShardUtil;
    }

    /**
     * @param ezShardUtil
     */
    public void setEzShardUtil(EZShardUtil ezShardUtil) {
        this.ezShardUtil = ezShardUtil;
    }

    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public void initShard() {
        Session session = null;
        Criteria criteria = null;
        List<UserSpace> spaces = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            criteria = session.createCriteria(UserSpace.class);
            spaces = criteria.list();
            if (spaces != null && !spaces.isEmpty()) {
                getEzShardUtil().buildShard(spaces);
            } else {
                System.out.println("Installing default user space...");
                transaction = session.beginTransaction();
                installUserSpace("RKDF_CE", "RKDF College of Engineering", transaction);
//                installUserSpace("RKDF_MAIN", "RKDF Main", transaction);
//                installUserSpace("RADHA_RAMAN", "Radha Raman", transaction);
//                installUserSpace("BANSAL", "Bansal College of Engineering", transaction);
//                installUserSpace("RGTU", "Rajeev Gandhi Technical University", transaction);
                installUserSpace("UIT", "University Institute of Technology", transaction);
                installSuperAdmin(session, transaction);
                spaces = criteria.list();
                if (spaces != null && !spaces.isEmpty()) {
                    getEzShardUtil().buildShard(spaces);
                }
                for (UserSpace userSpace : spaces) {
                    installFirstUserIn(userSpace);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    /**
     * @param userSpace
     */
    private void installFirstUserIn(UserSpace userSpace) {
        User user = new User("akhtar.azaz@live.com", "ejudo", UserUtil.USER_TYPE_FIRST_USER, EntityUtil.ACTIVE_ENTRY);
        user.setShardKey(userSpace.getUserSpaceId().toString());
        Password password = null;
        Session session = null;
        Transaction transaction = null;
        try {
            ezShardUtil.setShardKey(userSpace.getUserSpaceId().toString());
            ezShardUtil.initSessionFactory();
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            session.flush();
            password = new Password(user, "21232f297a57a5a743894a0e4a801fc3", EntityUtil.ACTIVE_ENTRY);
            password.setUser(user);
            session.save(password);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    /**
     * @param session
     * @param transaction
     * @return
     */
    public Boolean installSuperAdmin(Session session, Transaction transaction) {
        User user = new User("akhtar.azaz@live.com", "ejudo", UserUtil.USER_TYPE_SUPER_USER, EntityUtil.ACTIVE_ENTRY);
        Password password = null;
        Boolean success = false;
        try {
            session.save(user);
            session.flush();
            password = new Password(user, "21232f297a57a5a743894a0e4a801fc3", EntityUtil.ACTIVE_ENTRY);
            password.setUser(user);
            session.save(password);
            session.flush();
            transaction.commit();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
            transaction.rollback();
        }
        return success;
    }
    
    public List<HibernateProperty> getHibernateProperties(UserSpace userSpace) {
        List<HibernateProperty> hibernateProperties = new ArrayList<>();
        hibernateProperties.add(new HibernateProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect", EntityUtil.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.session_factory_name", userSpace.getUserSpaceName() + "_HibernateSessionFactory", EntityUtil.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver", EntityUtil.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + userSpace.getUserSpaceName() + "_PORTAL?createDatabaseIfNotExist=true", EntityUtil.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.username", "root", EntityUtil.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.password", "Admin", EntityUtil.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.shard_id", userSpace.getUserSpaceId().toString(), EntityUtil.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.hbm2ddl.auto", "update", EntityUtil.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.shard.enable_cross_shard_relationship_checks", "true", EntityUtil.ACTIVE_ENTRY));
        hibernateProperties.add(new HibernateProperty("hibernate.show_sql", "true", EntityUtil.ACTIVE_ENTRY));
        return hibernateProperties;
    };

    public UserSpace installUserSpace(String userSpaceName, String displayName, Transaction transaction) {
        UserSpace userSpace = new UserSpace(userSpaceName, displayName, EntityUtil.ACTIVE_ENTRY);
        List<HibernateProperty> hibernateProperties = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.save(userSpace);
            session.flush();
            hibernateProperties = getHibernateProperties(userSpace);
            for (HibernateProperty hibernateProperty : hibernateProperties) {
                hibernateProperty.setUserSpace(userSpace);
                hibernateProperty.setShardKey(userSpace.getUserSpaceId().toString());
                session.save(hibernateProperty);
                session.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } 
        return userSpace;
    }

}