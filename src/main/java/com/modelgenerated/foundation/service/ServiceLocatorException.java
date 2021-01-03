/*
 * ServiceLocatorException.java
 *
 * Created on December 22, 2002, 8:56 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.service;

/**
 *
 * @author  kevind
 */
public class ServiceLocatorException extends RuntimeException {
    
    /**
     * Creates a new instance of <code>ServiceLocatorException</code> without detail message.
     */
    public ServiceLocatorException() {
    }
    
    
    /**
     * Constructs an instance of <code>ServiceLocatorException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ServiceLocatorException(String msg) {
        super(msg);
    }
    
    public ServiceLocatorException(String msg, Throwable t) {
        super(msg, t);
    }
}
