/*
 * DataAccessException.java
 *
 * Created on October 23, 2002, 8:07 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import java.io.Serializable;

/**
 *
 * @author  kevind
 */
public class DataAccessException extends java.lang.RuntimeException implements Serializable {
    final static long serialVersionUID = 1L;
    
    /**
     * Creates a new instance of <code>DataAccessException</code> without detail message.
     */
    public DataAccessException() {
    }
    
    
    /**
     * Constructs an instance of <code>DataAccessException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DataAccessException(String msg) {
        super(msg);
    }
    public DataAccessException(String msg, Throwable t) {
        super(msg, t);
    }
}
