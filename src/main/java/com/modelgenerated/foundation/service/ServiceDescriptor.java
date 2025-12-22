/*
 * ServiceDescriptor.java
 *
 * Created on December 16, 2002, 10:43 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.service;

import com.modelgenerated.foundation.EjbVersionEnum;
import com.modelgenerated.foundation.jndi.JndiDescriptor;

/**
 *
 * @author  kevind
 */
public class ServiceDescriptor {
    private String remote = null;
    private String name = null;
    private String home = null;
    private String ejbClass = null;
    private EjbVersionEnum ejbVersion = null;
    private JndiDescriptor jndiDescriptor = null;
    
    public static final String EJB_VERSION2 = "2";
    public static final String EJB_VERSION3 = "3";
    
    
    
    /** Creates a new instance of ServiceDescriptor */
    public ServiceDescriptor() {
    }
    
    public String getRemote() {
        return remote;
    }
    
    public void setRemote(String newRemote) {
        remote = newRemote;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String newName) {
        name = newName;
    }
    
    public String getHome () {
        return home;
    }
    
    public void setHome(String newHome) {
        home = newHome;
    }

    public String getEjbClass () {
        return ejbClass;
    }
    
    public void setEjbClass(String newEjbClass) {
        ejbClass = newEjbClass;
    }    
            
    public EjbVersionEnum getEjbVersion() {
		return ejbVersion;
	}

	public void setEjbVersion(EjbVersionEnum ejbVersion) {
		this.ejbVersion = ejbVersion;
	}

	public JndiDescriptor getJndiDescriptor() {
        return jndiDescriptor;
    }
    
    public void setJndiDescriptor(JndiDescriptor newJndiDescriptor) {
        jndiDescriptor = newJndiDescriptor;
    }
    
    
}
