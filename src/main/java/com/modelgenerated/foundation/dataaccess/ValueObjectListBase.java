/*
 * ValueObjectListBase.java
 *
 * Created on May 23, 2003, 1:24 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.debug.DisplayBuffer;
import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.foundation.identity.IdentityBuilder;
import com.modelgenerated.util.Assert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author  kevind
 */
public class ValueObjectListBase<E> extends ArrayList<E> implements ValueObjectList<E>, Displayable {
	private static final long serialVersionUID = 1L;
	boolean isFullyLoaded = false;
    
    /** Creates a new instance of ValueObjectListBase */
    public ValueObjectListBase() {
    }

    /**
     * Constructor with initialCapacity
     */
    // public ValueObjectListBase(int initialCapacity) {
    //     super(initialCapacity);
    // }

    /**
     * Find the value object in the list that is identified by idString
     * @param idString
     * @return matching value object
     */
    @Override
    public Object findById(String idString) {
        Assert.check(idString != null, "idString != null");
        
        Identity id = IdentityBuilder.createIdentity(idString);
        Assert.check(id != null, "id != null");
        
        return findById(id);
    }
    
    /** 
     * Find the value object in the list that is identified by id
     * @param id
     * @return matching value object
     */
    @Override
    public Object findById(Identity id) {
        Assert.check(id != null, "id != null");
        
        Iterator<E> i = this.iterator();
        while (i.hasNext()) {
            ValueObject o = (ValueObject)i.next();
            if (id.equals(o.getId())) {
                return o;
            }
        }
        return null;
    }
    
    @Override
    public boolean getIsFullyLoaded() {
        return isFullyLoaded;
    }
    @Override
    public void setIsFullyLoaded(boolean isFullyLoaded) {
        this.isFullyLoaded = isFullyLoaded;
    }
    

    @Override
    public void sort(FieldAttribute fieldAttribute) {
        ValueObjectComparator comparator = new ValueObjectComparator();
        comparator.setSortBy1(fieldAttribute.attributeName);
        
        Collections.sort(this, comparator);
    }
    @Override
    public void sort(FieldAttribute fieldAttribute1, SortDirectionEnum direction1) { 
        ValueObjectComparator comparator = new ValueObjectComparator();
        comparator.setSortBy1(fieldAttribute1.attributeName);
        comparator.setSortDirection1(direction1);
        
        Collections.sort(this, comparator);
    }
    @Override
    public void sort(FieldAttribute fieldAttribute1, SortDirectionEnum direction1, 
                FieldAttribute fieldAttribute2, SortDirectionEnum direction2) {
        ValueObjectComparator comparator = new ValueObjectComparator();
        comparator.setSortBy1(fieldAttribute1.attributeName);
        comparator.setSortDirection1(direction1);
        comparator.setSortBy2(fieldAttribute2.attributeName);
        comparator.setSortDirection2(direction2);
        
        Collections.sort(this, comparator);
    }
    
    @Override
    public void sort(FieldAttribute fieldAttribute1, SortDirectionEnum direction1, 
                FieldAttribute fieldAttribute2, SortDirectionEnum direction2,
                FieldAttribute fieldAttribute3, SortDirectionEnum direction3) {
        ValueObjectComparator comparator = new ValueObjectComparator();
        comparator.setSortBy1(fieldAttribute1.attributeName);
        comparator.setSortDirection1(direction1);
        comparator.setSortBy2(fieldAttribute2.attributeName);
        comparator.setSortDirection2(direction2);
        comparator.setSortBy3(fieldAttribute3.attributeName);
        comparator.setSortDirection3(direction3);
        
        Collections.sort(this, comparator);
    }
    
    @Override
    public void sort(FieldAttribute fieldAttribute1, SortDirectionEnum direction1, 
                FieldAttribute fieldAttribute2, SortDirectionEnum direction2,
                FieldAttribute fieldAttribute3, SortDirectionEnum direction3,
                FieldAttribute fieldAttribute4, SortDirectionEnum direction4) {
        ValueObjectComparator comparator = new ValueObjectComparator();
        comparator.setSortBy1(fieldAttribute1.attributeName);
        comparator.setSortDirection1(direction1);
        comparator.setSortBy2(fieldAttribute2.attributeName);
        comparator.setSortDirection2(direction2);
        comparator.setSortBy3(fieldAttribute3.attributeName);
        comparator.setSortDirection3(direction3);
        comparator.setSortBy4(fieldAttribute4.attributeName);
        comparator.setSortDirection4(direction4);
        
        Collections.sort(this, comparator);
    }
    
    @Override
    public void sort(String attribute1, SortDirectionEnum direction1) {
	    ValueObjectComparator comparator = new ValueObjectComparator();
	    comparator.setSortBy1(attribute1);
	    comparator.setSortDirection1(direction1);
	    
	    Collections.sort(this, comparator);
	}

    @Override
    public void sort(String attribute1, SortDirectionEnum direction1, 
            String attribute2, SortDirectionEnum direction2) {
	    ValueObjectComparator comparator = new ValueObjectComparator();
	    comparator.setSortBy1(attribute1);
	    comparator.setSortDirection1(direction1);
	    comparator.setSortBy2(attribute2);
	    comparator.setSortDirection2(direction2);
	    
	    Collections.sort(this, comparator);
	}

    @Override
    public Integer getListSize() {
    	return new Integer(this.size());
    }

    // Displayable
    public String display() {
        return display ("");
    }
    
    public String display(String objectDescription) {
        Map displayedObjects = new HashMap();
        return display (objectDescription, 0, 0, displayedObjects);
    }
    
    public String display(String objectDescription, int level, int maxLevels, Map displayedObjects) {
        DisplayBuffer displayBuffer = DisplayBuffer.newInstance("List ", objectDescription, level, maxLevels);
        if (displayBuffer == null) {
            return "";
        }
        displayBuffer.addLine(level+1, "isFullyLoaded: " + isFullyLoaded);

        Iterator i = this.iterator();
        while (i.hasNext()) {
            Object o = i.next();
            if (o instanceof Displayable) { 
                displayBuffer.append(((Displayable)o).display("", level+1, maxLevels, displayedObjects));
            } else {
                //displayBuffer.append(((Displayable)o).display("", level+1, maxLevels));
            }
        }
        
        return displayBuffer.toString();
    }    
    
    
    
}
