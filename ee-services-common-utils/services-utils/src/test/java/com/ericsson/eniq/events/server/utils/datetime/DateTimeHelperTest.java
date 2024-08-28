/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.datetime;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.utils.DateTimeRange;
import com.ericsson.eniq.events.server.utils.DateTimeUtils;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * This class tests that the DateTimeHelper will return the correct EventDataSourceType, based on the specified
 * TimeRange. It is improtant to note that the TimeRange for different views is different.
 * Grid
 * 0 Min < t <= 5 Mins ==> Raw Tables
 * 5 Min < t <= 2 Hours ==> 1 Min Aggregations
 * 2 Hours < t <= 1 Week ==> 15 Min Aggregations
 * 1 Week < t < X ==> Day Aggregations.
 * <p/>
 * Chart
 * 0 Min < t <= 5 Mins ==> Raw Tables
 * 5 Min < t <= 12 hours ==> 1 Min Aggregations
 * 12 Hours < t <= 2 Weeks ==> 15 Min Aggregations
 * 2 Weeks < t < X ==> Day Aggregations.
 * <p/>
 * Chart (without 1 min aggregations)
 * 0 Min < t <= 2 Hours ==> Raw Tables
 * 2 Hours < t <= 2 Week ==> 15 Min Aggregations
 * 1 Week < t < X ==> Day Aggregations.
 *
 * @author eeikonl
 * @since 2011
 */
public class DateTimeHelperTest {

    private static final int LESS_THAN_1_WEEK = MINUTES_IN_A_WEEK - 1;

    private static final int GREATER_THAN_5_MINUTES = FIVE_MINUTE_VALUE + 1;

    private static final int LESS_THAN_2_HOURS = MINUTES_IN_2_HOURS - 1;

    private static final int GREATER_THAN_1_WEEK = MINUTES_IN_A_WEEK + 1;

    private static final int GREATER_THAN_2_HOURS = MINUTES_IN_2_HOURS + 1;

    private static final int GREATER_THAN_2_WEEKS = MINUTES_IN_2_WEEKS + 1;

    private DateTimeHelper objUnderTest;

    private EventDataSourceType result;

    protected Mockery mockery = new JUnit4Mockery();

    {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
    }

    @Before
    public void setup() {
        objUnderTest = new DateTimeHelper();

        final ApplicationConfigManager mockedApplicationConfigManager = mockery.mock(ApplicationConfigManager.class);
        objUnderTest.setApplicationConfigManager(mockedApplicationConfigManager);
        allowCallsOnMockedApplicationConfigManager(mockedApplicationConfigManager);

    }

    private void allowCallsOnMockedApplicationConfigManager(
            final ApplicationConfigManager mockedApplicationConfigManager) {
        mockery.checking(new Expectations() {
            {
                allowing(mockedApplicationConfigManager).getTimeDelayOneMinuteData(null);
                will(returnValue(30));
                allowing(mockedApplicationConfigManager).getTimeDelayFifteenMinuteData(null);
                will(returnValue(30));
                allowing(mockedApplicationConfigManager).getTimeDelayDayData(null);
                will(returnValue(240));
                allowing(mockedApplicationConfigManager).getOneMinuteAggregation();
                will(returnValue(true));
            }
        });

    }

    //////////////////////////////   GRID TEST CASES   ///////////////////////////////////////////////////////////////

    /**
     * Raw tables are used for TimeRanges less than or equal to 5 minutes.
     */
    @Test
    public void getEventDataSourceTypeForGridRaw() {
        EventDataSourceType expectedType = EventDataSourceType.RAW;
        result = objUnderTest.getEventDataSourceType(0);
        assertSame("Zero Minutes", expectedType, result);

        expectedType = EventDataSourceType.RAW;
        result = objUnderTest.getEventDataSourceType(ONE_MINUTE_VALUE);
        assertSame("One Minute", expectedType, result);

        expectedType = EventDataSourceType.RAW;
        result = objUnderTest.getEventDataSourceType(FIVE_MINUTE_VALUE);
        assertSame("Five Minutes", expectedType, result);

        expectedType = EventDataSourceType.RAW;
        result = objUnderTest.getEventDataSourceType(GREATER_THAN_5_MINUTES);
        assertNotSame("Six Minutes, expected to be different", expectedType, result);

    }

