/* AccountBaseImpl.java
* Generated value object code
*/

package com.modelgenerated.authentication.impl.gen;

import com.modelgenerated.authentication.Account;
import com.modelgenerated.foundation.dataaccess.AbstractValueObject;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.util.DateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public abstract class AccountBaseImpl extends AbstractValueObject implements Account, Serializable {
    final static long serialVersionUID = 1L;

    // com.modelgenerated.authentication.Account declarations
    protected String                               userName;
    protected String                               password;
    protected Boolean                              lockedOut;
    protected int                                  badAuthenticationAttempts;

    /** 
     * Returns true to indicate the database table behind this class is multi-tenant.
     */
    @Override
    public boolean getIsMultiTenant() {
        return true;
    }

    // com.modelgenerated.authentication.Account interfaces
    @Override
    public String getUserName() {
        return userName;
    }
    @Override
    public void setUserName(String newUserName) {
        if (!same(newUserName, userName )) { 
            isDirty = true;
        }
        userName = newUserName;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String newPassword) {
        if (!same(newPassword, password )) { 
            isDirty = true;
        }
        password = newPassword;
    }
    @Override
    public Boolean getLockedOut() {
        return lockedOut;
    }
    @Override
    public void setLockedOut(Boolean newLockedOut) {
        if (!same(newLockedOut, lockedOut )) { 
            isDirty = true;
        }
        lockedOut = newLockedOut;
    }
    @Override
    public int getBadAuthenticationAttempts() {
        return badAuthenticationAttempts;
    }
    @Override
    public void setBadAuthenticationAttempts(int newBadAuthenticationAttempts) {
        if (newBadAuthenticationAttempts != badAuthenticationAttempts ) { 
            isDirty = true;
        }
        badAuthenticationAttempts = newBadAuthenticationAttempts;
    }

    @Override
    public Iterator<ValueObject> getReferencedObjects() {
        List<ValueObject> referencedList = new ArrayList<ValueObject>();

        referencedList.addAll(unresolvedReferences.values());
        return referencedList.iterator();
    }
}
