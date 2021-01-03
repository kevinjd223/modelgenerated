package com.modelgenerated.authentication.impl;


import com.modelgenerated.authentication.Account;
import com.modelgenerated.authentication.PasswordUtil;
import com.modelgenerated.authentication.impl.gen.AccountBaseImpl;


public class AccountImpl extends AccountBaseImpl implements Account {
    /**
      * Accepts a clear text passord and fills in hashed password.
      */
	
    public void setClearPassword(String password) {
	    setPassword(password);
    }
    
    public void encryptPassword(String password) {
	    setPassword(PasswordUtil.encryptPassword(password));
    }

}
