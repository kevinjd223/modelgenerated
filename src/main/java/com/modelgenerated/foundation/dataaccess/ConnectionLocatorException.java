/*
 * ConnectionLocatorException.java
 *
 * Created on October 23, 2002, 8:07 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

/**
 *
 * @author  kevind
 */
public class ConnectionLocatorException extends java.lang.RuntimeException {
    
	private static final long serialVersionUID = 1L;
	/**
     * Creates a new instance of <code>DataAccessException</code> without detail message.
     */
    public ConnectionLocatorException() {
    }
    
    /**
     * Constructs an instance of <code>DataAccessException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ConnectionLocatorException(String msg) {
        super(msg);
    }
    public ConnectionLocatorException(String msg, Throwable t) {
        super(msg, t);
    }
}
