/*
 * ConnectionConfig.java
 *
 * Created on November 19, 2002, 8:26 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.config.Config;
import com.modelgenerated.foundation.config.ConfigNotFoundException;
import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.debug.DisplayBuffer;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import com.modelgenerated.foundation.logging.Logger;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author  kevind
 */
public class ConnectionConfig implements Config {
    public final static String CONFIG_NAME = "connectionConfig";

    private Map<String, String> env = System.getenv();
    private Map connectionDescriptors = new HashMap();
    
    /** Creates a new instance of ConnectionConfig */
    public ConnectionConfig() {
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

            NodeList nodes = root.getElementsByTagName("*");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element elem = (Element)nodes.item(i);
                String nodeName = elem.getNodeName();
                if (nodeName.equals("connection")) {
                    String name = elem.getAttribute("name");  
                    String driverName = elem.getAttribute("driverName");
                    String connectionString = getConnectionString(elem);
                    
                    JdbcConnectionDescriptor connectionDescriptor = new JdbcConnectionDescriptor(name, driverName, connectionString);
                    
                    connectionDescriptors.put(name, connectionDescriptor); 
                } else if (nodeName.equals("jndiconnection")) {
                    String name = elem.getAttribute("name");  
                    String jndiName = elem.getAttribute("jndiName");
                    
                    JndiConnectionDescriptor connectionDescriptor = new JndiConnectionDescriptor(name, jndiName);
                    
                    connectionDescriptors.put(name, connectionDescriptor); 
                    
                    
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
    
    public ConnectionDescriptor findConnectionDescriptor(String connectionName) {
        if (connectionDescriptors.containsKey(connectionName)) {
            return (ConnectionDescriptor)connectionDescriptors.get(connectionName);
        } 
        return null;
    }

    /**
     * The xml file might contain the connection string or a reference to an environment variable that holds the
     * connection string.
     */
    private String getConnectionString(Element elem) {

        String connectionString = elem.getAttribute("connectionString");
        if (connectionString != null && connectionString.startsWith("${"))  {
            String envVariableName = connectionString.substring(2, connectionString.length()-1);
            Logger.debug(this, "envVariableName: " + envVariableName);
            System.out.println("envVariableName: " + envVariableName);
            connectionString = env.get(envVariableName);
        }

        return connectionString;
    }


    

}
