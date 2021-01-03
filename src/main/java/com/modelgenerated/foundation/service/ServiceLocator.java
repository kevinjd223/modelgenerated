/*
 * ServiceLocator.java
 *
 * Created on December 22, 2002, 7:26 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.service;

import com.modelgenerated.foundation.config.ConfigLocator;
import com.modelgenerated.foundation.jndi.JndiDescriptor;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.util.Assert;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author  kevind
 */
public class ServiceLocator  {
    private static String loggerCategory = "com.modelgenerated.foundation.ServiceLocator";
    private static ServiceLocatorConfig serviceLocatorConfig;
    private static Map homes;
    
    static {
        try {
            System.out.println("ServiceLocator initialization");
            homes = new HashMap();
            // Load and cache the home interfaces
            serviceLocatorConfig = (ServiceLocatorConfig)ConfigLocator.findConfig(ServiceLocatorConfig.CONFIG_NAME);

            Iterator i = serviceLocatorConfig.getServices();
            while (i.hasNext()) {
                ServiceDescriptor serviceDescriptor = (ServiceDescriptor)i.next();

                if (EjbVersionEnum.EJB2 == serviceDescriptor.getEjbVersion()) {
                    JndiDescriptor jndiDescriptor = (JndiDescriptor)serviceDescriptor.getJndiDescriptor();
                    Context jndiContext = jndiDescriptor.getContext();

                    String remote = serviceDescriptor.getRemote();
                    String name = serviceDescriptor.getName();
                    System.out.println("remote: " + remote + ", name:" + name);
                    
                    Object home = jndiContext.lookup(name);
                    Assert.check(home != null, "home != null");

                    homes.put(remote, home);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // can't throw error from inside static block
            // other exception will be throw when the user tries to use the data that was being initialized here.
            //System.exit(-1);
        }
            
            
    }
    
    
    /** Creates a new instance of ServiceLocator */
    public ServiceLocator() {
    }
    
    public static void init() {
        // do nothing. forces stat
    }
    
    public static Object findService(String remoteName) {
        Assert.check(remoteName != null, "remoteName != null");
        Object remoteInterface = null;
        try {
            //Logger.debug(loggerCategory, "findService *" + remoteName + "*");
            serviceLocatorConfig = (ServiceLocatorConfig)ConfigLocator.findConfig(ServiceLocatorConfig.CONFIG_NAME);
            ServiceDescriptor serviceDescriptor = serviceLocatorConfig.findServiceDescriptor(remoteName);
            Assert.check(serviceDescriptor != null, "serviceDescriptor != null");
            //Logger.debug(loggerCategory, "serviceDescriptor.getEjbVersion() *" + serviceDescriptor.getEjbVersion() + "*");
            
            /*
            Object home = null;
            Iterator i = serviceLocatorConfig.getServices();
            while (i.hasNext()) {
                ServiceDescriptor serviceDescriptorEnum = (ServiceDescriptor)i.next();

                JndiDescriptor jndiDescriptor = (JndiDescriptor)serviceDescriptorEnum.getJndiDescriptor();
                Context jndiContext = jndiDescriptor.getContext();

                String remote = serviceDescriptorEnum.getRemote();
                if (remoteName.equals(remote)) {
                    String name = serviceDescriptorEnum.getName();
                    System.out.println("remote: " + remote + ", name:" + name);
                    home = jndiContext.lookup(name);
                    Assert.check(home != null, "home != null");
                }
            }
            */
            
            if (EjbVersionEnum.EJB2 == serviceDescriptor.getEjbVersion()) {
	            Object homeObject = homes.get(remoteName);
	            Assert.check(homeObject != null, "homeObject != null");
	
	            //Logger.debug(loggerCategory, "serviceDescriptor.getName(): " + serviceDescriptor.getName());
	            //Logger.debug(loggerCategory, "serviceDescriptor.getHome(): " + serviceDescriptor.getHome());
	            //Logger.debug(loggerCategory, "serviceDescriptor.getEjbClass(): " + serviceDescriptor.getEjbClass());
	            //Logger.debug(loggerCategory, "serviceDescriptor.getRemote(): " + serviceDescriptor.getRemote());
	
	            Class homeClass = Class.forName(serviceDescriptor.getHome()); 
	            Class remoteClass = Class.forName(serviceDescriptor.getRemote()); 
	
	            //Logger.debug(loggerCategory, "homeObject: " + homeObject.getClass().getName());
	            //Logger.debug(loggerCategory, "homeClass: " + homeClass);
	            //Logger.debug(loggerCategory, "about to narrow");
	
	            //Logger.debug(loggerCategory, "homeClass.getClassLoader() " + homeClass.getClassLoader());
	            //Logger.debug(loggerCategory, "remoteClass.getClassLoader() " + remoteClass.getClassLoader());
	            //Logger.debug(loggerCategory, "ServiceLocator.class.getClassLoader() " + ServiceLocator.class.getClassLoader());
	            //Logger.debug(loggerCategory, "homeObject.getClass().getClassLoader() " + homeObject.getClass().getClassLoader());
	
	
	            EJBHome ejbhome = (EJBHome)PortableRemoteObject.narrow(homeObject, homeClass);
	            //EJBHome home = (EJBHome)homeObject;
	            //Logger.debug(loggerCategory, "narrowed first");
	
	            Method createMethod = homeClass.getMethod("create", null);
	            Assert.check(createMethod != null, "createMethod != null");
	            Object remoteObject = createMethod.invoke(ejbhome, null);
	            Assert.check(remoteObject != null, "remoteObject != null");
	
	            // make sure it can be narrowed
	            Logger.debug(loggerCategory, "remoteObject: " + remoteObject);
	            //Logger.debug(loggerCategory, "remoteClass: " + remoteClass);
	            remoteInterface = (EJBObject)PortableRemoteObject.narrow(remoteObject, remoteClass);
	            //Logger.debug(loggerCategory, "narrowed second");
            } else {
    			final Hashtable jndiProperties = new Hashtable();
    	        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    	        final Context context = new InitialContext(jndiProperties);
    	        remoteInterface =  context.lookup(serviceDescriptor.getName());            	
            }
        } catch (Exception e) {
            Logger.debug(loggerCategory, e);
            throw new ServiceLocatorException("Could not find service: " + remoteName, e);
        }
            
        return remoteInterface;        
    }
}
