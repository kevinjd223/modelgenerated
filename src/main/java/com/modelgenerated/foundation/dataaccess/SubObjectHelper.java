/*
 * SubObjectHelper.java
 *
 * Created on March 24, 2003, 7:04 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;


import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.foundation.identity.IdentityBuilder;
import com.modelgenerated.util.Assert;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * UNDONE; should be ValueObjectHelper
 * @author  kevind
 */
public class SubObjectHelper {
    
    /** Creates a new instance of SubObjectHelper */
    public SubObjectHelper() {
    }
    
    public static void setSubObjectId(Object parentObject, String attributeName, byte[] bytes) {
        Identity id = null;
        if (bytes != null) {
            id = IdentityBuilder.createIdentity(bytes);
        }
        setSubObjectId(parentObject, attributeName, id);
    }

    
    public static void setSubObjectId(Object parentObject, String attributeName, Identity id) {
        try {
            Class[] parameterClasses = new Class[1];
            parameterClasses[0] = Identity.class;
            Method method = parentObject.getClass().getMethod("set" + attributeName, parameterClasses);
            Assert.check(method != null, "method != null");

            Object[] parameters = new Object[1];
            parameters[0] = id;
            method.invoke(parentObject, parameters);
        
        } catch (NoSuchMethodException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (InvocationTargetException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (IllegalAccessException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        }
        
    }

    public static Identity getSubObjectId(Object parentObject, String attributeName) {
        try {
            Method method = parentObject.getClass().getMethod("get" + attributeName, (Class[])null);
            Assert.check(method != null, "method != null");

            return (Identity)method.invoke(parentObject, (Object[])null);
        
        } catch (NoSuchMethodException e) {
            throw new SubObjectHelperException("error getting sub object id for attribute: " + attributeName, e);
        } catch (InvocationTargetException e) {
            throw new SubObjectHelperException("error getting sub object id for attribute: " + attributeName, e);
        } catch (IllegalAccessException e) {
            throw new SubObjectHelperException("error getting sub object id for attribute: " + attributeName, e);
        }
        
    }
    
    public static void setReadOnlyVariable(Object parentObject, String attributeName, String value) {
        try {
            Class[] parameterClasses = new Class[1];
            parameterClasses[0] = String.class;
            Method method = parentObject.getClass().getMethod("set" + attributeName, parameterClasses);
            Assert.check(method != null, "method != null");

            Object[] parameters = new Object[1];
            parameters[0] = value;
            method.invoke(parentObject, parameters);
        
        } catch (NoSuchMethodException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (InvocationTargetException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (IllegalAccessException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        }
        
    }
    public static void setReadOnlyVariable(Object parentObject, String attributeName, boolean value) {
        try {
            Class[] parameterClasses = new Class[1];
            parameterClasses[0] = Boolean.class;
            Method method = parentObject.getClass().getMethod("set" + attributeName, parameterClasses);
            Assert.check(method != null, "method != null");

            Object[] parameters = new Object[1];
            parameters[0] = new Boolean(value);
            method.invoke(parentObject, parameters);
        
        } catch (NoSuchMethodException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (InvocationTargetException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (IllegalAccessException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        }
        
    }
    public static void setReadOnlyVariable(Object parentObject, String attributeName, double value) {
        try {
            Class[] parameterClasses = new Class[1];
            parameterClasses[0] = Double.class;
            Method method = parentObject.getClass().getMethod("set" + attributeName, parameterClasses);
            Assert.check(method != null, "method != null");

            Object[] parameters = new Object[1];
            parameters[0] = new Double(value);
            method.invoke(parentObject, parameters);
        
        } catch (NoSuchMethodException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (InvocationTargetException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (IllegalAccessException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        }
    }

    public static void setReadOnlyVariable(Object parentObject, String attributeName, Double value) {
        try {
            Class[] parameterClasses = new Class[1];
            parameterClasses[0] = Double.class;
            Method method = parentObject.getClass().getMethod("set" + attributeName, parameterClasses);
            Assert.check(method != null, "method != null");

            Object[] parameters = new Object[1];
            parameters[0] = value;
            method.invoke(parentObject, parameters);
        
        } catch (NoSuchMethodException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (InvocationTargetException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (IllegalAccessException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        }
    }

    public static void setReadOnlyVariable(Object parentObject, String attributeName, int value) {
        try {
            Class[] parameterClasses = new Class[1];
            parameterClasses[0] = Integer.class;
            Method method = parentObject.getClass().getMethod("set" + attributeName, parameterClasses);
            Assert.check(method != null, "method != null");

            Object[] parameters = new Object[1];
            parameters[0] = new Integer(value);
            method.invoke(parentObject, parameters);
        
        } catch (NoSuchMethodException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (InvocationTargetException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (IllegalAccessException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        }
    }

    public static void setReadOnlyVariable(Object parentObject, String attributeName, Date value) {
        try {
            Class[] parameterClasses = new Class[1];
            parameterClasses[0] = Date.class;
            Method method = parentObject.getClass().getMethod("set" + attributeName, parameterClasses);
            Assert.check(method != null, "method != null");

            Object[] parameters = new Object[1];
            parameters[0] = value;
            method.invoke(parentObject, parameters);
        
        } catch (NoSuchMethodException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (InvocationTargetException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        } catch (IllegalAccessException e) {
            throw new SubObjectHelperException("error setting sub object id for attribute: " + attributeName, e);
        }
        
    }

    public static void setReadOnlyVariable(Object parentObject, String attributeName, byte[] bytes) {
        Identity id = null;
        if (bytes != null) {
            id = IdentityBuilder.createIdentity(bytes);
        }
        setSubObjectId(parentObject, attributeName, id);
    }


}


