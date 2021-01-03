/*
 * IdentityUUIDImpl.java
 *
 * Created on November 26, 2002, 9:34 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.identity;

import com.modelgenerated.util.Assert;
import java.io.Serializable;
import org.doomdark.uuid.UUID;
import org.doomdark.uuid.UUIDGenerator;

/**
 *
 * @author  kevind
 */
public class IdentityUUIDImpl implements Identity, Serializable {
	private static final long serialVersionUID = 1L;
	private final UUID uuid;
    
    /** Creates a new instance of IdentityUUIDImpl */
    public IdentityUUIDImpl() {
        UUIDGenerator uuidGenerator = UUIDGenerator.getInstance();
        uuid = uuidGenerator.generateTimeBasedUUID(uuidGenerator.getDummyAddress());
    }
    
    public IdentityUUIDImpl(String initValue) {
        uuid = new UUID(initValue);
    }
    
    public IdentityUUIDImpl(byte [] initValue) {
        uuid = new UUID(initValue);
    }

    public String getStringValue() {
        return uuid.toString();
    }
    
    public byte[] getByteValue() {
        return uuid.toByteArray();
    }
    
    public String toString(){
        if (uuid != null) {
            return uuid.toString();
        } else { 
            return "uninitialized identifier";
        }
    }
    
    public boolean equals(Object otherId) {
        if (!(otherId instanceof IdentityUUIDImpl)) {
            return false;            
        }
        Assert.check(uuid != null, "this.uuid == null");
        Assert.check(((IdentityUUIDImpl)otherId).uuid != null, "ohterId.uuid == null");
        
        return uuid.equals(((IdentityUUIDImpl)otherId).uuid);        
    }
    
    public int hashCode () {
        return uuid.hashCode();
    }
      
    
}