    /**
     * One Minute Aggragation tables are used for TimeRanges greater 5 minutes and less than 2 hours.
     */
    @Test
    public void getEventDataSourceType() {
        final EventDataSourceType expectedType = EventDataSourceType.AGGREGATED_1MIN;

        result = objUnderTest.getEventDataSourceType(GREATER_THAN_5_MINUTES);
        assertSame("Six Minutes", expectedType, result);

        result = objUnderTest.getEventDataSourceType(MINUTES_IN_2_HOURS);
        assertSame("Less than 2 Hours", expectedType, result);
    }

    /**
     * Boundary testing, test above and below the boundary, ensuring we do not get the correct EventDataSourceType
     * These are here to ensure that if anyone changes the boundaries later, they will be caught
     */
    @Test
    public void getEventDataSourceTypeForGridWithOneMinAggregatedBoundaryTesting() {
        final EventDataSourceType expectedType = EventDataSourceType.AGGREGATED_1MIN;

        result = objUnderTest.getEventDataSourceType(FIVE_MINUTE_VALUE);
        assertNotSame("5 Minutes, expected to be different", expectedType, result);

        result = objUnderTest.getEventDataSourceType(GREATER_THAN_2_HOURS);
        assertNotSame("2 Hours, expected to be different", expectedType, result);

    }

    /**
     * Fifteen Minute Aggragation tables are used for TimeRanges greater than or equal to 2 hours and less than 1 week.
     */
    @Test
    public void getEventDataSourceTypeForGridWith15MinAggregated() {
        final EventDataSourceType expectedType = EventDataSourceType.AGGREGATED_15MIN;
        result = objUnderTest.getEventDataSourceType(GREATER_THAN_2_HOURS);
        assertSame("Two Hours", expectedType, result);

        result = objUnderTest.getEventDataSourceType(LESS_THAN_1_WEEK);
        assertSame("Less than one week", expectedType, result);
    }

    /**
     * Boundary testing, test above and below the boundary, ensuring we do not get the correct EventDataSourceType
     * These are here to ensure that if anyone changes the boundaries later, they will be caught
     */
    @Test
    public void getEventDataSourceTypeForGridWith15MinAggregatedBoundaryTesting() {
        final EventDataSourceType expectedType = EventDataSourceType.AGGREGATED_15MIN;

        result = objUnderTest.getEventDataSourceType(MINUTES_IN_2_HOURS);
        assertNotSame("One week, expected to be different", expectedType, result);

        result = objUnderTest.getEventDataSourceType(GREATER_THAN_1_WEEK);
        assertNotSame("One week, expected to be different", expectedType, result);

    }

    /**
     * Day aggragation tables are used for TimeRanges that are not covered by the others, so anything greater than or
     * equal to 1 week, should use day aggregation tables
     */
    @Test
    public void getEventDataSourceTypeForGridWithDayAggregated() {
        final EventDataSourceType expectedType = EventDataSourceType.AGGREGATED_DAY;

        result = objUnderTest.getEventDataSourceType(LESS_THAN_1_WEEK);
        assertNotSame("Less than one week, expected to be different", expectedType, result);

        result = objUnderTest.getEventDataSourceType(GREATER_THAN_1_WEEK);
        assertSame("One Week", expectedType, result);
    }

    /**
     * Boundary testing, test above and below the boundary, ensuring we do not get the correct EventDataSourceType
     * These are here to ensure that if anyone changes the boundaries later, they will be caught
     */
    @Test
    public void getEventDataSourceTypeForGridWithDayAggregatedBoundaryTesting() {
        final EventDataSourceType expectedType = EventDataSourceType.AGGREGATED_DAY;

        result = objUnderTest.getEventDataSourceType(LESS_THAN_1_WEEK);
        assertNotSame("Less than one week, expected to be different", expectedType, result);
    }

