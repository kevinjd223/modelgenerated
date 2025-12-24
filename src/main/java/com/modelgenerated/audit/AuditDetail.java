/* AuditDetail.java
* Generated value object code
*/

package com.modelgenerated.audit;

import com.modelgenerated.foundation.dataaccess.FieldAttribute;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.identity.Identity;



public interface AuditDetail extends ValueObject {
    FieldAttribute ATTRIB_COLUMNNAME = new FieldAttribute("ColumnName", null, 50);
    FieldAttribute ATTRIB_NEWVALUE = new FieldAttribute("NewValue", null, 255);
    FieldAttribute ATTRIB_OLDVALUE = new FieldAttribute("OldValue", null, 255);
    FieldAttribute ATTRIB_RECORDID = new FieldAttribute("RecordId", null, 0);
    FieldAttribute ATTRIB_TABLENAME = new FieldAttribute("TableName", null, 50);
    String getTableName();
    void setTableName(String newTableName);
    String getColumnName();
    void setColumnName(String newColumnName);
    String getOldValue();
    void setOldValue(String newOldValue);
    String getNewValue();
    void setNewValue(String newNewValue);
    Identity getRecordId();
    void setRecordId(Identity newRecordId);
    Audit getParent();
    Identity getParentId();
    void setParent(Audit newParent);
}
