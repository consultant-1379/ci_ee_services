/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATE_TIME_FORMAT;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.FIVE_MINUTE_VALUE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MINUTES_IN_2_HOURS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MINUTES_IN_A_WEEK;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RECEIVED_DATE_FORMAT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.WebApplicationException;

import org.junit.Test;

/**
 * @author ehaoswa
 * @since 2010
 */
public class DateTimeUtilsTest {

    private static final long TWO_HOURS_AND_ONE_MINUTE = 121;

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeUtils#formattedDateTime(java.util.Date, java.lang.String)}
     * .
     */
    @Test
    public void testFormattedDateTimeDate_goBackTime() {
        final Calendar cal = Calendar.getInstance();
        final int CURRENT_MINUTES = 25;
        final String MINUTES_IN_PAST = "10";
        cal.set(2010, Calendar.APRIL, 21, 12, CURRENT_MINUTES, 56);

        assertEquals("2010-04-21 12:15", DateTimeUtils.formattedDateTime(cal.getTime(), MINUTES_IN_PAST));
    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeUtils#formattedDateTimeInDays(java.util.Date, java.lang.String)}
     * .
     */
    @Test
    public void testFormattedDateTimeInDays_goBackTime() {
        final Calendar cal = Calendar.getInstance();
        final int CURRENT_DAY = 21;
        final String DAYS_IN_PAST = "10";
        cal.set(2010, Calendar.APRIL, CURRENT_DAY, 12, 25, 10);

        assertEquals("2010-04-11 12:25", DateTimeUtils.formattedDateTimeInDays(cal.getTime(), DAYS_IN_PAST));
    }

    @Test
    public void testFormattedDateTime() throws Exception {
        String date;
        String time;

        date = "11052010";
        time = "1400";
        assertEquals("2010-05-11 14:00", DateTimeUtils.formattedDateTime(date, time));

        date = "12012008";
        time = "0001";
        assertEquals("2008-01-12 00:01", DateTimeUtils.formattedDateTime(date, time));

    }

    @Test
    public void testAddMinutesToFormattedDateTime() {
        String dateTime;
        String result;

        dateTime = "2010-05-11 14:00";
        result = DateTimeUtils.addMinutes(dateTime, 4);
        assertEquals("2010-05-11 14:04", result);

        dateTime = "2010-05-11 14:00";
        result = DateTimeUtils.addMinutes(dateTime, 0);
        assertEquals("2010-05-11 14:00", result);

        dateTime = "2010-05-11 14:45";
        result = DateTimeUtils.addMinutes(dateTime, -4);
        assertEquals("2010-05-11 14:41", result);

        dateTime = "2010-05-11 14:45";
        result = DateTimeUtils.addMinutes(dateTime, -60);
        assertEquals("2010-05-11 13:45", result);

        dateTime = "2010-05-11 14:45";
        result = DateTimeUtils.addMinutes(dateTime, -1440);
        assertEquals("2010-05-10 14:45", result);

        dateTime = "2010-05-11 14:45";
        result = DateTimeUtils.addMinutes(dateTime, 1440);
        assertEquals("2010-05-12 14:45", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormattedDateTimeFail_invalid24hrTime_bad_value() throws Exception {
        String date;
        String time;

        date = "11052010";
        time = "3000";
        DateTimeUtils.formattedDateTime(date, time);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormattedDateTimeFail_invalid24hrTime_too_short() throws Exception {
        String date;
        String time;

        date = "11052010";
        time = "001";
        DateTimeUtils.formattedDateTime(date, time);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormattedDateTimeFail_invalid24hrTime_too_long() throws Exception {
        String date;
        String time;

        date = "11052010";
        time = "23599";
        DateTimeUtils.formattedDateTime(date, time);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormattedDateTimeFail_null24hrTime() throws Exception {
        String date;

        date = "11052010";
        DateTimeUtils.formattedDateTime(date, null);
    }

    @Test(expected = WebApplicationException.class)
    public void testFormattedDateTimeFail_invalidDate() throws Exception {
        String date;
        String time;

        date = "1105";
        time = "2359";
        DateTimeUtils.formattedDateTime(date, time);
    }

    @Test
    public void testOneMinuteAggregatedRange_FiveMinutesIsFalse() throws WebApplicationException {
        assertThat(DateTimeUtils.oneMinuteAggregationRange(FIVE_MINUTE_VALUE), is(false));
    }

    @Test
    public void testOneMinuteAggregatedRange_SixMinutesIsTrue() throws WebApplicationException {
        final long sixMinutes = 6;
        assertThat(DateTimeUtils.oneMinuteAggregationRange(sixMinutes), is(true));
    }

    @Test
    public void testOneMinuteAggregatedRange_TwoHoursIsTrue() throws WebApplicationException {
        assertThat(DateTimeUtils.oneMinuteAggregationRange(MINUTES_IN_2_HOURS), is(true));
    }

    @Test
    public void testOneMinuteAggregatedRange_TwoHoursAndOneMinuteIsFalse() throws WebApplicationException {

        assertThat(DateTimeUtils.oneMinuteAggregationRange(TWO_HOURS_AND_ONE_MINUTE), is(false));
    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeUtils#fifteenMinuteAggregatedRangeForGrid(long)}
     * .
     * 
     * @throws WebApplicationException
     */
    @Test
    public void testFifteenMinuteAggregatedRangeForGrid_2HoursIsFalse() throws WebApplicationException {
        assertThat(DateTimeUtils.fifteenMinuteAggregatedRangeForGrid(MINUTES_IN_2_HOURS), is(false));
    }

    @Test
    public void testFifteenMinuteAggregatedRangeForGrid_2HoursAndOneMinuteIsTrue() throws WebApplicationException {
        assertThat(DateTimeUtils.fifteenMinuteAggregatedRangeForGrid(TWO_HOURS_AND_ONE_MINUTE), is(true));
    }

    @Test
    public void testFifteenMinuteAggregatedRangeForGrid_JustLessThanOneWeekIsTrue() throws WebApplicationException {
        final long oneMinuteLessThanOneWeek = MINUTES_IN_A_WEEK - 1;
        assertThat(DateTimeUtils.fifteenMinuteAggregatedRangeForGrid(oneMinuteLessThanOneWeek), is(true));
    }

    @Test
    public void testFifteenMinuteAggregatedRangeForGrid_OneWeekIsFalse() throws WebApplicationException {
        assertThat(DateTimeUtils.fifteenMinuteAggregatedRangeForGrid(MINUTES_IN_A_WEEK), is(false));
    }

    /**
    * Test method for
    * {@link com.ericsson.eniq.events.server.utils.DateTimeUtils#rawEventRange(long)}
    * .
    * 
    * @throws WebApplicationException
    */
    @Test
    public void testRawEventRange() throws WebApplicationException {
        final long RANGE_IN_MINUTES = 1;
        assertThat(DateTimeUtils.rawEventRange(RANGE_IN_MINUTES), is(true));
    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeUtils#timeInMinutesBetweenDates(java.util.Date, java.util.Date)}
     * .
     * 
     * @throws WebApplicationException
     */
    @Test
    public void testTimeInMinutesBetweenDates() throws WebApplicationException {
        final Date from = DateTimeUtils.parseDateTimeFormat("2010-04-21 12:15");
        final Date to = DateTimeUtils.parseDateTimeFormat("2010-04-21 12:18");

        assertThat(DateTimeUtils.timeInMinutesBetweenDates(from, to), is(3l));
    }

    @Test
    public void testUTCOffset() throws WebApplicationException {

        final String expectedOffset = "+0100";

        assertThat(DateTimeUtils.getUTCOffset(), is(expectedOffset));
    }

    @Test
    public void testGetlocalTime() {
        final String tzOffset = "-0700";

        final String formattedDateTime = "2010-09-04 14:02:00.0";

        System.out.print(DateTimeUtils.getLocalTime(formattedDateTime, tzOffset, RECEIVED_DATE_FORMAT));
    }

    @Test
    public void testTimestampRounding() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);

        Date d;
        String roundedTimestamp;

        // last quarter hour
        d = sdf.parse("2010-11-12 11:25");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastQuarterHour(d));
        assertEquals("2010-11-12 11:15", roundedTimestamp);

        d = sdf.parse("2010-11-12 11:16");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastQuarterHour(d));
        assertEquals("2010-11-12 11:15", roundedTimestamp);

        d = sdf.parse("2010-11-12 11:14");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastQuarterHour(d));
        assertEquals("2010-11-12 11:00", roundedTimestamp);

        d = sdf.parse("2010-11-12 10:59");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastQuarterHour(d));
        assertEquals("2010-11-12 10:45", roundedTimestamp);

        d = sdf.parse("2010-11-12 10:44");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastQuarterHour(d));
        assertEquals("2010-11-12 10:30", roundedTimestamp);

        // last minute
        // for DATE_TIME_FORMAT seconds are lopped off
        // so minute rounding is automatically done.
        // Just for testing we try a format with seconds
        //
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        d = sdf.parse("2010-11-12 11:25:02");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastMinute(d));
        assertEquals("2010-11-12 11:25:00", roundedTimestamp);

        d = sdf.parse("2010-11-12 11:16:58");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastMinute(d));
        assertEquals("2010-11-12 11:16:00", roundedTimestamp);

