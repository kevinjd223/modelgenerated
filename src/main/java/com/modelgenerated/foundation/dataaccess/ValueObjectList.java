/*
 * ValueObjectList.java
 *
 * Created on June 21, 2003, 1:31 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.identity.Identity;
import java.util.List;
/**
 *
 * @author  kevind
 */
public interface ValueObjectList<E> extends List<E>, Displayable {    
    /** 
     * Find the value object in the list that is identified by idString
     * @param idString
     * @return matching value object
     */
	public Object findById(String idString);    
    /** 
     * Find the value object in the list that is identified by idString
     * @param id
     * @return matching value object
     */
    public Object findById(Identity id);    

    public boolean getIsFullyLoaded();    
    public void setIsFullyLoaded(boolean isFullyLoaded);    
    public void sort(FieldAttribute fieldAttribute);
    public void sort(FieldAttribute fieldAttribute1, SortDirectionEnum direction1); 
    public void sort(FieldAttribute fieldAttribute1, SortDirectionEnum direction1, 
            FieldAttribute fieldAttribute2, SortDirectionEnum direction2);
    public void sort(FieldAttribute fieldAttribute1, SortDirectionEnum direction1, 
            FieldAttribute fieldAttribute2, SortDirectionEnum direction2, 
            FieldAttribute fieldAttribute3, SortDirectionEnum direction3);
    public void sort(FieldAttribute fieldAttribute1, SortDirectionEnum direction1, 
            FieldAttribute fieldAttribute2, SortDirectionEnum direction2, 
            FieldAttribute fieldAttribute3, SortDirectionEnum direction3, 
            FieldAttribute fieldAttribute4, SortDirectionEnum direction4);
    public void sort(String attribute1, SortDirectionEnum direction1);
    public void sort(String attribute1, SortDirectionEnum direction1, 
            String attribute2, SortDirectionEnum direction2);
    public Integer getListSize();
}
