// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.i18n;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.Duration;

import java.util.Collections;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.format.SimpleTimeFormat;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units.Year;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units.Month;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units.Week;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units.Day;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units.Hour;
import java.util.ResourceBundle;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units.Minute;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeFormat;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.TimeFormatProvider;
import java.util.ListResourceBundle;

public class Resources_cs extends ListResourceBundle implements TimeFormatProvider
{
    private static final Object[][] OBJECTS;
    
    public Object[][] getContents() {
        return Resources_cs.OBJECTS;
    }
    
    @Override
    public TimeFormat getFormatFor(final TimeUnit t) {
        if (t instanceof Minute) {
            return new CsTimeFormatBuilder("Minute").addFutureName("minutu", 1L).addFutureName("minuty", 4L).addFutureName("minut", Long.MAX_VALUE).addPastName("minutou", 1L).addPastName("minutami", Long.MAX_VALUE).build(this);
        }
        if (t instanceof Hour) {
            return new CsTimeFormatBuilder("Hour").addFutureName("hodinu", 1L).addFutureName("hodiny", 4L).addFutureName("hodin", Long.MAX_VALUE).addPastName("hodinou", 1L).addPastName("hodinami", Long.MAX_VALUE).build(this);
        }
        if (t instanceof Day) {
            return new CsTimeFormatBuilder("Day").addFutureName("den", 1L).addFutureName("dny", 4L).addFutureName("dn\u00ed", Long.MAX_VALUE).addPastName("dnem", 1L).addPastName("dny", Long.MAX_VALUE).build(this);
        }
        if (t instanceof Week) {
            return new CsTimeFormatBuilder("Week").addFutureName("t\u00fdden", 1L).addFutureName("t\u00fddny", 4L).addFutureName("t\u00fddn\u016f", Long.MAX_VALUE).addPastName("t\u00fddnem", 1L).addPastName("t\u00fddny", Long.MAX_VALUE).build(this);
        }
        if (t instanceof Month) {
            return new CsTimeFormatBuilder("Month").addFutureName("m\u011bs\u00edc", 1L).addFutureName("m\u011bs\u00edce", 4L).addFutureName("m\u011bs\u00edc\u016f", Long.MAX_VALUE).addPastName("m\u011bs\u00edcem", 1L).addPastName("m\u011bs\u00edci", Long.MAX_VALUE).build(this);
        }
        if (t instanceof Year) {
            return new CsTimeFormatBuilder("Year").addFutureName("rok", 1L).addFutureName("roky", 4L).addFutureName("let", Long.MAX_VALUE).addPastName("rokem", 1L).addPastName("roky", Long.MAX_VALUE).build(this);
        }
        return null;
    }
    
