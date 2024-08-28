/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.WebApplicationException;

import org.apache.commons.lang.time.DateUtils;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.common.ApplicationConstants;
import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;

/**
 * @author ehaoswa
 * @author edeccox
 * @since 2010
 */
public class DateTimeRangeTest extends BaseJMockUnitTest {

    ApplicationConfigManager mockedApplicationConfigManager;

    @Before
    public void setup() {
        mockedApplicationConfigManager = mockery.mock(ApplicationConfigManager.class);
        mockery.checking(new Expectations() {
            {
                ignoring(mockedApplicationConfigManager);
            }
        });
    }

    @Test
    public void testGetDateTimeRangeByDataTimeFromAndToTimeParamsWhenTimeFromAndToParamsAreAlsoPresent()
            throws WebApplicationException {

        final String timeFrom = "1500";
        final String timeTo = "1600";
        final String dateFrom = "22062010";
        final String dateTo = "23062010";

        final String inputFromDate = "2010-06-22 14:00";
        final Date fromDate = DateTimeUtils.parseDateTimeFormat(inputFromDate);
        final String dataTimeFrom = Long.toString(fromDate.getTime());

        final String inputEndDate = "2010-06-23 15:00";
        final Date toDate = DateTimeUtils.parseDateTimeFormat(inputEndDate);
        final String dataTimeTo = Long.toString(toDate.getTime());

        final String expectedFromDate = "2010-06-22 13:00";
        final String expectedEndDate = "2010-06-23 14:00";

        final FormattedDateTimeRange result = DateTimeRange.getFormattedDateTimeRange(null, null, timeFrom, timeTo,
                dateFrom, dateTo, dataTimeFrom, dataTimeTo, "+0100",
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES);
        assertThat(result.getStartDateTime(), is(expectedFromDate));
        assertThat(result.getEndDateTime(), is(expectedEndDate));

    }

