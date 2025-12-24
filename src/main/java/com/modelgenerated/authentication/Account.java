/* Account.java
* Generated value object code
*/

package com.modelgenerated.authentication;

import com.modelgenerated.foundation.dataaccess.FieldAttribute;
import com.modelgenerated.foundation.dataaccess.ValueObject;
import com.modelgenerated.foundation.identity.Identity;



public interface Account extends ValueObject {
    FieldAttribute ATTRIB_BADAUTHENTICATIONATTEMPTS = new FieldAttribute("BadAuthenticationAttempts", null, 0);
    FieldAttribute ATTRIB_LOCKEDOUT = new FieldAttribute("LockedOut", null, 0);
    FieldAttribute ATTRIB_PASSWORD = new FieldAttribute("Password", null, 50);
    FieldAttribute ATTRIB_USERNAME = new FieldAttribute("UserName", null, 50);
    String getUserName();
    void setUserName(String newUserName);
    String getPassword();
    void setPassword(String newPassword);
    Boolean getLockedOut();
    void setLockedOut(Boolean newLockedOut);
    int getBadAuthenticationAttempts();
    void setBadAuthenticationAttempts(int newBadAuthenticationAttempts);
    /**
      * Accepts a clear text password and fills in hashed password.
      */
    void encryptPassword(String password);
}
