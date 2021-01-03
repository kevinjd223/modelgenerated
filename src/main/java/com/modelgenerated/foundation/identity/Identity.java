/*
 * Identity.java
 *
 * Created on November 26, 2002, 9:25 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.identity;

import java.io.Serializable;


/**
 * Identifies a value object.
 *
 * Maybe rename to Identifier.
 * @author  kevind
 */
public interface Identity extends Serializable {
    public String getStringValue();
    public byte[] getByteValue();
}
