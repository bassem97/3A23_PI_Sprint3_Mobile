// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.Duration;

import java.util.Locale;
import java.util.ResourceBundle;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.LocaleAware;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeFormat;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.format.SimpleTimeFormat;

public class ResourcesTimeFormat extends SimpleTimeFormat implements TimeFormat, LocaleAware<ResourcesTimeFormat>
{
    private ResourceBundle bundle;
    private final ResourcesTimeUnit unit;
    private TimeFormat override;
    
    public ResourcesTimeFormat(final ResourcesTimeUnit unit) {
        this.unit = unit;
    }
    
    @Override
    public ResourcesTimeFormat setLocale(final Locale locale) {
        this.bundle = ResourceBundle.getBundle(this.unit.getResourceBundleName(), locale);
        if (this.bundle instanceof TimeFormatProvider) {
            final TimeFormat format = ((TimeFormatProvider)this.bundle).getFormatFor(this.unit);
            if (format != null) {
                this.override = format;
            }
        }
        else {
            this.override = null;
        }
        if (this.override == null) {
            this.setPattern(this.bundle.getString(this.unit.getResourceKeyPrefix() + "Pattern"));
            this.setFuturePrefix(this.bundle.getString(this.unit.getResourceKeyPrefix() + "FuturePrefix"));
            this.setFutureSuffix(this.bundle.getString(this.unit.getResourceKeyPrefix() + "FutureSuffix"));
            this.setPastPrefix(this.bundle.getString(this.unit.getResourceKeyPrefix() + "PastPrefix"));
            this.setPastSuffix(this.bundle.getString(this.unit.getResourceKeyPrefix() + "PastSuffix"));
            this.setSingularName(this.bundle.getString(this.unit.getResourceKeyPrefix() + "SingularName"));
            this.setPluralName(this.bundle.getString(this.unit.getResourceKeyPrefix() + "PluralName"));
            try {
                this.setFuturePluralName(this.bundle.getString(this.unit.getResourceKeyPrefix() + "FuturePluralName"));
            }
            catch (Exception ex) {}
            try {
                this.setFutureSingularName(this.bundle.getString(this.unit.getResourceKeyPrefix() + "FutureSingularName"));
            }
            catch (Exception ex2) {}
            try {
                this.setPastPluralName(this.bundle.getString(this.unit.getResourceKeyPrefix() + "PastPluralName"));
            }
            catch (Exception ex3) {}
            try {
                this.setPastSingularName(this.bundle.getString(this.unit.getResourceKeyPrefix() + "PastSingularName"));
            }
            catch (Exception ex4) {}
        }
        return this;
    }
    
    @Override
    public String decorate(final Duration duration, final String time) {
        return (this.override == null) ? super.decorate(duration, time) : this.override.decorate(duration, time);
    }
    
    @Override
    public String decorateUnrounded(final Duration duration, final String time) {
        return (this.override == null) ? super.decorateUnrounded(duration, time) : this.override.decorateUnrounded(duration, time);
    }
    
    @Override
    public String format(final Duration duration) {
        return (this.override == null) ? super.format(duration) : this.override.format(duration);
    }
    
    @Override
    public String formatUnrounded(final Duration duration) {
        return (this.override == null) ? super.formatUnrounded(duration) : this.override.formatUnrounded(duration);
    }
}
