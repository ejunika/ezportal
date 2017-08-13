package com.ez.portal.core.dao.intf;

import com.ez.portal.core.entity.PortalSession;

public interface PortalSessionDAO extends CommonDAO<PortalSession, Long> {
    
    PortalSession getPortalSessionByPortalSessionToken(String portalSessionToken) throws Exception;

    PortalSession createSuperUserSession(PortalSession portalSession) throws Exception;
    
}
