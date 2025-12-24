/* TenantImpl.java
* Generated value object code
*/

package com.modelgenerated.authentication.impl.gen;

import com.modelgenerated.authentication.Tenant;
import com.modelgenerated.foundation.dataaccess.AbstractValueObject;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.util.DateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class TenantImpl extends AbstractValueObject implements Tenant, Serializable {
    final static long serialVersionUID = 1L;

    // com.modelgenerated.authentication.Tenant declarations
    protected String                               tenantName;

    /** 
     * Returns false to indicate the database table behind this class is NOT multi-tenant.
     */
    @Override
    public boolean getIsMultiTenant() {
        return false;
    }

    // com.modelgenerated.authentication.Tenant interfaces
    @Override
    public String getTenantName() {
        return tenantName;
    }
    @Override
    public void setTenantName(String newTenantName) {
        if (!same(newTenantName, tenantName )) { 
            isDirty = true;
        }
        tenantName = newTenantName;
    }

    @Override
    public Iterator<ValueObject> getReferencedObjects() {
        List<ValueObject> referencedList = new ArrayList<ValueObject>();

        referencedList.addAll(unresolvedReferences.values());
        return referencedList.iterator();
    }
}
