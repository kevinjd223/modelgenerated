/*
 * JndiLocatorException.java
 *
 * Created on December 22, 2002, 8:56 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.jndi;

/**
 * 
 * @author  kevind
 */
public class JndiLocatorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new instance of <code>ServiceLocatorException</code> without detail message.
     */
    public JndiLocatorException() {
    }
    
    
    /**
     * Constructs an instance of <code>JndiLocatorException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public JndiLocatorException(String msg) {
        super(msg);
    }
    
    public JndiLocatorException(String msg, Throwable t) {
        super(msg, t);
    }
}
