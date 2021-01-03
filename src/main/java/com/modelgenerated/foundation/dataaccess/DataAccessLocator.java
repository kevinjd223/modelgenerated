/*
 * DataAccessLocator.java
 *
 * Created on November 2, 2002, 9:57 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;


import com.modelgenerated.foundation.config.ConfigLocator;
import com.modelgenerated.foundation.factory.Factory;
import com.modelgenerated.util.Assert;
import java.util.HashMap;
import java.util.Map;

/**
 * Functions as a factory for DAO's.
 * DataAccessLocator can static load DAO's and treat them as singletons since they must be thread-safe.
 *
 * @author  kevind
 */
public class DataAccessLocator {
    private static DataAccessConfig dataAccessConfig = (DataAccessConfig)ConfigLocator.findConfig(DataAccessConfig.CONFIG_NAME);
    private static Map daoMap = new HashMap();
    
    /** Creates a new instance of DataAccessLocator */
    public DataAccessLocator() {        
    }
    
    public static DataAccessObject findDAO(String valueObjectInterface) {
        DataAccessObject dataAccessObject = (DataAccessObject)daoMap.get(valueObjectInterface);
        if (dataAccessObject == null) {
            DAODescriptor daoDescriptor = dataAccessConfig.findDAODescriptor(valueObjectInterface);
            Assert.check(daoDescriptor != null, "daoDescriptor != null " + valueObjectInterface);
            String daoInterface = daoDescriptor.getDAOInterface();        

            dataAccessObject = (DataAccessObject)Factory.createObject(daoInterface);

            dataAccessObject.init(daoDescriptor);
            daoMap.put(valueObjectInterface, dataAccessObject);            
            
            //Logger.debug("DataAccessLocator", "created DAO : " + valueObjectInterface);
        } else {
            //Logger.debug("DataAccessLocator", "got DAO from Map: " + valueObjectInterface);
        }
        
        return dataAccessObject;
    }
    
    
    public static DataAccessObject findDAO(Class valueObjectInterface) {
        DataAccessObject dataAccessObject = (DataAccessObject)daoMap.get(valueObjectInterface);
        if (dataAccessObject == null) {
        	String valueObjectInterfaceName = valueObjectInterface.getName();
            DAODescriptor daoDescriptor = dataAccessConfig.findDAODescriptor(valueObjectInterfaceName);
            Assert.check(daoDescriptor != null, "daoDescriptor != null " + valueObjectInterface);
            String daoInterfaceName = daoDescriptor.getDAOInterface();        

            dataAccessObject = (DataAccessObject)Factory.createObject(daoInterfaceName, valueObjectInterface.getClassLoader());

            dataAccessObject.init(daoDescriptor);
            daoMap.put(valueObjectInterface, dataAccessObject);            
            
            //Logger.debug("DataAccessLocator", "created DAO : " + valueObjectInterface);
        } else {
            //Logger.debug("DataAccessLocator", "got DAO from Map: " + valueObjectInterface);
        }
        
        return dataAccessObject;
    }
    
    
    
}
