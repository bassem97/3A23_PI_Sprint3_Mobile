// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.DurationImpl;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.ResourcesTimeFormat;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.ResourcesTimeUnit;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units.*;

import java.util.*;

public class PrettyTime
{
    private volatile Date reference;
    private volatile Locale locale;
    private volatile Map<TimeUnit, TimeFormat> units;
    private List<TimeUnit> mCachedUnits;
    
    public PrettyTime() {
        this.locale = Locale.getDefault();
        this.units = new LinkedHashMap<TimeUnit, TimeFormat>();
        this.initTimeUnits();
    }
    
    public PrettyTime(final Date reference) {
        this();
        this.setReference(reference);
    }
    
    public PrettyTime(final Locale locale) {
        this.locale = Locale.getDefault();
        this.units = new LinkedHashMap<TimeUnit, TimeFormat>();
        this.setLocale(locale);
        this.initTimeUnits();
    }
    
    public PrettyTime(final Date reference, final Locale locale) {
        this(locale);
        this.setReference(reference);
    }
    
    public Duration approximateDuration(final Date then) {
        if (then == null) {
            throw new IllegalArgumentException("Date to approximate must not be null.");
        }
        Date ref = this.reference;
        if (null == ref) {
            ref = new Date();
        }
        final long difference = then.getTime() - ref.getTime();
        return this.calculateDuration(difference);
    }
    
    private void initTimeUnits() {
        this.addUnit(new JustNow());
        this.addUnit(new Millisecond());
        this.addUnit(new Second());
        this.addUnit(new Minute());
        this.addUnit(new Hour());
        this.addUnit(new Day());
        this.addUnit(new Week());
        this.addUnit(new Month());
        this.addUnit(new Year());
        this.addUnit(new Decade());
        this.addUnit(new Century());
        this.addUnit(new Millennium());
    }
    
    private void addUnit(final ResourcesTimeUnit unit) {
        this.registerUnit(unit, new ResourcesTimeFormat(unit));
    }
    
    private Duration calculateDuration(final long difference) {
        final long absoluteDifference = Math.abs(difference);
        final List<TimeUnit> localUnits = this.getUnits();
        final DurationImpl result = new DurationImpl();
        int i = 0;
        while (i < localUnits.size()) {
            final TimeUnit unit = localUnits.get(i);
            final long millisPerUnit = Math.abs(unit.getMillisPerUnit());
            long quantity = Math.abs(unit.getMaxQuantity());
            final boolean isLastUnit = i == localUnits.size() - 1;
            if (0L == quantity && !isLastUnit) {
                quantity = localUnits.get(i + 1).getMillisPerUnit() / unit.getMillisPerUnit();
            }
            if (millisPerUnit * quantity > absoluteDifference || isLastUnit) {
                result.setUnit(unit);
                if (millisPerUnit > absoluteDifference) {
                    result.setQuantity(this.getSign(difference));
                    result.setDelta(0L);
                    break;
                }
                result.setQuantity(difference / millisPerUnit);
                result.setDelta(difference - result.getQuantity() * millisPerUnit);
                break;
            }
            else {
                ++i;
            }
        }
        return result;
    }
    
    private long getSign(final long difference) {
        if (0L > difference) {
            return -1L;
        }
        return 1L;
    }
    
    public List<Duration> calculatePreciseDuration(final Date then) {
        if (then == null) {
            throw new IllegalArgumentException("Date to calculate must not be null.");
        }
        if (null == this.reference) {
            this.reference = new Date();
        }
        final List<Duration> result = new ArrayList<Duration>();
        final long difference = then.getTime() - this.reference.getTime();
        Duration duration = this.calculateDuration(difference);
        result.add(duration);
        while (0L != duration.getDelta()) {
            duration = this.calculateDuration(duration.getDelta());
            if (result.size() > 0) {
                final Duration last = result.get(result.size() - 1);
                if (last.getUnit().equals(duration.getUnit())) {
                    break;
                }
            }
            result.add(duration);
        }
        return result;
    }
    
    public String format(final Date then) {
        if (then == null) {
            throw new IllegalArgumentException("Date to format must not be null.");
        }
        final Duration d = this.approximateDuration(then);
        return this.format(d);
    }
    
    public String format(final Calendar then) {
        if (then == null) {
            throw new IllegalArgumentException("Provided Calendar must not be null.");
        }
        return this.format(then.getTime());
    }
    
    public String formatUnrounded(final Date then) {
        if (then == null) {
            throw new IllegalArgumentException("Date to format must not be null.");
        }
        final Duration d = this.approximateDuration(then);
        return this.formatUnrounded(d);
    }
    
