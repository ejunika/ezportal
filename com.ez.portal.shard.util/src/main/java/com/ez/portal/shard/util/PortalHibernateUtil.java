package com.ez.portal.shard.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.core.entity.HibernateProperty;
import com.ez.portal.core.entity.InfoKey;
import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserInfo;
import com.ez.portal.core.entity.UserSpace;
import com.ez.portal.core.util.EntryStatus;
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
			criteria = session.createCriteria(UserSpace.class)
					.add(Restrictions.eq("entryStatus", EntryStatus.ACTIVE_ENTRY));
			spaces = criteria.list();
			if (spaces != null && !spaces.isEmpty()) {
				getEzShardUtil().buildShard(spaces);
			} else {
				System.out.println("Installing default user space...");
				transaction = session.beginTransaction();
				installUserSpace("DEV_SPACE", "The developement space", transaction);
				installUserSpace("QA_SPACE", "The quality assurance space", transaction);
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
		InfoKey infoKey01 = new InfoKey((byte) 1, 0, "FIRST_NAME", "First Name", EntryStatus.ACTIVE_ENTRY,
				userSpace.getUserSpaceId().toString());
		InfoKey infoKey02 = new InfoKey((byte) 1, 1, "MIDDLE_NAME", "Middle Name", EntryStatus.ACTIVE_ENTRY,
				userSpace.getUserSpaceId().toString());
		InfoKey infoKey03 = new InfoKey((byte) 1, 2, "LAST_NAME", "Last Name", EntryStatus.ACTIVE_ENTRY,
				userSpace.getUserSpaceId().toString());

		User user = new User("akhtar.azaz@live.com", "ejudo", UserUtil.USER_TYPE_FIRST_USER, EntryStatus.ACTIVE_ENTRY,
				userSpace.getUserSpaceId().toString());
		Password password = null;
		Session session = null;
		Transaction transaction = null;
		try {
			ezShardUtil.setShardKey(userSpace.getUserSpaceId().toString());
			ezShardUtil.initSessionFactory();
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.save(infoKey01);
			session.flush();
			session.save(infoKey02);
			session.flush();
			session.save(infoKey03);
			session.flush();
			session.save(user);
			session.flush();
			session.save(new UserInfo(infoKey01, "Md", user, EntryStatus.ACTIVE_ENTRY,
					userSpace.getUserSpaceId().toString()));
			session.flush();
			session.save(new UserInfo(infoKey02, "Azaz", user, EntryStatus.ACTIVE_ENTRY,
					userSpace.getUserSpaceId().toString()));
			session.flush();
			session.save(new UserInfo(infoKey03, "Akhtar", user, EntryStatus.ACTIVE_ENTRY,
					userSpace.getUserSpaceId().toString()));
			session.flush();
			password = new Password(user, "21232f297a57a5a743894a0e4a801fc3", EntryStatus.ACTIVE_ENTRY);
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
		User user = new User("akhtar.azaz@live.com", "ejudo", UserUtil.USER_TYPE_SUPER_USER, EntryStatus.ACTIVE_ENTRY);
		Password password = null;
		Boolean success = false;
		try {
			session.save(user);
			session.flush();
			password = new Password(user, "21232f297a57a5a743894a0e4a801fc3", EntryStatus.ACTIVE_ENTRY);
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

	/**
	 * @param userSpace
	 * @return
	 */
	public List<HibernateProperty> getHibernateProperties(UserSpace userSpace) {
		List<HibernateProperty> hibernateProperties = new ArrayList<>();
		hibernateProperties.add(new HibernateProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect",
				EntryStatus.ACTIVE_ENTRY));
		hibernateProperties.add(new HibernateProperty("hibernate.session_factory_name",
				userSpace.getUserSpaceName() + "_HibernateSessionFactory", EntryStatus.ACTIVE_ENTRY));
		hibernateProperties.add(new HibernateProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver",
				EntryStatus.ACTIVE_ENTRY));
		hibernateProperties.add(new HibernateProperty("hibernate.connection.url",
				"jdbc:mysql://localhost:3306/" + userSpace.getUserSpaceName() + "_PORTAL?createDatabaseIfNotExist=true",
				EntryStatus.ACTIVE_ENTRY));
		hibernateProperties
				.add(new HibernateProperty("hibernate.connection.username", "root", EntryStatus.ACTIVE_ENTRY));
		hibernateProperties
				.add(new HibernateProperty("hibernate.connection.password", "Admin", EntryStatus.ACTIVE_ENTRY));
		hibernateProperties.add(new HibernateProperty("hibernate.connection.shard_id",
				userSpace.getUserSpaceId().toString(), EntryStatus.ACTIVE_ENTRY));
		hibernateProperties.add(new HibernateProperty("hibernate.hbm2ddl.auto", "update", EntryStatus.ACTIVE_ENTRY));
		hibernateProperties.add(new HibernateProperty("hibernate.shard.enable_cross_shard_relationship_checks", "true",
				EntryStatus.ACTIVE_ENTRY));
		hibernateProperties.add(new HibernateProperty("hibernate.show_sql", "true", EntryStatus.ACTIVE_ENTRY));
		return hibernateProperties;
	};

	/**
	 * @param userSpaceName
	 * @param displayName
	 * @param transaction
	 * @return
	 */
	public UserSpace installUserSpace(String userSpaceName, String displayName, Transaction transaction) {
		UserSpace userSpace = new UserSpace(userSpaceName, displayName, EntryStatus.ACTIVE_ENTRY);
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