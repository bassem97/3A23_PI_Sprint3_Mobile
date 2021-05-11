// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime;

public interface TimeFormat
{
    String format(final Duration p0);
    
    String formatUnrounded(final Duration p0);
    
    String decorate(final Duration p0, final String p1);
    
    String decorateUnrounded(final Duration p0, final String p1);
}
