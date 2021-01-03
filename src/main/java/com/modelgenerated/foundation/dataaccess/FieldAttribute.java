/* FieldAttribute.java
 *
 */
package com.modelgenerated.foundation.dataaccess;

import java.io.Serializable;

import com.modelgenerated.util.StringUtil;

/**
 * Describes an attribute of a generated object.
 */
public class FieldAttribute implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * The name of the attribute.
	 */
	public final String attributeName;
	/**
	 * The database column referenced by this attribute. Will be null in column
	 * is same as attribute name.
	 */
	public final String columnReference;
	/**
	 * For string attributes, this is the max length that can be stored in the
	 * database. Will be 0 if the attribute is not a string.
	 */
	public final int length;

	public FieldAttribute(String attributeName, String columnReference, int length) {
		this.attributeName = attributeName;
		this.columnReference = columnReference;
		this.length = length;
	}

	/**
	 * Returns the attribute name.
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * Returns the column in the database that is references. Only used for
	 * join, otherwise will be null.
	 */
	public String getColumnReference() {
		return columnReference;
	}

	/**
	 * Returns the max length that can be stored for a string. Will be 0 if the
	 * attribut is not a string.
	 */
	public int getLength() {
		return length;
	}

}
