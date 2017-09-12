package com.ez.portal.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.ez.portal.core.dao.intf.UserDAO;
import com.ez.portal.core.entity.InfoKey;
import com.ez.portal.core.entity.Password;
import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.entity.UserInfo;
import com.ez.portal.core.util.EntryStatus;
import com.ez.portal.core.util.PortalUtils;
import com.ez.portal.core.util.UserUtil;

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
			criteria.add(Restrictions.eq("entryStatus", EntryStatus.ACTIVE_ENTRY));
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
					password.setCreatedBy(user);
					password.setUpdatedBy(user);
					password.setUser(user);
					session.save(password);
					session.flush();
					session.clear();
				}
				if (userInfos != null) {
					for (UserInfo userInfo : userInfos) {
						InfoKey infoKey = userInfo.getInfoKey();
						infoKey.setCreatedBy(user);
						infoKey.setShardKey(getEzShardUtil().getShardKey());
						session.saveOrUpdate(infoKey);
						session.flush();
						session.clear();
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
			criteria.add(Restrictions.eq("entryStatus", EntryStatus.ACTIVE_ENTRY));
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
			criteria.add(Restrictions.eq("entryStatus", EntryStatus.ACTIVE_ENTRY));
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

	@Override
	public User createUser(User user) throws Exception {
		user.setEntryStatus(EntryStatus.NEW_ENTRY);
		user.setCreatedBy(getEzShardUtil().getActiveUser());
		user.setUpdatedBy(getEzShardUtil().getActiveUser());
		Password password = new Password(user, PortalUtils.getPasswordHash("admin"), EntryStatus.NEW_ENTRY);
		user = createUser(user, password);
		if (user == null) {
			throw new Exception();
		}
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllPossibleUsers() throws Exception {
		List<User> users = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getSessionFactory().openSession();
			criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.ne("userType", UserUtil.USER_TYPE_FIRST_USER));
			criteria.add(Restrictions.ne("userId", getEzShardUtil().getActiveUser().getUserId()));
			users = criteria.list();
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
	public Boolean unblockUser(Long userId) throws Exception {
		return changeEntryStatusOfUser(userId, EntryStatus.ACTIVE_ENTRY);
	}

	@Override
	public Boolean blockUser(Long userId) throws Exception {
		return changeEntryStatusOfUser(userId, EntryStatus.BLOCKED_ENTRY);
	}

	@SuppressWarnings("unchecked")
	public Boolean changeEntryStatusOfUser(Long userId, Byte entryStatus) {
		Boolean result = false;
		User user = null;
		Password password = null;
		List<Password> passwords = null;
		List<PortalSession> portalSessions;
		Session session = null;
		Transaction transaction = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			user = (User) session.createCriteria(User.class).add(Restrictions.eq("userId", userId)).uniqueResult();
			if (user != null) {
				if (entryStatus.equals(EntryStatus.BLOCKED_ENTRY)) {
					portalSessions = session.createCriteria(PortalSession.class).add(Restrictions.eq("user", user))
							.add(Restrictions.eq("entryStatus", EntryStatus.ACTIVE_ENTRY)).list();
					if (portalSessions != null) {
						for (PortalSession portalSession : portalSessions) {
							portalSession.setEntryStatus(entryStatus);
							session.update(portalSession);
							session.flush();
							session.clear();
						}
					}
				}
				passwords = session.createCriteria(Password.class).add(Restrictions.eq("user", user))
						.addOrder(Order.desc("createdAt")).list();
				if (passwords != null && !passwords.isEmpty()) {
					password = passwords.get(0);
					if (password != null) {
						password.setEntryStatus(entryStatus);
						session.update(password);
						session.flush();
						session.clear();
					}
					user.setEntryStatus(entryStatus);
					session.update(user);
					session.flush();
					session.clear();
					result = true;
				}
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsersByEntryStatusList(List<String> entryStatusList) throws Exception {
		List<User> users = null;
		List<Byte> bytes = null;
		Session session = null;
		Criteria criteria = null;
		try {
			session = getSessionFactory().openSession();
			criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.ne("userType", UserUtil.USER_TYPE_FIRST_USER));
			criteria.add(Restrictions.ne("userId", getEzShardUtil().getActiveUser().getUserId()));
			if (entryStatusList != null && !entryStatusList.isEmpty()) {
				bytes = new ArrayList<>();
				for(String entryStatus : entryStatusList) {
					try {
						bytes.add(Byte.parseByte(entryStatus));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				criteria.add(Restrictions.in("entryStatus", bytes));
				users = criteria.list();
				session.flush();
				session.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return users;
	}

}
