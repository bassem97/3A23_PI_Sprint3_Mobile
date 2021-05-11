// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime;

public interface Duration
{
    long getQuantity();
    
    long getQuantityRounded(final int p0);
    
    TimeUnit getUnit();
    
    long getDelta();
    
    boolean isInPast();
    
    boolean isInFuture();
}