    @Test
    public void testGetDateTimeRangeByDataTimeFromAndToTimeParamsWhenTimeParamIsAlsoPresent()
            throws WebApplicationException {
        final String inputFromDate = "2010-06-22 14:00";
        final Date fromDate = DateTimeUtils.parseDateTimeFormat(inputFromDate);
        final String dataTimeFrom = Long.toString(fromDate.getTime());

        final String inputEndDate = "2010-06-23 15:00";
        final Date toDate = DateTimeUtils.parseDateTimeFormat(inputEndDate);
        final String dataTimeTo = Long.toString(toDate.getTime());

        final String expectedFromDate = "2010-06-22 13:00";
        final String expectedEndDate = "2010-06-23 14:00";

        final FormattedDateTimeRange result = DateTimeRange.getFormattedDateTimeRange(null, "30", null, null, null,
                null, dataTimeFrom, dataTimeTo, "+0100",
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES);
        assertThat(result.getStartDateTime(), is(expectedFromDate));
        assertThat(result.getEndDateTime(), is(expectedEndDate));

    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeRange#getDateTimeRange(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws WebApplicationException
     */
    @Test
    public void testGetDateTimeRangeByDataTimeFromAndToTimeParams() throws WebApplicationException {
        final String inputFromDate = "2010-06-22 14:00";
        final Date fromDate = DateTimeUtils.parseDateTimeFormat(inputFromDate);
        final String dataTimeFrom = Long.toString(fromDate.getTime());

        final String inputEndDate = "2010-06-23 15:00";
        final Date toDate = DateTimeUtils.parseDateTimeFormat(inputEndDate);
        final String dataTimeTo = Long.toString(toDate.getTime());

        final String expectedFromDate = "2010-06-22 13:00";
        final String expectedEndDate = "2010-06-23 14:00";

        final FormattedDateTimeRange result = DateTimeRange.getFormattedDateTimeRange(null, null, null, null, null,
                null, dataTimeFrom, dataTimeTo, "+0100",
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES);
        assertThat(result.getStartDateTime(), is(expectedFromDate));
        assertThat(result.getEndDateTime(), is(expectedEndDate));

    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeRange#getDateTimeRange(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws WebApplicationException
     */
    @Test
    public void testGetDateTimeRangeByDataTimeFromAndToTimeParamsAndRangeAboveOneWeek() throws WebApplicationException {
        final String inputFromDate = "2010-06-22 00:00";
        final Date fromDate = DateTimeUtils.parseDateTimeFormat(inputFromDate);
        final String dataTimeFrom = Long.toString(fromDate.getTime());

        final String inputEndDate = "2010-06-30 00:00";
        final Date toDate = DateTimeUtils.parseDateTimeFormat(inputEndDate);
        final String dataTimeTo = Long.toString(toDate.getTime());

        final String expectedFromDate = "2010-06-22 00:00";
        final String expectedEndDate = "2010-06-30 00:00";

        final FormattedDateTimeRange result = DateTimeRange.getFormattedDateTimeRange("ERR", null, null, null, null,
                null, dataTimeFrom, dataTimeTo, "+0100",
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES);
        assertThat(result.getStartDateTime(), is(expectedFromDate));
        assertThat(result.getEndDateTime(), is(expectedEndDate));

    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeRange#formattedDateTimeAgainst15MinsTable(java.lang.String, int)}
     * .
     * 
     * @throws WebApplicationException
     */
    @Test
    public void testFormattedDateTimeAgainst15MinsTable() throws WebApplicationException {
        String dateTime = "2010-05-11 15:27";
        int minutes = 27;
        String expectDateTime = "2010-05-11 15:15";

        assertThat(DateTimeRange.formattedDateTimeAgainst15MinsTable(dateTime, minutes), is(expectDateTime));

        dateTime = "2010-05-11 15:37";
        minutes = 37;
        expectDateTime = "2010-05-11 15:30";

        assertThat(DateTimeRange.formattedDateTimeAgainst15MinsTable(dateTime, minutes), is(expectDateTime));

        dateTime = "2010-05-11 15:47";
        minutes = 47;
        expectDateTime = "2010-05-11 15:45";

        assertThat(DateTimeRange.formattedDateTimeAgainst15MinsTable(dateTime, minutes), is(expectDateTime));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormattedDateTimeAgainst15MinsTableFailMinutesOutOfRange() throws WebApplicationException {
        DateTimeRange.formattedDateTimeAgainst15MinsTable("", 60);
    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeRange#formattedDateTimeAgainstDayTable(java.lang.String, int)}
     * .
     * 
     * @throws WebApplicationException
     */
    @Test
    public void testFormattedDateTimeAgainstDayTable() throws WebApplicationException {
        final String dateTime = "2010-05-11 15:27";
        final int minutes = 27;
        final String expectDateTime = "2010-05-11 15:00";

        assertThat(DateTimeRange.formattedDateTimeAgainstDayTable(dateTime, minutes), is(expectDateTime));
    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeRange#getDateTimeRange(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws WebApplicationException
     */
    @Test
    public void testGetDateTimeRangeByTimerangeParams() throws WebApplicationException {
        final String timeFrom = "1500";

        final String timeTo = "1600";

        final String dateFrom = "14112009";

        final String dateTo = "17112009";

        final FormattedDateTimeRange actualDateTimeRange = DateTimeRange.getFormattedDateTimeRange(null, null,
                timeFrom, timeTo, dateFrom, dateTo, null, null, "+0100",
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES);

        final String expectedStartTime = "2009-11-14 14:00";
        final String expectedEndTime = "2009-11-17 15:00";

        assertThat(actualDateTimeRange.getStartDateTime(), is(expectedStartTime));

        assertThat(actualDateTimeRange.getEndDateTime(), is(expectedEndTime));
    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeRange#getDateTimeRange(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
     * .
     * 
     * @throws WebApplicationException
     */
    @Test
    public void testGetDateTimeRangeByTimeParams() throws WebApplicationException {
        final String time = "30";
        final long currentTime = System.currentTimeMillis();
        final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
        final Date now = new Date(currentTime - offSet);
        final String formattedStartDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, time), "+0200", DATE_TIME_FORMAT),
                "+0200", DATE_TIME_FORMAT);
        final String formattedEndDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, "0"), "+0200", DATE_TIME_FORMAT),
                "+0200", DATE_TIME_FORMAT);
        final FormattedDateTimeRange expectedDateTimeRange = createTimeRange(formattedStartDateTime,
                formattedEndDateTime);

        final FormattedDateTimeRange actualDateTimeRange = DateTimeRange.getFormattedDateTimeRange(null, time, null,
                null, null, null, null, null, "+0200",
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES);
        assertThat(actualDateTimeRange.getEndDateTime(), is(expectedDateTimeRange.getEndDateTime()));
    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeRange#getFormattedTimeRangeInDays(java.lang.String, java.lang.String)}
     * .
     * 
     * @throws WebApplicationException
     */

    @Test
    public void testGetFormattedTimeRangeInDays() throws WebApplicationException {
        final String offsetInDays = "30";
        final String tzOffset = "+0200";
        final long currentTime = System.currentTimeMillis();
        final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
        final Date now = new Date(currentTime - offSet);
        final String formattedStartDateTime = DateTimeUtils.getUTCTime(DateTimeUtils.getLocalTime(
                DateTimeUtils.formattedDateTimeInDays(now, offsetInDays), tzOffset, DATE_TIME_FORMAT), tzOffset,
                DATE_TIME_FORMAT);
        final String formattedEndDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, "0"), tzOffset, DATE_TIME_FORMAT),
                tzOffset, DATE_TIME_FORMAT);
        final FormattedDateTimeRange expectedDateTimeRange = createTimeRangeInDays(formattedStartDateTime,
                formattedEndDateTime);
        final FormattedDateTimeRange actualDateTimeRange = DateTimeRange.getFormattedTimeRangeInDays(tzOffset,
                offsetInDays);
        assertThat(actualDateTimeRange.getEndDateTime(), is(expectedDateTimeRange.getEndDateTime()));
    }

    //	@Test
    //	Note: 11.11.2010 edeccox - DateTimeRange
    //	.getDateTimeRangeOfChartAndSummaryGrid(fdtr, viewName); moved to BaseResource
    //	public void testGetDateTimeRangeOfChartAndSummaryGrid_15Mins()
    //			throws WebApplicationException {
    //		final String viewName = EventDataSourceType.AGGREGATED_15MIN.getValue();
    //
    //		final String timeFrom = "1513";
    //
    //		final String timeTo = "1622";
    //
    //		final String dateFrom = "14112009";
    //
    //		final String dateTo = "17112009";
    //
    //		final FormattedDateTimeRange fdtr = createTimeRange(
    //				dateFrom, 
    //				timeFrom,
    //				dateTo, 
    //				timeTo
    //				);
    //
    //		final String expectedStartTime = "2009-11-14 15:00";
    //		final String expectedEndTime = "2009-11-17 16:15";
    //
    //		final FormattedDateTimeRange fdtrOfChartAndSummaryGrid = DateTimeRange
    //				.getDateTimeRangeOfChartAndSummaryGrid(fdtr, viewName);
    //
    //		assertThat(fdtrOfChartAndSummaryGrid.getStartDateTime(),
    //				is(expectedStartTime));
    //
    //		assertThat(fdtrOfChartAndSummaryGrid.getEndDateTime(),
    //				is(expectedEndTime));
    //	}

    //	@Test
    //	Note: 11.11.2010 edeccox - DateTimeRange
    //	.getDateTimeRangeOfChartAndSummaryGrid(fdtr, viewName); moved to BaseResource
    //	public void testGetDateTimeRangeOfChartAndSummaryGrid_Day()
    //			throws WebApplicationException {
    //		final String viewName = EventDataSourceType.AGGREGATED_DAY.getValue();
    //
    //		final String timeFrom = "1513";
    //
    //		final String timeTo = "1622";
    //
    //		final String dateFrom = "14112009";
    //
    //		final String dateTo = "24112009";
    //
    //		final FormattedDateTimeRange fdtr = createTimeRange(
    //				dateFrom, 
    //				timeFrom,
    //				dateTo, 
    //				timeTo
    //				);
    //
    //		final String expectedStartTime = "2009-11-14 15:00";
    //		final String expectedEndTime = "2009-11-24 16:00";
    //
    //		final FormattedDateTimeRange fdtrOfChartAndSummaryGrid = DateTimeRange
    //				.getDateTimeRangeOfChartAndSummaryGrid(fdtr, viewName);
    //
    //		assertThat(fdtrOfChartAndSummaryGrid.getStartDateTime(),
    //				is(expectedStartTime));
    //
    //		assertThat(fdtrOfChartAndSummaryGrid.getEndDateTime(),
    //				is(expectedEndTime));
    //	}

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeRange#getChartInterval(com.ericsson.eniq.events.server.utils.DateTimeRange.FormattedDateTimeRange, java.lang.String)}
     * .
     * 
     * @throws WebApplicationException
     */
    @Test
    public void testGetChartInterval_15Mins() throws WebApplicationException {
        final String timerange = EventDataSourceType.AGGREGATED_15MIN.getValue();

        final String timeFrom = "0300";

        final String timeTo = "1600";

        final String dateFrom = "14112009";

        final String dateTo = "14112009";

        final FormattedDateTimeRange dateTimeRange;

        dateTimeRange = createTimeRange(dateFrom, timeFrom, dateTo, timeTo);

        final int expectedInterval = 900;

        assertThat(DateTimeRange.getChartInterval(dateTimeRange, timerange), is(expectedInterval));
    }

    /**
     * Test method for
     * {@link com.ericsson.eniq.events.server.utils.DateTimeRange#getChartInterval(com.ericsson.eniq.events.server.utils.DateTimeRange.FormattedDateTimeRange, java.lang.String)}
     * .
     * 
     * @throws WebApplicationException
     */
    @Test
    public void testGetChartInterval_1Min() throws WebApplicationException {
        final String timerange = EventDataSourceType.AGGREGATED_1MIN.getValue();

        final String timeFrom = "1600";

        final String timeTo = "1900";

        final String dateFrom = "05112010";

        final String dateTo = "05112010";

        final FormattedDateTimeRange dateTimeRange;

        dateTimeRange = createTimeRange(dateFrom, timeFrom, dateTo, timeTo);

        final int expectedInterval = 360;

        assertThat(DateTimeRange.getChartInterval(dateTimeRange, timerange), is(expectedInterval));
    }

    @Test
    public void testGetChartInterval_Day() throws WebApplicationException {
        final String timerange = EventDataSourceType.AGGREGATED_DAY.getValue();

        String timeFrom = "1600";

        String timeTo = "1900";

        String dateFrom = "05112010";

        String dateTo = "24112010";

        FormattedDateTimeRange dateTimeRange;

        dateTimeRange = createTimeRange(dateFrom, timeFrom, dateTo, timeTo);

        int expectedInterval = 86400;

        assertThat(DateTimeRange.getChartInterval(dateTimeRange, timerange), is(expectedInterval));

        timeFrom = "1600";

        timeTo = "1900";

        dateFrom = "01052010";

        dateTo = "01072010";

        dateTimeRange = createTimeRange(dateFrom, timeFrom, dateTo, timeTo);

        expectedInterval = 172800;

        assertThat(DateTimeRange.getChartInterval(dateTimeRange, timerange), is(expectedInterval));

    }

    @Test
    public void testGetChartSlotCount_1Min() throws WebApplicationException {

        final String timeFrom = "1600";

        final String timeTo = "1616";

        final String dateFrom = "05112010";

        final String dateTo = "05112010";

        final FormattedDateTimeRange dateTimeRange;

        dateTimeRange = createTimeRange(dateFrom, timeFrom, dateTo, timeTo);

        final int expectedChartSlotCount = 16;

        assertThat(DateTimeRange.getChartSlotCount(dateTimeRange, 60), is(expectedChartSlotCount));
    }

    @Test
    public void testGetChartSlotCount_Day() throws WebApplicationException {

        final String timeFrom = "1600";

        final String timeTo = "1616";

        final String dateFrom = "05112010";

        final String dateTo = "24112010";

        final FormattedDateTimeRange dateTimeRange;

        dateTimeRange = createTimeRange(dateFrom, timeFrom, dateTo, timeTo);

        final int expectedChartSlotCount = 19;

        assertThat(DateTimeRange.getChartSlotCount(dateTimeRange, 86400), is(expectedChartSlotCount));
    }

    @Test
    public void testGetKPITimeList() throws WebApplicationException {

        final String timeFrom = "1600";

        final String timeTo = "1616";

        final String dateFrom = "05112010";

        final String dateTo = "24112010";

        final FormattedDateTimeRange dateTimeRange;

        dateTimeRange = createTimeRange(dateFrom, timeFrom, dateTo, timeTo);

        final int expectedKPITimeListSize = ApplicationConstants.CHART_SLOT_COUNT;

        final String dataSourceTypeString = EventDataSourceType.AGGREGATED_DAY.getValue();
        final int actualKPITimeListSize = DateTimeRange.getSamplingTimeList(dateTimeRange,
                DateTimeRange.getChartInterval(dateTimeRange, dataSourceTypeString)).length;

        assertThat(actualKPITimeListSize, is(expectedKPITimeListSize));

    }

    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final int TIME_DELAY_1MIN_DATA_ROP_1 = 3;

    public static final int TIME_DELAY_1MIN_DATA_ROP_5 = 9;

    public static final int TIME_DELAY_1MIN_DATA_ROP_15 = 24;

    public static final int TIME_DELAY_15MIN_DATA = 10;

    public static final int TIME_DELAY_DAY_DATA = 180;

    /*
     * Worked example:
     *
     * Case 1. 1 minute ROP, time range = last 30 minutes (i.e., Class 1.)
     *
     *  now = 2010-11-12 11:24:06
     *
     *  delay = 1 + 1.5*1  --> 3 minutes
     *
     *  query end_time =   (now - 3 mins) rounded to last minute
     *                 =>  2010-11-12 11:21:00
     *
     *  query start_time = 2010-11-12 10:51:00
     */
    @Test
    public void testTimeRangeDelayCalculation_class_1() throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);

        Date now;

        String startTime;
        String endTime;
        FormattedDateTimeRange tr;
        int timeRangeInMinutes;

        now = sdf.parse("2010-11-12 11:24:00");
        timeRangeInMinutes = 30;
        endTime = "2010-11-12 11:24:06";
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_1,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(30, tr.getRangeInMinutes());
        assertEquals("2010-11-12 11:21:00", tr.getEndDateTime());
        assertEquals("2010-11-12 10:51:00", tr.getStartDateTime());
        assertEquals(21, tr.getMinutesOfEndDateTime());
        assertEquals(51, tr.getMinutesOfStartDateTime());

        timeRangeInMinutes = 60;
        endTime = "2010-11-12 11:24:06";
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_5,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(60, tr.getRangeInMinutes());
        assertEquals("2010-11-12 11:15:00", tr.getEndDateTime());
        assertEquals("2010-11-12 10:15:00", tr.getStartDateTime());
        assertEquals(15, tr.getMinutesOfEndDateTime());
        assertEquals(15, tr.getMinutesOfStartDateTime());

        timeRangeInMinutes = 120;
        endTime = "2010-11-12 11:24:06";
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_15,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(120, tr.getRangeInMinutes());
        assertEquals("2010-11-12 11:00:00", tr.getEndDateTime());
        assertEquals("2010-11-12 09:00:00", tr.getStartDateTime());
        assertEquals(0, tr.getMinutesOfEndDateTime());
        assertEquals(0, tr.getMinutesOfStartDateTime());

    }

    /* Case 2. 15 minute ROP, time range = last 6 hours (i.e., Class 2)
    *
    *  now = 2010-11-12 11:24:06   (start time = 2010-11-12 05:24:06
    *
    *  delay = 10 minutes
    *
    *  query end_time =   (now - 10 mins) rounded to last quarter hour
    *                 =>  2010-11-12 11:00:00
    *
    *  query start_time = 2010-11-12 05:00:00
    *
    */
    @Test
    public void testTimeRangeDelayCalculation_class_2() throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
        Date now;

        String startTime;
        String endTime;
        FormattedDateTimeRange tr;
        int timeRangeInMinutes;

        now = sdf.parse("2010-11-12 11:24:00");
        timeRangeInMinutes = 121;
        endTime = "2010-11-12 11:24:06";
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_1,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(121, tr.getRangeInMinutes());
        assertEquals("2010-11-12 11:00:00", tr.getEndDateTime());
        assertEquals("2010-11-12 08:59:00", tr.getStartDateTime());
        assertEquals(0, tr.getMinutesOfEndDateTime());
        assertEquals(59, tr.getMinutesOfStartDateTime());

        // change ROP to 5 min ensure has no effect
        timeRangeInMinutes = 121;
        endTime = "2010-11-12 11:24:06";
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_5,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(121, tr.getRangeInMinutes());
        assertEquals("2010-11-12 11:00:00", tr.getEndDateTime());
        assertEquals("2010-11-12 08:59:00", tr.getStartDateTime());
        assertEquals(0, tr.getMinutesOfEndDateTime());
        assertEquals(59, tr.getMinutesOfStartDateTime());

        timeRangeInMinutes = MINUTES_IN_A_WEEK;
        endTime = "2010-11-12 11:24:06";
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_15,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(MINUTES_IN_A_WEEK, tr.getRangeInMinutes());
        //assertEquals("2010-11-12 11:00:00", tr.getEndDateTime());
        //assertEquals("2010-11-05 11:00:00", tr.getStartDateTime());
        assertEquals(0, tr.getMinutesOfEndDateTime());
        assertEquals(0, tr.getMinutesOfStartDateTime());

    }

    /* Case 3. 5 minute ROP, time range = last 8 days (i.e., Class 3)
     *
     *  now = 2010-11-12 02:24:06   (start time = 2010-11-04 02:24:06)
     *
     *  delay = 3 hours
     *
     *  query end_time =   (now - 3 hrs) rounded to last day
     *                 =>  (2010-11-11 11:24:06) rounded to last day
     *                 =>  2010-11-11 00:00:00  midnight of the 10th
     *
     *  query start_time = 2010-11-03 00:00:00
     *
     *  Note: Classes 2 & 3 are ROP independent
     *
     * Case 4. 15 minute ROP, time range = last 8 days (i.e., Class 3)
     *
     *  now = 2010-11-12 04:24:06   (start time = 2010-11-04 04:24:06)
     *
     *  delay = 3 hours
     *
     *  query end_time =   (now - 3 hrs) rounded to last day
     *                 =>  (2010-11-12 01:24:06) rounded to last day
     *                 =>  2010-11-12 00:00:00  midnight of the 11th
     *
     *  query start_time = 2010-11-04 00:00:00
     */
    @Test
    public void testTimeRangeDelayCalculation_class_3() throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);

        Date now;
        String startTime;
        String endTime;
        FormattedDateTimeRange tr;
        int timeRangeInMinutes;

        now = sdf.parse("2010-11-12 02:24:06");
        timeRangeInMinutes = MINUTES_IN_A_WEEK + MINUTES_IN_A_DAY; // 8 days
        endTime = "2010-11-12 02:24:06";
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_1,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(timeRangeInMinutes, tr.getRangeInMinutes());
        assertEquals("2010-11-11 00:00:00", tr.getEndDateTime());
        assertEquals("2010-11-03 00:00:00", tr.getStartDateTime());
        assertEquals(0, tr.getMinutesOfEndDateTime());
        assertEquals(0, tr.getMinutesOfStartDateTime());

        // shift end time by two hours later and notice that the end time
        // shifts to a day later. This is because an additional batch of
        // aggregated data will have come available in the meantime.
        //
        now = sdf.parse("2010-11-12 04:24:06");
        timeRangeInMinutes = MINUTES_IN_A_WEEK + MINUTES_IN_A_DAY; // 8 days
        endTime = "2010-11-12 04:24:06";
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_1,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(timeRangeInMinutes, tr.getRangeInMinutes());
        assertEquals("2010-11-12 00:00:00", tr.getEndDateTime());
        assertEquals("2010-11-04 00:00:00", tr.getStartDateTime());
        assertEquals(0, tr.getMinutesOfEndDateTime());
        assertEquals(0, tr.getMinutesOfStartDateTime());

    }

    @Test
    public void test_do_not_apply_offset_class_1() throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
        Date now;

        String startTime;
        String endTime;
        FormattedDateTimeRange tr;
        int timeRangeInMinutes;

        now = sdf.parse("2010-11-12 12:24:00");
        timeRangeInMinutes = 30;
        endTime = "2010-11-12 11:24:06"; // end time is one hour past so do not apply offset
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_1,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(30, tr.getRangeInMinutes());
        assertEquals("2010-11-12 11:24:06", tr.getEndDateTime());
        assertEquals("2010-11-12 10:54:06", tr.getStartDateTime());
        assertEquals(24, tr.getMinutesOfEndDateTime());
        assertEquals(54, tr.getMinutesOfStartDateTime());
    }

    @Test
    public void test_do_not_apply_offset_class_2() throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
        Date now;

        String startTime;
        String endTime;
        FormattedDateTimeRange tr;
        int timeRangeInMinutes;

        now = sdf.parse("2010-11-12 11:35:06");
        timeRangeInMinutes = 180; // time range => 15 minute data is targetted
        endTime = "2010-11-12 11:24:06"; // end time is 11 minutes earlier so do not apply offset (takes 10 minutes by default to be ready)
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_1,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(180, tr.getRangeInMinutes());
        assertEquals("2010-11-12 11:24:06", tr.getEndDateTime());
        assertEquals("2010-11-12 08:24:06", tr.getStartDateTime());
        assertEquals(24, tr.getMinutesOfEndDateTime());
        assertEquals(24, tr.getMinutesOfStartDateTime());
    }

    @Test
    public void test_do_not_apply_offset_class_3() throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
        Date now;

        String startTime;
        String endTime;
        FormattedDateTimeRange tr;
        int timeRangeInMinutes;

        now = sdf.parse("2010-11-13 02:24:06");
        timeRangeInMinutes = MINUTES_IN_A_WEEK + MINUTES_IN_A_DAY; // 8 days
        endTime = "2010-11-12 02:24:06"; // end time is day earlier so daily aggregations are up to date for selected range
        startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));
        tr = DateTimeRange.getFormattedDateTimeRange(startTime, endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA_ROP_1,
                TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        assertEquals(timeRangeInMinutes, tr.getRangeInMinutes());
        //assertEquals("2010-11-12 02:24:06", tr.getEndDateTime());
        //assertEquals("2010-11-04 02:24:06", tr.getStartDateTime());
        //assertEquals(24, tr.getMinutesOfEndDateTime());
        //assertEquals(24, tr.getMinutesOfStartDateTime());
    }

    public static FormattedDateTimeRange createTimeRange(final String dateFrom, final String timeFrom,
            final String dateTo, final String timeTo) {
        return DateTimeRange.getFormattedDateTimeRange(DateTimeUtils.formattedDateTime(dateFrom, timeFrom),
                DateTimeUtils.formattedDateTime(dateTo, timeTo),
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES);

    }

    public static FormattedDateTimeRange createTimeRange(final String startDateTime, final String endDateTime) {
        return DateTimeRange.getFormattedDateTimeRange(startDateTime, endDateTime,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES);

    }

    public static FormattedDateTimeRange createTimeRangeInDays(final String startDateTime, final String endDateTime) {
        return DateTimeRange.getFormattedDateTimeRange(startDateTime, endDateTime, 0, 0, 0);
    }

    @Test
    public void testGetTimewindowValue() throws Exception {
        final String timeFrom = "1600";

        final String timeTo = "1616";

        final String dateFrom = "05112010";

        final String dateTo = "05112010";

        final FormattedDateTimeRange dateTimeRange;

        dateTimeRange = createTimeRange(dateFrom, timeFrom, dateTo, timeTo);

        final int expectedTimewindowValue = 960;

        assertThat(DateTimeRange.getTimewindowValue(dateTimeRange), is(expectedTimewindowValue));
    }
}
