package com.ez.portal.core.dao.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ez.portal.core.dao.intf.PortalSessionDAO;
import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.User;
import com.ez.portal.core.status.PortalSessionStatus;

public class PortalSessionDAOManager {

    public static final Long MAX_PORTAL_SESSION_DURATION = (long) (5 * 60 * 1000);

    private PortalSessionDAO portalSessionDAO;
    
    public static final Map<String, PortalSession> PORTAL_SESSION_MAP = new HashMap<>();

    public PortalSessionDAO getPortalSessionDAO() {
        return portalSessionDAO;
    }

    public void setPortalSessionDAO(PortalSessionDAO portalSessionDAO) {
        this.portalSessionDAO = portalSessionDAO;
    }

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
    
    public Boolean checkPortalSession(String portalSessionToken) {
        Boolean result = false;
        PortalSession portalSession = null;
        try {
            if (PORTAL_SESSION_MAP.containsKey(portalSessionToken)) {
                portalSession = PORTAL_SESSION_MAP.get(portalSessionToken);
            } else {
                portalSession = portalSessionDAO.getPortalSessionByPortalSessionToken(portalSessionToken);
            }
            if (portalSession != null
                    && portalSession.getPortalSessionStatus().equals(PortalSessionStatus.ACTIVE_SESSION)) {
                if (getPortalSessionInActivityDuration(portalSession) < MAX_PORTAL_SESSION_DURATION) {
                    portalSession = portalSessionDAO.addOrUpdate(portalSession);
                    PORTAL_SESSION_MAP.put(portalSessionToken, portalSession);
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
    
    public Boolean isValidPortalSession(String portalSessionToken) {
        Boolean validity = false;
        if (checkPortalSession(portalSessionToken)) {
            validity = true;
        } else {
            makeSessionOut(portalSessionToken);
        }
        return validity;
    }

    public Long getPortalSessionInActivityDuration(PortalSession portalSession) {
        Long portalSessionInActivityDuration = 0l;
        if (portalSession != null) {
            portalSessionInActivityDuration = new Date().getTime() - portalSession.getUpdatedAt().getTime();
        }
        return portalSessionInActivityDuration;
    }
    
    public Long getPortalSessionActivityDuration(PortalSession portalSession) {
        Long portalSessionInActivityDuration = 0l;
        if (portalSession != null) {
            portalSessionInActivityDuration = new Date().getTime() - portalSession.getCreatedAt().getTime();
        }
        return portalSessionInActivityDuration;
    }

    public String generateSessionToken() {
        return UUID.randomUUID().toString().toUpperCase();
    }

}
