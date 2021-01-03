/*
 * DataAccessException.java
 *
 * Created on October 23, 2002, 8:07 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import java.io.Serializable;

/**
 * TODO: change this to Exception instead of RuntimeException
 * @author  kevind
 */
public class DataAccessExceptionDuplicate extends java.lang.RuntimeException implements Serializable {
    final static long serialVersionUID = 1L;
    /**
     * Creates a new instance of <code>DataAccessException</code> without detail message.
     */
    public DataAccessExceptionDuplicate() {
    }
    
    
    /**
     * Constructs an instance of <code>DataAccessException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DataAccessExceptionDuplicate(String msg) {
        super(msg);
    }
    public DataAccessExceptionDuplicate(String msg, Throwable t) {
        super(msg, t);
    }
}
