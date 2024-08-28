/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.WebApplicationException;

import org.apache.commons.lang.time.DateUtils;

import com.ericsson.eniq.events.server.logging.ServicesLogger;

/**
 * Utility class to manipulate and format time parameters
 * passed from RESTful clients for mapping to SQL queries. 
 * Also included are methods to support data source decision
 * logic based on time interval calculations.  
 *  
 * @author estepdu
 * @author ehaoswa
 * @author edeccox
 *
 */
public class DateTimeUtils {

    // Prevent instantiation
    /**
     * The Constructor.
     */
    private DateTimeUtils() {
    }

    /**
     * Format date time.
     *
     * @param dateTime the date time
     * @return the string
     */
    private static String formatDateTime(final Date dateTime) {
        return getDateTimeFormat().format(dateTime);
    }

    /**
     * Get the application date time format for queries.
     * @return date format object with local TZ
     */
    public static DateFormat getDateTimeFormat() {
        return getDateFormat(DATE_TIME_FORMAT);
    }

    public static DateFormat getDateTimeFormatUTC() {
        return getTimeFormatUTC(DATE_TIME_FORMAT);
    }

    /**
     * Get the application date format with local TZ.
     *
     * @return date format object with local TZ
     */
    private static DateFormat getDateFormat() {
        return getDateFormat(DATE_FORMAT);
    }

    /**
     * Get date format object formatted according to 
     * in-bound ui request format. 
     * @return date format with local TZ
     */
    private static DateFormat getUIDateFormat() {
        return getDateFormat(UI_DATE_FORMAT);
    }

