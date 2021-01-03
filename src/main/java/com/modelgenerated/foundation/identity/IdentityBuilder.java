/*
 * IdentityBuilder.java
 *
 * Created on December 4, 2002, 6:18 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.identity;

import com.modelgenerated.util.Assert;

/**
 * Contains classes to create Identity Objects.
 * @author  kevind
 */
public class IdentityBuilder {
    
    /** Creates a new instance of IdentityBuilder */
    public IdentityBuilder() {
    }
    
    public static synchronized Identity createIdentity()  {
        IdentityUUIDImpl id = new IdentityUUIDImpl();
        return id;     
    }
    
    public static Identity createIdentity(String initValue) {
    	Assert.check(initValue != null, "initValue != null");
        return new IdentityUUIDImpl(initValue);
    }

    public static Identity createIdentity(byte[] initValue) {
		Assert.check(initValue != null, "initValue != null");
        return new IdentityUUIDImpl(initValue);
    }
    
}
