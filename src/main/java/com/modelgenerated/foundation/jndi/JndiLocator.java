/*
 * ServiceLocator.java
 *
 * Created on December 22, 2002, 7:26 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.jndi;

import com.modelgenerated.foundation.config.ConfigLocator;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.util.Assert;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.Context;

/**
 *
 * @author  kevind
 */
public class JndiLocator {
    private static JndiLocatorConfig jndiLocatorConfig;
    private static Map servers;
    
    static {
        try {
            servers = new HashMap();
            // Load and cache the home interfaces
            jndiLocatorConfig = (JndiLocatorConfig)ConfigLocator.findConfig(JndiLocatorConfig.CONFIG_NAME);

            Iterator i = jndiLocatorConfig.getJndiServers();
            while (i.hasNext()) {
                JndiDescriptor jndiDescriptor = (JndiDescriptor)i.next();

                /*
                Properties properties = new Properties();
                properties.put(Context.INITIAL_CONTEXT_FACTORY, jndiDescriptor.getInitialContextFactory());
                properties.put(Context.PROVIDER_URL, jndiDescriptor.getProviderUrl());
                properties.put(Context.URL_PKG_PREFIXES, jndiDescriptor.getUrlPkgPrefixes());
                Context jndiContext = new InitialContext(properties);
                */
                Context jndiContext = new InitialContext();

                servers.put(jndiDescriptor.getName(), jndiContext);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
            
            
    }
    
    
    /** Creates a new instance of ServiceLocator */
    public JndiLocator() {
    }
    
    
    public static void init() {
        // do nothing. forces stat
    }
    
    public static Context findServer(String name) {
    	Assert.check(name != null, "name != null");
    	Logger.debug(JndiLocator.class.getName(), "findServer: " + name);
    	
        if (servers.containsKey(name)) {
            return (Context)servers.get(name);
        }
        return null;        
    }
}
