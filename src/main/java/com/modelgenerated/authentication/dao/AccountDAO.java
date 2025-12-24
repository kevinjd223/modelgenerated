/* AccountDAO.java
* Generated value object code
*/

package com.modelgenerated.authentication.dao;

import com.modelgenerated.authentication.Account;
import com.modelgenerated.authentication.AccountList;
import com.modelgenerated.foundation.dataaccess.DataAccessObject;
import com.modelgenerated.foundation.dataaccess.SearchCriteria;
import com.modelgenerated.foundation.dataaccess.TransactionContext;
import com.modelgenerated.foundation.dataaccess.UserContext;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.identity.Identity;
import java.util.Map;

public interface AccountDAO extends DataAccessObject {
    public Account findByUserName(UserContext userContext, String userName);
    public Account findByUserName(TransactionContext transactionContext, String userName);
    public Account findByUserName(TransactionContext transactionContext, String userName, Map<Identity,ValueObject> loadedObjects);

    public AccountList search(UserContext userContext, SearchCriteria searchCriteria, boolean deepCopy);
    public AccountList search(TransactionContext transactionContext, SearchCriteria searchCriteria, boolean deepCopy);
    public int searchCount(UserContext userContext, SearchCriteria searchCriteria);
    public int searchCount(TransactionContext transactionContext, SearchCriteria searchCriteria);

}