    /////////////////////////////////   Formatted DateTime Tests   ////////////////////////////////////////////////

    /*
     * For the FormattedDateTime tests, we can either specify an exact date time combination or just a time combination
     * This corresponds to the options in the EventsUI gui, where you can pick 5 minuts, 30 mins etc or specify andf
     * exact date time range.
     */
    @Test
    public void getFormattedDateTimeRangeForSpecificRangeWithTZOffset() {

        final String timeFrom = "1500";

        final String timeTo = "1600";

        final String dateFrom = "14112009";

        final String dateTo = "17112009";

        final String expectedStartTime = "2009-11-14 14:00";
        final String expectedEndTime = "2009-11-17 15:00";

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_FROM_QUERY_PARAM, timeFrom);
        requestParameters.add(TIME_TO_QUERY_PARAM, timeTo);
        requestParameters.add(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.add(DATE_TO_QUERY_PARAM, dateTo);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(actualDateTimeRange.getStartDateTime(), is(expectedStartTime));

        assertThat(actualDateTimeRange.getEndDateTime(), is(expectedEndTime));

    }

    @Test
    public void getFormattedDateTimeRangeForSpecificRangeWithAnotherTZOffset() {

        final String timeFrom = "1300";

        final String timeTo = "1400";

        final String dateFrom = "14112009";

        final String dateTo = "17112009";

        final String expectedStartTime = "2009-11-14 14:00";
        final String expectedEndTime = "2009-11-17 15:00";

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_FROM_QUERY_PARAM, timeFrom);
        requestParameters.add(TIME_TO_QUERY_PARAM, timeTo);
        requestParameters.add(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.add(DATE_TO_QUERY_PARAM, dateTo);
        requestParameters.add(TZ_OFFSET, "-0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(actualDateTimeRange.getStartDateTime(), is(expectedStartTime));

        assertThat(actualDateTimeRange.getEndDateTime(), is(expectedEndTime));

    }

    @Test
    public void getFormattedDateTimeRangeForSpecificRangeWithoutTZOffset() {

        final String timeFrom = "1400";

        final String timeTo = "1500";

        final String dateFrom = "14112009";

        final String dateTo = "17112009";

        final String expectedStartTime = "2009-11-14 14:00";
        final String expectedEndTime = "2009-11-17 15:00";

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_FROM_QUERY_PARAM, timeFrom);
        requestParameters.add(TIME_TO_QUERY_PARAM, timeTo);
        requestParameters.add(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.add(DATE_TO_QUERY_PARAM, dateTo);
        requestParameters.add(TZ_OFFSET, "+0000");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(actualDateTimeRange.getStartDateTime(), is(expectedStartTime));

        assertThat(actualDateTimeRange.getEndDateTime(), is(expectedEndTime));

    }

    @Test
    public void translateDateTimeParameters() {

        final String timeFrom = "1400";

        final String timeTo = "1500";

        final String dateFrom = "14112009";

        final String dateTo = "17112009";

        final String expectedStartTime = "2009-11-14 14:00";
        final String expectedEndTime = "2009-11-17 15:00";

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_FROM_QUERY_PARAM, timeFrom);
        requestParameters.add(TIME_TO_QUERY_PARAM, timeTo);
        requestParameters.add(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.add(DATE_TO_QUERY_PARAM, dateTo);
        requestParameters.add(TZ_OFFSET, "+0000");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.translateDateTimeParameters(requestParameters,
                null);

        assertThat(actualDateTimeRange.getStartDateTime(), is(expectedStartTime));

        assertThat(actualDateTimeRange.getEndDateTime(), is(expectedEndTime));

    }

    /**
     * Not sure how likely this scenario is, but on the off chance that we receive requestParameters with no
     * Timezopne param specified, we should default to +0000
     * TODO At the moment, this test generates a NullPointerException. Need to fix source code to cater for this case.
     * When we do that, we can re-enable the test
     *
     * @Test
     */
    public void getFormattedDateTimeRangeForSpecificRangeNoTZOffset() {

        final String timeFrom = "1400";

        final String timeTo = "1500";

        final String dateFrom = "14112009";

        final String dateTo = "17112009";

        final String expectedStartTime = "2009-11-14 14:00";
        final String expectedEndTime = "2009-11-17 15:00";

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_FROM_QUERY_PARAM, timeFrom);
        requestParameters.add(TIME_TO_QUERY_PARAM, timeTo);
        requestParameters.add(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.add(DATE_TO_QUERY_PARAM, dateTo);

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(actualDateTimeRange.getStartDateTime(), is(expectedStartTime));

        assertThat(actualDateTimeRange.getEndDateTime(), is(expectedEndTime));

    }

    @Test
    public void getFormattedDateTimeRangeForTimePeriodTZOffset() {

        final String time = "30";
        final long currentTime = System.currentTimeMillis();
        final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
        final Date now = new Date(currentTime - offSet);

        final String formattedStartDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, time), "+0100", DATE_TIME_FORMAT),
                "+0100", DATE_TIME_FORMAT);
        final String formattedEndDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, "0"), "+0100", DATE_TIME_FORMAT),
                "+0100", DATE_TIME_FORMAT);
        final FormattedDateTimeRange expectedDateTimeRange = createTimeRange(formattedStartDateTime,
                formattedEndDateTime);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        System.out.println("Actual Start Time" + actualDateTimeRange.getStartDateTime());
        System.out.println("Actual End Time" + actualDateTimeRange.getEndDateTime());
        System.out.println("Expected Start Time" + expectedDateTimeRange.getStartDateTime());
        System.out.println("Expected End Time" + expectedDateTimeRange.getEndDateTime());
        assertEquals(actualDateTimeRange.getEndDateTime(), expectedDateTimeRange.getEndDateTime());
    }

    @Test
    public void getFormattedDateTimeRangeFor2HrTimePeriodTZOffset() {

        final String time = String.valueOf(MINUTES_IN_2_HOURS);
        final long currentTime = System.currentTimeMillis();
        final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
        final Date now = new Date(currentTime - offSet);

        final String formattedStartDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, time), "+0100", DATE_TIME_FORMAT),
                "+0100", DATE_TIME_FORMAT);
        final String formattedEndDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, "0"), "+0100", DATE_TIME_FORMAT),
                "+0100", DATE_TIME_FORMAT);
        final FormattedDateTimeRange expectedDateTimeRange = createTimeRange(formattedStartDateTime,
                formattedEndDateTime);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        System.out.println("Actual Start Time" + actualDateTimeRange.getStartDateTime());
        System.out.println("Actual End Time" + actualDateTimeRange.getEndDateTime());
        System.out.println("Expected Start Time" + expectedDateTimeRange.getStartDateTime());
        System.out.println("Expected End Time" + expectedDateTimeRange.getEndDateTime());
        assertEquals(actualDateTimeRange.getEndDateTime(), expectedDateTimeRange.getEndDateTime());
    }

    @Test
    public void getFormattedDateTimeRangeFor1WeekTimePeriodTZOffset() {

        final String time = String.valueOf(MINUTES_IN_A_WEEK);
        final long currentTime = System.currentTimeMillis();
        final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
        final Date now = new Date(currentTime - offSet);

        final String formattedStartDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, time), "+0100", DATE_TIME_FORMAT),
                "+0100", DATE_TIME_FORMAT);
        final String formattedEndDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, "0"), "+0100", DATE_TIME_FORMAT),
                "+0100", DATE_TIME_FORMAT);
        final FormattedDateTimeRange expectedDateTimeRange = createTimeRange(formattedStartDateTime,
                formattedEndDateTime);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertEquals(actualDateTimeRange.getEndDateTime(), expectedDateTimeRange.getEndDateTime());
        assertEquals(actualDateTimeRange.getStartDateTime(), expectedDateTimeRange.getStartDateTime());
    }

    @Test
    public void getEventDataSourceTypeForGridForTimeIntervalExpectingTR1() {

        final String time = String.valueOf(FIVE_MINUTE_VALUE);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(objUnderTest.getEventDataSourceType(actualDateTimeRange), is(EventDataSourceType.RAW));
    }

    @Test
    public void getEventDataSourceTypeForGridExpectingTR2() {

        final String time = String.valueOf(LESS_THAN_2_HOURS);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(objUnderTest.getEventDataSourceType(actualDateTimeRange), is(EventDataSourceType.AGGREGATED_1MIN));
    }

    @Test
    public void getEventDataSourceTypeForGridExpectingTR3() {

        final String time = String.valueOf(GREATER_THAN_2_HOURS);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(objUnderTest.getEventDataSourceType(actualDateTimeRange), is(EventDataSourceType.AGGREGATED_15MIN));
    }

    @Test
    public void getEventDataSourceTypeForGridExpectingTR4() {

        final String time = String.valueOf(GREATER_THAN_1_WEEK);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(objUnderTest.getEventDataSourceType(actualDateTimeRange), is(EventDataSourceType.AGGREGATED_DAY));
    }

    @Test
    public void getEventDataSourceTypeForGridForTimeIntervalWithout1MinAggExpectingTR1() {

        final String time = String.valueOf(FIVE_MINUTE_VALUE);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(objUnderTest.getEventDataSourceType(actualDateTimeRange), is(EventDataSourceType.RAW));
    }

    @Test
    public void getEventDataSourceTypeForGridForTimeIntervalWithout1MinAggExpectingTR3() {

        final String time = String.valueOf(GREATER_THAN_2_HOURS);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(objUnderTest.getEventDataSourceType(actualDateTimeRange), is(EventDataSourceType.AGGREGATED_15MIN));
    }

    @Test
    public void getEventDataSourceTypeForGridForTimeIntervalWithout1MinAggExpectingTR4() {

        final String time = String.valueOf(GREATER_THAN_2_WEEKS);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getFormattedDateTimeRange(requestParameters,
                null);

        assertThat(objUnderTest.getEventDataSourceType(actualDateTimeRange), is(EventDataSourceType.AGGREGATED_DAY));
    }

    @Test
    public void getAndCheckFormattedDateTimeRangeForDailyAggregationNoTimeParam() {

        final String timeFrom = "1500";

        final String timeTo = "1600";

        final String dateFrom = "14112009";

        final String dateTo = "17112009";

        final String expectedStartTime = "2009-11-14 14:00";
        final String expectedEndTime = "2009-11-17 15:00";

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_FROM_QUERY_PARAM, timeFrom);
        requestParameters.add(TIME_TO_QUERY_PARAM, timeTo);
        requestParameters.add(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.add(DATE_TO_QUERY_PARAM, dateTo);
        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest
                .getAndCheckFormattedDateTimeRangeForDailyAggregation(requestParameters, null);

        assertThat(actualDateTimeRange.getStartDateTime(), is(expectedStartTime));

        assertThat(actualDateTimeRange.getEndDateTime(), is(expectedEndTime));
    }

    @Test
    public void getAndCheckFormattedDateTimeRangeForDailyAggregationWithTimeParam() {

        final String time = String.valueOf(GREATER_THAN_2_WEEKS);
        final long currentTime = System.currentTimeMillis();
        final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
        final Date now = new Date(currentTime - offSet);

        final String formattedStartDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, time), "+0100", DATE_TIME_FORMAT),
                "+0100", DATE_TIME_FORMAT);
        final String formattedEndDateTime = DateTimeUtils.getUTCTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, "0"), "+0100", DATE_TIME_FORMAT),
                "+0100", DATE_TIME_FORMAT);
        final FormattedDateTimeRange expectedDateTimeRange = createTimeRange(formattedStartDateTime,
                formattedEndDateTime);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);

        requestParameters.add(TZ_OFFSET, "+0000");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest
                .getAndCheckFormattedDateTimeRangeForDailyAggregation(requestParameters, null);

        assertEquals(actualDateTimeRange.getEndDateTime(), expectedDateTimeRange.getEndDateTime());
        assertEquals(actualDateTimeRange.getStartDateTime(), expectedDateTimeRange.getStartDateTime());
    }

    @Test
    public void translateDateTimeParametersWithTimeParam() {

        final String time = String.valueOf(MINUTES_IN_2_WEEKS);
        final long currentTime = System.currentTimeMillis();
        final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
        final Date now = new Date(currentTime - offSet);

        final String formattedStartDateTime = DateTimeUtils.getLocalTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, time), "+0100", DATE_TIME_FORMAT),
                "+0100", DATE_TIME_FORMAT);
        final String formattedEndDateTime = DateTimeUtils.getLocalTime(
                DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, "0"), "+0100", DATE_TIME_FORMAT),
                "+0100", DATE_TIME_FORMAT);

        final FormattedDateTimeRange expectedDateTimeRange = createTimeRange(formattedStartDateTime,
                formattedEndDateTime);

        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

        requestParameters.add(TIME_QUERY_PARAM, time);

        requestParameters.add(TZ_OFFSET, "+0100");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.translateDateTimeParameters(requestParameters,
                null);

        assertEquals(actualDateTimeRange.getEndDateTime(), expectedDateTimeRange.getEndDateTime());
        assertEquals(actualDateTimeRange.getStartDateTime(), expectedDateTimeRange.getStartDateTime());
    }

    @Test
    public void getDateTimeRangeOfChartAndSummaryGridTimeRange() {

        final FormattedDateTimeRange expectedDateTimeRange = createTimeRange("2011-06-13 00:00", "2011-06-29 00:00");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getDateTimeRange(expectedDateTimeRange,
                EventDataSourceType.AGGREGATED_DAY);

        assertEquals(actualDateTimeRange.getEndDateTime(), expectedDateTimeRange.getEndDateTime());
        assertEquals(actualDateTimeRange.getStartDateTime(), expectedDateTimeRange.getStartDateTime());
    }

    @Test
    public void getDateTimeRangeOfChartAndSummaryGrid15MinAgg() {

        final FormattedDateTimeRange expectedDateTimeRange = createTimeRange("2011-06-13 00:00", "2011-06-29 00:00");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getDateTimeRange(expectedDateTimeRange,
                EventDataSourceType.AGGREGATED_15MIN);

        assertEquals(actualDateTimeRange.getEndDateTime(), expectedDateTimeRange.getEndDateTime());
        assertEquals(actualDateTimeRange.getStartDateTime(), expectedDateTimeRange.getStartDateTime());
    }

    @Test
    public void testIsDayAggregation() {
        FormattedDateTimeRange expectedDateTimeRange = createTimeRange("2011-06-21 00:00", "2011-06-29 00:00");
        assertTrue(objUnderTest.isDayAggregation(expectedDateTimeRange));

        expectedDateTimeRange = createTimeRange("2011-06-13 00:00", "2011-06-29 00:00");
        assertTrue(objUnderTest.isDayAggregation(expectedDateTimeRange));
    }

    @Test
    public void getDateTimeRangeOfChartAndSummaryGrid1MinAgg() {

        final FormattedDateTimeRange expectedDateTimeRange = createTimeRange("2011-06-14 00:00", "2011-06-30 00:00");

        final FormattedDateTimeRange actualDateTimeRange = objUnderTest.getDateTimeRange(expectedDateTimeRange,
                EventDataSourceType.AGGREGATED_1MIN);

        assertEquals(actualDateTimeRange.getEndDateTime(), expectedDateTimeRange.getEndDateTime());
        assertEquals(actualDateTimeRange.getStartDateTime(), expectedDateTimeRange.getStartDateTime());
    }

    private FormattedDateTimeRange createTimeRange(final String startDateTime, final String endDateTime) {
        return DateTimeRange.getFormattedDateTimeRange(startDateTime, endDateTime,
                ApplicationConfigManager.ENIQ_EVENTS_DT_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_DT_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES,
                ApplicationConfigManager.ENIQ_EVENTS_DT_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES);

    }

}
