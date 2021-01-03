/* OrderBy.java
 *
 */
package com.modelgenerated.foundation.dataaccess;

import java.io.Serializable;

/**
 * @author kevin
 *
 */
public class OrderBy implements Serializable {
	private static final long serialVersionUID = 1L;
	FieldAttribute      orderByField;
    SortDirectionEnum   sortDirection;
    
    public OrderBy(FieldAttribute orderByField, SortDirectionEnum sortDirection) {
        this.orderByField = orderByField;
        this.sortDirection = sortDirection;
    }

	public FieldAttribute getOrderByField() {
		return orderByField;
	}
	public void setOrderByField(FieldAttribute string) {
		orderByField = string;
	}

	public SortDirectionEnum getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(SortDirectionEnum newSortDirection) {
		sortDirection = newSortDirection;
	}
	public boolean equals(Object o) {
		if (o instanceof OrderBy) {
			OrderBy orderBy = (OrderBy)o;
			if (((orderBy.getOrderByField() == null && orderByField == null) 
					|| (orderByField != null && orderByField.equals(orderBy.getOrderByField())))
				&& ((orderBy.getSortDirection() == null && sortDirection == null) 
				|| (sortDirection != null && sortDirection.equals(orderBy.getSortDirection())))) {
				return true;
			}
		}
		return false;
	}

	

}
