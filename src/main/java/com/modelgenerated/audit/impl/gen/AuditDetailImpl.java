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
import com.modelgenerated.foundation.debug.DisplayBuffer;
import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.util.DateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AuditDetailImpl extends AbstractValueObject implements AuditDetail, Serializable, Displayable {
    final static long serialVersionUID = 1L;

    // com.modelgenerated.audit.AuditDetail declarations
    protected String                              tableName;
    protected String                              columnName;
    protected String                              oldValue;
    protected String                              newValue;
    protected Identity                            recordId;
    protected Audit                               parent;
    protected Identity                            parentId;

    public boolean getIsMultiTenant() {
        return true;
    }

    // com.modelgenerated.audit.AuditDetail interfaces
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String newTableName) {
        if (!same(newTableName, tableName )) { 
            isDirty = true;
        }
        tableName = newTableName;
    }
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String newColumnName) {
        if (!same(newColumnName, columnName )) { 
            isDirty = true;
        }
        columnName = newColumnName;
    }
    public String getOldValue() {
        return oldValue;
    }
    public void setOldValue(String newOldValue) {
        if (!same(newOldValue, oldValue )) { 
            isDirty = true;
        }
        oldValue = newOldValue;
    }
    public String getNewValue() {
        return newValue;
    }
    public void setNewValue(String newNewValue) {
        if (!same(newNewValue, newValue )) { 
            isDirty = true;
        }
        newValue = newNewValue;
    }
    public Identity getRecordId() {
        return recordId;
    }
    public void setRecordId(Identity newRecordId) {
        if (!same(newRecordId, recordId )) { 
            isDirty = true;
        }
        recordId = newRecordId;
    }
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
    public Identity getParentId() {
        if (parent != null) {
            return parent.getId();
        } else {
            return parentId;
        }
    }
    public void setParentId(Identity newParentId) {
        if (!same(parentId, newParentId )) { 
            isDirty = true;
        }
        parentId = newParentId;
    }

    public Iterator<ValueObject> getReferencedObjects() {
        List<ValueObject> referencedList = new ArrayList<ValueObject>();

        Iterator<ValueObject> i = unresolvedReferences.values().iterator();
        while (i.hasNext()) {
            referencedList.add(i.next());
        }
        if (parent != null) {
            referencedList.add(parent);
        }

        return referencedList.iterator();
    }

    // Displayable methods
    public String display() {
        return display ("");
    }

    public String display(String objectDescription) {
        Map<Object,Displayable> displayedObjects = new HashMap<Object,Displayable>();
        return display (objectDescription, 0, 0, displayedObjects);
    }

    public String display(String objectDescription, int level, int maxLevels, Map<Object,Displayable> displayedObjects) {
        DisplayBuffer displayBuffer = DisplayBuffer.newInstance("AuditDetailImpl", objectDescription, level, maxLevels);
        if (displayBuffer == null) {
            return "";
        }
        if (this.getId() != null && displayedObjects.get(this.getId()) != null) {
            displayBuffer.addLine(level+1, "id: " + id);
            return displayBuffer.toString();
        }
        displayedObjects.put(this.getId(), this);
        displayBuffer.addLine(level+1, "id: " + id);
        displayBuffer.addLine(level+1, "isDirty: " + isDirty);
        displayBuffer.addLine(level+1, "isDeleted: " + isDeleted);
        displayBuffer.addLine(level+1, "isNew: " + isNew);
        displayBuffer.addLine(level+1, "createdDate: " + DateUtil.formatDateTime(createdDate));
        displayBuffer.addLine(level+1, "createdBy: " + createdBy);
        displayBuffer.addLine(level+1, "modifiedDate: " + DateUtil.formatDateTime(modifiedDate));
        displayBuffer.addLine(level+1, "modifiedBy: " + modifiedBy);
        displayBuffer.addLine(level+1, "isJITLoadingEnabled: " + getIsJITLoadingEnabled());
        displayBuffer.addLine(level+1, "unresolvedReferences: ");
        Iterator<ValueObject> i = unresolvedReferences.values().iterator();
        while (i.hasNext()) {
            ValueObject childObject = (ValueObject)i.next();
            displayBuffer.addLine(level+2, "id: " + childObject.getId() + " class " + childObject.getClass().getName());
        }
        displayBuffer.addLine(level+1, "tableName: " + getTableName());
        displayBuffer.addLine(level+1, "columnName: " + getColumnName());
        displayBuffer.addLine(level+1, "oldValue: " + getOldValue());
        displayBuffer.addLine(level+1, "newValue: " + getNewValue());
        displayBuffer.addLine(level+1, "recordId: " + getRecordId());
        if (parent == null) {
            displayBuffer.addLine(level+1, "parentId: " + parentId);
        } else {
            displayBuffer.append(parent.display("parent", level+1, maxLevels, displayedObjects));
        }
        return displayBuffer.toString();
    }
}
