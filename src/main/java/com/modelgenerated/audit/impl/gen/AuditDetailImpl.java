/* AuditDetailImpl.java
* Generated value object code
*/

package com.modelgenerated.audit.impl.gen;

import com.modelgenerated.audit.Audit;
import com.modelgenerated.audit.AuditDetail;
import com.modelgenerated.audit.dao.AuditDAO;
import com.modelgenerated.foundation.dataaccess.AbstractValueObject;
import com.modelgenerated.foundation.dataaccess.DataAccessLocator;
import com.modelgenerated.foundation.dataaccess.LoadedObjectVisitor;
import com.modelgenerated.foundation.dataaccess.ObjectNotLoadedException;
import com.modelgenerated.foundation.dataaccess.TransactionContext;
import com.modelgenerated.foundation.dataaccess.UserContext;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.util.DateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AuditDetailImpl extends AbstractValueObject implements AuditDetail, Serializable {
    final static long serialVersionUID = 1L;

    // com.modelgenerated.audit.AuditDetail declarations
    protected String                               tableName;
    protected String                               columnName;
    protected String                               oldValue;
    protected String                               newValue;
    protected Identity                             recordId;
    protected Audit                                parent;
    protected Identity                             parentId;

    /** 
     * Returns true to indicate the database table behind this class is multi-tenant.
     */
    @Override
    public boolean getIsMultiTenant() {
        return true;
    }

    // com.modelgenerated.audit.AuditDetail interfaces
    @Override
    public String getTableName() {
        return tableName;
    }
    @Override
    public void setTableName(String newTableName) {
        if (!same(newTableName, tableName )) { 
            isDirty = true;
        }
        tableName = newTableName;
    }
    @Override
    public String getColumnName() {
        return columnName;
    }
    @Override
    public void setColumnName(String newColumnName) {
        if (!same(newColumnName, columnName )) { 
            isDirty = true;
        }
        columnName = newColumnName;
    }
    @Override
    public String getOldValue() {
        return oldValue;
    }
    @Override
    public void setOldValue(String newOldValue) {
        if (!same(newOldValue, oldValue )) { 
            isDirty = true;
        }
        oldValue = newOldValue;
    }
    @Override
    public String getNewValue() {
        return newValue;
    }
    @Override
    public void setNewValue(String newNewValue) {
        if (!same(newNewValue, newValue )) { 
            isDirty = true;
        }
        newValue = newNewValue;
    }
    @Override
    public Identity getRecordId() {
        return recordId;
    }
    @Override
    public void setRecordId(Identity newRecordId) {
        if (!same(newRecordId, recordId )) { 
            isDirty = true;
        }
        recordId = newRecordId;
    }
    @Override
    public Audit getParent() {
        if (parent == null && parentId != null) {
            if (!getIsJITLoadingEnabled()) {
                throw new ObjectNotLoadedException("Object Parent is not loaded");
            }
            AuditDAO auditDAO = (AuditDAO)DataAccessLocator.findDAO("com.modelgenerated.audit.Audit");
            TransactionContext transactionContext = new TransactionContext(this.getUserContext());
            Map<Identity,ValueObject> loadedObjects = LoadedObjectVisitor.getLoadedObjects(this);
            try {
                parent = (Audit)auditDAO.find(transactionContext, parentId, loadedObjects);
            } finally {
                transactionContext.close();
            }
            // 'if (... != null)' to handle broken links 
            if (parent != null) {
                parent.addUnresolvedReference(this);
            }
        }
        return parent;
    }
    @Override
    public void setParent(Audit newParent) {
        if (!same(parentId, newParent )) { 
            isDirty = true;
        }
        parent = newParent;
        if (newParent == null) {
            parentId = null;
        } else {
            parent.addUnresolvedReference(this);
        }
    }
    @Override
    public Identity getParentId() {
        if (parent != null) {
            return parent.getId();
        } else {
            return parentId;
        }
    }
    /**
     * Sets the ParentId.
     * <p>It is not part of the com.modelgenerated.audit.AuditDetail.
     */ 
    public void setParentId(Identity newParentId) {
        if (!same(parentId, newParentId )) { 
            isDirty = true;
        }
        parentId = newParentId;
    }

    @Override
    public Iterator<ValueObject> getReferencedObjects() {
        List<ValueObject> referencedList = new ArrayList<ValueObject>();

        referencedList.addAll(unresolvedReferences.values());
        if (parent != null) {
            referencedList.add(parent);
        }

        return referencedList.iterator();
    }
}