        d = sdf.parse("2010-11-12 11:01:00");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastMinute(d));
        assertEquals("2010-11-12 11:01:00", roundedTimestamp);

        d = sdf.parse("2010-11-12 10:59:30");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastMinute(d));
        assertEquals("2010-11-12 10:59:00", roundedTimestamp);

        // rounded to end of last day
        sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        d = sdf.parse("2010-11-12 00:30");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastDay(d));
        assertEquals("2010-11-12 00:00", roundedTimestamp);

        d = sdf.parse("2010-11-12 07:30");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastDay(d));
        assertEquals("2010-11-12 00:00", roundedTimestamp);

        d = sdf.parse("2010-11-12 12:30");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastDay(d));
        assertEquals("2010-11-12 00:00", roundedTimestamp);

        d = sdf.parse("2010-11-12 23:59");
        roundedTimestamp = sdf.format(DateTimeUtils.roundToLastDay(d));
        assertEquals("2010-11-12 00:00", roundedTimestamp);
    }

    @Test
    public void testGetPreviousDay() throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT);
        final Date startDate = sdf.parse("2010-11-12 00:00");
        final Date expectedDate = sdf.parse("2010-11-11 00:00");
        final String previousDay = DateTimeUtils.getPreviousDay(startDate);

        assertEquals(sdf.format(expectedDate), previousDay);
    }

    @Test
    public void testGetUTCTime() {
        final String result = DateTimeUtils.getUTCTime(
                DateTimeUtils.formattedDateTime(Long.parseLong("1317636066000")), "+0800", DATE_TIME_FORMAT);
        assertEquals("2011-10-03 04:01", result);
    }

}
