// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.ResourcesTimeUnit;

public class Hour extends ResourcesTimeUnit implements TimeUnit
{
    public Hour() {
        this.setMillisPerUnit(3600000L);
    }
    
    @Override
    protected String getResourceKeyPrefix() {
        return "Hour";
    }
}
