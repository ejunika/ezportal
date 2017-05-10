package com.ez.portal.core.service.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("CORE-SERVICE STARTED");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("CORE-SERVICE STOPPED");
    }

}
