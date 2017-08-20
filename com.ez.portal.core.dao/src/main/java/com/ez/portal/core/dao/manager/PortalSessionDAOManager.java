package com.ez.portal.core.dao.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ez.portal.core.dao.intf.PortalSessionDAO;
import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.status.PortalSessionStatus;

/**
 * The {@code PortalSessionDAOManager} class manages all the operation related
 * to the {@code PortalSessionDAO} implementations.
 * 
 * @author azaz.akhtar
 *
 */
public class PortalSessionDAOManager {

	/**
	 * PortalSession timeout for every session
	 */
	public static final Long MAX_PORTAL_SESSION_DURATION = (long) (5 * 60 * 1000);

	/**
	 * 
	 * */
	private PortalSessionDAO portalSessionDAO;

	/**
	 * 
	 */
	public static final Map<String, PortalSession> PORTAL_SESSION_MAP = new HashMap<>();

	/**
	 * @return
	 */
	public PortalSessionDAO getPortalSessionDAO() {
		return portalSessionDAO;
	}

	/**
	 * @param portalSessionDAO
	 */
	public void setPortalSessionDAO(PortalSessionDAO portalSessionDAO) {
		this.portalSessionDAO = portalSessionDAO;
	}

	/**
	 * @param user
	 * @return
	 */
	public String createSession(User user) {
		String portalSessionToken = null;
		if (user != null) {
			try {
				PortalSession portalSession = portalSessionDAO
						.add(new PortalSession(PortalSessionStatus.ACTIVE_SESSION, generateSessionToken(), user));
				if (portalSession != null) {
					portalSessionToken = portalSession.getPortalSessionToken();
					PORTAL_SESSION_MAP.put(portalSessionToken, portalSession);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return portalSessionToken;
	}

	/**
	 * @param user
	 * @return
	 */
	public String createSuperUserSession(User user) {
		String portalSessionToken = null;
		if (user != null) {
			try {
				PortalSession portalSession = portalSessionDAO.createSuperUserSession(
						new PortalSession(PortalSessionStatus.ACTIVE_SESSION, generateSessionToken(), user));
				if (portalSession != null) {
					portalSessionToken = portalSession.getPortalSessionToken();
					PORTAL_SESSION_MAP.put(portalSessionToken, portalSession);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return portalSessionToken;
	}

	/**
	 * @param portalSessionToken
	 * @return
	 */
	public Boolean makeSessionOut(String portalSessionToken) {
		Boolean result = null;
		PortalSession portalSession = null;
		Long portalSessionActivityDuration = 0l;
		try {
			portalSession = portalSessionDAO.getPortalSessionByPortalSessionToken(portalSessionToken);
			if (portalSession != null) {
				portalSession.setPortalSessionStatus(PortalSessionStatus.IN_ACTIVE_SESSION);
				portalSession.setUpdatedAt(new Date());
				portalSessionActivityDuration = getPortalSessionActivityDuration(portalSession);
				if (portalSessionActivityDuration > MAX_PORTAL_SESSION_DURATION) {
					portalSession.setPortalSessionDuration(MAX_PORTAL_SESSION_DURATION);
				} else {
					portalSession.setPortalSessionDuration(portalSessionActivityDuration);
				}
				portalSessionDAO.addOrUpdate(portalSession);
				PORTAL_SESSION_MAP.remove(portalSessionToken);
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/**
	 * @param portalSessionToken
	 * @return
	 */
	public Boolean checkPortalSession(String portalSessionToken) {
		Boolean result = false;
		PortalSession portalSession = null;
		try {
			if (PORTAL_SESSION_MAP.containsKey(portalSessionToken)) {
				portalSession = PORTAL_SESSION_MAP.get(portalSessionToken);
			} else {
				portalSession = portalSessionDAO.getPortalSessionByPortalSessionToken(portalSessionToken);
			}
			if (portalSession != null) {
				setActiveUserAndShard(portalSession);
				if (portalSession.getPortalSessionStatus().equals(PortalSessionStatus.ACTIVE_SESSION)
						&& getPortalSessionInActivityDuration(portalSession) < MAX_PORTAL_SESSION_DURATION) {
					portalSession = portalSessionDAO.addOrUpdate(portalSession);
					PORTAL_SESSION_MAP.put(portalSessionToken, portalSession);
					result = true;
				} else {
					makeSessionOut(portalSessionToken);
				}
			} else {
				makeSessionOut(portalSessionToken);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/**
	 * @param portalSession
	 */
	private void setActiveUserAndShard(PortalSession portalSession) {
		try {
			portalSessionDAO.setActiveUserAndShard(portalSession);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param portalSession
	 * @return
	 */
	public Long getPortalSessionInActivityDuration(PortalSession portalSession) {
		Long portalSessionInActivityDuration = 0l;
		if (portalSession != null) {
			portalSessionInActivityDuration = new Date().getTime() - portalSession.getUpdatedAt().getTime();
		}
		return portalSessionInActivityDuration;
	}

	/**
	 * @param portalSession
	 * @return
	 */
	public Long getPortalSessionActivityDuration(PortalSession portalSession) {
		Long portalSessionInActivityDuration = 0l;
		if (portalSession != null) {
			portalSessionInActivityDuration = new Date().getTime() - portalSession.getCreatedAt().getTime();
		}
		return portalSessionInActivityDuration;
	}

	/**
	 * It generates a random unique session token
	 * 
	 * @return An unique random {@link String}
	 */
	public String generateSessionToken() {
		return UUID.randomUUID().toString().toUpperCase();
	}

}