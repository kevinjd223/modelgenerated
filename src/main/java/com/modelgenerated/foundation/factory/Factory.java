/*
 * Factory.java
 *
 * Created on November 1, 2002, 6:47 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.factory;

import com.modelgenerated.util.Assert;
import com.modelgenerated.foundation.config.ConfigLocator;
import com.modelgenerated.foundation.logging.Logger;
/**
 *
 * @author  kevind
 */
public class Factory {
    private static FactoryConfig factoryConfig = (FactoryConfig)ConfigLocator.findConfig(FactoryConfig.CONFIG_NAME);
    
    /** Creates a new instance of Factory */
    public Factory() {
    }
    
    /*
     * This is the original method. Should migrate to passing in Class. 
     */
    public static Object createObject(String interfaceName) {
    	return createObject(interfaceName, Factory.class.getClassLoader());
    }
    /*
     * Uses the class loader of the interface. 
     */
    public static Object createObject(Class theInterface) {
        return createObject(theInterface.getName(), theInterface.getClassLoader());
    }

    public static Object createObject(String interfaceName, ClassLoader classLoader) {
        String className = null;
        try {
            Assert.check(interfaceName != null, "interfaceName != null");
            className = factoryConfig.findClassName(interfaceName);
            if (className == null) {
                throw new FactoryException("Could not find interface: " + interfaceName);                
            }
            Class newClass = Class.forName(className, true, classLoader);
            Assert.check(newClass != null, "newClass != null");
            Object object = newClass.newInstance();
            return object;
        } catch (ClassNotFoundException e) {
            throw new FactoryException("Could not find class: " + className, e);
        } catch (InstantiationException e) {
            throw new FactoryException("Could not instantiate class: " + className, e);
        } catch (IllegalAccessException e) {
            throw new FactoryException("Illegal access to class: " + className, e);
        }
    }
    
}
