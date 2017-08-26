package com.ez.portal.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.core.dao.intf.UserDAO;
import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserInfo;
import com.ez.portal.core.util.EntityUtil;

/**
 * @author azaz.akhtar
 *
 */
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	
	@Override
	public User getActiveUserByEmailId(String emailId) throws Exception {
		User user = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getSessionFactory().openSession();
			criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("emailId", emailId));
			criteria.add(Restrictions.eq("entryStatus", EntityUtil.ACTIVE_ENTRY));
			user = (User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User createUser(User user, Password password, List<UserInfo> userInfos) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if (user != null) {
				user.setShardKey(getEzShardUtil().getShardKey());
				session.save(user);
				if (password != null) {
					password.setShardKey(getEzShardUtil().getShardKey());
					password.setUser(user);
					session.save(password);
					session.flush();
					session.clear();
				}
				if (userInfos != null) {
					for (UserInfo userInfo : userInfos) {
						userInfo.setShardKey(getEzShardUtil().getShardKey());
						userInfo.setUser(user);
						session.save(userInfo);
						session.flush();
						session.clear();
					}
				}
				transaction.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User getUserByAuthenticationToken(String authenticationToken) throws Exception {
		User user = null;
		PortalSession portalSession = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getSessionFactory().openSession();
			criteria = session.createCriteria(PortalSession.class);
			criteria.add(Restrictions.eq("portalSessionToken", authenticationToken));
			criteria.add(Restrictions.eq("entryStatus", EntityUtil.ACTIVE_ENTRY));
			portalSession = (PortalSession) criteria.uniqueResult();
			if (portalSession != null) {
				user = portalSession.getCreatedBy();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User getUser(String portalSessionToken) throws Exception {
		User user = null;
		PortalSession portalSession = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getSessionFactory().openSession();
			criteria = session.createCriteria(PortalSession.class);
			criteria.add(Restrictions.eq("portalSessionToken", portalSessionToken));
			criteria.add(Restrictions.eq("entryStatus", EntityUtil.ACTIVE_ENTRY));
			portalSession = (PortalSession) criteria.uniqueResult();
			if (portalSession != null) {
				user = portalSession.getCreatedBy();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User createUser(User user, Password password) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if (user != null) {
				user.setShardKey(getEzShardUtil().getShardKey());
				session.save(user);
				session.flush();
				session.clear();
				if (password != null) {
					password.setShardKey(getEzShardUtil().getShardKey());
					password.setUser(user);
					session.save(password);
					session.flush();
					session.clear();
				}
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByEmailIdInAllUserSpaces(String emailId) throws Exception {
		List<User> users = null;
		Session session = null;
		try {
			getEzShardUtil().initSessionFactory();
			session = getSessionFactory().openSession();
			users = session.createCriteria(User.class).add(Restrictions.eq("emailId", emailId))
					.addOrder(Order.asc("shardKey")).list();
			session.flush();
			session.clear();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

	@Override
	public User getSuperUserByEmailId(String emailId) throws Exception {
		User user = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getSessionFactory(true).openSession();
			criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("emailId", emailId));
			user = (User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User getActiveUserByEmailId(String emailId, String shardKey) throws Exception {
		User user = null;
		getEzShardUtil().initSessionFactory(shardKey);
		user = getActiveUserByEmailId(emailId);
		if (user != null) {
			getEzShardUtil().setActiveUser(user);
		}
		return user;
	}

	@Override
	public User getUserByEmailId(String emailId, String shardKey) throws Exception {
		User user = null;
		getEzShardUtil().initSessionFactory(shardKey);
		user = getUserByEmailId(emailId);
		if (user != null) {
			getEzShardUtil().setActiveUser(user);
		}
		return user;
	}

}
