/*
 * AbstractValueObject.java
 *
 * Created on December 30, 2002, 9:49 PM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;


import com.modelgenerated.foundation.identity.Identity;
import com.modelgenerated.util.Assert;
import com.modelgenerated.util.StringUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for generate value objects.
 * @author  kevind
 */
public abstract class AbstractValueObject implements ValueObject, Serializable {
	private static final long serialVersionUID = 1L;
	protected UserContext userContext;
    protected Identity id;
    protected Identity tenantId;
    protected boolean isDirty;
    protected boolean isDeleted;
    protected boolean isNew;
    protected Date createdDate;
    protected String createdBy;
    protected Date modifiedDate;
    protected String modifiedBy;
    
    
    protected Map<Identity,ValueObject> unresolvedReferences = new HashMap<Identity,ValueObject>();
    // UNDONE: initialize jitLoadingEnabled to null when DAO is providing factory method and can
    // initialize. 
    // Why is this a string and why is it transient? 
    transient private String jitLoadingEnabled = "yes";
    
    /** Creates a new instance of AbstractValueObject */
    public AbstractValueObject() {
        isNew = true;
    }

    /**
     * The user object used to read or create this object.
     * @return
     */
    @Override
    public UserContext getUserContext() {
        return userContext;
    }
    
    @Override
    public void setUserContext(UserContext newUserContext) {
    	userContext = newUserContext;        
    }

    /**
     * The unigue id of this object
     */
    @Override
    public Identity getId() {
        return id;
    }
    
    @Override
    public void setId(Identity newId) {
        id = newId;        
    }
    
    @Override
    public Identity getTenantId() {
    	// put assert in for testing. Probably should be removed once DataAccess has been tested
    	Assert.check(this.getIsMultiTenant(), "ValueObject must be multiTenant to call getTenantId");
        return tenantId;
    }
    
    @Override
    public void setTenantId(Identity newTenantId) {
    	// put assert in for testing. Probably should be removed once DataAccess has been tested
    	Assert.check(this.getIsMultiTenant(), "ValueObject must be multiTenant to call setTenantId");
        tenantId = newTenantId;        
    }

    @Override
    public boolean getIsMultiTenant() {
    	// default implementation is false;
    	return false;
    }
    
    @Override
    public boolean getIsDirty() {
        return isDirty;
    }
    
    @Override
    public void setIsDirty(boolean newIsDirty) {
        isDirty = newIsDirty;
    }

    @Override
    public boolean getIsDeleted() {
        return isDeleted;
    }
    
    @Override
    public void setIsDeleted(boolean newIsDeleted) {
        isDeleted = newIsDeleted;
    }

    /**
     * Returns the New flag.
     * True if this object is newly created and has yet to be saved to the database.
     * @return
     */
    @Override
    public boolean getIsNew() {
        return isNew;
    }
    
    @Override
    public void setIsNew(boolean newIsNew) {
        isNew = newIsNew;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean getIsJITLoadingEnabled() {
        return jitLoadingEnabled != null;
    }
    
    @Override
    public void setIsJITLoadingEnabled(boolean enabled) {
        if (enabled) { 
            jitLoadingEnabled = "yes";
        } else {
            jitLoadingEnabled = null;
        }            
    }
    
    @Override
    public void addUnresolvedReference(ValueObject valueObject) {
        unresolvedReferences.put(valueObject.getId(), valueObject);
    }
    
    @Override
    public Date getCreatedDate() {
        return createdDate;
    }
    @Override
    public void setCreatedDate(Date newCreatedDate) {
        createdDate = newCreatedDate;
    }
    
    @Override
    public String getCreatedBy() {
        return createdBy;
    }
    @Override
    public void setCreatedBy(String newCreatedBy) {
        createdBy = newCreatedBy;
    }
    
    @Override
    public Date getModifiedDate() {
        return modifiedDate;
    }
    @Override
    public void setModifiedDate(Date newModifiedDate) {
        modifiedDate = newModifiedDate;
    }
    
    @Override
    public String getModifiedBy() {
        return modifiedBy;
    }    

    @Override
    public void setModifiedBy(String newModifiedBy) {
        modifiedBy = newModifiedBy;
    }
    

    
    /**
     * Tests if two objects are the "same".
     * Same means either both objects are null or they are "equal".
     */
    protected static boolean same(String o1, String o2) {
        String s1 = StringUtil.nullToEmpty(o1);
        String s2 = StringUtil.nullToEmpty(o2);
        return s1.equals(s2);
    }
    
    protected static boolean same(Object o1, Object o2) {
        if ((o1 instanceof String) && StringUtil.isEmpty((String)o1)) {
            o1 = null; 
        }
        if ((o1 instanceof EnumBase)) {
            o1 = ((EnumBase)o1).getValue(); 
        }
        if ((o2 instanceof String) && StringUtil.isEmpty((String)o2)) {
            o2 = null; 
        }
        if ((o2 instanceof EnumBase)) {
            o2 = ((EnumBase)o2).getValue(); 
        }
        
        if (o1 != null) {
            return o1.equals(o2);
        } 
        return (o2 == null);
    }
    
    protected static boolean same(Identity id1, ValueObject vo2) {
        Identity id2 = vo2 == null ? null : vo2.getId();        
        
        if (id1 != null) {
            return id1.equals(id2);
        } 
        return (id2 == null);
    }
    
    
    /**
     * Compares valueObject ids to verify they are the same object.  
     */
    public boolean equals(Object otherObject) {
        if (!(otherObject instanceof AbstractValueObject)) {
            return false;            
        }
        Assert.check(id != null, "this.id == null");
        Assert.check(((AbstractValueObject)otherObject).id != null, "otherId.id == null");
        
        return id.equals(((AbstractValueObject)otherObject).id);        
    }
    
    /**
     * Returns a hash value for this object
     * Used by objects like java.util.HashMap
     *  
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
    	Assert.check(id != null, "Values objects should never have a null id.");
    	
        if (id != null) { 
            return id.toString().hashCode();
        } else {
            return super.hashCode();
        }        
    }
    
    
    
    
}
