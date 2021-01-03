/* Tenant.java
* Generated value object code
*/

package com.modelgenerated.authentication;

import com.modelgenerated.foundation.dataaccess.FieldAttribute;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.identity.Identity;
import java.util.Date;



public interface Tenant extends ValueObject, Displayable {
    public static final FieldAttribute ATTRIB_TENANTNAME = new FieldAttribute("TenantName", null, 0);
    public String getTenantName();
    public void setTenantName(String newTenantName);
}
