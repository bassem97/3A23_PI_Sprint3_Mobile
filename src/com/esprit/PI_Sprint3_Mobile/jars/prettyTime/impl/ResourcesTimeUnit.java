// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl;


import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;

public abstract class ResourcesTimeUnit implements TimeUnit
{
    private long maxQuantity;
    private long millisPerUnit;
    
    public ResourcesTimeUnit() {
        this.maxQuantity = 0L;
        this.millisPerUnit = 1L;
    }
    
    protected abstract String getResourceKeyPrefix();
    
    protected String getResourceBundleName() {
        return "com.esprit.PI_Sprint3_Mobile.jars.prettyTime.i18n.Resources";
    }
    
    @Override
    public long getMaxQuantity() {
        return this.maxQuantity;
    }
    
    public void setMaxQuantity(final long maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
    
    @Override
    public long getMillisPerUnit() {
        return this.millisPerUnit;
    }
    
    public void setMillisPerUnit(final long millisPerUnit) {
        this.millisPerUnit = millisPerUnit;
    }
    
    @Override
    public String toString() {
        return this.getResourceKeyPrefix();
    }
}