    static {
        OBJECTS = new Object[][] { { "CenturyPattern", "%n %u" }, { "CenturyFuturePrefix", "za " }, { "CenturyFutureSuffix", "" }, { "CenturyPastPrefix", "p\u0159ed " }, { "CenturyPastSuffix", "" }, { "CenturySingularName", "stolet\u00ed" }, { "CenturyPluralName", "stolet\u00ed" }, { "CenturyPastSingularName", "stolet\u00edm" }, { "CenturyPastPluralName", "stolet\u00edmi" }, { "CenturyFutureSingularName", "stolet\u00ed" }, { "CenturyFuturePluralName", "stolet\u00ed" }, { "DayPattern", "%n %u" }, { "DayFuturePrefix", "za " }, { "DayFutureSuffix", "" }, { "DayPastPrefix", "p\u0159ed " }, { "DayPastSuffix", "" }, { "DaySingularName", "den" }, { "DayPluralName", "dny" }, { "DecadePattern", "%n %u" }, { "DecadeFuturePrefix", "za " }, { "DecadeFutureSuffix", "" }, { "DecadePastPrefix", "p\u0159ed " }, { "DecadePastSuffix", "" }, { "DecadeSingularName", "desetilet\u00ed" }, { "DecadePluralName", "desetilet\u00ed" }, { "DecadePastSingularName", "desetilet\u00edm" }, { "DecadePastPluralName", "desetilet\u00edmi" }, { "DecadeFutureSingularName", "desetilet\u00ed" }, { "DecadeFuturePluralName", "desetilet\u00ed" }, { "HourPattern", "%n %u" }, { "HourFuturePrefix", "za " }, { "HourFutureSuffix", "" }, { "HourPastPrefix", "p\u0159ed" }, { "HourPastSuffix", "" }, { "HourSingularName", "hodina" }, { "HourPluralName", "hodiny" }, { "JustNowPattern", "%u" }, { "JustNowFuturePrefix", "" }, { "JustNowFutureSuffix", "za chv\u00edli" }, { "JustNowPastPrefix", "p\u0159ed chv\u00edl\u00ed" }, { "JustNowPastSuffix", "" }, { "JustNowSingularName", "" }, { "JustNowPluralName", "" }, { "MillenniumPattern", "%n %u" }, { "MillenniumFuturePrefix", "za " }, { "MillenniumFutureSuffix", "" }, { "MillenniumPastPrefix", "p\u0159ed " }, { "MillenniumPastSuffix", "" }, { "MillenniumSingularName", "tis\u00edcilet\u00ed" }, { "MillenniumPluralName", "tis\u00edcilet\u00ed" }, { "MillisecondPattern", "%n %u" }, { "MillisecondFuturePrefix", "za " }, { "MillisecondFutureSuffix", "" }, { "MillisecondPastPrefix", "p\u0159ed " }, { "MillisecondPastSuffix", "" }, { "MillisecondSingularName", "milisekunda" }, { "MillisecondPluralName", "milisekundy" }, { "MillisecondPastSingularName", "milisekundou" }, { "MillisecondPastPluralName", "milisekundami" }, { "MillisecondFutureSingularName", "milisekundu" }, { "MillisecondFuturePluralName", "milisekund" }, { "MinutePattern", "%n %u" }, { "MinuteFuturePrefix", "za " }, { "MinuteFutureSuffix", "" }, { "MinutePastPrefix", "p\u0159ed " }, { "MinutePastSuffix", "" }, { "MinuteSingularName", "minuta" }, { "MinutePluralName", "minuty" }, { "MonthPattern", "%n %u" }, { "MonthFuturePrefix", "za " }, { "MonthFutureSuffix", "" }, { "MonthPastPrefix", "p\u0159ed " }, { "MonthPastSuffix", "" }, { "MonthSingularName", "m\u011bs\u00edc" }, { "MonthPluralName", "m\u011bs\u00edce" }, { "SecondPattern", "%n %u" }, { "SecondFuturePrefix", "za " }, { "SecondFutureSuffix", "" }, { "SecondPastPrefix", "p\u0159ed " }, { "SecondPastSuffix", "" }, { "SecondSingularName", "sekunda" }, { "SecondPluralName", "sekundy" }, { "WeekPattern", "%n %u" }, { "WeekFuturePrefix", "za " }, { "WeekFutureSuffix", "" }, { "WeekPastPrefix", "p\u0159ed " }, { "WeekPastSuffix", "" }, { "WeekSingularName", "t\u00fdden" }, { "WeekPluralName", "t\u00fddny" }, { "YearPattern", "%n %u" }, { "YearFuturePrefix", "za " }, { "YearFutureSuffix", "" }, { "YearPastPrefix", "p\u0159ed " }, { "YearPastSuffix", "" }, { "YearSingularName", "rok" }, { "YearPluralName", "roky" }, { "AbstractTimeUnitPattern", "" }, { "AbstractTimeUnitFuturePrefix", "" }, { "AbstractTimeUnitFutureSuffix", "" }, { "AbstractTimeUnitPastPrefix", "" }, { "AbstractTimeUnitPastSuffix", "" }, { "AbstractTimeUnitSingularName", "" }, { "AbstractTimeUnitPluralName", "" } };
    }
    
    private static class CsTimeFormatBuilder
    {
        private List<CsName> names;
        private String resourceKeyPrefix;
        
