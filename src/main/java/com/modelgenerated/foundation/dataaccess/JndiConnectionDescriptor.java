/*
 * JndiConnectionDescriptor.java
 *
 * Created on November 19, 2002, 8:27 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.debug.DisplayBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author  kevind
 */
public class JndiConnectionDescriptor extends ConnectionDescriptor {
    private String jndiName;
    
    /** Creates a new instance of ConnectionDescriptor */
    public JndiConnectionDescriptor(String initName, String initJndiName) {
        name = initName;
        jndiName = initJndiName;
    }
    
    
    public String getJndiName() {
        return jndiName;
    }
    public void setJndiName(String newJndiName) {
        jndiName = newJndiName;
    }
    
}
