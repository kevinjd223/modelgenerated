/* Audit.java
* Generated value object code
*/

package com.modelgenerated.audit;

import com.modelgenerated.foundation.dataaccess.FieldAttribute;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.identity.Identity;
import java.util.Date;



public interface Audit extends ValueObject, Displayable {
    public static final FieldAttribute ATTRIB_EVENTDATE = new FieldAttribute("EventDate", null, 0);
    public static final FieldAttribute ATTRIB_EVENTTYPE = new FieldAttribute("EventType", null, 0);
    public static final FieldAttribute ATTRIB_SYSTEMUSER = new FieldAttribute("SystemUser", null, 0);
    public String getSystemUser();
    public void setSystemUser(String newSystemUser);
    public String getEventType();
    public void setEventType(String newEventType);
    public Date getEventDate();
    public void setEventDate(Date newEventDate);
    public AuditDetailList getAuditDetailList();
    public void setAuditDetailList(AuditDetailList newAuditDetailList);
}
