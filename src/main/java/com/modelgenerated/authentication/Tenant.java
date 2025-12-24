/* Tenant.java
* Generated value object code
*/

package com.modelgenerated.authentication;

import com.modelgenerated.foundation.dataaccess.FieldAttribute;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.identity.Identity;



public interface Tenant extends ValueObject {
    FieldAttribute ATTRIB_TENANTNAME = new FieldAttribute("TenantName", null, 50);
    String getTenantName();
    void setTenantName(String newTenantName);
}
