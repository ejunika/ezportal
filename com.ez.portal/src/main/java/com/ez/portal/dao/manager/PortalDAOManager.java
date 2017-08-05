package com.ez.portal.dao.manager;

import com.ez.portal.core.dao.manager.CoreDAOManager;

public class PortalDAOManager {
    
    private CoreDAOManager coreDAOManager;

    public CoreDAOManager getCoreDAOManager() {
        return coreDAOManager;
    }

    public void setCoreDAOManager(CoreDAOManager coreDAOManager) {
        this.coreDAOManager = coreDAOManager;
    }
    
}
