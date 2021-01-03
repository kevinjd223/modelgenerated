/*
 * ResultSetWrapper.java
 *
 * Created on September 8, 2004, 3:57 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author  kevind
 */
public class ResultSetWrapper {
    
    /** Creates a new instance of ResultSetWrapper */
    public ResultSetWrapper() {
    }
    
	public static Boolean getBoolean(ResultSet resultSet, int index) throws SQLException {
		boolean value = resultSet.getBoolean(index);
		if (resultSet.wasNull()) {
			return null;
		} else {
			return new Boolean(value);
		}        
	}
    
	public static Double getDouble(ResultSet resultSet, int index) throws SQLException {
		double value = resultSet.getDouble(index);
		if (resultSet.wasNull()) {
			return null;
		} else {
			return new Double(value);
		}        
	}
    
	public static Integer getInteger(ResultSet resultSet, int index) throws SQLException {
		int value = resultSet.getInt(index);
		if (resultSet.wasNull()) {
			return null;
		} else {
			return new Integer(value);
		}        
	}
    
}
