// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.Duration;

public class DurationImpl implements Duration
{
    private long quantity;
    private long delta;
    private TimeUnit unit;
    
    @Override
    public long getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(final long quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public TimeUnit getUnit() {
        return this.unit;
    }
    
    public void setUnit(final TimeUnit unit) {
        this.unit = unit;
    }
    
    @Override
    public long getDelta() {
        return this.delta;
    }
    
    public void setDelta(final long delta) {
        this.delta = delta;
    }
    
    @Override
    public boolean isInPast() {
        return this.getQuantity() < 0L;
    }
    
    @Override
    public boolean isInFuture() {
        return !this.isInPast();
    }
    
    @Override
    public long getQuantityRounded(final int tolerance) {
        long quantity = Math.abs(this.getQuantity());
        if (this.getDelta() != 0L) {
            final double threshold = Math.abs(this.getDelta() / (double)this.getUnit().getMillisPerUnit() * 100.0);
            if (threshold > tolerance) {
                ++quantity;
            }
        }
        return quantity;
    }
    
    @Override
    public String toString() {
        return "DurationImpl [" + this.quantity + " " + this.unit + ", delta=" + this.delta + "]";
    }
}
