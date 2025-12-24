/* TenantDAO.java
* Generated value object code
*/

package com.modelgenerated.authentication.dao;

import com.modelgenerated.authentication.Tenant;
import com.modelgenerated.authentication.TenantList;
import com.modelgenerated.foundation.dataaccess.DataAccessObject;
import com.modelgenerated.foundation.dataaccess.SearchCriteria;
import com.modelgenerated.foundation.dataaccess.TransactionContext;
import com.modelgenerated.foundation.dataaccess.UserContext;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.identity.Identity;
import java.util.Map;

public interface TenantDAO extends DataAccessObject {
    public Tenant findByName(UserContext userContext, String tenantName);
    public Tenant findByName(TransactionContext transactionContext, String tenantName);
    public Tenant findByName(TransactionContext transactionContext, String tenantName, Map<Identity,ValueObject> loadedObjects);

    public TenantList search(UserContext userContext, SearchCriteria searchCriteria, boolean deepCopy);
    public TenantList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy);
    public int searchCount(UserContext userContext, SearchCriteria searchCriteria);
    public int searchCount(TransactionContext transactionContext, SearchCriteria searchCriteria);

}
