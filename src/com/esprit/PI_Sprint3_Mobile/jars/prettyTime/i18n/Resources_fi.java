// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.i18n;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.units.Day;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.Duration;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.format.SimpleTimeFormat;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeFormat;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;
import java.util.concurrent.ConcurrentMap;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.TimeFormatProvider;
import java.util.ListResourceBundle;

public class Resources_fi extends ListResourceBundle implements TimeFormatProvider
{
    private static final int tolerance = 50;
    private static Object[][] CONTENTS;
    private volatile ConcurrentMap<TimeUnit, TimeFormat> formatMap;
    
    public Resources_fi() {
        this.formatMap = new ConcurrentHashMap<TimeUnit, TimeFormat>();
    }
    
    @Override
    public TimeFormat getFormatFor(final TimeUnit t) {
        if (!this.formatMap.containsKey(t)) {
            this.formatMap.putIfAbsent(t, new FiTimeFormat(this, t));
        }
        return this.formatMap.get(t);
    }
    
    @Override
    protected Object[][] getContents() {
        return Resources_fi.CONTENTS;
    }
    
    static {
        Resources_fi.CONTENTS = new Object[][] { { "JustNowPattern", "%u" }, { "JustNowPastSingularName", "hetki" }, { "JustNowFutureSingularName", "hetken" }, { "JustNowPastSuffix", "sitten" }, { "JustNowFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "MillisecondPattern", "%u" }, { "MillisecondPluralPattern", "%n %u" }, { "MillisecondPastSingularName", "millisekunti" }, { "MillisecondPastPluralName", "millisekuntia" }, { "MillisecondFutureSingularName", "millisekunnin" }, { "MillisecondPastSuffix", "sitten" }, { "MillisecondFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "SecondPattern", "%u" }, { "SecondPluralPattern", "%n %u" }, { "SecondPastSingularName", "sekunti" }, { "SecondPastPluralName", "sekuntia" }, { "SecondFutureSingularName", "sekunnin" }, { "SecondPastSuffix", "sitten" }, { "SecondFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "MinutePattern", "%u" }, { "MinutePluralPattern", "%n %u" }, { "MinutePastSingularName", "minuutti" }, { "MinutePastPluralName", "minuuttia" }, { "MinuteFutureSingularName", "minuutin" }, { "MinutePastSuffix", "sitten" }, { "MinuteFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "HourPattern", "%u" }, { "HourPluralPattern", "%n %u" }, { "HourPastSingularName", "tunti" }, { "HourPastPluralName", "tuntia" }, { "HourFutureSingularName", "tunnin" }, { "HourPastSuffix", "sitten" }, { "HourFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "DayPattern", "%u" }, { "DayPluralPattern", "%n %u" }, { "DayPastSingularName", "eilen" }, { "DayPastPluralName", "p\u00e4iv\u00e4\u00e4" }, { "DayFutureSingularName", "huomenna" }, { "DayFuturePluralName", "p\u00e4iv\u00e4n" }, { "DayPastSuffix", "sitten" }, { "DayFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "WeekPattern", "%u" }, { "WeekPluralPattern", "%n %u" }, { "WeekPastSingularName", "viikko" }, { "WeekPastPluralName", "viikkoa" }, { "WeekFutureSingularName", "viikon" }, { "WeekFuturePluralName", "viikon" }, { "WeekPastSuffix", "sitten" }, { "WeekFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "MonthPattern", "%u" }, { "MonthPluralPattern", "%n %u" }, { "MonthPastSingularName", "kuukausi" }, { "MonthPastPluralName", "kuukautta" }, { "MonthFutureSingularName", "kuukauden" }, { "MonthPastSuffix", "sitten" }, { "MonthFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "YearPattern", "%u" }, { "YearPluralPattern", "%n %u" }, { "YearPastSingularName", "vuosi" }, { "YearPastPluralName", "vuotta" }, { "YearFutureSingularName", "vuoden" }, { "YearPastSuffix", "sitten" }, { "YearFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "DecadePattern", "%u" }, { "DecadePluralPattern", "%n %u" }, { "DecadePastSingularName", "vuosikymmen" }, { "DecadePastPluralName", "vuosikymment\u00e4" }, { "DecadeFutureSingularName", "vuosikymmenen" }, { "DecadePastSuffix", "sitten" }, { "DecadeFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "CenturyPattern", "%u" }, { "CenturyPluralPattern", "%n %u" }, { "CenturyPastSingularName", "vuosisata" }, { "CenturyPastPluralName", "vuosisataa" }, { "CenturyFutureSingularName", "vuosisadan" }, { "CenturyPastSuffix", "sitten" }, { "CenturyFutureSuffix", "p\u00e4\u00e4st\u00e4" }, { "MillenniumPattern", "%u" }, { "MillenniumPluralPattern", "%n %u" }, { "MillenniumPastSingularName", "vuosituhat" }, { "MillenniumPastPluralName", "vuosituhatta" }, { "MillenniumFutureSingularName", "vuosituhannen" }, { "MillenniumPastSuffix", "sitten" }, { "MillenniumFutureSuffix", "p\u00e4\u00e4st\u00e4" } };
    }
    
