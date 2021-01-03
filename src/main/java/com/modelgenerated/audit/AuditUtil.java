/*
 * AuditUtil.java
 *
 * Created on October 11, 2003, 12:13 PM
 */

package com.modelgenerated.audit;

import com.modelgenerated.audit.dao.AuditDAO;
import com.modelgenerated.audit.dao.AuditDetailDAO;

import com.modelgenerated.foundation.dataaccess.UserContext;
import com.modelgenerated.foundation.dataaccess.DataAccessLocator;
import com.modelgenerated.foundation.dataaccess.TransactionContext;
import com.modelgenerated.foundation.factory.Factory;
import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.foundation.identity.IdentityBuilder;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.util.Assert;
import java.util.Date;

/**
 *
 * @author  kevind
 */
public class AuditUtil {
    
    /** Creates a new instance of AuditUtil */
    public AuditUtil() {
    }
    
    public Audit createAuditRecord(TransactionContext transactionContext, String eventType) {
        Assert.check (transactionContext != null, "transactionContext != null");
        UserContext userContext = transactionContext.getUserContext();
        Assert.check (userContext != null, "userContext != null");        
        
        Audit audit = (Audit)Factory.createObject(Audit.class.getName());
        Identity id = IdentityBuilder.createIdentity();
        audit.setId(id);        
        
        audit.setSystemUser(userContext.getUserName());
        audit.setEventType(eventType);
        audit.setEventDate(new Date());
        
        AuditDAO auditDAO = (AuditDAO)DataAccessLocator.findDAO(Audit.class.getName());
        auditDAO.save(userContext, audit);
        
        return audit;
    }
    
    public void createAuditDetailRecord(TransactionContext transactionContext, Audit audit, Identity recordId, String table, String column, Object oldValue, Object newValue) {
        Assert.check (transactionContext != null, "transactionContext != null");
        UserContext userContext = transactionContext.getUserContext();
        Assert.check (userContext != null, "userContext != null");        
        
        AuditDetail auditDetail = (AuditDetail)Factory.createObject(AuditDetail.class.getName());
        Identity id = IdentityBuilder.createIdentity();
        auditDetail.setId(id);
        
        auditDetail.setParent(audit);
        auditDetail.setRecordId(recordId);
        auditDetail.setTableName(table);
        auditDetail.setColumnName(column);
        if (oldValue != null) {
            auditDetail.setOldValue(oldValue.toString());
        }
        if (newValue != null) {
            auditDetail.setNewValue(newValue.toString());
        }        
        
        AuditDetailDAO auditDetailDAO = (AuditDetailDAO)DataAccessLocator.findDAO(AuditDetail.class.getName());
        auditDetailDAO.save(userContext, auditDetail);                
    }
    
    public void createAuditDetailRecord(TransactionContext transactionContext, Audit audit, Identity recordId, String table, String column, int oldValue, int newValue) {
        createAuditDetailRecord(transactionContext, audit, recordId, table, column, "" + oldValue, "" + newValue);
    }
    
    public void createAuditDetailRecord(TransactionContext transactionContext, Audit audit, Identity recordId, String table, String column, Object oldValue, int newValue) {
        createAuditDetailRecord(transactionContext, audit, recordId, table, column, oldValue, "" + newValue);
    }
    
    public void createAuditDetailRecord(TransactionContext transactionContext, Audit audit, Identity recordId, String table, String column, int oldValue, Object newValue) {
        createAuditDetailRecord(transactionContext, audit, recordId, table, column, "" + oldValue, newValue);
    }
    
    public boolean compareValuesDiffer(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return false;
        } else if (value1 == null && value2 != null) {
            return true;
        } else { // neither value is null
            return !value1.equals(value2);
        }
    }
    public boolean compareValuesDiffer(int value1, int value2) {
        return value1 != value2;
    }
    
}
