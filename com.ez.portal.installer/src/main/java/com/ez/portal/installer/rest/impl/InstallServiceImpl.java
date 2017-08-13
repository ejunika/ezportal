package com.ez.portal.installer.rest.impl;

import com.ez.portal.installer.Installer;
import com.ez.portal.installer.response.InstallResponse;
import com.ez.portal.installer.rest.intf.InstallService;

public class InstallServiceImpl implements InstallService {

    private Installer installer;
    
    public Installer getInstaller() {
        return installer;
    }

    public void setInstaller(Installer installer) {
        this.installer = installer;
    }

    @Override
    public InstallResponse install() throws Exception {
        installer.install();
        return null;
    }

}
