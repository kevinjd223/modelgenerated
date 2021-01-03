/*
 * ConnectionLocator.java
 *
 * Created on November 17, 2002, 5:06 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.debug.DisplayUtil;
import com.modelgenerated.foundation.config.ConfigLocator;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.util.Assert;
import com.modelgenerated.util.StringUtil;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import javax.naming.NamingEnumeration;

/**
 *
 * @author  kevind
 */
public class ConnectionLocator {
    public static final String DEFAULT_CONNECTION_NAME = "default";
    private static ConnectionConfig connectionConfig = (ConnectionConfig)ConfigLocator.findConfig(ConnectionConfig.CONFIG_NAME);
    
    /** Creates a new instance of ConnectionLocator */
    public ConnectionLocator() {
    }

    public static Connection findDefaultConnection () {
        return findConnection (DEFAULT_CONNECTION_NAME);
    }
    
    public static Connection findConnection (String connectionName) {
        Assert.check(connectionName != null, "connectionName != null");
        Assert.check(!StringUtil.isEmpty(connectionName), "!StringUtil.isEmpty(connectionName)");
        
        ConnectionDescriptor connectionDescriptor = connectionConfig.findConnectionDescriptor(connectionName);
        Assert.check(connectionDescriptor != null, "connectionDescriptor != null");
        if (connectionDescriptor instanceof JdbcConnectionDescriptor) {
            return findJdbcConnection ((JdbcConnectionDescriptor)connectionDescriptor);    
        } else {
            return findJndiConnection ((JndiConnectionDescriptor)connectionDescriptor);    
        }
    }
    
    
    public static Connection findJdbcConnection (JdbcConnectionDescriptor connectionDescriptor) {        
        Connection conn = null;
        try {        
            // try to instantiate the driver so we can catch exception if it does not
        	{
        	   Class.forName(connectionDescriptor.getDriverClass()).newInstance(); // why instantiate and not doing anything with it? kd- 2014-05-14
        	}
            conn = DriverManager.getConnection(connectionDescriptor.getConnectionString());
            return conn;
            
        } catch (ClassNotFoundException e) {
            throw new ConnectionLocatorException("Error connecting to " + connectionDescriptor.getName(), e);
        } catch (IllegalAccessException e) {
            throw new ConnectionLocatorException("Error connecting to " + connectionDescriptor.getName(), e);
        } catch (InstantiationException e) {
            throw new ConnectionLocatorException("Error connecting to " + connectionDescriptor.getName(), e);
        } catch (SQLException e) {
            Logger.debug(ConnectionLocator.class.getCanonicalName(), "SQLException" + e.getMessage());
        	throw new ConnectionLocatorException("Error connecting to " + connectionDescriptor.getName(), e);
        }
    }  

    public static Connection findJndiConnection (JndiConnectionDescriptor jndiConnectionDescriptor) {        
        Connection conn = null;
        try {        	
            InitialContext initialContext = new InitialContext();
            Assert.check(initialContext != null, "initialContext != null");

            DataSource dataSource = (DataSource)initialContext.lookup(jndiConnectionDescriptor.getJndiName());
            Assert.check(dataSource != null, "dataSource != null");
            
            conn = dataSource.getConnection();
            Assert.check(conn != null, "conn != null");
            
            return conn;
        } catch (NamingException e) {
            throw new ConnectionLocatorException("Error connecting to ", e);
        } catch (SQLException e) {
            throw new ConnectionLocatorException("Error connecting to ", e);
        }
    }  

    
    public static void displayContext (String name, int indent) throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
        properties.put(Context.PROVIDER_URL, "localhost:1099");
        //properties.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
        
        InitialContext initialContext = new InitialContext(properties);
        NamingEnumeration names = initialContext.list(name);
        while (names.hasMore()) {
            NameClassPair nameClassPair = (NameClassPair)names.next();
            Logger.debug(ConnectionLocator.class.getCanonicalName(), DisplayUtil.indent(indent) + "naming: " + nameClassPair.getName() + ", class: " + nameClassPair.getClassName() );                
            //Object o = initialContext.lookup(nameClassPair.getName());
            if (nameClassPair.getClassName().equals("org.jnp.interfaces.NamingContext")) {            
                //displayContext (name + "/" + nameClassPair.getName() , indent+1);
            }
        }
    }
    
}

