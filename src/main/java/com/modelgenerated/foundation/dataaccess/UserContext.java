/*
 * UserContext.java
 *
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.authentication.Tenant;
import com.modelgenerated.foundation.identity.Identity;

import java.io.Serializable;


/**
 * This object is used by data access objects to give context to updates.
 * 
 * @author  kevind
 */
public class UserContext implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String userName;
    private final Identity accountId;
    private final Tenant tenant;
    private Object user;
    
	/** Creates a new instance of UserContext */
    public UserContext(String userName, Identity accountId, Tenant tenant) {
    	this.userName = userName;
    	this.accountId = accountId;
    	this.tenant = tenant;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public Identity getAccountId() {
        return accountId;
    }
    
    public Tenant getTenant() {
		return tenant;
	}

    public Identity getTenantId() {
		return tenant == null ? null : tenant.getId();
	}

    /** 
     * User attribute is a place holder for the application to store information about the user. The modelgenerated data access code does not use this in any way other 
     * than to pass it back calling application when requested.
     * TODO: remove this and make the caller extend this class to add type safe user record.   
     */
    public Object getUser() {
        return user;
    }
    
    public void setUser(Object newUser) {
        user = newUser;
    }
    
}
