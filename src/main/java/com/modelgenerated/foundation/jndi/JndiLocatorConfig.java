/*
 * JndiLocatorConfig.java
 *
 * Created on November 1, 2002, 6:46 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.jndi;

import com.modelgenerated.foundation.config.Config;
import com.modelgenerated.foundation.config.ConfigNotFoundException;
import com.modelgenerated.util.Assert;
import com.modelgenerated.util.DomUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
 *     <JndiServers>
 *         <JndiServer name="default" initialContextFactory="org.jnp.interfaces.NamingContextFactory"
 *              providerUrl="localhost:1099" urlPkgPrefixes="org.jboss.naming:org.jnp.interfaces"/>
 *     </JndiServers>
 * </root>
 * 
  * @author  kevind
 */
public class JndiLocatorConfig implements Config {
    public final static String CONFIG_NAME = "JndiLocatorConfig";
    private Map<String, JndiDescriptor> jndiServers = new HashMap<String, JndiDescriptor>();
    
    /** Creates a new instance of FactoryConfig */
    public JndiLocatorConfig() {
    }
    
    public String getName() {
        return CONFIG_NAME;
    }
    
    public void load(InputStream configInputStream) {
    	Assert.check(configInputStream != null, "configInputStream != null");
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();                       
            Document doc = db.parse(configInputStream);

            Element root = doc.getDocumentElement();
            System.out.println("nodename: " + root.getNodeName());

            Element jndiServers = DomUtil.getChildElement(root,"JndiServers");
            loadJndiDescriptors(jndiServers);

        
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
    
    private void loadJndiDescriptors(Element jndiServersElement) {
        NodeList nodes = jndiServersElement.getElementsByTagName("JndiServer");
        for (int i = 0; i < nodes.getLength(); i++) {
            Element elem = (Element)nodes.item(i);

            String name = elem.getAttribute("name");  
            String initialContextFactory = elem.getAttribute("initialContextFactory");  
            String providerUrl = elem.getAttribute("providerUrl");
            String urlPkgPrefixes = elem.getAttribute("urlPkgPrefixes");

            JndiDescriptor jndiDescriptor = new JndiDescriptor();
            jndiDescriptor.setInitialContextFactory(initialContextFactory);
            jndiDescriptor.setName(name);
            jndiDescriptor.setProviderUrl(providerUrl);
            jndiDescriptor.setUrlPkgPrefixes(urlPkgPrefixes);
            
            jndiDescriptor.setContext(getContext(jndiDescriptor));

            jndiServers.put(name, jndiDescriptor);                
        }
    }

    private Context getContext(JndiDescriptor jndiDescriptor) {
        try {
        	/*
            Properties properties = new Properties();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, jndiDescriptor.getInitialContextFactory());
            properties.put(Context.PROVIDER_URL, jndiDescriptor.getProviderUrl());
            properties.put(Context.URL_PKG_PREFIXES, jndiDescriptor.getUrlPkgPrefixes());

            return new InitialContext(properties);
            */
            InitialContext initialContext = new InitialContext();
            return initialContext;
        	
        } catch (NamingException e) {
            // Just return null. Inaccesible Jndi servers may be configured. As long as they're not used don't throw an error.
            return null;
            //throw new JndiLocatorException("Couldn't find jndi context", e);
        }
    }

    public JndiDescriptor findJndiDescriptor(String name) {
        if (jndiServers.containsKey(name)) {
            return (JndiDescriptor)jndiServers.get(name);
        }
        return null;        
    }
    
    public Iterator getJndiServers() {
        return jndiServers.values().iterator();
    }
   
}
