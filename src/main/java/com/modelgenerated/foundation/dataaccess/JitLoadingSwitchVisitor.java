/*
 * JitLoadingSwitchVisitor.java
 *
 * Created on February 7, 2004, 7:27 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.util.Assert;

import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author  kevind
 */
public class JitLoadingSwitchVisitor {
    
    /** Creates a new instance of JitLoadingSwitchVisitor */
    public JitLoadingSwitchVisitor() {
    }
    
    public static void enableJitLoading(ValueObject valueObject) {
        Assert.check(valueObject != null, "valueObject != null");
        Logger.info("com.modelgenerated.foundation.dataaccess.JitLoadingSwitchVisitor", "enable: " + valueObject.getClass().toString());
        
        Map visitedMap = new HashMap();
        JitLoadingSwitchVisitor jitLoadingSwitchVisitor = new JitLoadingSwitchVisitor();
        
        jitLoadingSwitchVisitor.switchJitLoading(valueObject, true, visitedMap);
        
        Logger.info("com.modelgenerated.foundation.dataaccess.JitLoadingSwitchVisitor", "enable visitedMap.size(): " + visitedMap.size());
    }
    
    public static void disableJitLoading(ValueObject valueObject) {
        Assert.check(valueObject != null, "valueObject != null");
        Logger.info("com.modelgenerated.foundation.dataaccess.JitLoadingSwitchVisitor", "disable: " + valueObject.getClass().toString());
        
        Map visitedMap = new HashMap();
        JitLoadingSwitchVisitor jitLoadingSwitchVisitor = new JitLoadingSwitchVisitor();
        
        jitLoadingSwitchVisitor.switchJitLoading(valueObject, false, visitedMap);

        Logger.info("com.modelgenerated.foundation.dataaccess.JitLoadingSwitchVisitor", "disable visitMap.size(): " + visitedMap.size());
    }
    
    /*
     * recursive routine to enable jit loading
     */
    private void switchJitLoading(ValueObject valueObject, boolean enable, Map visitedMap) {        
        Assert.check(valueObject != null, "valueObject != null");
        Assert.check(visitedMap != null, "visitedMap != null");
        
        if (wasVisited(visitedMap, valueObject)) {
            return;
        }

        //Logger.debug(this, "switchJitLoading " + valueObject.getClass().toString());
        
        addToVisited(visitedMap, valueObject);
        valueObject.setIsJITLoadingEnabled(enable);
        Iterator<ValueObject> i = valueObject.getReferencedObjects();
        if (i != null) { // if not persisted it will be null 
            while (i.hasNext()) {
                ValueObject referencedObject = (ValueObject)i.next();
                Assert.check(referencedObject instanceof ValueObject, "referenced Object must be instanceof ValueObject");
                switchJitLoading(referencedObject, enable, visitedMap);
            }
        }
    }
    
    private boolean wasVisited(Map visitedMap, ValueObject valueObject) {
        // this was this
        //return (visitedMap.get(valueObject.getId()) != null);        
        List list = (List)visitedMap.get(valueObject.getId());
        if (list != null) {
            Iterator i = list.iterator();
            while (i.hasNext()) {
                ValueObject valueObjectEnum = (ValueObject)i.next();
                if (valueObject == valueObjectEnum ) {
                    return true; 
                }
            }
        }
        return false;
    }
    private void addToVisited(Map visitedMap, ValueObject valueObject) {
        // this was this
        //visitedMap.put(valueObject.getId(), valueObject);
        List list = (List)visitedMap.get(valueObject.getId());
        if (list == null) {
            list = new ArrayList();
            visitedMap.put(valueObject.getId(), list);
        }
        list.add(valueObject);
    }
    
}
