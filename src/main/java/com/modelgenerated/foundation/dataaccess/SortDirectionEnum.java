/*
 * SortDirectionEnum.java
 *
 * Created on April 5, 2003, 8:06 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

/**
 *
 * @author  kevind
 */
public enum SortDirectionEnum {
    ASCENDING("ascending"), 
    DESCENDING("descending");
    
    
    private final String value;
    
    private SortDirectionEnum(String initValue) {
    	value = initValue;
    }
    
    
    public static SortDirectionEnum getFieldType(String value) {
        for (SortDirectionEnum e: SortDirectionEnum.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }

    public String toString() {
        return value;
    }
    
    
    
}
