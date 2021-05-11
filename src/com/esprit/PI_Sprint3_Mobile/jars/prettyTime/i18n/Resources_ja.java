// 
// Decompiled by Procyon v0.5.36
// 

package com.esprit.PI_Sprint3_Mobile.jars.prettyTime.i18n;

import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.Duration;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeFormat;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.TimeUnit;
import java.util.concurrent.ConcurrentMap;
import com.esprit.PI_Sprint3_Mobile.jars.prettyTime.impl.TimeFormatProvider;
import java.util.ListResourceBundle;

public class Resources_ja extends ListResourceBundle implements TimeFormatProvider
{
    private static final Object[][] OBJECTS;
    private volatile ConcurrentMap<TimeUnit, TimeFormat> formatMap;
    
    public Resources_ja() {
        this.formatMap = new ConcurrentHashMap<TimeUnit, TimeFormat>();
    }
    
    public Object[][] getContents() {
        return Resources_ja.OBJECTS;
    }
    
    @Override
    public TimeFormat getFormatFor(final TimeUnit t) {
        if (!this.formatMap.containsKey(t)) {
            this.formatMap.putIfAbsent(t, new JaTimeFormat(this, t));
        }
        return this.formatMap.get(t);
    }
    
    static {
        OBJECTS = new Object[][] { { "CenturyPattern", "%n%u" }, { "CenturyFuturePrefix", "\u4eca\u304b\u3089" }, { "CenturyFutureSuffix", "\u4e16\u7d00\u306b\u3082\u308f\u305f\u3063" }, { "CenturyPastPrefix", "" }, { "CenturyPastSuffix", "\u4e16\u7d00\u524d" }, { "CenturySingularName", "" }, { "CenturyPluralName", "" }, { "DayPattern", "%n%u" }, { "DayFuturePrefix", "\u4eca\u304b\u3089" }, { "DayFutureSuffix", "\u9593" }, { "DayPastPrefix", "" }, { "DayPastSuffix", "\u524d" }, { "DaySingularName", "\u65e5" }, { "DayPluralName", "\u65e5" }, { "DecadePattern", "%n%u" }, { "DecadeFuturePrefix", "\u4eca\u304b\u3089" }, { "DecadeFutureSuffix", "\u5e74\u9593" }, { "DecadePastPrefix", "" }, { "DecadePastSuffix", "\u5e74\u524d" }, { "DecadeSingularName", "" }, { "DecadePluralName", "" }, { "HourPattern", "%n%u" }, { "HourFuturePrefix", "\u4eca\u304b\u3089" }, { "HourFutureSuffix", "" }, { "HourPastPrefix", "" }, { "HourPastSuffix", "\u524d" }, { "HourSingularName", "\u6642\u9593" }, { "HourPluralName", "\u6642\u9593" }, { "JustNowPattern", "%u" }, { "JustNowFuturePrefix", "" }, { "JustNowFutureSuffix", "\u4eca\u304b\u3089\u3059\u3050" }, { "JustNowPastPrefix", "" }, { "JustNowPastSuffix", "\u3055\u3063\u304d" }, { "JustNowSingularName", "" }, { "JustNowPluralName", "" }, { "MillenniumPattern", "%n %u" }, { "MillenniumFuturePrefix", "" }, { "MillenniumFutureSuffix", "\u4eca\u304b\u3089" }, { "MillenniumPastPrefix", "" }, { "MillenniumPastSuffix", "\u524d" }, { "MillenniumSingularName", "\u5343\u5e74" }, { "MillenniumPluralName", "\u5343\u5e74" }, { "MillisecondPattern", "%n%u" }, { "MillisecondFuturePrefix", "" }, { "MillisecondFutureSuffix", "\u4eca\u304b\u3089" }, { "MillisecondPastPrefix", "" }, { "MillisecondPastSuffix", "\u524d" }, { "MillisecondSingularName", "\u30df\u30ea\u79d2" }, { "MillisecondPluralName", "\u30df\u30ea\u79d2" }, { "MinutePattern", "%n%u" }, { "MinuteFuturePrefix", "\u4eca\u304b\u3089" }, { "MinuteFutureSuffix", "" }, { "MinutePastPrefix", "" }, { "MinutePastSuffix", "\u524d" }, { "MinuteSingularName", "\u5206" }, { "MinutePluralName", "\u5206" }, { "MonthPattern", "%n%u" }, { "MonthFuturePrefix", "\u4eca\u304b\u3089" }, { "MonthFutureSuffix", "" }, { "MonthPastPrefix", "" }, { "MonthPastSuffix", "\u524d" }, { "MonthSingularName", "\u30f6\u6708" }, { "MonthPluralName", "\u30f6\u6708" }, { "SecondPattern", "%n%u" }, { "SecondFuturePrefix", "" }, { "SecondFutureSuffix", "\u4eca\u304b\u3089" }, { "SecondPastPrefix", "" }, { "SecondPastSuffix", "\u524d" }, { "SecondSingularName", "\u79d2" }, { "SecondPluralName", "\u79d2" }, { "WeekPattern", "%n%u" }, { "WeekFuturePrefix", "\u4eca\u304b\u3089" }, { "WeekFutureSuffix", "\u9031\u9593" }, { "WeekPastPrefix", "" }, { "WeekPastSuffix", "\u9031\u9593\u524d" }, { "WeekSingularName", "" }, { "WeekPluralName", "" }, { "YearPattern", "%n%u" }, { "YearFuturePrefix", "" }, { "YearFutureSuffix", "\u5e74\u5f8c\u306e" }, { "YearPastPrefix", "" }, { "YearPastSuffix", "\u5e74\u524d" }, { "YearSingularName", "" }, { "YearPluralName", "" }, { "AbstractTimeUnitPattern", "" }, { "AbstractTimeUnitFuturePrefix", "" }, { "AbstractTimeUnitFutureSuffix", "" }, { "AbstractTimeUnitPastPrefix", "" }, { "AbstractTimeUnitPastSuffix", "" }, { "AbstractTimeUnitSingularName", "" }, { "AbstractTimeUnitPluralName", "" } };
    }
    
