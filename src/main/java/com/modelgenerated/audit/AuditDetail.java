/* AuditDetail.java
* Generated value object code
*/

package com.modelgenerated.audit;

import com.modelgenerated.foundation.dataaccess.FieldAttribute;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.identity.Identity;
import java.util.Date;



public interface AuditDetail extends ValueObject, Displayable {
    public static final FieldAttribute ATTRIB_COLUMNNAME = new FieldAttribute("ColumnName", null);
    public static final FieldAttribute ATTRIB_NEWVALUE = new FieldAttribute("NewValue", null);
    public static final FieldAttribute ATTRIB_OLDVALUE = new FieldAttribute("OldValue", null);
    public static final FieldAttribute ATTRIB_RECORDID = new FieldAttribute("RecordId", null);
    public static final FieldAttribute ATTRIB_TABLENAME = new FieldAttribute("TableName", null);
    public String getTableName();
    public void setTableName(String newTableName);
    public String getColumnName();
    public void setColumnName(String newColumnName);
    public String getOldValue();
    public void setOldValue(String newOldValue);
    public String getNewValue();
    public void setNewValue(String newNewValue);
    public Identity getRecordId();
    public void setRecordId(Identity newRecordId);
    public Audit getParent();
    public Identity getParentId();
    public void setParent(Audit newParent);
}
