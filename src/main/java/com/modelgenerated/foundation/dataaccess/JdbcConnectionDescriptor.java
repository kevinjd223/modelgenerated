/*
 * JdbcConnectionDescriptor.java
 *
 * Created on November 19, 2002, 8:27 AM
 * Copyright 2002-2005 Kevin Delargy.
*/

package com.modelgenerated.foundation.dataaccess;


/**
 *
 * @author  kevind
 */
public class JdbcConnectionDescriptor extends ConnectionDescriptor {
    private String driverClass;
    private String connectionString;
    
    /** Creates a new instance of ConnectionDescriptor */
    public JdbcConnectionDescriptor(String initName, String initDriverClass, String initConnectionString) {
        name = initName;
        driverClass = initDriverClass;
        connectionString = initConnectionString;
    }
    
    public String getDriverClass() {
        return driverClass;
    }
    
    public void setDriverClass (String newDriverClass){
        driverClass = newDriverClass;
    }

    public String getConnectionString() {
        return connectionString;
    }
    
    public void setConnectionString (String newConnectionString){
        connectionString = newConnectionString;
    }
    
    
}
