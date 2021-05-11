// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.format;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.Duration;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeFormat;

public class SimpleTimeFormat implements TimeFormat
{
    private static final String NEGATIVE = "-";
    public static final String SIGN = "%s";
    public static final String QUANTITY = "%n";
    public static final String UNIT = "%u";
    private String singularName;
    private String pluralName;
    private String futureSingularName;
    private String futurePluralName;
    private String pastSingularName;
    private String pastPluralName;
    private String pattern;
    private String futurePrefix;
    private String futureSuffix;
    private String pastPrefix;
    private String pastSuffix;
    private int roundingTolerance;
    
    public SimpleTimeFormat() {
        this.singularName = "";
        this.pluralName = "";
        this.futureSingularName = "";
        this.futurePluralName = "";
        this.pastSingularName = "";
        this.pastPluralName = "";
        this.pattern = "";
        this.futurePrefix = "";
        this.futureSuffix = "";
        this.pastPrefix = "";
        this.pastSuffix = "";
        this.roundingTolerance = 50;
    }
    
    @Override
    public String format(final Duration duration) {
        return this.format(duration, true);
    }
    
    @Override
    public String formatUnrounded(final Duration duration) {
        return this.format(duration, false);
    }
    
    @Override
    public String decorate(final Duration duration, final String time) {
        final StringBuilder result = new StringBuilder();
        if (duration.isInPast()) {
            result.append(this.pastPrefix).append(" ").append(time).append(" ").append(this.pastSuffix);
        }
        else {
            result.append(this.futurePrefix).append(" ").append(time).append(" ").append(this.futureSuffix);
        }
        return result.toString().replaceAll("\\s+", " ").trim();
    }
    
    @Override
    public String decorateUnrounded(final Duration duration, final String time) {
        return this.decorate(duration, time);
    }
    
    private String format(final Duration duration, final boolean round) {
        final String sign = this.getSign(duration);
        final String unit = this.getGramaticallyCorrectName(duration, round);
        final long quantity = this.getQuantity(duration, round);
        return this.applyPattern(sign, unit, quantity);
    }
    
    private String applyPattern(final String sign, final String unit, final long quantity) {
        String result = this.getPattern(quantity).replaceAll("%s", sign);
        result = result.replaceAll("%n", String.valueOf(quantity));
        result = result.replaceAll("%u", unit);
        return result;
    }
    
    protected String getPattern(final long quantity) {
        return this.pattern;
    }
    
    public String getPattern() {
        return this.pattern;
    }
    
    protected long getQuantity(final Duration duration, final boolean round) {
        return Math.abs(round ? duration.getQuantityRounded(this.roundingTolerance) : duration.getQuantity());
    }
    
    protected String getGramaticallyCorrectName(final Duration d, final boolean round) {
        String result = this.getSingularName(d);
        if (Math.abs(this.getQuantity(d, round)) == 0L || Math.abs(this.getQuantity(d, round)) > 1L) {
            result = this.getPluralName(d);
        }
        return result;
    }
    
    private String getSign(final Duration d) {
        if (d.getQuantity() < 0L) {
            return "-";
        }
        return "";
    }
    
    private String getSingularName(final Duration duration) {
        if (duration.isInFuture() && this.futureSingularName != null && this.futureSingularName.length() > 0) {
            return this.futureSingularName;
        }
        if (duration.isInPast() && this.pastSingularName != null && this.pastSingularName.length() > 0) {
            return this.pastSingularName;
        }
        return this.singularName;
    }
    
    private String getPluralName(final Duration duration) {
        if (duration.isInFuture() && this.futurePluralName != null && this.futureSingularName.length() > 0) {
            return this.futurePluralName;
        }
        if (duration.isInPast() && this.pastPluralName != null && this.pastSingularName.length() > 0) {
            return this.pastPluralName;
        }
        return this.pluralName;
    }
    
    public SimpleTimeFormat setPattern(final String pattern) {
        this.pattern = pattern;
        return this;
    }
    
    public SimpleTimeFormat setFuturePrefix(final String futurePrefix) {
        this.futurePrefix = futurePrefix.trim();
        return this;
    }
    
    public SimpleTimeFormat setFutureSuffix(final String futureSuffix) {
        this.futureSuffix = futureSuffix.trim();
        return this;
    }
    
    public SimpleTimeFormat setPastPrefix(final String pastPrefix) {
        this.pastPrefix = pastPrefix.trim();
        return this;
    }
    
    public SimpleTimeFormat setPastSuffix(final String pastSuffix) {
        this.pastSuffix = pastSuffix.trim();
        return this;
    }
    
    public SimpleTimeFormat setRoundingTolerance(final int roundingTolerance) {
        this.roundingTolerance = roundingTolerance;
        return this;
    }
    
    public SimpleTimeFormat setSingularName(final String name) {
        this.singularName = name;
        return this;
    }
    
    public SimpleTimeFormat setPluralName(final String pluralName) {
        this.pluralName = pluralName;
        return this;
    }
    
    public SimpleTimeFormat setFutureSingularName(final String futureSingularName) {
        this.futureSingularName = futureSingularName;
        return this;
    }
    
    public SimpleTimeFormat setFuturePluralName(final String futurePluralName) {
        this.futurePluralName = futurePluralName;
        return this;
    }
    
    public SimpleTimeFormat setPastSingularName(final String pastSingularName) {
        this.pastSingularName = pastSingularName;
        return this;
    }
    
    public SimpleTimeFormat setPastPluralName(final String pastPluralName) {
        this.pastPluralName = pastPluralName;
        return this;
    }
    
    @Override
    public String toString() {
        return "SimpleTimeFormat [pattern=" + this.pattern + ", futurePrefix=" + this.futurePrefix + ", futureSuffix=" + this.futureSuffix + ", pastPrefix=" + this.pastPrefix + ", pastSuffix=" + this.pastSuffix + ", roundingTolerance=" + this.roundingTolerance + "]";
    }
}
