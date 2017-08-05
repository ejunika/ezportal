package com.ez.portal.core.dao.intf;

import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.util.dao.intf.CommonDAO;

public interface PortalSessionDAO extends CommonDAO<PortalSession, Long> {
    
    PortalSession getPortalSessionByPortalSessionToken(String portalSessionToken) throws Exception;
    
}
