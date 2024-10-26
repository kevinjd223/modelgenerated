/*
 * ObjectFieldSizeConfig.java
 *
 * Created on September 26, 2003, 11:28 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;


import com.modelgenerated.foundation.config.Config;
import com.modelgenerated.foundation.config.ConfigNotFoundException;
import com.modelgenerated.util.Assert;
import java.util.Enumeration;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;


/**
 *
 * @author  kevind
 */
public class ObjectFieldSizeConfig  implements Config {
    public final static String CONFIG_NAME = "objectFieldSizeConfig";
    private Map<String,Integer> objectFieldSizes = new HashMap<String,Integer>();
    
    /** Creates a new instance of ObjectFieldSizeConfig */
    public ObjectFieldSizeConfig() {
    }

    public String getName() {
        return CONFIG_NAME;
    }    
    
    public void load(InputStream configInputStream) {
        try {
            Properties properties = new Properties();
            properties.load(configInputStream);
            configInputStream.close();
        
            Enumeration<Object> enumProperty = properties.keys();
            while (enumProperty.hasMoreElements()) {
                String objectField = (String)enumProperty.nextElement();
                String sizeString = properties.getProperty(objectField);
                Integer size = new Integer(sizeString);
                Assert.check(size != null, "size != null");
                
                objectFieldSizes.put(objectField, size);
            }
        
        } catch (MalformedURLException e) {
            throw new ConfigNotFoundException("Bad url", e);
        } catch (IOException e) {
            throw new ConfigNotFoundException("Couldn't parse config input stream", e);
        }
    }
    
    public int getObjectFieldSize(String objectField) {
        Integer size = (Integer)objectFieldSizes.get(objectField);
        return size == null ? 0 : size.intValue();
    }

}