    /**
     * Creates a simple date format object with local TZ
     * and specified format string.
     *
     * @param formatString the format string
     * @return simple date format object.
     */
    private static DateFormat getDateFormat(final String formatString) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat;
    }

    /**
     * Convenience method to add minutes to a formatted dateTime string.
     * Parse the string to create a date, add the specified number of 
     * minutes (may be negative) and reformat. 
     * 
     * @param dateTime formatted dateTime string
     * @param minutes minutes to add
     * @return formatted date time string with added minutes
     */
    public static String addMinutes(final String dateTime, final int minutes) {
        final Date date = DateUtils.addMinutes(DateTimeUtils.parseDateTimeFormat(dateTime), minutes);
        return formatDateTime(date);
    }

    /**
     * <br>This method is use to return a valid string representation of the date and time for the SQL query.
     * <br>The method will subtract a number in minutes from the date reference passed.
     *
     * @param date timestamp from which to offset
     * @param timeOffsetInMinutes time in minutes past relative to date
     *
     * @return String: A valid date time representation for the SQL query
     */
    public static String formattedDateTime(final Date date, final String timeOffsetInMinutes) {
        return getDateTimeFormat().format(DateUtils.addMinutes(date, -Integer.parseInt(timeOffsetInMinutes)));
    }

    /**
     * <br>This method is use to return a valid string representation of the date and time for the SQL query.
     * <br>The method will subtract a number in minutes from the date reference passed.
     *
     * @param date timestamp from which to offset
     * @param timeOffsetInDays time in days past relative to date
     *
     * @return String: A valid date time representation for the SQL query
     */
    public static String formattedDateTimeInDays(final Date date, final String timeOffsetInDays) {
        return getDateTimeFormat().format(DateUtils.addDays(date, -Integer.parseInt(timeOffsetInDays)));
    }

    /**
     * <br>This method is use to return a valid string representation of the date and time for the SQL query.
     *
     * @param date UI date 
     * @param time UI 24hr time, expected format HHmm
     * @return A valid date time representation for the SQL query
     * @throws WebApplicationException the parse exception
     */
    public static String formattedDateTime(final String date, final String time) throws WebApplicationException {
        return getDateFormat().format(parse(date, getUIDateFormat())).concat(SINGLE_SPACE).concat(convertUITime(time));
    }

    /**
     * For chart and grid.
     * This method will check to see of the two dates are <= 1 minute.
     * Once the time range in minutes is <=1 minute go to the raw aggregated view.
     * @param timeRangeInMinutes TODO
     *
     * @return boolean true if the time range is <=1 minute
     */
    public static boolean rawEventRange(final long timeRangeInMinutes) {
        return timeRangeInMinutes <= FIVE_MINUTE_VALUE;
    }

    /**
     *  Rule for selection of 1 Minute aggregation data source for grid data
     * @param timeRangeInMinutes TODO
     *
     * @return boolean true if within range otherwise false
     */
    public static boolean oneMinuteAggregationRange(final long timeRangeInMinutes) {
        final int lowerLimit = FIVE_MINUTE_VALUE;
        final int upperLimit = MINUTES_IN_2_HOURS;
        final boolean oneMinuteAggregatedRangeForGrid = timeRangeInMinutes > lowerLimit
                && timeRangeInMinutes <= upperLimit;
        logDecision("oneMinuteAggregatedRangeForGrid", timeRangeInMinutes, lowerLimit, upperLimit,
                oneMinuteAggregatedRangeForGrid);
        return oneMinuteAggregatedRangeForGrid;
    }

    private static void logDecision(final String methodName, final long timeRangeInMinutes, final int lowerLimit,
            final int upperLimit, final boolean decision) {
        ServicesLogger.detailed(DateTimeUtils.class.getName(), methodName, "Decision on this time range is " + decision
                + " for query of time range in minutes " + timeRangeInMinutes + " (constraints of this method are "
                + lowerLimit + " and " + upperLimit + ")");
    }

    /**
     *  Rule for selection of 15 Minute aggregation data source for grid data
     * @param timeRangeInMinutes TODO
     *
     * @return boolean true if within range otherwise false
     */
    public static boolean fifteenMinuteAggregatedRangeForGrid(final long timeRangeInMinutes) {
        final int lowerLimit = MINUTES_IN_2_HOURS;
        final int upperLimit = MINUTES_IN_A_WEEK;
        final boolean fifteenMinuteAggregatedRangeForGrid = timeRangeInMinutes > lowerLimit
                && timeRangeInMinutes < upperLimit;
        logDecision("fifteenMinuteAggregatedRangeForGrid", timeRangeInMinutes, lowerLimit, upperLimit,
                fifteenMinuteAggregatedRangeForGrid);
        return fifteenMinuteAggregatedRangeForGrid;
    }

    /**
     * For Chart only.
     * Once the time range in minutes is <=120 minute go to the raw view.
     * @param timeRangeInMinutes 
     *      
     * @return boolean true if the time range is <=1 minute
     */
    public static boolean rawEventRangeForTimeSeriesChartWihtoutOneMinAggregate(final long timeRangeInMinutes) {
        return timeRangeInMinutes <= MINUTES_IN_2_HOURS;
    }

    /**
     * Validate and convert the GUI time to a valid time format.
     * Expected input format HHmm
     * output format HH:mm
     * 
     * @param uiTime as a string e.g., 1800
     * @return a valid time format e.g., 18:00
     */
    private static String convertUITime(final String uiTime) {
        if (uiTime == null) {
            throw new IllegalArgumentException("The time parameter may not be null");
        }

        if (!UI_TIME_PATTERN_24HR.matcher(uiTime).matches()) {
            throw new IllegalArgumentException("The time format is invalid \"" + uiTime + "\" should be HHmm.");
        }

        return uiTime.substring(0, 2).concat(":").concat(uiTime.substring(2, 4));
    }

    /**
     * Get the time difference in minutes between to given dates.
     *
     * @param timeDateFrom time and date from a point in time that you want to query from
     * @param timeDateTo time and date from a point in time that you want to query to
     *
     * @return Time in minutes between two dates.
     */
    public static long timeInMinutesBetweenDates(final Date timeDateFrom, final Date timeDateTo) {
        return TimeUnit.MILLISECONDS.toMinutes(timeDateTo.getTime())
                - TimeUnit.MILLISECONDS.toMinutes(timeDateFrom.getTime());
    }

    /**
     * Parses text of the given string to produce a date. The string must be in the format @see DATE_TIME_FORMAT
     *
     * @param source A <code>String</code> to parse.
     * @return A  parsed from the string.
     * @throws WebApplicationException if the specified string cannot be parsed.
     */
    public static Date parseDateTimeFormat(final String source) throws WebApplicationException {
        return parse(source, getDateTimeFormat());
    }

    /**
     * Parses text of the given string to produce a date.
     * This is a wrapper on dateTimeformat.parse to return a runtime exception instead.
     *
     * @param source A <code>String</code> to parse.
     * @param dateTimeformat Date time format of source string
     * @return A  parsed from the string.
     * @throws WebApplicationException if the specified string cannot be parsed.
     */
    public static Date parse(final String source, final DateFormat dateTimeformat) throws WebApplicationException {
        try {
            return dateTimeformat.parse(source);
        } catch (final ParseException e) {
            throw new WebApplicationException(e);
        }
    }

    /**
     * Convert a date formatted as per the date time formatter in this class (@see DATE_TIME_FORMAT)
     * into its millisecond representation
     * 
     * If the formattedDate argument is null, then null is returned (not ideal, but formattedDate may be null
     * in the case of a static type query (eg CauseCodeTablesCCResource) which has no queries, but still needs
     * to populate the date time range fields in the JSON output) .  These date time range fields in the JSON output
     * are used at a later stage to export the same data to CSV - if these values are null, and the query is a static
     * query, there is no side effect, as time/date params won't be used for a static query)
     *
     * @param formattedDate the formatted date
     * @param tzOffset the tz offset
     * @return the time in milliseconds
     */
    public static String getTimeInMilliseconds(final String formattedDate, final String tzOffset) {
        if (formattedDate == null) {
            return null;
        }
        Date parsedDate;
        try {
            parsedDate = getDateTimeFormat().parse(getLocalTime(formattedDate, tzOffset, DATE_TIME_FORMAT));
            final long dateInMilliseconds = parsedDate.getTime();
            return Long.toString(dateInMilliseconds);
        } catch (final ParseException e) {
            throw new WebApplicationException(e);
        }

    }

    public static String getTimeInMillisecondsUTC(final String formattedDate) {
        if (formattedDate == null) {
            return null;
        }
        Date parsedDate;
        try {
            parsedDate = getDateTimeFormatUTC().parse(formattedDate);
            final long dateInMilliseconds = parsedDate.getTime();
            return Long.toString(dateInMilliseconds);
        } catch (final ParseException e) {
            throw new WebApplicationException(e);
        }

    }

    /**
     * Format a time in milliseconds into the format specified in @see DATE_TIME_FORMAT.
     *
     * @param timeInMilliseconds the time in milliseconds
     * @return the string
     */
    public static String formattedDateTime(final long timeInMilliseconds) {
        final Date date = new Date(timeInMilliseconds);
        return getDateTimeFormat().format(date);
    }

    /**
     * Gets the utc offset.
     * Use the number format make the output in standard timezone format
     * e.g. +0000 or +0800
     * @return the UTC offset
     */
    public static String getUTCOffset() {
        final TimeZone currentTimeZone = Calendar.getInstance().getTimeZone();
        final NumberFormat hourFormat = new DecimalFormat(HOUR_FORMAT);
        final NumberFormat minuteFormat = new DecimalFormat(MINUTE_FORMAT);
        final long elapsedTime = currentTimeZone.getRawOffset() / MILLISECOND_IN_1_SECOND;
        final StringBuffer sb = new StringBuffer();
        return sb.append(hourFormat.format(elapsedTime / SECONDS_IN_1_HOUR))
                .append(minuteFormat.format((elapsedTime % SECONDS_IN_1_HOUR) / SECONDS_IN_1_MIN)).toString();
    }

    /**
     * Gets the local time.
     *
     * @param formattedDate the formatted date
     * @param tzOffset the timezone offset
     * @param dateTimeFormat the date time format
     * @return the local time
     */
    public static String getLocalTime(final String formattedDate, final String tzOffset, final String dateTimeFormat) {
        final Date date = parse(formattedDate, getDateFormat(dateTimeFormat));
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String tzSign = "";
        if (tzOffset.substring(0, 1).equals("-")) {
            tzSign = "-";
        }
        calendar.add(Calendar.HOUR, Integer.parseInt(tzSign + tzOffset.substring(1, 3)));
        calendar.add(Calendar.MINUTE, Integer.parseInt(tzSign + tzOffset.substring(3, 5)));
        return getDateFormat(dateTimeFormat).format(calendar.getTime());

    }

    /**
     * Gets the utc time.
     *
     * @param formattedDate the formatted date
     * @param tzOffset the tz offset
     * @param dateTimeFormat the date time format
     * @return the UTC time
     */
    public static String getUTCTime(final String formattedDate, final String tzOffset, final String dateTimeFormat) {
        final Date date = parse(formattedDate, getDateFormat(dateTimeFormat));
        final Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String tzSign = "-";
        if (tzOffset.substring(0, 1).equals("-")) {
            tzSign = "";
        }
        calendar.add(Calendar.HOUR, Integer.parseInt(tzSign + tzOffset.substring(1, 3)));
        calendar.add(Calendar.MINUTE, Integer.parseInt(tzSign + tzOffset.substring(3, 5)));
        return getDateFormat(dateTimeFormat).format(calendar.getTime());

    }

    /**
     * Rounds the time stamp to the last quarter hour
     * @param d - time stamp
     * @return time stamp rounded to last quarter hour
     */
    public static Date roundToLastQuarterHour(final Date d) {
        final Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MINUTE, -(c.get(Calendar.MINUTE) % 15));
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date stepBackTwoPeriodsOfQuarterHour(final Date d) {
        final Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.MINUTE, -((c.get(Calendar.MINUTE) % 15) + 15));
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
     * Rounds down to last minute
     * @param d - time stamp
     * @return time stamp rounded to last minute
     */
    public static Date roundToLastMinute(final Date d) {
        final Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.SECOND, -(c.get(Calendar.SECOND) % 60));
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * Rounds down to midnight of previous day
     * e.g., if time now is 19:
     * time now           : Mon Nov 08 19:49:33 GMT 2010
     * rounded to last day: Mon Nov 08 00:00:00 GMT 2010
     * 
     * @param d - time stamp
     * @return time stamp rounded to midnight of previous day
     */
    public static Date roundToLastDay(final Date d) {
        final Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.HOUR_OF_DAY, -(c.get(Calendar.HOUR_OF_DAY)));
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    /**
    * Return the previous day from the given date.
    * @param date
    * @return
    */
    public static String getPreviousDay(final Date date) {
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -1); //subtract one day.
        return formatDateTime(c.getTime());
    }

    /**
     * Format a timestamp in milliseconds into the format specified in @see DATE_TIME_FORMAT.
     *
     * @param eventTime the time in milliseconds
     * @return the string
     */
    public static String formattedEventTimeUTC(final String eventTime) {
        final long timeStamp = Long.parseLong(eventTime);
        return getTimeFormatUTC(EVENT_TIME_FORMAT).format(timeStamp);
    }

    /**
     * Get the application event time format for queries.
     * @return event time formatter
     */
    private static DateFormat getTimeFormatUTC(final String timeFormat) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }
}
