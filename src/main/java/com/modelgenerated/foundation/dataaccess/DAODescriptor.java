/*
 * DAODescriptor.java
 *
 * Created on January 25, 2003, 8:11 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.dataaccess.ConnectionLocator;


/**
 * Describes the DAO connection used for a valueObjectInterface.  
 */
public class DAODescriptor {
    private final String voInterface;
    private final String daoInterface;
    private final boolean audited;
    private final String connectionName; // default if no connectionName is set
    
    
    /** Creates a new instance of DAODescriptor */
    public DAODescriptor(String voInterface, String daoInterface, boolean audited, String connectionName) {
        this.voInterface = voInterface;
        this.daoInterface = daoInterface;
        this.audited = audited;
        this.connectionName = connectionName != null 
        		? connectionName 
        		: ConnectionLocator.DEFAULT_CONNECTION_NAME; // default if no connectionName is set
    }
    
    public String getVOInterface() {
        return voInterface;        
    }
    
    public String getDAOInterface() {
        return daoInterface;        
    }
    
    public boolean getAudited() {
        return audited;        
    }
    
    public String getConnectionName() {
        return connectionName;
    }
    
}
