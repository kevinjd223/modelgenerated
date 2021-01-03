/*
 * Created on Sep 30, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.modelgenerated.foundation.dataaccess;


import java.io.Serializable;

import com.modelgenerated.util.StringUtil;

/**
 *
 */
public class FieldAttribute implements Serializable {
	private static final long serialVersionUID = 1L;
	String attributeName;
    String columnReference;
    
    public FieldAttribute(String attributeName, String columnReference) {
        this.attributeName = attributeName;
        this.columnReference = columnReference;
    }

	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String string) {
		attributeName = string;
	}

	public String getColumnReference() {
        return columnReference;
	}
	public void setColumnReference(String string) {
		columnReference = string;
	}

	public boolean equals(Object o) {
		if (o instanceof FieldAttribute) {
			FieldAttribute fieldAttribute = (FieldAttribute)o;
			if (StringUtil.same(this.getAttributeName(), fieldAttribute.getAttributeName())
					&& StringUtil.same(this.getColumnReference(), fieldAttribute.getColumnReference())) {
				return true;
			}
		}
		return false;
	}
	
	
	
}
