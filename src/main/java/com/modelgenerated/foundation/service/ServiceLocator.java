/*
 * ServiceLocator.java
 *
 * Created on December 22, 2002, 7:26 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.service;

import com.modelgenerated.foundation.config.ConfigLocator;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.util.Assert;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author  kevind
 */
public class ServiceLocator  {
    private static String loggerCategory = "com.modelgenerated.foundation.ServiceLocator";
    private static ServiceLocatorConfig serviceLocatorConfig;

    
    /** Creates a new instance of ServiceLocator */
    public ServiceLocator() {
    }
    
    public static void init() {
        // do nothing. forces stat
    }

    public static <T> T findService(Class<T> remoteClass) {
        return (T)findService(remoteClass.getName());
    }

    public static Object findService(String remoteName) {
        Assert.check(remoteName != null, "remoteName != null");
        Object remoteInterface = null;
        try {
            serviceLocatorConfig = (ServiceLocatorConfig)ConfigLocator.findConfig(ServiceLocatorConfig.CONFIG_NAME);
            ServiceDescriptor serviceDescriptor = serviceLocatorConfig.findServiceDescriptor(remoteName);
            Assert.check(serviceDescriptor != null, "serviceDescriptor != null");

            final Hashtable jndiProperties = new Hashtable();
            jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            final Context context = new InitialContext(jndiProperties);
            remoteInterface =  context.lookup(serviceDescriptor.getName());
        } catch (Exception e) {
            Logger.debug(loggerCategory, e);
            throw new ServiceLocatorException("Could not find service: " + remoteName, e);
        }
            
        return remoteInterface;        
    }
}
