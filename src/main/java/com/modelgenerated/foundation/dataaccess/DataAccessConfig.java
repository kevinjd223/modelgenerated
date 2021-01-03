/*
 * DataAccessConfig.java
 *
 * Created on November 2, 2002, 9:57 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.config.Config;
import com.modelgenerated.foundation.config.ConfigLocator;
import com.modelgenerated.foundation.config.ConfigNotFoundException;
import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.foundation.debug.DisplayBuffer;
import com.modelgenerated.util.Assert;
import com.modelgenerated.util.DomUtil;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author  kevind
 */
public class DataAccessConfig implements Config {
    public final static String CONFIG_NAME = "dataAccessConfig";
    private Map<String, DAODescriptor> daoDescriptors = new HashMap<String, DAODescriptor>();
    
    /** Creates a new instance of DataAccessConfig */
    public DataAccessConfig() {
    }
    
    public String getName() {
        return CONFIG_NAME;
    }
    
    public void load(InputStream configInputStream) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();                       
            Document doc = db.parse(configInputStream);

            Element root = doc.getDocumentElement();
            System.out.println("nodename: " + root.getNodeName());
            Element config = DomUtil.getChildElement(root, "ConfigData");
            Assert.check(config != null, "config != null");

            Element objects = DomUtil.getChildElement(config, "Objects");
            NodeList nodes = objects.getElementsByTagName("*");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element elem = (Element)nodes.item(i);
                String nodeName = elem.getNodeName();
                if (nodeName.equals("Object")) {
                    String voInterface = DomUtil.getChildElementText(elem, "ValueObjectInterface");  
                    String daoInterface = DomUtil.getChildElementText(elem, "DataAccessObjectInterface");  
                    boolean audited = "yes".equals(DomUtil.getChildElementText(elem, "Audited")) ;
                    String connectionName = DomUtil.getChildElementText(elem, "ConnectionName");  
                    
                    // Make sure we have minimal info. Change to exception.  
                    Assert.check(voInterface != null, "voInterface != null");
                    Assert.check(daoInterface != null, "daoInterface != null");
                    
                    daoDescriptors.put(voInterface, new DAODescriptor(voInterface, daoInterface, audited, connectionName)); 
                }
            }
        } catch (MalformedURLException e) {
            throw new ConfigNotFoundException("Bad url", e);
        } catch (ParserConfigurationException e) {
            throw new ConfigNotFoundException("Error parsing configXML file", e);
        } catch (SAXException e) {
            throw new ConfigNotFoundException("Error parsing configXML file", e);
        } catch (IOException e) {
            throw new ConfigNotFoundException("Couldn't parse config input stream", e);
        }
    }    
    
    public DAODescriptor findDAODescriptor(String name) {
        if (daoDescriptors.containsKey(name)) {
            return (DAODescriptor)daoDescriptors.get(name);
        } 
        return null;
    }
    
    public Iterator<DAODescriptor> getDAODescriptors() {
        return daoDescriptors.values().iterator();
    }
    
    
    
}
