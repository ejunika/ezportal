package com.ez.portal.core.rest.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.util.EntityUtil;

/**
 * @author azaz.akhtar
 *
 */
public class PortalSessionServiceManager extends AbstractServiceManager {

	/**
	 * PortalSession timeout for every session
	 */
	public static final Long MAX_PORTAL_SESSION_DURATION = (long) (15 * 60 * 1000);

	/**
	 * 
	 */
	public static final Map<String, PortalSession> PORTAL_SESSION_MAP = new HashMap<>();

	/**
	 * @param user
	 * @return
	 */
	public String createSession(User user) {
		String portalSessionToken = null;
		if (user != null) {
			try {
				PortalSession portalSession = getDaoManager().getPortalSessionDAO()
						.add(new PortalSession(EntityUtil.ACTIVE_ENTRY, generateSessionToken(), user));
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
				PortalSession portalSession = getDaoManager().getPortalSessionDAO().createSuperUserSession(
						new PortalSession(EntityUtil.ACTIVE_ENTRY, generateSessionToken(), user));
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
			portalSession = getDaoManager().getPortalSessionDAO()
					.getPortalSessionByPortalSessionToken(portalSessionToken);
			if (portalSession != null) {
				portalSession.setEntryStatus(EntityUtil.ARCHIVED_ENTRY);
				portalSession.setUpdatedAt(new Date());
				portalSessionActivityDuration = getPortalSessionActivityDuration(portalSession);
				if (portalSessionActivityDuration > MAX_PORTAL_SESSION_DURATION) {
					portalSession.setPortalSessionDuration(MAX_PORTAL_SESSION_DURATION);
				} else {
					portalSession.setPortalSessionDuration(portalSessionActivityDuration);
				}
				getDaoManager().getPortalSessionDAO().addOrUpdate(portalSession);
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
				portalSession = getDaoManager().getPortalSessionDAO()
						.getPortalSessionByPortalSessionToken(portalSessionToken);
			}
			if (portalSession != null) {
				setActiveUserAndShard(portalSession);
				if (portalSession.getEntryStatus().equals(EntityUtil.ACTIVE_ENTRY)
						&& getPortalSessionInActivityDuration(portalSession) < MAX_PORTAL_SESSION_DURATION) {
					portalSession = getDaoManager().getPortalSessionDAO().addOrUpdate(portalSession);
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
			getDaoManager().getPortalSessionDAO().setActiveUserAndShard(portalSession);
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

	public User getActiveUser(String portalSessionToken) {
		return PORTAL_SESSION_MAP.get(portalSessionToken).getUser();
	}

}
