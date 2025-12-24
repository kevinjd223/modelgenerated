/* AuditDetailDAO.java
* Generated value object code
*/

package com.modelgenerated.audit.dao;

import com.modelgenerated.audit.AuditDetail;
import com.modelgenerated.audit.AuditDetailList;
import com.modelgenerated.foundation.dataaccess.DataAccessObject;
import com.modelgenerated.foundation.dataaccess.SearchCriteria;
import com.modelgenerated.foundation.dataaccess.TransactionContext;
import com.modelgenerated.foundation.dataaccess.UserContext;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.audit.Audit;
import java.util.Map;

public interface AuditDetailDAO extends DataAccessObject {
    public AuditDetailList findByParent(UserContext userContext, Audit parent);
    public AuditDetailList findByParent(TransactionContext transactionContext, Audit parent);
    public AuditDetailList findByParent(TransactionContext transactionContext, Audit parent, Map<Identity,ValueObject> loadedObjects);

    public AuditDetailList search(UserContext userContext, SearchCriteria searchCriteria, boolean deepCopy);
    public AuditDetailList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy);
    public int searchCount(UserContext userContext, SearchCriteria searchCriteria);
    public int searchCount(TransactionContext transactionContext, SearchCriteria searchCriteria);

}
