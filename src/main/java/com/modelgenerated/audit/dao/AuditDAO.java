/* AuditDAO.java
* Generated value object code
*/

package com.modelgenerated.audit.dao;

import com.modelgenerated.audit.Audit;
import com.modelgenerated.audit.AuditList;
import com.modelgenerated.foundation.dataaccess.DataAccessObject;
import com.modelgenerated.foundation.dataaccess.SearchCriteria;
import com.modelgenerated.foundation.dataaccess.TransactionContext;
import com.modelgenerated.foundation.dataaccess.UserContext;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.identity.Identity;
import java.util.Map;

public interface AuditDAO extends DataAccessObject {
    public Audit findByUser(UserContext userContext, String systemUser);
    public Audit findByUser(TransactionContext transactionContext, String systemUser);
    public Audit findByUser(TransactionContext transactionContext, String systemUser, Map<Identity,ValueObject> loadedObjects);

    public AuditList search(UserContext userContext, SearchCriteria searchCriteria, boolean deepCopy);
    public AuditList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy);

}
