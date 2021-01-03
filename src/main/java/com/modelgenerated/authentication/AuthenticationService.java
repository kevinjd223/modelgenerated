/*
 * SecurityService.java
 *
 * Created on December 8, 2002, 8:54 AM
 */

package com.modelgenerated.authentication;

import com.modelgenerated.foundation.dataaccess.UserContext;
import java.rmi.RemoteException;

/**
 *
 * @author  kevind
 */
public interface AuthenticationService {
    public static final int SUCCESS = 0;
    public static final int DUPLICATEROW = 1001;
   
    public UserContext Authenticate(String userID, String password, String tenantName);
    public Account newAccount(UserContext userContext);
    public int saveAccount(UserContext userContext, Account account);
    public Account findAccount(UserContext userContext, String id);
    public Account findAccountByUserName(UserContext userContext, String userName);
    public AccountList getAccounts(UserContext userContext);
    
    public Tenant newTenant(UserContext userContext);
    public int saveTenant(UserContext userContext, Tenant tenant);
    public Tenant findTenant(UserContext userContext, String id);
    public TenantList getTenants(UserContext userContext);
    
}
