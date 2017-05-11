package com.ez.portal.osgi.core.rest.service.activator;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.ez.portal.osgi.core.rest.service.impl.PortalServiceImpl;
import com.ez.portal.rest.service.PortalService;

public class Activator implements BundleActivator {

    public static final String TYPE = "TYPE";
    public static final String VALUE = "CORE_REST";

    private ServiceRegistration serviceRegistration;
    private Hashtable<String, String> dictionary;

    public ServiceRegistration getServiceRegistration() {
        return serviceRegistration;
    }

    public void setServiceRegistration(ServiceRegistration serviceRegistration) {
        this.serviceRegistration = serviceRegistration;
    }

    public Hashtable<String, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Hashtable<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void start(BundleContext context) throws Exception {
        PortalService portalService = new PortalServiceImpl();
        dictionary = new Hashtable<String, String>();
        dictionary.put(TYPE, VALUE);
        try {
            serviceRegistration = context.registerService(PortalService.class.getName(), portalService, dictionary);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e);
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        try {
            serviceRegistration.unregister();
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e);
        }
    }

}