        CsTimeFormatBuilder(final String resourceKeyPrefix) {
            this.names = new ArrayList<CsName>();
            this.resourceKeyPrefix = resourceKeyPrefix;
        }
        
        CsTimeFormatBuilder addFutureName(final String name, final long limit) {
            return this.addName(true, name, limit);
        }
        
        CsTimeFormatBuilder addPastName(final String name, final long limit) {
            return this.addName(false, name, limit);
        }
        
        private CsTimeFormatBuilder addName(final boolean isFuture, final String name, final long limit) {
            if (name == null) {
                throw new IllegalArgumentException();
            }
            this.names.add(new CsName(isFuture, name, limit));
            return this;
        }
        
        CsTimeFormat build(final ResourceBundle bundle) {
            return new CsTimeFormat(this.resourceKeyPrefix, bundle, this.names);
        }
    }
    
    private static class CsTimeFormat extends SimpleTimeFormat implements TimeFormat
    {
        private final List<CsName> futureNames;
        private final List<CsName> pastNames;
        
        public CsTimeFormat(final String resourceKeyPrefix, final ResourceBundle bundle, final Collection<CsName> names) {
            this.futureNames = new ArrayList<CsName>();
            this.pastNames = new ArrayList<CsName>();
            this.setPattern(bundle.getString(resourceKeyPrefix + "Pattern"));
            this.setFuturePrefix(bundle.getString(resourceKeyPrefix + "FuturePrefix"));
            this.setFutureSuffix(bundle.getString(resourceKeyPrefix + "FutureSuffix"));
            this.setPastPrefix(bundle.getString(resourceKeyPrefix + "PastPrefix"));
            this.setPastSuffix(bundle.getString(resourceKeyPrefix + "PastSuffix"));
            this.setSingularName(bundle.getString(resourceKeyPrefix + "SingularName"));
            this.setPluralName(bundle.getString(resourceKeyPrefix + "PluralName"));
            try {
                this.setFuturePluralName(bundle.getString(resourceKeyPrefix + "FuturePluralName"));
            }
            catch (Exception ex) {}
            try {
                this.setFutureSingularName(bundle.getString(resourceKeyPrefix + "FutureSingularName"));
            }
            catch (Exception ex2) {}
            try {
                this.setPastPluralName(bundle.getString(resourceKeyPrefix + "PastPluralName"));
            }
            catch (Exception ex3) {}
            try {
                this.setPastSingularName(bundle.getString(resourceKeyPrefix + "PastSingularName"));
            }
            catch (Exception ex4) {}
            for (final CsName name : names) {
                if (name.isFuture()) {
                    this.futureNames.add(name);
                }
                else {
                    this.pastNames.add(name);
                }
            }
            Collections.sort(this.futureNames);
            Collections.sort(this.pastNames);
        }
        
        @Override
        protected String getGramaticallyCorrectName(final Duration d, final boolean round) {
            final long quantity = Math.abs(this.getQuantity(d, round));
            if (d.isInFuture()) {
                return this.getGramaticallyCorrectName(quantity, this.futureNames);
            }
            return this.getGramaticallyCorrectName(quantity, this.pastNames);
        }
        
        private String getGramaticallyCorrectName(final long quantity, final List<CsName> names) {
            for (final CsName name : names) {
                if (name.getThreshold() >= quantity) {
                    return name.get();
                }
            }
            throw new IllegalStateException("Invalid resource bundle configuration");
        }
    }
    
    private static class CsName implements Comparable<CsName>
    {
        private final boolean isFuture;
        private final String value;
        private final Long threshold;
        
        public CsName(final boolean isFuture, final String value, final Long threshold) {
            this.isFuture = isFuture;
            this.value = value;
            this.threshold = threshold;
        }
        
        public boolean isFuture() {
            return this.isFuture;
        }
        
        public String get() {
            return this.value;
        }
        
        public long getThreshold() {
            return this.threshold;
        }
        
        @Override
        public int compareTo(final CsName o) {
            return this.threshold.compareTo(o.getThreshold());
        }
    }
}
