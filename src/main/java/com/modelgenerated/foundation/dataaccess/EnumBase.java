/*
 * Created on Dec 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.modelgenerated.foundation.dataaccess;

/**
 * @author kevin
 *
 */
public class EnumBase {
    protected String value;
    protected String display;

    protected EnumBase() {
    }

    protected EnumBase(String initValue, String initDisplay) {
        value = initValue;
        display = initDisplay;
    }

    public String getDisplay() {
        return display;
    }
    public String getValue() {
        return value;
    }

    public String toString() {
        return display;
    }
}
