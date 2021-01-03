/*
 * FactoryException.java
 *
 * Created on November 1, 2002, 9:59 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.factory;

/**
 * Non recoverable exception thrown from Factory. 
 * @author  kevind
 */
public class FactoryException extends java.lang.RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new instance of <code>FactoryException</code> without detail message.
     */
    public FactoryException() {
    }
    
    
    /**
     * Constructs an instance of <code>FactoryException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public FactoryException(String msg) {
        super(msg);
    }

    public FactoryException(String msg, Throwable t) {
        super(msg, t);
    }
}
