/*
 * DataAccessObject.java
 *
 * Created on December 31, 2002, 6:35 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.identity.Identity;

import java.util.Map;

/**
 * Interface for a generated data access object.  
 * @author  kevind
 */
public interface DataAccessObject {
    
    void init(DAODescriptor daoDescriptor);
    public ValueObject newValueObject();
    public ValueObjectList<?> newListObject();    
    ValueObject find(UserContext userContext, Identity id);
    ValueObject find(TransactionContext transactionContext, Identity id);
    ValueObject find(TransactionContext transactionContext, Identity id, Map<Identity,ValueObject> loadedObjects);
    ValueObject findForUpdate(TransactionContext transactionContext, Identity id);
    void save(UserContext userContext, ValueObject valueObject);
    void preSaveNew(TransactionContext transactionContext, ValueObject valueObject);
    void preSaveExisting(TransactionContext transactionContext, ValueObject valueObject);
    void save(TransactionContext transactionContext, ValueObject valueObject);
    void save(TransactionContext transactionContext, ValueObject valueObject, Map<Identity,ValueObject> savedObjects);
    void save(TransactionContext transactionContext, ValueObjectList<?> valueObjectList, Map<Identity,ValueObject> savedObjects);    
    void saveNew(TransactionContext transactionContext, ValueObject valueObject);
    void saveNewAuditData(TransactionContext transactionContext, ValueObject valueObject);
    void saveExisting(TransactionContext transactionContext, ValueObject existingValueObject, ValueObject valueObject);
    void saveExistingAuditData(TransactionContext transactionContext, ValueObject existingValueObject, ValueObject valueObject);
    void saveDelete(TransactionContext transactionContext, ValueObject valueObject);
    void saveDeleteAuditData(TransactionContext transactionContext, ValueObject valueObject);
    void saveChildren(TransactionContext transactionContext, ValueObject valueObject, Map<Identity,ValueObject> saveObjects);
    
}
