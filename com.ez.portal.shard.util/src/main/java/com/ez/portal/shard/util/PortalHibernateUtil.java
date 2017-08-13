package com.ez.portal.shard.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.shard.entity.HibernateProperty;
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
                installUserSpace("RKDF_CE", transaction);
                installUserSpace("RKDF_MAIN", transaction);
                installUserSpace("RADHA_RAMAN", transaction);
                installUserSpace("BANSAL", transaction);
                installUserSpace("RGTU", transaction);
                installUserSpace("UIT", transaction);
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
    
    private void installFirstUserIn(UserSpace userSpace) {
        User user = new User("akhtar.azaz@live.com", "ejudo", (byte) 1);
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
            password = new Password(user, "21232f297a57a5a743894a0e4a801fc3", (byte) 1);
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

    public Boolean installSuperAdmin(Session session, Transaction transaction) {
        User user = new User("akhtar.azaz@live.com", "ejudo", (byte) 1);
        Password password = null;
        Boolean success = false;
        try {
            session.save(user);
            session.flush();
            password = new Password(user, "21232f297a57a5a743894a0e4a801fc3", (byte) 1);
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
        hibernateProperties.add(new HibernateProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"));
        hibernateProperties.add(new HibernateProperty("hibernate.session_factory_name", userSpace.getUserSpaceName() + "_HibernateSessionFactory"));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver"));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + userSpace.getUserSpaceName() + "_PORTAL?createDatabaseIfNotExist=true"));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.username", "root"));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.password", "Admin"));
        hibernateProperties.add(new HibernateProperty("hibernate.connection.shard_id", userSpace.getUserSpaceId().toString()));
        hibernateProperties.add(new HibernateProperty("hibernate.hbm2ddl.auto", "update"));
        hibernateProperties.add(new HibernateProperty("hibernate.shard.enable_cross_shard_relationship_checks", "true"));
        hibernateProperties.add(new HibernateProperty("hibernate.show_sql", "true"));
        return hibernateProperties;
    };

    public UserSpace installUserSpace(String userSpaceName, Transaction transaction) {
        UserSpace defaultUserSpace = new UserSpace(userSpaceName);
        List<HibernateProperty> hibernateProperties = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            defaultUserSpace.setUserSpaceStatus((byte) 1);
            session.save(defaultUserSpace);
            session.flush();
            hibernateProperties = getHibernateProperties(defaultUserSpace);
            for (HibernateProperty hibernateProperty : hibernateProperties) {
                hibernateProperty.setUserSpace(defaultUserSpace);
                session.save(hibernateProperty);
                session.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } 
        return defaultUserSpace;
    }

}