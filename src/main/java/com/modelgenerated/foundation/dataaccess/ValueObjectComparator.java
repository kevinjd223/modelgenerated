/*
 * ValueObjectComparator.java
 *
 * Created on December 31, 2002, 6:37 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.foundation.dataaccess;

import com.modelgenerated.util.Assert;
import com.modelgenerated.util.StringUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

/**
 *
 * @author  kevind
 */
public class ValueObjectComparator implements Comparator {
    String sortBy1 = null;
    SortDirectionEnum sortDirection1 = SortDirectionEnum.ASCENDING;
    String sortBy2 = null;
    SortDirectionEnum sortDirection2 = SortDirectionEnum.ASCENDING;
    String sortBy3 = null;
    SortDirectionEnum sortDirection3 = SortDirectionEnum.ASCENDING;
    String sortBy4 = null;
    SortDirectionEnum sortDirection4 = SortDirectionEnum.ASCENDING;
    
    
    public void setSortBy1(String sortBy) {
        this.sortBy1 = sortBy;        
    }
    public void setSortDirection1(SortDirectionEnum sortDirection) {
        this.sortDirection1 = sortDirection;        
    }

    public void setSortBy2(String sortBy) {
        this.sortBy2 = sortBy;        
    }
    public void setSortDirection2(SortDirectionEnum sortDirection) {
        this.sortDirection2 = sortDirection;        
    }

    public void setSortBy3(String sortBy) {
        this.sortBy3 = sortBy;        
    }
    public void setSortDirection3(SortDirectionEnum sortDirection) {
        this.sortDirection3 = sortDirection;        
    }

    public void setSortBy4(String sortBy) {
        this.sortBy4 = sortBy;        
    }
    public void setSortDirection4(SortDirectionEnum sortDirection) {
        this.sortDirection4 = sortDirection;        
    }
    
    /** Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.<p>
     *
     * The implementor must ensure that <tt>sgn(compare(x, y)) ==
     * -sgn(compare(y, x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>compare(x, y)</tt> must throw an exception if and only
     * if <tt>compare(y, x)</tt> throws an exception.)<p>
     *
     * The implementor must also ensure that the relation is transitive:
     * <tt>((compare(x, y)&gt;0) &amp;&amp; (compare(y, z)&gt;0))</tt> implies
     * <tt>compare(x, z)&gt;0</tt>.<p>
     *
     * Finally, the implementer must ensure that <tt>compare(x, y)==0</tt>
     * implies that <tt>sgn(compare(x, z))==sgn(compare(y, z))</tt> for all
     * <tt>z</tt>.<p>
     *
     * It is generally the case, but <i>not</i> strictly required that
     * <tt>(compare(x, y)==0) == (x.equals(y))</tt>.  Generally speaking,
     * any comparator that violates this condition should clearly indicate
     * this fact.  The recommended language is "Note: this comparator
     * imposes orderings that are inconsistent with equals."
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return a negative integer, zero, or a positive integer as the
     * 	       first argument is less than, equal to, or greater than the
     * 	       second.
     * @throws ClassCastException if the arguments' types prevent them from
     * 	       being compared by this Comparator.
     *
     */
    public int compare(Object o1, Object o2) {
        //Assert.check(o1 != null, "o1 != null");
        //Assert.check(o2 != null, "o2 != null");
        //Logger.debug(this, "SORT compareo1");
        int compValue = compare(o1, o2, sortBy1, sortDirection1);
        //logCompare("One",o1, o2, sortBy1, compValue);
        
        if (compValue == 0 && !StringUtil.isEmpty(sortBy2)) {
            compValue = compare(o1, o2, sortBy2, sortDirection2);
            //logCompare("Two",o1, o2, sortBy2, compValue);
            if (compValue == 0 && !StringUtil.isEmpty(sortBy3)) {
                compValue = compare(o1, o2, sortBy3, sortDirection3);
                if (compValue == 0 && !StringUtil.isEmpty(sortBy4)) {
                    compValue = compare(o1, o2, sortBy4, sortDirection4);
                }
            }             
        }
        return compValue;
    }
    
    private int compare(Object o1, Object o2, String sortBy, SortDirectionEnum sortDirection) {

        ValueObject valueObject1 = (ValueObject)o1;
        //Assert.check(valueObject1 != null, "valueObject1 != null");
        ValueObject valueObject2 = (ValueObject)o2;
        //Assert.check(valueObject2 != null, "valueObject2 != null");
        
        Comparable comp1 = getComparable(valueObject1, sortBy);
        Comparable comp2 = getComparable(valueObject2, sortBy);
        
        if (comp1 != null && comp1 instanceof String) {
        	comp1 = ((String)comp1).toLowerCase();
        }
        if (comp2 != null && comp2 instanceof String) {
        	comp2 = ((String)comp2).toLowerCase();
        }
        
        //Logger.debug(this, "  compare - comp1: " + comp1 == null ? "nul" : comp1.toString());
        //Logger.debug(this, "  compare - comp2: " + comp2 == null ? "null" : comp2.toString());
        // null sorts greater than all values
        int compareResults;
        if (comp1 == null && comp2 != null) {
            compareResults = -1;
        } else if (comp1 != null && comp2 == null) {
            compareResults = 1;
        } else if (comp1 == null && comp2 == null) {
            compareResults = 0;
        } else {        
            compareResults = comp1.compareTo(comp2);
        }
        //Logger.debug(this, "  sortDirection: " + sortDirection);
        //Logger.debug(this, "  compareResults: " + compareResults);
        return sortDirection == SortDirectionEnum.DESCENDING ? compareResults * -1 : compareResults; 
    }    

    /*
     * Uses reflection to return the comparable field
     */
    private static Comparable getComparable(Object parentObject, String attributeName) {
        try {
            if (parentObject == null ) {
                return null; 
            }
            
            Method method = parentObject.getClass().getMethod("get" + attributeName, (Class[])null);
            Assert.check(method != null, "method != null");
            
            Comparable comparable = (Comparable)method.invoke(parentObject, (Object[])null);
            return comparable;        
        } catch (NoSuchMethodException e) {
            throw new SubObjectHelperException("error getting sub object id for attribute: " + attributeName, e);
        } catch (InvocationTargetException e) {
            throw new SubObjectHelperException("error getting sub object id for attribute: " + attributeName, e);
        } catch (IllegalAccessException e) {
            throw new SubObjectHelperException("error getting sub object id for attribute: " + attributeName, e);
        }
        
    }
    
    
}
