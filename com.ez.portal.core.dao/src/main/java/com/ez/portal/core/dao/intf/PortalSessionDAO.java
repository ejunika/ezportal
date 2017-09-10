package com.ez.portal.core.dao.intf;

import com.ez.portal.core.entity.PortalSession;
import com.ez.portal.core.entity.UserSpace;

/**
 * @author azaz.akhtar
 *
 */
public interface PortalSessionDAO extends CommonDAO<PortalSession, Long> {
    
    /**
     * @param portalSessionToken
     * @return
     * @throws Exception
     */
    PortalSession getPortalSessionByPortalSessionToken(String portalSessionToken) throws Exception;

    /**
     * @param portalSession
     * @return
     * @throws Exception
     */
    PortalSession createSuperUserSession(PortalSession portalSession) throws Exception;
    
    /**
     * @param portalSession
     * @throws Exception
     */
    void setActiveUserAndShard(PortalSession portalSession) throws Exception;

	/**
	 * @param portalSessionToken
	 * @return
	 * @throws Exception
	 */
	UserSpace getUserSpaceByPortalSessionToken(String portalSessionToken) throws Exception;
    
}
