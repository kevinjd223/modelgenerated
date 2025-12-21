/* UserContext.java
 *
 * Copyright 2002-2024 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.authentication.Tenant;
import com.modelgenerated.foundation.identity.Identity;
import java.io.Serializable;

/**
 * This object is used by data access objects to give context to database operations.
 * This class is immutable
 * @author  kevind
 */

public class UserContext implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String userName;
    private final Identity accountId;
    private final Tenant tenant;
    private final Object user;

	/** Creates a new instance of UserContext */
    public UserContext(String userName, Identity accountId, Tenant tenant) {
        this.userName = userName;
        this.accountId = accountId;
        this.tenant = tenant;
        this.user = null;
    }

    /**
     * Constructor that allow user to be set.
     * @param userContext
     * @param _user
     */
    public UserContext(UserContext userContext, Object _user) {
        this.userName = userContext.userName;
        this.accountId = userContext.accountId;
        this.tenant = userContext.tenant;
        this.user = _user;
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
     * User attribute is a place for the application to store information about the user. The modelgenerated data access code does not use this in any way other
     * than to pass it back to the calling application when requested.
     */
    public Object getUser() {
        return user;
    }

}
