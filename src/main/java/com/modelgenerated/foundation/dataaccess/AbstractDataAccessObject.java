/*
 * AbstractDataAccessObject.java
 *
 * Created on December 31, 2002, 6:31 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.util.Assert;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author  kevind
 */
public abstract class AbstractDataAccessObject implements DataAccessObject {
    protected DAODescriptor daoDescriptor;
    
    /** Creates a new instance of AbstractDataAccessObject */
    public AbstractDataAccessObject() {
    }

    @Override
    public void init(DAODescriptor initialDAODescriptor) {
        Assert.check(initialDAODescriptor != null, "initialDAODescriptor != null");
        Assert.check(daoDescriptor == null, "daoDescriptor == null");
        daoDescriptor = initialDAODescriptor;
    }
   
    
    @Override
    public ValueObject find (UserContext userContext, Identity id) {
        Assert.check(daoDescriptor != null, "daoDescriptor != null");
        Assert.check(userContext != null, "userContext != null");
        Assert.check(id != null, "id != null");
        
        TransactionContext transactionContext = new TransactionContext(userContext);;
        try {
            return find(transactionContext, id);
        } finally {
            transactionContext.close();
        }
        
    }
    
    @Override
    public void save (UserContext userContext, ValueObject valueObject) {
        Assert.check(daoDescriptor != null, "daoDescriptor != null");
        Assert.check(userContext != null, "userContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        
        
        TransactionContext transactionContext = new TransactionContext(userContext);;
        try {
            save(transactionContext, valueObject);
        } finally {
            transactionContext.close();
        }
        
    }
    /** 
     * Saves a value object. 
     * Creates a map of saveObjects and turns off Jit loading before saving
     * @see com.modelgenerated.foundation.dataaccess.DataAccessObject#save(com.modelgenerated.foundation.dataaccess.TransactionContext, com.modelgenerated.foundation.dataaccess.ValueObject)
     */
    @Override
    public void save(TransactionContext transactionContext, ValueObject valueObject) {
        Assert.check(daoDescriptor != null, "daoDescriptor != null");
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        Map<Identity, ValueObject> savedObjects = new HashMap<Identity, ValueObject>();
        JitLoadingSwitchVisitor.disableJitLoading(valueObject);
        save(transactionContext, valueObject, savedObjects);
        JitLoadingSwitchVisitor.enableJitLoading(valueObject);
    }
        
    @Override
    public void save(TransactionContext transactionContext, ValueObject valueObject, Map<Identity, ValueObject> savedObjects) {
        Assert.check(daoDescriptor != null, "daoDescriptor != null");
        Assert.check(transactionContext != null, "transactionContext != null");
        Assert.check(valueObject != null, "valueObject != null");
        
        UserContext userContext = transactionContext.getUserContext();
        Assert.check(userContext != null, "userContext != null");
        
        if (savedObjects.get(valueObject.getId()) != null) {
            return;
        }
        savedObjects.put(valueObject.getId(), valueObject);
        
        if (valueObject.getIsNew()) {
            if (!valueObject.getIsDeleted()) {
                preSaveNew(transactionContext, valueObject);
                valueObject.setCreatedDate(transactionContext.getTransactionTime());
                valueObject.setCreatedBy(userContext.getUserName());
                valueObject.setModifiedDate(transactionContext.getTransactionTime());
                valueObject.setModifiedBy(userContext.getUserName());
                saveNew(transactionContext, valueObject);
                if (daoDescriptor.getAudited()) {
                    saveNewAuditData(transactionContext, valueObject);
                }
                valueObject.setIsNew(false);
            }
        } else {
            if (valueObject.getIsDeleted()) {
                saveDelete(transactionContext, valueObject);
                if (daoDescriptor.getAudited()) {
                    saveDeleteAuditData(transactionContext, valueObject);
                }
            } else {      
                preSaveExisting(transactionContext, valueObject);
                if (valueObject.getIsDirty()) {
                    ValueObject existingValueObject = this.findForUpdate(transactionContext, valueObject.getId());
                    // don't disable jit for existing object.
                    //JitLoadingSwitchVisitor.disableJitLoading(existingValueObject);

                    valueObject.setModifiedDate(transactionContext.getTransactionTime());
                    valueObject.setModifiedBy(userContext.getUserName());
                    saveExisting(transactionContext, existingValueObject, valueObject);
                    if (daoDescriptor.getAudited()) {
                        saveExistingAuditData(transactionContext, existingValueObject, valueObject);
                    }
                }
            }
        }
        saveChildren(transactionContext, valueObject, savedObjects);
        
    }
    /*
    private void save(TransactionContext transactionContext, ValueObjectList<?> valueObjectList) {
        Assert.check(daoDescriptor != null, "daoDescriptor != null");
        Assert.check(transactionContext != null, "transactionContext != null");
        
        Map<Identity, ValueObject> savedObjects = new HashMap<Identity, ValueObject>();
        save(transactionContext, valueObjectList, savedObjects);
    }
    */
    
    @Override
    public void save(TransactionContext transactionContext, ValueObjectList<?> valueObjectList, Map<Identity, ValueObject> savedObjects) {
        Assert.check(daoDescriptor != null, "daoDescriptor != null");
        Assert.check(transactionContext != null, "transactionContext != null");
        
        Logger.debug(this, "save list " + this.getClass().getName());
        Iterator i = valueObjectList.iterator();
        while (i.hasNext()) {
            ValueObject valueObject = (ValueObject)i.next();
            //Logger.debug(this, "  save child " + valueObject.getClass().getName());
            save(transactionContext, valueObject, savedObjects);
        }
        
    }
    
    protected String getVendorSpecificRowLimitPrefix(Connection connection, int topAmount) throws SQLException {
        StringBuffer sql = new StringBuffer();
        if (topAmount > 0) {
            DatabaseMetaData meta = connection.getMetaData();
            if ("Microsoft SQL Server".equals(meta.getDatabaseProductName())) {
                sql.append("top ");
                sql.append(topAmount);
                sql.append(" ");
            }
        }
        return sql.toString();
    }
    
    protected String getVendorSpecificRowLimitPostfix(Connection connection, int topAmount) throws SQLException {
        StringBuffer sql = new StringBuffer();
        if (topAmount > 0) {
            DatabaseMetaData meta = connection.getMetaData();
            if (!"Microsoft SQL Server".equals(meta.getDatabaseProductName())) {
                sql.append(" limit ");
                sql.append(topAmount);
                sql.append(" ");
            }
        }
        return sql.toString();
    }
    
    

    
}
