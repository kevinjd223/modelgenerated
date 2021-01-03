/*
 * FactoryConfig.java
 *
 * Created on November 1, 2002, 6:46 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.factory;

import com.modelgenerated.foundation.config.Config;
import com.modelgenerated.foundation.config.ConfigNotFoundException;
import java.util.Map;
import java.util.HashMap;
import java.io.*;
import java.net.MalformedURLException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * The config file should look like this.
 *
 * <root ConfigClassName="com.modelgenerated.foundation.dataaccess.DataAccessConfig">
 *     <ConfigData>
 *         <ObjectDefinitionPath path="./ObjectDefinition"/>
 *         <Objects>
 *             <Object interface="com.modelgenerated.foundataion.dataaccess.SampleObject" Audited="false"/>
 *             <Object interface="com.modelgenerated.domain.party.person" Audited="false"/>
 *         </Objects>
 *     </ConfigData>
 * </root>
 * 
  * @author  kevind
 */
public class FactoryConfig implements Config {
    public final static String CONFIG_NAME = "factoryConfig";
    private Map<String, String> classes = new HashMap<String, String>();
    
    /** Creates a new instance of FactoryConfig */
    public FactoryConfig() {
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
                if (nodeName.equals("class")) {
                    String interfaceName = elem.getAttribute("interface");  
                    String className = elem.getAttribute("class");
                    classes.put(interfaceName, className);                
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

    public String findClassName(String interfaceName) {
        if (classes.containsKey(interfaceName)) {
            return (String)classes.get(interfaceName);
        } 
        return null;
    }
    
}
