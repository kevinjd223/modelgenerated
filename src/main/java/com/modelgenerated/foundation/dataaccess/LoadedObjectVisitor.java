/*
 * LoadedObjectVisitor.java
 *
 * Created on February 7, 2004, 7:27 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.util.Assert;

import java.util.HashMap; //UNDONE: use set.
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author  kevind
 */
public class LoadedObjectVisitor {
    
    /** Creates a new instance of JitLoadingSwitchVisitor */
    public LoadedObjectVisitor() {
    }
    
    public static Map<Identity,ValueObject> getLoadedObjects(ValueObject valueObject) {
        Map<Identity,ValueObject> loadedObjects = new HashMap<Identity,ValueObject>();
        LoadedObjectVisitor loadedObjectVisitor = new LoadedObjectVisitor();
        
        loadedObjectVisitor.getLoadedObjects(valueObject, loadedObjects);        
        return loadedObjects;
    }
    
    /*
     * recursive routine to find all loaded object in a graph.
     */
    private void getLoadedObjects(ValueObject valueObject, Map<Identity,ValueObject> loadedObjects) {
        if (loadedObjects.get(valueObject.getId()) != null) {
            return;
        }
        loadedObjects.put(valueObject.getId(), valueObject);
        
        Iterator<ValueObject> i = valueObject.getReferencedObjects();
        while (i != null && i.hasNext()) {
            ValueObject referencedObject = (ValueObject)i.next();
            
            Assert.check(referencedObject instanceof ValueObject, "referencedObject must be instanceof ValueObject");
            getLoadedObjects(referencedObject, loadedObjects);
        }
    }
}