    private static class JaTimeFormat implements TimeFormat
    {
        private static final String NEGATIVE = "-";
        public static final String SIGN = "%s";
        public static final String QUANTITY = "%n";
        public static final String UNIT = "%u";
        private final ResourceBundle bundle;
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
        
        public JaTimeFormat(final ResourceBundle bundle, final TimeUnit unit) {
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
            this.bundle = bundle;
            this.setPattern(bundle.getString(this.getUnitName(unit) + "Pattern"));
            this.setFuturePrefix(bundle.getString(this.getUnitName(unit) + "FuturePrefix"));
            this.setFutureSuffix(bundle.getString(this.getUnitName(unit) + "FutureSuffix"));
            this.setPastPrefix(bundle.getString(this.getUnitName(unit) + "PastPrefix"));
            this.setPastSuffix(bundle.getString(this.getUnitName(unit) + "PastSuffix"));
            this.setSingularName(bundle.getString(this.getUnitName(unit) + "SingularName"));
            this.setPluralName(bundle.getString(this.getUnitName(unit) + "PluralName"));
            try {
                this.setFuturePluralName(bundle.getString(this.getUnitName(unit) + "FuturePluralName"));
            }
            catch (Exception ex) {}
            try {
                this.setFutureSingularName(bundle.getString(this.getUnitName(unit) + "FutureSingularName"));
            }
            catch (Exception ex2) {}
            try {
                this.setPastPluralName(bundle.getString(this.getUnitName(unit) + "PastPluralName"));
            }
            catch (Exception ex3) {}
            try {
                this.setPastSingularName(bundle.getString(this.getUnitName(unit) + "PastSingularName"));
            }
            catch (Exception ex4) {}
        }
        
        private String getUnitName(final TimeUnit unit) {
            return unit.getClass().getSimpleName();
        }
        
        @Override
        public String format(final Duration duration) {
            return this.format(duration, true);
        }
        
        @Override
        public String formatUnrounded(final Duration duration) {
            return this.format(duration, false);
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
        
        @Override
        public String decorate(final Duration duration, final String time) {
            final StringBuilder result = new StringBuilder();
            if (duration.isInPast()) {
                result.append(this.pastPrefix).append(time).append(this.pastSuffix);
            }
            else {
                result.append(this.futurePrefix).append(time).append(this.futureSuffix);
            }
            return result.toString().replaceAll("\\s+", " ").trim();
        }
        
        @Override
        public String decorateUnrounded(final Duration duration, final String time) {
            return this.decorate(duration, time);
        }
        
        public JaTimeFormat setPattern(final String pattern) {
            this.pattern = pattern;
            return this;
        }
        
        public JaTimeFormat setFuturePrefix(final String futurePrefix) {
            this.futurePrefix = futurePrefix.trim();
            return this;
        }
        
        public JaTimeFormat setFutureSuffix(final String futureSuffix) {
            this.futureSuffix = futureSuffix.trim();
            return this;
        }
        
        public JaTimeFormat setPastPrefix(final String pastPrefix) {
            this.pastPrefix = pastPrefix.trim();
            return this;
        }
        
        public JaTimeFormat setPastSuffix(final String pastSuffix) {
            this.pastSuffix = pastSuffix.trim();
            return this;
        }
        
        public JaTimeFormat setRoundingTolerance(final int roundingTolerance) {
            this.roundingTolerance = roundingTolerance;
            return this;
        }
        
        public JaTimeFormat setSingularName(final String name) {
            this.singularName = name;
            return this;
        }
        
        public JaTimeFormat setPluralName(final String pluralName) {
            this.pluralName = pluralName;
            return this;
        }
        
        public JaTimeFormat setFutureSingularName(final String futureSingularName) {
            this.futureSingularName = futureSingularName;
            return this;
        }
        
        public JaTimeFormat setFuturePluralName(final String futurePluralName) {
            this.futurePluralName = futurePluralName;
            return this;
        }
        
        public JaTimeFormat setPastSingularName(final String pastSingularName) {
            this.pastSingularName = pastSingularName;
            return this;
        }
        
        public JaTimeFormat setPastPluralName(final String pastPluralName) {
            this.pastPluralName = pastPluralName;
            return this;
        }
        
        @Override
        public String toString() {
            return "JaTimeFormat [pattern=" + this.pattern + ", futurePrefix=" + this.futurePrefix + ", futureSuffix=" + this.futureSuffix + ", pastPrefix=" + this.pastPrefix + ", pastSuffix=" + this.pastSuffix + ", roundingTolerance=" + this.roundingTolerance + "]";
        }
    }
}
