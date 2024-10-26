/* ValueObject.java
 *
 * Copyright 2002-2024 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.foundation.identity.Identity;
import java.util.Date;
import java.util.Iterator;

/**
 * Base interface for generated value objects.
 * @author  kevind
 */
public interface ValueObject {
    public UserContext getUserContext();
    public void setUserContext(UserContext newUserContext);
    public Identity getId();
    public void setId(Identity newId);

    /**
     * Returns true if this object is configured for multi-tenancy
     * @return
     */
    public boolean getIsMultiTenant();
    /**
     * Returns the tenantId associated with the object
     * @return
     */
    public Identity getTenantId();
    public void setTenantId(Identity newTenantId);

    /**
     * Returns true if the object is dirty and needs to be saved
     * @return
     */
    public boolean getIsDirty();
    public void setIsDirty(boolean newIsDirty);
    public boolean getIsDeleted();
    public void setIsDeleted(boolean newIsDeleted);
    public boolean getIsNew();
    public void setIsNew(boolean newIsNew);
    public boolean getIsJITLoadingEnabled();
    public void setIsJITLoadingEnabled(boolean enabled);
    public Iterator<ValueObject> getReferencedObjects();
    public void addUnresolvedReference(ValueObject valueObject);
    
    public Date getCreatedDate();
    public void setCreatedDate(Date newCreatedDate);
    public String getCreatedBy();
    public void setCreatedBy(String newCreatedBy);

    public Date getModifiedDate();
    public void setModifiedDate(Date newModifiedDate);
    public String getModifiedBy();
    public void setModifiedBy(String newModifiedBy);
    
    
}
