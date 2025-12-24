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


public class AuditImpl extends AbstractValueObject implements Audit, Serializable {
    final static long serialVersionUID = 1L;

    // com.modelgenerated.audit.Audit declarations
    protected String                               systemUser;
    protected String                               eventType;
    protected Date                                 eventDate;
    protected AuditDetailList                      auditDetailList;

    /** 
     * Returns true to indicate the database table behind this class is multi-tenant.
     */
    @Override
    public boolean getIsMultiTenant() {
        return true;
    }

    // com.modelgenerated.audit.Audit interfaces
    @Override
    public String getSystemUser() {
        return systemUser;
    }
    @Override
    public void setSystemUser(String newSystemUser) {
        if (!same(newSystemUser, systemUser )) { 
            isDirty = true;
        }
        systemUser = newSystemUser;
    }
    @Override
    public String getEventType() {
        return eventType;
    }
    @Override
    public void setEventType(String newEventType) {
        if (!same(newEventType, eventType )) { 
            isDirty = true;
        }
        eventType = newEventType;
    }
    @Override
    public Date getEventDate() {
        return eventDate;
    }
    @Override
    public void setEventDate(Date newEventDate) {
        if (!same(newEventDate, eventDate )) { 
            isDirty = true;
        }
        eventDate = newEventDate;
    }
    @Override
    public AuditDetailList getAuditDetailList() {
        if (auditDetailList == null) {
            if (isNew) {
                auditDetailList = (AuditDetailList)Factory.createObject(AuditDetailList.class);
            } else if (getIsJITLoadingEnabled()) {
                AuditDetailDAO auditDetailDAO = (AuditDetailDAO)DataAccessLocator.findDAO("com.modelgenerated.audit.AuditDetail");
                TransactionContext transactionContext = new TransactionContext(this.getUserContext());
                Map<Identity,ValueObject> loadedObjects = LoadedObjectVisitor.getLoadedObjects(this);
                try {
                    // targetObject: com.modelgenerated.modelmetadata.ObjectDescriptor
                    // query.fieldName: Parent
                    // targetField.getName(): Parent
                    // targetField.getColumnName(): AuditId
                    // targetField.getType(): Class
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
    @Override
    public void setAuditDetailList(AuditDetailList newAuditDetailList) {
        auditDetailList = newAuditDetailList;
    }

    @Override
    public Iterator<ValueObject> getReferencedObjects() {
        List<ValueObject> referencedList = new ArrayList<ValueObject>();

        referencedList.addAll(unresolvedReferences.values());
        if (auditDetailList != null) {
            for (AuditDetail auditDetail : auditDetailList) {
                referencedList.add(auditDetail);
            }
        }
        return referencedList.iterator();
    }
}
