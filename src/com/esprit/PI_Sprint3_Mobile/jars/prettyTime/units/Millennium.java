// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.ResourcesTimeUnit;

public class Millennium extends ResourcesTimeUnit implements TimeUnit
{
    public Millennium() {
        this.setMillisPerUnit(31556926000000L);
    }
    
    @Override
    protected String getResourceKeyPrefix() {
        return "Millennium";
    }
}
