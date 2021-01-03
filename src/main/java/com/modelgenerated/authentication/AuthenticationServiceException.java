/*
 * AuthenticationServiceException.java
 *
 * Created on October 23, 2002, 8:07 PM
 */

package com.modelgenerated.authentication;

/**
 *
 * @author  kevind
 */
public class AuthenticationServiceException extends java.lang.RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new instance of <code>SecurityServiceException</code> without detail message.
     */
    public AuthenticationServiceException() {
    }
    
    
    /**
     * Constructs an instance of <code>SecurityServiceException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public AuthenticationServiceException(String msg) {
        super(msg);
    }
    public AuthenticationServiceException(String msg, Throwable t) {
        super(msg, t);
    }
}
