/*
 * SearchCriteriaBase.java
 *
 * Created on May 23, 2003, 8:09 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

import com.modelgenerated.util.StringUtil;

/**
 *
 * @author  kevind
 */
public class SearchCriteriaBase implements SearchCriteria, Serializable {
	private static final long serialVersionUID = 1L;
	protected String previousAlias;
    protected ArrayList<OrderBy> orderByList = new ArrayList<OrderBy>();
    protected int topAmount = 0;
    
    /** Creates a new instance of SearchCriteriaBase */
    public SearchCriteriaBase() {
    }
    
    /**
     * Implements a Decorator pattern for search criteria.
     *
     */
    public void setPreviousAlias(String newPreviousAlias) {
        previousAlias = newPreviousAlias;
    }

    public void addSearchCriteria() {
    }
    
    public String getFromClause() {
        return "";
    }
    
    public String getWhereClause() {
        return "";
    }
    
    //public void setParameters(PreparedStatement statement) {
    //}
    
    public void setParameters(PreparedStatement statement, int parmIndex) {
    }

    /* 
     * Make private to discover legacy search criteria
     */
    //private void setParameters(PreparedStatement statement) {
    //}
    
    // todo: refactor/rename getOrderBy():String so it is not confused with getOrderByList()
    public String getOrderBy() {
        StringBuffer strbuf = new StringBuffer();        
        if (orderByList.size() > 0) {
            strbuf.append("ORDER BY ");
            for (int i = 0; i < orderByList.size(); i++) {
                OrderBy orderBy = (OrderBy)orderByList.get(i);

                if (i != 0) {
                    strbuf.append(", ");
                }
                FieldAttribute fieldAttribute = orderBy.getOrderByField();
                String columnName = fieldAttribute.getColumnReference();
                if (StringUtil.isEmpty(columnName)) {
                    strbuf.append(previousAlias + "." + fieldAttribute.getAttributeName());
                } else {
                    strbuf.append(columnName);
                }
                if (orderBy.getSortDirection() == SortDirectionEnum.DESCENDING) {
                    strbuf.append(" desc");
                }
            }
        }
        
        return strbuf.toString();        
    }

    public void addOrderBy(OrderBy orderBy) {
        orderByList.add(orderBy);
    }
    public void addOrderBy(FieldAttribute newOrderBy) {
        OrderBy orderBy = new OrderBy(newOrderBy, SortDirectionEnum.ASCENDING);        
        orderByList.add(orderBy);
    }
    public void addOrderBy(FieldAttribute newOrderBy, SortDirectionEnum sortDirection) {
        OrderBy orderBy = new OrderBy(newOrderBy, sortDirection);        
        orderByList.add(orderBy);
    }
    // todo: refactor getOrderByList to return a strongly typed list instead of an iterator.
    public Iterator<OrderBy> getOrderByList() {
        return orderByList.iterator();
    }
    public void clearOrderBy() {
        orderByList = new ArrayList<OrderBy>();;
    }
    public OrderBy getFirstOrderBy() {
    	if (orderByList == null) {
    		return null;
    	}
    	if (orderByList.size() >= 1) {
    		return (OrderBy)orderByList.get(0);
    	}
    	return null;
    }
    
    public int getTopAmount() {
        return topAmount;
    }
    public void setTopAmount(int topAmount) {
        this.topAmount = topAmount;        
    }
    
}
