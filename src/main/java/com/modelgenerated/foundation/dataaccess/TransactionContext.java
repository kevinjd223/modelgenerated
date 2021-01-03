/*
 * TransactionContext.java
 *
 * Created on February 5, 2003, 7:47 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;


import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.util.DateUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents a database transaction.
 * Has a list of connections that are part of this transaction.
 *
 * @author  kevind
 */
public class TransactionContext {    
    UserContext userContext;
    Date transactionTime = DateUtil.getNow();
    Map<String,Connection> connectionMap = new HashMap<String,Connection>();
    
    /** Creates a new instance of TransactionContext */
    public TransactionContext(UserContext initialUserContext) {
        userContext = initialUserContext;
    }
    
    public Connection findConnection(String connectionName) {
        if (connectionMap.containsKey(connectionName)) {
            return (Connection)connectionMap.get(connectionName);
        }
        Connection connection = ConnectionLocator.findConnection(connectionName);
        if (connection != null) {
            connectionMap.put(connectionName, connection);
        }        
        return connection;
    }
    public UserContext getUserContext() {
        return userContext;
    }
    
    public void close() {
        // todo: just use entrySet()
        for (String key : connectionMap.keySet()) {
            Connection connection = (Connection)connectionMap.get(key);
            try {
                connection.close();
            } catch (SQLException e) {
                // just log a warning and continue
                Logger.warn(this, "SQLException closing connection: " + key);
            }
        }
        
    }
    public Date getTransactionTime() {
        return transactionTime;
    }
    public void setTransactionTime(Date newTransactionTime) {
        transactionTime = newTransactionTime;
    }
    
}
