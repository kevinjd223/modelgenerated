/*
 * AuthenticationServiceHome.java
 *
 * Created on December 7, 2002, 8:07 AM
 */

package com.modelgenerated.authentication;

import java.rmi.RemoteException;
import javax.ejb.CreateException;


/**
 *
 * @author  kevind
 */
public interface AuthenticationServiceHome extends javax.ejb.EJBHome {
    public AuthenticationService create() throws RemoteException, CreateException;
    
    
}
