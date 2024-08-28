/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

/**
 * some date/time utilities for tests to use
 * @author eemecoy
 *
 */
public class DateTimeUtilities {
    private DateTimeUtilities() {
    }

    private static String DATE_FORMAT = "yyyy-MM-dd";

    private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static String DATE_PARAM_FORMAT = "ddMMyyyy";

    private static String TIME_PARAM_FORMAT = "HHmm";

    private static String DATE_TIME_FORMAT_ZERO_SECONDS = "yyyy-MM-dd HH:mm:00";

    private static String HOUR_FORMAT = "HH";

    private static String DAY_FORMAT = "EEEE";

    public static String getDateMinus2Minutes() {
        return getDateTime(DATE_FORMAT, Calendar.MINUTE, -2);
    }

    public static String getDateMinus30Minutes() {
        return getDateTime(DATE_FORMAT, Calendar.MINUTE, -30);
    }

    public static String getDateMinus36Hours() {
        return getDateTime(DATE_FORMAT, Calendar.HOUR, -36);
    }

    public static String getDateMinus12Hours() {
        return getDateTime(DATE_FORMAT, Calendar.HOUR, -12);
    }

    public static String getDateMinus3Minutes() {
        return getDateTime(DATE_FORMAT, Calendar.MINUTE, -3);
    }

    public static String getDateMinus48Hours() {
        return getDateTime(DATE_FORMAT, Calendar.HOUR, -48);
    }

    public static String getDateMinus5Minutes() {
        return getDateTime(DATE_FORMAT, Calendar.MINUTE, -5);
    }

    public static String getDateTime(final int unitOfTime, final int amountOfTime) {
        return getDateTime(DATE_TIME_FORMAT, unitOfTime, amountOfTime);
    }

    public static String getDateTimeZeroSeconds(final int unitOfTime, final int amountOfTime) {
        return getDateTime(DATE_TIME_FORMAT_ZERO_SECONDS, unitOfTime, amountOfTime);
    }

    public static String getDateTime(final String format, final int unitOfTime, final int amountOfTime) {
        final DateFormat dateFormat = new SimpleDateFormat(format);
        final Date date = new Date();

        // Make sure we're always in GMT so our tests dont fail when timezone changes!
        final Calendar calendar = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
        dateFormat.setCalendar(calendar);

        calendar.setTime(date);
        calendar.add(unitOfTime, amountOfTime);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * This method is intended for use by service test classes wishing to test the event time column translation from
     * UTC time to local time. This method allows the test class to get the expected local time, using the offset
     *
     * @param format            Date/Time Format
     * @param unitOfTime        Calendar element to change, e.g Calendar.HOURS
     * @param amountOfTime      Unit to change time by, e.g -5
     * @param hourOffset        Hour offset
     * @return
     */
    public static String getDateTimeWithOffSet(final String format, final int unitOfTime, final int amountOfTime,
            final int hourOffset) {
        final DateFormat dateFormat = new SimpleDateFormat(format);
        final Date date = new Date();

        // Make sure we're always in GMT so our tests dont fail when timezone changes!
        final Calendar calendar = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
        dateFormat.setCalendar(calendar);

        calendar.setTime(date);
        calendar.add(unitOfTime, amountOfTime);
        calendar.add(Calendar.HOUR, hourOffset);
        return dateFormat.format(calendar.getTime());
    }

    public static String applyOffsetToDateTime(final String dateTime, final int hourOffset) throws ParseException {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        final Date date = dateFormat.parse(dateTime);
        final Calendar calendar = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
        dateFormat.setCalendar(calendar);
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hourOffset);
        return dateFormat.format(calendar.getTime());
    }

    public static String getTimeField(final String timeStr, final String timeField) {
        final String[] allStrings = timeStr.split("\\s");
        if ("HOUR_ID".equals(timeField)) {
            final String[] allHourMinFields = allStrings[1].split(":");
            return allHourMinFields[0];
        } else if ("DATE_ID".equals(timeField)) {
            return allStrings[0];
        }
        return allStrings[0];
    }

    public static String getDateTimeMinus2Minutes() {
        return getDateTime(DATE_TIME_FORMAT, Calendar.MINUTE, -2);
    }

    public static String getDateTimeMinus2MinutesWithOffSet(final int hourOffSet) {
        return getDateTimeWithOffSet(DATE_TIME_FORMAT, Calendar.MINUTE, -2, hourOffSet);
    }

    public static String getDateTimeMinus5Minutes() {
        return getDateTime(DATE_TIME_FORMAT, Calendar.MINUTE, -5);
    }

    public static String getDateTimeMinus5MinutesWithOffSet(final int hourOffSet) {
        return getDateTimeWithOffSet(DATE_TIME_FORMAT, Calendar.MINUTE, -5, hourOffSet);
    }

    public static String getDateTimeMinus30Minutes() {
        return getDateTime(DATE_TIME_FORMAT, Calendar.MINUTE, -30);
    }

    public static String getDateTimeMinus30MinutesWithOffSet(final int hourOffSet) {
        return getDateTimeWithOffSet(DATE_TIME_FORMAT, Calendar.MINUTE, -30, hourOffSet);
    }

    public static String getDateTimeMinus35Minutes() {
        return getDateTime(DATE_TIME_FORMAT, Calendar.MINUTE, -35);
    }

    public static String getDateTimeMinus40Minutes() {
        return getDateTime(DATE_TIME_FORMAT, Calendar.MINUTE, -40);
    }

    public static String getDateTimeMinus55Minutes() {
        return getDateTime(DATE_TIME_FORMAT, Calendar.MINUTE, -55);
    }

    public static String getDateTimeMinusMinutes(final int noOfMinutes) {
        return getDateTime(DATE_TIME_FORMAT, Calendar.MINUTE, -noOfMinutes);
    }

