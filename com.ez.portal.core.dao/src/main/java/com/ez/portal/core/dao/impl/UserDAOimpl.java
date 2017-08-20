package com.ez.portal.core.dao.impl;

import java.util.Date;
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
import com.ez.portal.core.status.PasswordStatus;
import com.ez.portal.core.status.PortalSessionStatus;
import com.ez.portal.shard.util.PortalHibernateUtil;

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
    public User createUser(User user, Password password, List<UserInfo> userInfos) throws Exception {
        Session session = null;
        Transaction transaction = null;
        Date currentTime = new Date();
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            if (user != null) {
                user.setCreatedAt(currentTime);
                user.setUpdatedAt(currentTime);
                user.setShardKey(getEzShardUtil().getShardKey());
                session.save(user);
                if (password != null) {
                    password.setCreatedAt(currentTime);
                    password.setUpdatedAt(currentTime);
                    password.setShardKey(getEzShardUtil().getShardKey());
                    password.setPasswordStatus(PasswordStatus.ACTIVE_PASSWORD);
                    password.setUser(user);
                    session.save(password);
                    session.flush();
                    session.clear();
                }
                if (userInfos != null) {
                    for (UserInfo userInfo : userInfos) {
                        userInfo.setCreatedAt(currentTime);
                        userInfo.setUpdatedAt(currentTime);
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
            criteria.add(Restrictions.eq("portalSessionStatus", PortalSessionStatus.ACTIVE_SESSION));
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
    		criteria.add(Restrictions.eq("portalSessionStatus", PortalSessionStatus.ACTIVE_SESSION));
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
        Date currentTime = new Date();
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            if (user != null) {
                user.setCreatedAt(currentTime);
                user.setUpdatedAt(currentTime);
                user.setShardKey(getEzShardUtil().getShardKey());
                session.save(user);
                session.flush();
                session.clear();
                if (password != null) {
                    password.setCreatedAt(currentTime);
                    password.setUpdatedAt(currentTime);
                    password.setShardKey(getEzShardUtil().getShardKey());
                    password.setPasswordStatus(PasswordStatus.ACTIVE_PASSWORD);
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
            session = PortalHibernateUtil.sessionFactory.openSession();
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
