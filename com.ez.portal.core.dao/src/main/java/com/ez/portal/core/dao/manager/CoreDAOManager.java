package com.ez.portal.core.dao.manager;

public class CoreDAOManager {
    
    private UserDAOManager userDAOManager;
    
    private UserInfoDAOManager userInfoDAOManager;
    
    private PortalSessionDAOManager portalSessionDAOManager;

    public UserDAOManager getUserDAOManager() {
        return userDAOManager;
    }

    public void setUserDAOManager(UserDAOManager userDAOManager) {
        this.userDAOManager = userDAOManager;
    }

    public UserInfoDAOManager getUserInfoDAOManager() {
        return userInfoDAOManager;
    }

    public void setUserInfoDAOManager(UserInfoDAOManager userInfoDAOManager) {
        this.userInfoDAOManager = userInfoDAOManager;
    }

    public PortalSessionDAOManager getPortalSessionDAOManager() {
        return portalSessionDAOManager;
    }

    public void setPortalSessionDAOManager(PortalSessionDAOManager portalSessionDAOManager) {
        this.portalSessionDAOManager = portalSessionDAOManager;
    }

    
}
