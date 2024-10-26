/*
 * ResultSetWrapper.java
 *
 * Created on September 8, 2004, 3:57 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.

		Instant;
import java.time.LocalDate;

/**
 * Contains static methods to wrap ResultSet methods.
 * Handles null value for specific types. Converts java.sql.Date to java.time.Instant and java.time.LocalDate.
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
			return Boolean.valueOf(value);
		}        
	}
    
	public static Double getDouble(ResultSet resultSet, int index) throws SQLException {
		double value = resultSet.getDouble(index);
		if (resultSet.wasNull()) {
			return null;
		} else {
			return Double.valueOf(value);
		}        
	}

	public static Instant getInstant(ResultSet resultSet, int index) throws SQLException {
		Timestamp timestamp = resultSet.getTimestamp(index);
		return timestamp == null ? null : timestamp.toInstant();
	}

	public static Integer getInteger(ResultSet resultSet, int index) throws SQLException {
		int value = resultSet.getInt(index);
		if (resultSet.wasNull()) {
			return null;
		} else {
			return Integer.valueOf(value);
		}        
	}

	public static LocalDate getLocalDate(ResultSet resultSet, int index) throws SQLException {
		Date date = resultSet.getDate(index);
		return date == null ? null : date.toLocalDate();
	}

}
