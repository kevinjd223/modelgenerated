/*
 * ServiceLocatorConfig.java
 *
 * Created on November 1, 2002, 6:46 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.service;

import com.modelgenerated.foundation.EjbVersionEnum;
import com.modelgenerated.foundation.config.Config;
import com.modelgenerated.foundation.config.ConfigLocator;
import com.modelgenerated.foundation.config.ConfigNotFoundException;
import com.modelgenerated.foundation.jndi.JndiDescriptor;
import com.modelgenerated.foundation.jndi.JndiLocatorConfig;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.util.Assert;
import com.modelgenerated.util.DomUtil;

import java.util.Iterator;
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
 * <root>
 *     <Services>
 *         <Service>
 *             <Remote>com.modelgenerated.domain.security.SecurityService</Remote>
 *             <Home>com.modelgenerated.domain.security.SecurityServiceHome</Home>
 *             <EjbClass>com.modelgenerated.domain.security.SecurityServiceBean</EjbClass>
 *             <JndiServer>default</JndiServer>
 *         </Service>
 *     </Services>
 * </root>
 * 
  * @author  kevind
 */
public class ServiceLocatorConfig implements Config {
    public final static String CONFIG_NAME = "serviceLocatorConfig";
    private Map<String, ServiceDescriptor> services = new HashMap<String, ServiceDescriptor>();
    
    /** Creates a new instance of FactoryConfig */
    public ServiceLocatorConfig() {
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

            Element services = DomUtil.getChildElement(root,"Services");
            loadServices(services);

        
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
    
    private void loadServices(Element servicesElement) {
        NodeList nodes = servicesElement.getElementsByTagName("Service");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element elem = (Element)nodes.item(i);

            String remote = DomUtil.getChildElementText(elem, "Remote");  
            String name = DomUtil.getChildElementText(elem, "Name");  
            String home = DomUtil.getChildElementText(elem, "Home");  
            String ejbClass = DomUtil.getChildElementText(elem, "EjbClass");
            String jndi = DomUtil.getChildElementText(elem, "JndiServer");
            String ejbVersion = DomUtil.getChildElementText(elem, "EjbVersion");

            Logger.debug(this, "remote >" + remote + "<");
            ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
            serviceDescriptor.setRemote(remote);
            serviceDescriptor.setName(name);
            serviceDescriptor.setHome(home);
            serviceDescriptor.setEjbClass(ejbClass);
            serviceDescriptor.setEjbVersion(EjbVersionEnum.getEjbVersionEnum(ejbVersion));
            
            JndiLocatorConfig jndiLocatorConfig;
            jndiLocatorConfig = (JndiLocatorConfig)ConfigLocator.findConfig(JndiLocatorConfig.CONFIG_NAME);
            JndiDescriptor jndiDescriptor = jndiLocatorConfig.findJndiDescriptor(jndi);
            
            Assert.check(jndiDescriptor != null, "jndiDescriptor != null" + jndi);
            serviceDescriptor.setJndiDescriptor(jndiDescriptor);

            services.put(remote, serviceDescriptor);                
        }
    }

    public ServiceDescriptor findServiceDescriptor(String remote) {
        if (services.containsKey(remote)) {
            return (ServiceDescriptor)services.get(remote);
        }
        return null;        
    }
    
    public Iterator getServices() {
        return services.values().iterator();
    }
   
}