    public static String getDateTimeMinus25Minutes() {
        return getDateTime(DATE_TIME_FORMAT, Calendar.MINUTE, -25);
    }

    public static String getDateTimeMinus36Hours() {
        return getDateTime(DATE_TIME_FORMAT, Calendar.HOUR, -36);
    }

    public static String getDateTimeMinus36HoursWithOffSet(final int hourOffSet) {
        return getDateTimeWithOffSet(DATE_TIME_FORMAT, Calendar.HOUR, -36, hourOffSet);
    }

    public static String getDateTimeMinus3Minutes() {
        return getDateTime(DATE_TIME_FORMAT, Calendar.MINUTE, -3);
    }

    public static String getDateTimeMinus48Hours() {
        return getDateTime(DATE_TIME_FORMAT, Calendar.HOUR, -48);
    }

    public static String getDateTimeMinus48HoursWithOffSet(final int hourOffSet) {
        return getDateTimeWithOffSet(DATE_TIME_FORMAT, Calendar.HOUR, -48, hourOffSet);
    }

    public static String getDateTimeMinusHours(final int noOfHours) {
        return getDateTime(DATE_TIME_FORMAT, Calendar.HOUR, -noOfHours);
    }

    public static String getHourMinusHours(final int noOfHours) {
        return getDateTime(HOUR_FORMAT, Calendar.HOUR, -noOfHours);
    }

    public static String getHourMinusMinutes(final int noOfMinutes) {
        return getDateTime(HOUR_FORMAT, Calendar.MINUTE, -noOfMinutes);
    }

    public static String getDateTimeMinusDay(final int day) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);

        // Make sure we're always in GMT so our tests dont fail when timezone changes!
        final Calendar calendar = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
        dateFormat.setCalendar(calendar);
        calendar.add(Calendar.DAY_OF_YEAR, -day);
        return dateFormat.format(calendar.getTime());
    }

    public static int getDayOfWeekMinusDay(final int day) {
        final Calendar calendar = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
        calendar.add(Calendar.DAY_OF_YEAR, -day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getNameOfDayOfWeek(final int day) {
        final DateFormat dateFormat = new SimpleDateFormat(DAY_FORMAT);
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, day);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * Create a map of date time strings based on the number of minutes ago.  Can't use the other methods
     * as they get a new Date object each time which may result in time having moved to the
     * next minute.
     *
     * @param startMinsAgo
     * @param noTimeSlots
     * @return
     */
    public static Map<Integer, String> getDateTimeMap(final int startMinsAgo, final int noTimeSlots) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT_ZERO_SECONDS);
        final Date date = new Date();

        // Make sure we're always in GMT so our tests dont fail when timezone changes!
        final Calendar calendar = Calendar.getInstance(new SimpleTimeZone(0, "GMT"));
        dateFormat.setCalendar(calendar);

        final Map<Integer, String> dateTimeMap = new HashMap<Integer, String>();

        for (int minsAgo = startMinsAgo; minsAgo < (startMinsAgo + noTimeSlots); minsAgo++) {
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, -minsAgo);
            dateTimeMap.put(minsAgo, dateFormat.format(calendar.getTime()));
        }

        return dateTimeMap;
    }

    public static String adjustForDST(final String originalDateTime) throws ParseException {
        final boolean inDaylightTime = TimeZone.getDefault().inDaylightTime(new Date());
        if (inDaylightTime) {
            final DateFormat dateFormat = getDateFormat("yyyy-MM-dd HH:mm:ss.S");
            final Date date = dateFormat.parse(originalDateTime);
            System.out.println(date);
            final Date dateWithDST = DateUtils.addHours(date, 1);
            return dateFormat.format(dateWithDST);
        }
        return originalDateTime;
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
     * Extracts the date part of a full datetime string in the correct format for use as a DateTo or DateFrom request parameter
     *
     * @param dateTimeString the full dateTime string we want to extract the DateParam value from.
     * @return string value of just the date part of the input string formatted for use in the DateParam of the request parameters.
     */
    public static String getDateParamValueFromDateTimeString(final String dateTimeString) throws ParseException {
        //create dateformatter to parse back to date
        final SimpleDateFormat fullDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        fullDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        //parse the string back to a date
        final Date dateObject = fullDateFormat.parse(dateTimeString);

        //create formatter to format just the date param format i.e. ddMMyyyy
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PARAM_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return dateFormat.format(dateObject);
    }

    /**
     * Extracts the time part of a full datetime string in the correct format for use as a TimeTo or TimeFrom request parameter
     *
     * @param dateTimeString the full dateTime string we want to extract the TimeParam value from.
     * @return string value of just the time part of the input string formatted for use in the TimeParam of the request parameters.
     */
    public static String getTimeParamValueFromDateTimeString(final String dateTimeString) throws ParseException {
        //create dateformatter to parse back to date
        final SimpleDateFormat fullDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        fullDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        //parse the string back to a date
        final Date dateObject = fullDateFormat.parse(dateTimeString);

        //create the formatter to format to just time param format i.e HHmm
        final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_PARAM_FORMAT);
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return timeFormat.format(dateObject);
    }

    /**
     * Extracts the time part of a full datetime string in the correct format for use as a TimeTo or TimeFrom request parameter
     *
     * @param dateTimeMillis the millisecond value for the datetime
     * @return string value of just the time part of the input string formatted for use in the TimeParam of the request parameters.
     */
    public static String getDateTimeFromMillisecond(final long dateTimeMillis) throws ParseException {
        //create dateformatter to parse to correct date format
        final SimpleDateFormat fullDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        fullDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        //parse the string back to a date
        final Date dateObject = new Date(dateTimeMillis);

        return fullDateFormat.format(dateObject);
    }
}
