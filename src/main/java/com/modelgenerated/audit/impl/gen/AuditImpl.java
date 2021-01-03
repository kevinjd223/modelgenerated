/* AuditImpl.java
* Generated value object code
*/

package com.modelgenerated.audit.impl.gen;

import com.modelgenerated.audit.Audit;
import com.modelgenerated.audit.AuditDetail;
import com.modelgenerated.audit.AuditDetailList;
import com.modelgenerated.audit.dao.AuditDetailDAO;
import com.modelgenerated.foundation.dataaccess.AbstractValueObject;
import com.modelgenerated.foundation.dataaccess.DataAccessLocator;
import com.modelgenerated.foundation.dataaccess.LoadedObjectVisitor;
import com.modelgenerated.foundation.dataaccess.ObjectNotLoadedException;
import com.modelgenerated.foundation.dataaccess.TransactionContext;
import com.modelgenerated.foundation.dataaccess.UserContext;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.debug.DisplayBuffer;
import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.factory.Factory;
import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.util.DateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class AuditImpl extends AbstractValueObject implements Audit, Serializable, Displayable {
    final static long serialVersionUID = 1L;

    // com.modelgenerated.audit.Audit declarations
    protected String                              systemUser;
    protected String                              eventType;
    protected Date                                eventDate;
    protected AuditDetailList                     auditDetailList;

    public boolean getIsMultiTenant() {
        return true;
    }

    // com.modelgenerated.audit.Audit interfaces
    public String getSystemUser() {
        return systemUser;
    }
    public void setSystemUser(String newSystemUser) {
        if (!same(newSystemUser, systemUser )) { 
            isDirty = true;
        }
        systemUser = newSystemUser;
    }
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String newEventType) {
        if (!same(newEventType, eventType )) { 
            isDirty = true;
        }
        eventType = newEventType;
    }
    public Date getEventDate() {
        return eventDate;
    }
    public void setEventDate(Date newEventDate) {
        if (!same(newEventDate, eventDate )) { 
            isDirty = true;
        }
        eventDate = newEventDate;
    }
    public AuditDetailList getAuditDetailList() {
        if (auditDetailList == null) {
            if (isNew) {
                auditDetailList = (AuditDetailList)Factory.createObject(AuditDetailList.class);
            } else if (getIsJITLoadingEnabled()) {
                AuditDetailDAO auditDetailDAO = (AuditDetailDAO)DataAccessLocator.findDAO("com.modelgenerated.audit.AuditDetail");
                TransactionContext transactionContext = new TransactionContext(this.getUserContext());
                Map<Identity,ValueObject> loadedObjects = LoadedObjectVisitor.getLoadedObjects(this);
                try {
                    auditDetailList = auditDetailDAO.findByParent(transactionContext, this, loadedObjects);
                } finally {
                    transactionContext.close();
                }
            } else {
                throw new ObjectNotLoadedException("Object AuditDetailList is not loaded");
            }
        }
        return auditDetailList;
    }
    public void setAuditDetailList(AuditDetailList newAuditDetailList) {
        auditDetailList = newAuditDetailList;
    }

    public Iterator<ValueObject> getReferencedObjects() {
        List<ValueObject> referencedList = new ArrayList<ValueObject>();

        Iterator<ValueObject> i = unresolvedReferences.values().iterator();
        while (i.hasNext()) {
            referencedList.add(i.next());
        }
        if (auditDetailList != null) {
            for (AuditDetail auditDetail : auditDetailList) {
                referencedList.add(auditDetail);
            }
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
        DisplayBuffer displayBuffer = DisplayBuffer.newInstance("AuditImpl", objectDescription, level, maxLevels);
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
        displayBuffer.addLine(level+1, "systemUser: " + getSystemUser());
        displayBuffer.addLine(level+1, "eventType: " + getEventType());
        displayBuffer.addLine(level+1, "eventDate: " + getEventDate());
        if (auditDetailList == null) {
            displayBuffer.addLine(level+1, "auditDetailList: " + auditDetailList);
        } else {
            displayBuffer.append(auditDetailList.display("auditDetailList", level+1, maxLevels, displayedObjects));
        }
        return displayBuffer.toString();
    }
}
