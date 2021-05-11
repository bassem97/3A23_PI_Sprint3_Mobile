// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.ResourcesTimeUnit;

public class Minute extends ResourcesTimeUnit implements TimeUnit
{
    public Minute() {
        this.setMillisPerUnit(60000L);
    }
    
    @Override
    protected String getResourceKeyPrefix() {
        return "Minute";
    }
}
