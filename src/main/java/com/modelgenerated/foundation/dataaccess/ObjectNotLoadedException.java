/*
 * ObjectNotLoadedException.java
 *
 * Created on October 23, 2002, 8:07 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

/**
 * This exception is throw if JIT loading is off and there is an attempt the read
 * a child object that has not been loaded yet.
 *
 * @author  kevind
 */
public class ObjectNotLoadedException extends java.lang.RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new instance of <code>DataAccessException</code> without detail message.
     */
    public ObjectNotLoadedException() {
    }
    
    /**
     * Constructs an instance of <code>DataAccessException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ObjectNotLoadedException(String msg) {
        super(msg);
    }
    public ObjectNotLoadedException(String msg, Throwable t) {
        super(msg, t);
    }
}
