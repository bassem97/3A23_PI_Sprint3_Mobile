// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.ResourcesTimeUnit;

public class Millisecond extends ResourcesTimeUnit implements TimeUnit
{
    public Millisecond() {
        this.setMillisPerUnit(1L);
    }
    
    @Override
    protected String getResourceKeyPrefix() {
        return "Millisecond";
    }
}
