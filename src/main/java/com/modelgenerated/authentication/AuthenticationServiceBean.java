/*
 * AuthenticationServiceBean.java
 *
 * Created on December 8, 2002, 8:55 AM
 */

package com.modelgenerated.authentication;



import com.modelgenerated.authentication.dao.AccountDAO;
import com.modelgenerated.authentication.dao.TenantDAO;
import com.modelgenerated.foundation.dataaccess.DataAccessExceptionDuplicate;
import com.modelgenerated.foundation.dataaccess.UserContext;
import com.modelgenerated.foundation.dataaccess.DataAccessLocator;
import com.modelgenerated.foundation.dataaccess.SearchCriteria;
import com.modelgenerated.foundation.dataaccess.SearchCriteriaBase;
import com.modelgenerated.foundation.factory.Factory;
import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.foundation.identity.IdentityBuilder;
import com.modelgenerated.foundation.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;

/**
 *
 * @author  kevind
 */
@Stateless
@EJB(name = "java:global/AuthenticationService", beanInterface = AuthenticationService.class)
public class AuthenticationServiceBean implements AuthenticationService {

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public UserContext Authenticate(String userID, String password, String tenantName) {
    	return Authenticate(userID, password, tenantName, 5);
    }

    private UserContext Authenticate(String userName, String password, String tenantName, int badAttemptLimit) {
        AccountDAO accountDAO = (AccountDAO)DataAccessLocator.findDAO(Account.class.getName());
        TenantDAO tenantDAO = (TenantDAO)DataAccessLocator.findDAO(Tenant.class.getName());
        
        // create a tempUserContext so we can interact with database before we even have a tenant
        UserContext tempUserContext = new UserContext(null, null, null);
        Tenant tenant = (Tenant)tenantDAO.findByName(tempUserContext, tenantName);
        Logger.debug(this, "tenant " + tenant);
        if (tenant == null) {
        	return null;
        }
        
        // create a tempUserContext so we can interact with database before authenticating
        tempUserContext = new UserContext(null, null, tenant);
        Account account = (Account)accountDAO.findByUserName(tempUserContext, userName);
        Logger.debug(this, "account " + account);
        
        String encrytedPassword = PasswordUtil.encryptPassword(password);
        // Logger.debug(this, "encrytedPassword " + encrytedPassword);
        
        if (account == null || Boolean.TRUE.equals(account.getLockedOut())) {
            Logger.info(this, "Account not found: " + userName);
        	return null;
        } else {        	
            UserContext userContext = new UserContext(userName, account.getId(), tenant);
            // Logger.debug(this, "db encrytedPassword " + account.getPassword());
            if (account.getPassword().equals(encrytedPassword)) {
            	account.setBadAuthenticationAttempts(0);
            	accountDAO.save(userContext, account);
            	
                return userContext;
            } else {
                Logger.info(this, "Bad authentication attempt: " + userName);
            	int badAttempts = account.getBadAuthenticationAttempts() + 1;            	
            	account.setBadAuthenticationAttempts(badAttempts);
            	if (badAttempts >= badAttemptLimit) {
            		account.setLockedOut(Boolean.TRUE);            		
            	}
            	accountDAO.save(userContext, account);
            }
        }
        return null;        
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Account newAccount(UserContext userContext) {
        Account account = (Account)Factory.createObject(Account.class.getName());
        Identity id = IdentityBuilder.createIdentity();
        account.setId(id);
        return account;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public int saveAccount(UserContext userContext, Account account) {
    	int retValue = AuthenticationService.SUCCESS;
    
        AccountDAO accountDAO = (AccountDAO)DataAccessLocator.findDAO(Account.class.getName());

        Logger.debug(this, "updateAccount " + account.getUserName());
        try {
            accountDAO.save(userContext, account);        
        } catch (Exception e) {
        	Logger.debug(this, "=========================== caught ");
        	Logger.debug(this, e.getMessage());
        	if (e instanceof DataAccessExceptionDuplicate || e.getCause() instanceof DataAccessExceptionDuplicate) {
        		retValue = AuthenticationService.DUPLICATEROW;        		
        	} else {
        		throw e;
        	}
        }
        
        return retValue;
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public AccountList getAccounts(UserContext userContext) {
        AccountDAO accountDAO = (AccountDAO)DataAccessLocator.findDAO(Account.class.getName());

        SearchCriteria searchCriteria = new SearchCriteriaBase();
        AccountList accountList = accountDAO.search(userContext, searchCriteria, false);
        return accountList;        
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Account findAccount(UserContext userContext, String id) {
        AccountDAO accountDAO = (AccountDAO)DataAccessLocator.findDAO(Account.class.getName());
        
        Identity identity = IdentityBuilder.createIdentity(id);
        Account account = (Account)accountDAO.find(userContext, identity);
        Logger.debug(this, "findAccount id=" + id);
        return account;        
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Account findAccountByUserName(UserContext userContext, String userName) {
        AccountDAO accountDAO = (AccountDAO)DataAccessLocator.findDAO(Account.class.getName());
        
        Account account = (Account)accountDAO.findByUserName(userContext, userName);
        return account;        
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Tenant newTenant(UserContext userContext) {
    	Tenant tenant = (Tenant)Factory.createObject(Tenant.class.getName());
	    Identity id = IdentityBuilder.createIdentity();
	    tenant.setId(id);
	    return tenant;
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public int saveTenant(UserContext userContext, Tenant tenant) {
		int retValue = AuthenticationService.SUCCESS;
		TenantDAO tenantDAO = (TenantDAO)DataAccessLocator.findDAO(Tenant.class.getName());
	
	    try {
	    	tenantDAO.save(userContext, tenant);        
	    } catch (Exception e) {
	    	if (e instanceof DataAccessExceptionDuplicate || e.getCause() instanceof DataAccessExceptionDuplicate) {
	    		retValue = AuthenticationService.DUPLICATEROW;        		
	    	} else {
	    		throw e;
	    	}
	    }
	    
	    return retValue;
	}

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public Tenant findTenant(UserContext userContext, String id) {
    	TenantDAO tenantDAO = (TenantDAO)DataAccessLocator.findDAO(Tenant.class.getName());
	    
	    Identity identity = IdentityBuilder.createIdentity(id);
	    Tenant tenant = (Tenant)tenantDAO.find(userContext, identity);
	    Logger.debug(this, "findTenantid=" + id);
	    return tenant;        
	}

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public TenantList getTenants(UserContext userContext) {
    	TenantDAO tenantDAO = (TenantDAO)DataAccessLocator.findDAO(Tenant.class.getName());
	
	    SearchCriteria searchCriteria = new SearchCriteriaBase();
	    TenantList tenantList = tenantDAO.search(userContext, searchCriteria, false);
	    return tenantList;        
	}

    
    
}
