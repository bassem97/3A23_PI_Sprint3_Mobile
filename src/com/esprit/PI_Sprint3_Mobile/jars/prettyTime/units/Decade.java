// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.ResourcesTimeUnit;

public class Decade extends ResourcesTimeUnit implements TimeUnit
{
    public Decade() {
        this.setMillisPerUnit(315569259747L);
    }
    
    @Override
    protected String getResourceKeyPrefix() {
        return "Decade";
    }
}