    public String format(final Duration duration) {
        if (duration == null) {
            throw new IllegalArgumentException("Duration to format must not be null.");
        }
        final TimeFormat format = this.getFormat(duration.getUnit());
        final String time = format.format(duration);
        return format.decorate(duration, time);
    }
    
    public String formatUnrounded(final Duration duration) {
        if (duration == null) {
            throw new IllegalArgumentException("Duration to format must not be null.");
        }
        final TimeFormat format = this.getFormat(duration.getUnit());
        final String time = format.formatUnrounded(duration);
        return format.decorateUnrounded(duration, time);
    }
    
    public String format(final List<Duration> durations) {
        if (durations == null) {
            throw new IllegalArgumentException("Duration list must not be null.");
        }
        String result = null;
        if (durations != null) {
            final StringBuilder builder = new StringBuilder();
            Duration duration = null;
            TimeFormat format = null;
            for (int i = 0; i < durations.size(); ++i) {
                duration = durations.get(i);
                format = this.getFormat(duration.getUnit());
                builder.append(format.formatUnrounded(duration));
                builder.append(" ");
            }
            result = format.decorateUnrounded(duration, builder.toString());
        }
        return result;
    }
    
    public String formatApproximateDuration(final Date date) {
        final Duration duration = this.approximateDuration(date);
        return this.formatDuration(duration);
    }
    
    public String formatDuration(final Duration duration) {
        final TimeFormat timeFormat = this.getFormat(duration.getUnit());
        return timeFormat.format(duration);
    }
    
    public TimeFormat getFormat(final TimeUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Time unit must not be null.");
        }
        if (this.units.get(unit) != null) {
            return this.units.get(unit);
        }
        return null;
    }
    
    public Date getReference() {
        return this.reference;
    }
    
    public PrettyTime setReference(final Date timestamp) {
        this.reference = timestamp;
        return this;
    }
    
    public List<TimeUnit> getUnits() {
        if (this.mCachedUnits == null) {
            final List<TimeUnit> result = new ArrayList<TimeUnit>(this.units.keySet());
            Collections.sort(result, new TimeUnitComparator());
            this.mCachedUnits = Collections.unmodifiableList((List<? extends TimeUnit>)result);
        }
        return this.mCachedUnits;
    }
    
    public <UNIT extends TimeUnit> UNIT getUnit(final Class<UNIT> unitType) {
        if (unitType == null) {
            throw new IllegalArgumentException("Unit type to get must not be null.");
        }
        for (final TimeUnit unit : this.units.keySet()) {
            if (unitType.isAssignableFrom(unit.getClass())) {
                return (UNIT)unit;
            }
        }
        return null;
    }
    
    public PrettyTime registerUnit(final TimeUnit unit, final TimeFormat format) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit to register must not be null.");
        }
        if (format == null) {
            throw new IllegalArgumentException("Format to register must not be null.");
        }
        this.mCachedUnits = null;
        this.units.put(unit, format);
        if (unit instanceof LocaleAware) {
            ((LocaleAware)unit).setLocale(this.locale);
        }
        if (format instanceof LocaleAware) {
            ((LocaleAware)format).setLocale(this.locale);
        }
        return this;
    }
    
    public <UNIT extends TimeUnit> TimeFormat removeUnit(final Class<UNIT> unitType) {
        if (unitType == null) {
            throw new IllegalArgumentException("Unit type to remove must not be null.");
        }
        for (final TimeUnit unit : this.units.keySet()) {
            if (unitType.isAssignableFrom(unit.getClass())) {
                this.mCachedUnits = null;
                return this.units.remove(unit);
            }
        }
        return null;
    }
    
    public TimeFormat removeUnit(final TimeUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit to remove must not be null.");
        }
        this.mCachedUnits = null;
        return this.units.remove(unit);
    }
    
    public Locale getLocale() {
        return this.locale;
    }
    
    public PrettyTime setLocale(final Locale locale) {
        this.locale = locale;
        for (final TimeUnit unit : this.units.keySet()) {
            if (unit instanceof LocaleAware) {
                ((LocaleAware)unit).setLocale(locale);
            }
        }
        for (final TimeFormat format : this.units.values()) {
            if (format instanceof LocaleAware) {
                ((LocaleAware)format).setLocale(locale);
            }
        }
        return this;
    }
    
    @Override
    public String toString() {
        return "PrettyTime [reference=" + this.reference + ", locale=" + this.locale + "]";
    }
    
    public List<TimeUnit> clearUnits() {
        final List<TimeUnit> result = this.getUnits();
        this.mCachedUnits = null;
        this.units.clear();
        return result;
    }
}
