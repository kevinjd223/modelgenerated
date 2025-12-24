/* Audit.java
* Generated value object code
*/

package com.modelgenerated.audit;

import com.modelgenerated.foundation.dataaccess.FieldAttribute;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.identity.Identity;
import java.util.Date;



public interface Audit extends ValueObject {
    FieldAttribute ATTRIB_EVENTDATE = new FieldAttribute("EventDate", null, 0);
    FieldAttribute ATTRIB_EVENTTYPE = new FieldAttribute("EventType", null, 20);
    FieldAttribute ATTRIB_SYSTEMUSER = new FieldAttribute("SystemUser", null, 50);
    String getSystemUser();
    void setSystemUser(String newSystemUser);
    String getEventType();
    void setEventType(String newEventType);
    Date getEventDate();
    void setEventDate(Date newEventDate);
    AuditDetailList getAuditDetailList();
    void setAuditDetailList(AuditDetailList newAuditDetailList);
}
