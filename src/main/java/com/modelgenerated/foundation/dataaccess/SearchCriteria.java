/*
 * SearchCriteria.java
 *
 * Created on May 23, 2003, 7:07 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import java.sql.PreparedStatement;
import java.util.Iterator;

/**
 *
 * @author  kevind
 */
public interface SearchCriteria {
    public void setPreviousAlias(String newPreviousAlias);
    public String getFromClause();
    public String getWhereClause();
    
    //public void setParameters(PreparedStatement statement);
    public void setParameters(PreparedStatement statement, int paramIndex);
    
    /*
     * 
     */
    public String getOrderBy();

    public void addOrderBy(OrderBy orderBy);
    /*
     * 
     */
    public void addOrderBy(FieldAttribute newOrderBy);
    /*
     * 
     */
    public void addOrderBy(FieldAttribute newOrderBy, SortDirectionEnum sortDirection);
    public Iterator getOrderByList();
    public void clearOrderBy();
    /* make it easier to get the first orderby field so user doesn't need to use iterator 
     * with all error checking if they only need the first object. 
     */
    public OrderBy getFirstOrderBy();
    
    /** 
     * Implements a Decorator pattern for search criteria. 
     */
    public void addSearchCriteria();
    
    public int getTopAmount();
    public void setTopAmount(int topAmount);
    
}
