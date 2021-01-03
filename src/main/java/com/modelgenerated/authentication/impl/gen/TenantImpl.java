/* TenantImpl.java
* Generated value object code
*/

package com.modelgenerated.authentication.impl.gen;

import com.modelgenerated.authentication.Tenant;
import com.modelgenerated.foundation.dataaccess.AbstractValueObject;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.debug.DisplayBuffer;
import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.util.DateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class TenantImpl extends AbstractValueObject implements Tenant, Serializable, Displayable {
    final static long serialVersionUID = 1L;

    // com.modelgenerated.authentication.Tenant declarations
    protected String                              tenantName;

    public boolean getIsMultiTenant() {
        return false;
    }

    // com.modelgenerated.authentication.Tenant interfaces
    public String getTenantName() {
        return tenantName;
    }
    public void setTenantName(String newTenantName) {
        if (!same(newTenantName, tenantName )) { 
            isDirty = true;
        }
        tenantName = newTenantName;
    }

    public Iterator<ValueObject> getReferencedObjects() {
        List<ValueObject> referencedList = new ArrayList<ValueObject>();

        Iterator<ValueObject> i = unresolvedReferences.values().iterator();
        while (i.hasNext()) {
            referencedList.add(i.next());
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
        DisplayBuffer displayBuffer = DisplayBuffer.newInstance("TenantImpl", objectDescription, level, maxLevels);
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
        displayBuffer.addLine(level+1, "tenantName: " + getTenantName());
        return displayBuffer.toString();
    }
}
