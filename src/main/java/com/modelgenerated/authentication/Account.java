/* Account.java
* Generated value object code
*/

package com.modelgenerated.authentication;

import com.modelgenerated.foundation.dataaccess.FieldAttribute;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.identity.Identity;
import java.util.Date;



public interface Account extends ValueObject, Displayable {
    public static final FieldAttribute ATTRIB_BADAUTHENTICATIONATTEMPTS = new FieldAttribute("BadAuthenticationAttempts", null, 0);
    public static final FieldAttribute ATTRIB_LOCKEDOUT = new FieldAttribute("LockedOut", null, 0);
    public static final FieldAttribute ATTRIB_PASSWORD = new FieldAttribute("Password", null, 0);
    public static final FieldAttribute ATTRIB_USERNAME = new FieldAttribute("UserName", null, 0);
    public String getUserName();
    public void setUserName(String newUserName);
    public String getPassword();
    public void setPassword(String newPassword);
    public Boolean getLockedOut();
    public void setLockedOut(Boolean newLockedOut);
    public int getBadAuthenticationAttempts();
    public void setBadAuthenticationAttempts(int newBadAuthenticationAttempts);
    /**
      * Accepts a clear text password and fills in hashed password.
      */
    public void encryptPassword(String password);
}
