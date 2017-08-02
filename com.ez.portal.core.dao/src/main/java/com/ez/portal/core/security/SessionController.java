package com.ez.portal.core.security;

import com.ez.portal.core.dao.intf.LoginDAO;

public class SessionController implements Runnable {

    private LoginDAO loginDAO;
    
    private Long portalSessionId;
    
    public SessionController() {
        super();
    }
    
    public SessionController(LoginDAO loginDAO, Long portalSessionId) {
        super();
        this.loginDAO = loginDAO;
        this.portalSessionId = portalSessionId;
    }
    
    public LoginDAO getLoginDAO() {
        return loginDAO;
    }
    
    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }
    
    public Long getPortalSessionId() {
        return portalSessionId;
    }

    public void setPortalSessionId(Long portalSessionId) {
        this.portalSessionId = portalSessionId;
    }

    @Override
    public void run() {
        if (loginDAO != null && portalSessionId != null) {
            try {
                Thread.sleep(60 * 1000);
                loginDAO.makeSessionOut(portalSessionId);
                System.out.println("SESSION OUT");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
