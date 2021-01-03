/*
 * JndiDescriptor.java
 *
 * Created on December 16, 2002, 10:42 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.jndi;

import javax.naming.Context;

/**
 *
 * @author  kevind
 */


public class JndiDescriptor {
    private String name = null;
    private String initialContextFactory = null;
    private String providerUrl = null;
    private String urlPkgPrefixes = null;
    private Context context = null;
    
    /** Creates a new instance of JndiDescriptor */
    public JndiDescriptor() {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    public String getInitialContextFactory() {
        return initialContextFactory;
    }
    
    public void setInitialContextFactory(String newInitialContextFactory) {
        initialContextFactory = newInitialContextFactory;
    }
    
    public String getProviderUrl () {
        return providerUrl;
    }
    
    public void setProviderUrl (String newProviderUrl) {
        providerUrl = newProviderUrl;
    }
    
    public String getUrlPkgPrefixes() {
        return urlPkgPrefixes;
    }
    
    public void setUrlPkgPrefixes(String newUrlPkgPrefixes) {
        urlPkgPrefixes = newUrlPkgPrefixes;
    }
    
    public Context getContext() {
        return context;
    }

    public void setContext(Context newContext) {
        context = newContext;
    }

    
}