    private static class FiTimeFormat extends SimpleTimeFormat
    {
        private final ResourceBundle bundle;
        private String pastName;
        private String futureName;
        private String pastPluralName;
        private String futurePluralName;
        private String pluralPattern;
        
        public FiTimeFormat(final ResourceBundle rb, final TimeUnit unit) {
            this.pastName = "";
            this.futureName = "";
            this.pastPluralName = "";
            this.futurePluralName = "";
            this.pluralPattern = "";
            this.bundle = rb;
            if (this.bundle.containsKey(this.getUnitName(unit) + "PastSingularName")) {
                this.setPastName(this.bundle.getString(this.getUnitName(unit) + "PastSingularName")).setFutureName(this.bundle.getString(this.getUnitName(unit) + "FutureSingularName")).setPastPluralName(this.bundle.getString(this.getUnitName(unit) + "PastSingularName")).setFuturePluralName(this.bundle.getString(this.getUnitName(unit) + "FutureSingularName")).setPluralPattern(this.bundle.getString(this.getUnitName(unit) + "Pattern"));
                if (this.bundle.containsKey(this.getUnitName(unit) + "PastPluralName")) {
                    this.setPastPluralName(this.bundle.getString(this.getUnitName(unit) + "PastPluralName"));
                }
                if (this.bundle.containsKey(this.getUnitName(unit) + "FuturePluralName")) {
                    this.setFuturePluralName(this.bundle.getString(this.getUnitName(unit) + "FuturePluralName"));
                }
                if (this.bundle.containsKey(this.getUnitName(unit) + "PluralPattern")) {
                    this.setPluralPattern(this.bundle.getString(this.getUnitName(unit) + "PluralPattern"));
                }
                this.setPattern(this.bundle.getString(this.getUnitName(unit) + "Pattern")).setPastSuffix(this.bundle.getString(this.getUnitName(unit) + "PastSuffix")).setFutureSuffix(this.bundle.getString(this.getUnitName(unit) + "FutureSuffix")).setFuturePrefix("").setPastPrefix("").setSingularName("").setPluralName("");
            }
        }
        
        public String getPastName() {
            return this.pastName;
        }
        
        public String getFutureName() {
            return this.futureName;
        }
        
        public String getPastPluralName() {
            return this.pastPluralName;
        }
        
        public String getFuturePluralName() {
            return this.futurePluralName;
        }
        
        public String getPluralPattern() {
            return this.pluralPattern;
        }
        
        public FiTimeFormat setPastName(final String pastName) {
            this.pastName = pastName;
            return this;
        }
        
        public FiTimeFormat setFutureName(final String futureName) {
            this.futureName = futureName;
            return this;
        }
        
        @Override
        public FiTimeFormat setPastPluralName(final String pastName) {
            this.pastPluralName = pastName;
            return this;
        }
        
        @Override
        public FiTimeFormat setFuturePluralName(final String futureName) {
            this.futurePluralName = futureName;
            return this;
        }
        
        public FiTimeFormat setPluralPattern(final String pattern) {
            this.pluralPattern = pattern;
            return this;
        }
        
        @Override
        protected String getGramaticallyCorrectName(final Duration d, final boolean round) {
            String result = d.isInPast() ? this.getPastName() : this.getFutureName();
            if (Math.abs(this.getQuantity(d, round)) == 0L || Math.abs(this.getQuantity(d, round)) > 1L) {
                result = (d.isInPast() ? this.getPastPluralName() : this.getFuturePluralName());
            }
            return result;
        }
        
        @Override
        protected String getPattern(final long quantity) {
            if (Math.abs(quantity) == 1L) {
                return this.getPattern();
            }
            return this.getPluralPattern();
        }
        
        @Override
        public String decorate(final Duration duration, final String time) {
            String result = "";
            if (duration.getUnit() instanceof Day && Math.abs(duration.getQuantityRounded(50)) == 1L) {
                result = time;
            }
            else {
                result = super.decorate(duration, time);
            }
            return result;
        }
        
        private String getUnitName(final TimeUnit unit) {
            return unit.getClass().getSimpleName();
        }
    }
}
