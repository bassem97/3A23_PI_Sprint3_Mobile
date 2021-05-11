// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units;

import java.io.Serializable;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;
import java.util.Comparator;

public class TimeUnitComparator implements Comparator<TimeUnit>, Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Override
    public int compare(final TimeUnit left, final TimeUnit right) {
        if (left.getMillisPerUnit() < right.getMillisPerUnit()) {
            return -1;
        }
        if (left.getMillisPerUnit() > right.getMillisPerUnit()) {
            return 1;
        }
        return 0;
    }
}
