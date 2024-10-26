/*
 * JDBCUtil.java
 *
 * Created on February 12, 2003, 7:26 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;


import com.modelgenerated.util.Assert;
import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.foundation.logging.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 *
 * @author  kevind
 */
public class JDBCUtil {
    
    public static void setStatement(PreparedStatement statement, int index, String value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index); 
        }
        if (value == null) {
            statement.setNull(index, Types.VARCHAR);
        } else {
            statement.setString(index, value);
        }        
    }
    public static void setStatement(PreparedStatement statement, int index, String value, boolean nullable, String fieldName, int length) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index); 
        }
        if (value != null && value.length() > length) {
            Logger.debug(JDBCUtil.class.getName(), fieldName + " will be truncated to " + length + " characters. ");
            Logger.debug(JDBCUtil.class.getName(), "Original string: " + value);
            value = value.substring(0, length);
            Logger.debug(JDBCUtil.class.getName(), "New string: " + value);
        }
        
        if (value == null) {
            statement.setNull(index, Types.VARCHAR);
        } else {
            statement.setString(index, value);
        }        
    }


    /**
     * Sets a date on a jdbc prepared statement
     * Sets using timestamp which causes problems for "Date" type on mysql 8.0. When used in equal clauses in search
     * criteria date that should match are not found. This overloaded date method will be replaced to fix this.
     * @deprecated
     */
    public static void setStatement(PreparedStatement statement, int index, java.util.Date value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index); 
        }
        if (value == null) {
            statement.setNull(index, Types.DATE);
        } else {
            statement.setTimestamp(index, new java.sql.Timestamp(value.getTime()));
        }        
    }

    /**
     *  Sets a date on a jdbc prepared statement
     */
    public static void setStatementDate(PreparedStatement statement, int index, java.util.Date value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index);
        }
        if (value == null) {
            statement.setNull(index, Types.DATE);
        } else {
            statement.setDate(index, new java.sql.Date(value.getTime()));
        }
    }

    /**
     *  Sets a datetime on a jdbc prepared statement
     */
    public static void setStatementDateTime(PreparedStatement statement, int index, java.util.Date value, boolean nullable) throws SQLException {
        setStatementTimestamp(statement, index, value, nullable);
    }

    /**
     *  Sets an instant on a jdbc prepared statement
     */
    public static void setStatement(PreparedStatement statement, int index, java.time.Instant value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index);
        }
        if (value == null) {
            statement.setNull(index, Types.DATE);
        } else {
            statement.setTimestamp(index, new java.sql.Timestamp(value.toEpochMilli()));
        }
    }

    /**
     *  Sets an instant on a jdbc prepared statement
     */
    public static void setStatement(PreparedStatement statement, int index, java.time.LocalDate value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index);
        }
        if (value == null) {
            statement.setNull(index, Types.DATE);
        } else {
            statement.setDate(index, java.sql.Date.valueOf(value));
        }
    }

    /**
     *  Sets a date on a jdbc prepared statement
     */
    public static void setStatementTimestamp(PreparedStatement statement, int index, java.util.Date value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index);
        }
        if (value == null) {
            statement.setNull(index, Types.DATE);
        } else {
            statement.setTimestamp(index, new java.sql.Timestamp(value.getTime()));
        }
    }


    public static void setStatement(PreparedStatement statement, int index, Long value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index); 
        }
        if (value == null) {
            statement.setNull(index, Types.INTEGER);
        } else {
            statement.setLong(index, value.longValue());
        }        
    }
    public static void setStatement(PreparedStatement statement, int index, Boolean value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index); 
        }
        if (value == null) {
            statement.setNull(index, Types.TINYINT);
        } else {
            statement.setBoolean(index, value.booleanValue());
        }        
    }
    public static void setStatement(PreparedStatement statement, int index, Double value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index); 
        }
        if (value == null) {
            statement.setNull(index, Types.DOUBLE);
        } else {
            statement.setDouble(index, value.doubleValue());
        }        
    }
    public static void setStatement(PreparedStatement statement, int index, long value) throws SQLException {
        statement.setLong(index, value);
    }
    public static void setStatement(PreparedStatement statement, int index, int value, boolean nullable) throws SQLException {
        Assert.check(nullable == false, "nullable == false, index = " + index); 
        statement.setInt(index, value);
    }

    public static void setStatement(PreparedStatement statement, int index, Integer value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index);
        }
        if (value == null) {
            statement.setNull(index, Types.INTEGER);
        } else {
            statement.setDouble(index, value.intValue());
        }
    }
    public static void setStatement(PreparedStatement statement, int index, EnumBase value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index); 
        }
        if (value == null) {
            statement.setNull(index, Types.VARCHAR);
        } else {
            statement.setString(index, value.getValue());
        }        
    }
    public static void setStatement(PreparedStatement statement, int index, java.lang.Enum<?> value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index); 
        }
        if (value == null) {
            statement.setNull(index, Types.VARCHAR);
        } else {
            try {
                Class[] parameterClasses = new Class[1];
                Method method = value.getClass().getMethod("getValue", (Class[])null);
                Assert.check(method != null, "method != null");

                statement.setString(index, (String)method.invoke(value, (Object[])null));
            
            } catch (NoSuchMethodException e) {
                throw new SubObjectHelperException("error setting sub object id for attribute: " + value.getClass().toString(), e);
            } catch (InvocationTargetException e) {
                throw new SubObjectHelperException("error setting sub object id for attribute: " + value.getClass().toString(), e);
            } catch (IllegalAccessException e) {
                throw new SubObjectHelperException("error setting sub object id for attribute: " + value.getClass().toString(), e);
            }
            
        }        
    }
    public static void setStatement(PreparedStatement statement, int index, Identity value, boolean nullable) throws SQLException {
        if (!nullable) {
            Assert.check(value != null, "value != null, index = " + index); 
        }
        if (value == null) {
            statement.setNull(index, Types.VARBINARY);
        } else {
            statement.setBytes(index, value.getByteValue());
        }        
    }
    
}
