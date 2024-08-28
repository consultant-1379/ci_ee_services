/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.logging.performance.ServicesPerformanceThreadLocalHolder.*;
import static com.ericsson.eniq.events.server.utils.DateTimeUtils.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.WebApplicationException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.utils.datetime.DataTieredDateTimeRange;

/**
 * The Class DateTimeRange.
 *
 * @author ehaoswa
 * @since 2010
 */
public final class DateTimeRange {

    private DateTimeRange() {
    }

    private static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

    private static final SimpleDateFormat dbUTCDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
            Locale.getDefault());
    static {
        dbUTCDateTimeFormat.setTimeZone(UTC_TIME_ZONE);
    }

    /**
     * This class is used if you do need offset to be applied.
     *
     * @author echchik
     */
    private static class FormattedTimeRange implements FormattedDateTimeRange {
        private final String startDateTime;

        private final String endDateTime;

        private final long rangeInMinutes;

        private final Date startTimeInDate;

        private final Date endTimeInDate;

        public FormattedTimeRange(final String startTime, final String endTime, final String timestampFormat)
                throws ParseException {
            this.startDateTime = startTime;
            this.endDateTime = endTime;
            final SimpleDateFormat sdf = new SimpleDateFormat(timestampFormat);
            startTimeInDate = sdf.parse(startTime);
            endTimeInDate = sdf.parse(endTime);
            this.rangeInMinutes = (endTimeInDate.getTime() - startTimeInDate.getTime()) / 60000;
            if (this.rangeInMinutes < 0) {
                throw new IllegalArgumentException("Start time [" + this.startDateTime + "] after end time ["
                        + this.endDateTime + "]");
            }
        }

        @Override
        public String getStartDateTime() {
            return startDateTime;
        }

        @Override
        public String getEndDateTime() {
            return endDateTime;
        }

        @Override
        public Date getStartDate() {
            return startTimeInDate;
        }

        @Override
        public Date getEndDate() {
            return endTimeInDate;
        }

        @Override
        public int getMinutesOfStartDateTime() {
            return 0;
        }

        @Override
        public int getMinutesOfEndDateTime() {
            return 0;
        }

        @Override
        public long getRangeInMinutes() {
            return rangeInMinutes;
        }

        @Override
        public Date getCurrentDateTime() {
            // TODO Auto-generated method stub
            return null;
        }
    }

    /**
     * Adds a computed negative offset to the time range to account for
     * the data availability latency if applicable.
     * The offset is only applied if the current time - end time is <=
     * the applicable delay. This accounts for defined time range queries
     * where start and end are specified as opposed to the 'now - t' type 
     * queries.
     * 
     * <p/>
     * Data delay offsets are computed for queries based on the time range of
     * the query, the data collection interval (ROP) which can be 1, 5 or 15 minutes
     * The latter is configured on the SGSN node and will be the same for a
     * particular deployment of ENIQ events i.e., all SGSNs must be on the same
     * ROP for a particular deployment.
     * <p/>
     * Target intervals fall into the following classes:
     * 1. <= 2 hours (alignment interval = 1min)
     * 2.  < 1 week  (alignment interval = 15min)
     * 3. >= 1 week  (alignment interval = 1day)
     * <p/>
     * The general formula for start time and end time applied to the query is:
     * <p/>
     * end_time    = (now - delay) rounded down to the nearest calendar 'alignment interval' above.
     * start_time  = end_time - interval
     * <p/>
     * Delay Calculation
     * -------------------------
     * Class 1. <= 2hours
     * <p/>
     * delay =  1 minute + 1.5*ROP  (rounded up to nearest minute)
     * <p/>
     * Class 2. < 1 week
     * <p/>
     * delay =  10 minutes
     * <p/>
     * Class 1. >= 1 week
     * <p/>
     * delay =  3 hours (daily aggregations data for previous day is usually available around 2am)
     * <p/>
     * <p/>
     * Note: classes 2 & 3 are ROP independent.
     * <p/>
     * Worked example:
     * <p/>
     * Case 1. 1 minute ROP, time range = last 30 minutes (i.e., Class 1.)
     * <p/>
     * now = 2010-11-12 11:24:06
     * <p/>
     * delay = 1 + 1.5*1  --> 3 minutes
     * <p/>
     * query end_time =   (now - 3 minutes) rounded to last minute
     * =>  2010-11-12 11:21:00
     * <p/>
     * query start_time = 2010-11-12 10:51:00
     * <p/>
     * Case 2. 15 minute ROP, time range = last 6 hours (i.e., Class 2)
     * <p/>
     * now = 2010-11-12 11:24:06   (start time = 2010-11-12 05:24:06
     * <p/>
     * delay = 10 minutes
     * <p/>
     * query end_time =   (now - 10 minutes) rounded to last quarter hour
     * =>  2010-11-12 11:00:00
     * <p/>
     * query start_time = 2010-11-12 05:00:00
     * <p/>
     * <p/>
     * Case 3. 5 minute ROP, time range = last 8 days (i.e., Class 3)
     * <p/>
     * now = 2010-11-12 02:24:06   (start time = 2010-11-04 02:24:06)
     * <p/>
     * delay = 3 hours
     * <p/>
     * query end_time =   (now - 3 hrs) rounded to last day
     * =>  (2010-11-11 11:24:06) rounded to last day
     * =>  2010-11-11 00:00:00  midnight of the 10th
     * <p/>
     * query start_time = 2010-11-03 00:00:00
     * <p/>
     * Note: Classes 2 & 3 are ROP independent
     * <p/>
     * Case 4. 15 minute ROP, time range = last 8 days (i.e., Class 3)
     * <p/>
     * now = 2010-11-12 04:24:06   (start time = 2010-11-04 04:24:06)
     * <p/>
     * delay = 3 hours
     * <p/>
     * query end_time =   (now - 3 hrs) rounded to last day
     * =>  (2010-11-12 01:24:06) rounded to last day
     * =>  2010-11-12 00:00:00  midnight of the 11th
     * <p/>
     * query start_time = 2010-11-04 00:00:00
     */
    private static class FormattedTimeRangeWithDataDelayOffset implements FormattedDateTimeRange {
        private final Date startDateTime;

        private final Date endDateTime;

        private final Date currentDateTime;

        private long rangeInMinutes;

        private final SimpleDateFormat sdf;

        private final int timeDelay1minData;

        private final int timeDelay15minData;

        private final int timeDelayDailyData;

        private long minutesFromCurrentTime; //NOPMD (eemecoy 15/11/2010, PMD is getting confused)

        public FormattedTimeRangeWithDataDelayOffset(final String startTime, final String endTime,
                final String timestampFormat, final int timeDelay1minData, final int timeDelay15minData,
                final int timeDelayDailyData, final Date currentTime) throws ParseException {
            this.timeDelay1minData = timeDelay1minData;
            this.timeDelay15minData = timeDelay15minData;
            this.timeDelayDailyData = timeDelayDailyData;
            this.sdf = new SimpleDateFormat(timestampFormat);
            this.currentDateTime = currentTime;
            final Date startDateTime1 = sdf.parse(startTime);
            final Date endDateTime1 = sdf.parse(endTime);
            // delta from current time. if less than zero it means the end time is 
            // in the future so just set to zero as latency rules will then apply.
            this.minutesFromCurrentTime = (currentTime.getTime() - endDateTime1.getTime()) / 60000;
            if (this.minutesFromCurrentTime < 0) {
                this.minutesFromCurrentTime = 0;
            }

            this.rangeInMinutes = (endDateTime1.getTime() - startDateTime1.getTime()) / 60000;
            if (this.rangeInMinutes < 0) {
                throw new IllegalArgumentException("Start time [" + startDateTime1 + "] after end time ["
                        + endDateTime1 + "]");
            }

            if (applyTimeDelayForOneMinuteData()) {
                logTimeDelayApplied(timeDelay1minData, "last minute");
                this.endDateTime = roundToLastMinute(DateUtils.addMinutes(endDateTime1, -(this.timeDelay1minData)));
            } else if (applyTimeDelayForFifteenMinuteData()) {
                logTimeDelayApplied(timeDelay15minData, "last quarter hour");
                this.endDateTime = roundToLastQuarterHour(DateUtils
                        .addMinutes(endDateTime1, -(this.timeDelay15minData)));
            } else if (applyTimeDelayForDayData()) {
                logTimeDelayApplied(timeDelayDailyData, "last day");
                rangeInMinutes = (roundToLastDay(endDateTime1).getTime() - roundToLastDay(startDateTime1).getTime()) / 60000;
                if (this.minutesFromCurrentTime < this.timeDelayDailyData) {

                    //only apply the time delay for searches from now: dates in far past should have no delay as 
                    //aggregations will already have occurred
                    final int timeDelayToApply = ((int) this.minutesFromCurrentTime) - this.timeDelayDailyData;
                    this.endDateTime = roundToLastDay(DateUtils.addMinutes(endDateTime1, timeDelayToApply));
                } else {
                    this.endDateTime = roundToLastDay(endDateTime1);
                }
            } else {
                this.endDateTime = endDateTime1;
            }

            this.startDateTime = DateUtils.addMinutes(this.endDateTime, -((int) this.rangeInMinutes));

        }

        private void logTimeDelayApplied(final int timeDelayApplied, final String roundingMechanism) {
            ServicesLogger.detailed(this.getClass().getName(), "FormattedTimeRangeWithDataDelayOffset",
                    "Applying time delay of " + timeDelayApplied + ", will round to " + roundingMechanism
                            + " after this");
        }

        @Override
        public String getStartDateTime() {
            return sdf.format(getStartDate());
        }

        @Override
        public String getEndDateTime() {
            return sdf.format(getEndDate());
        }

        @Override
        public Date getStartDate() {
            return this.startDateTime;
        }

        @Override
        public Date getEndDate() {
            return this.endDateTime;
        }

        @Override
        public int getMinutesOfStartDateTime() {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(getStartDate());
            return cal.get(Calendar.MINUTE);
        }

        @Override
        public int getMinutesOfEndDateTime() {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(getEndDate());
            return cal.get(Calendar.MINUTE);
        }

        @Override
        public long getRangeInMinutes() {
            setInterval(rangeInMinutes);
            return this.rangeInMinutes;
        }

        private boolean applyTimeDelayForOneMinuteData() {
            final boolean isMinutesFromCurrentTimeLessThanTimeDelay = isMinutesFromCurrentTimeLessThanTimeDelay(this.timeDelay1minData);
            final boolean oneMinuteAggregatedRangeForGrid = oneMinuteAggregationRange(this.rangeInMinutes);
            final boolean shouldApplyTimeDelayForOneMinuteData = isMinutesFromCurrentTimeLessThanTimeDelay
                    && oneMinuteAggregatedRangeForGrid;
            logDecision("applyTimeDelayForOneMinuteData", isMinutesFromCurrentTimeLessThanTimeDelay,
                    oneMinuteAggregatedRangeForGrid, shouldApplyTimeDelayForOneMinuteData);
            return shouldApplyTimeDelayForOneMinuteData;
        }

        private boolean applyTimeDelayForFifteenMinuteData() {
            final boolean minutesFromCurrentTimeLessThanTimeDelay = isMinutesFromCurrentTimeLessThanTimeDelay(timeDelay15minData);
            final boolean fifteenMinuteAggregatedRangeForGrid = fifteenMinuteAggregatedRangeForGrid(this.rangeInMinutes);
            final boolean shouldApplyTimeDelayForRange = minutesFromCurrentTimeLessThanTimeDelay
                    && fifteenMinuteAggregatedRangeForGrid;
            logDecision("applyTimeDelayForFifteenMinuteData", minutesFromCurrentTimeLessThanTimeDelay,
                    fifteenMinuteAggregatedRangeForGrid, shouldApplyTimeDelayForRange);
            return shouldApplyTimeDelayForRange;
        }

        private boolean applyTimeDelayForDayData() {
            final boolean rawEventRangeDoesntApply = !rawEventRange(this.rangeInMinutes);
            final boolean oneMinuteAggregatedRangeDoesntApply = !oneMinuteAggregationRange(this.rangeInMinutes);
            final boolean fifteenMinuteAggregatedRangeDoesntApply = !fifteenMinuteAggregatedRangeForGrid(this.rangeInMinutes);
            final boolean applyTimeDelayForDayData = rawEventRangeDoesntApply && oneMinuteAggregatedRangeDoesntApply
                    && fifteenMinuteAggregatedRangeDoesntApply;
            ServicesLogger.detailed(this.getClass().getName(), "applyTimeDelayForDayData",
                    "Applying time delay for day data " + applyTimeDelayForDayData
                            + " (due to rawEventRangeDoesntApply: " + rawEventRangeDoesntApply
                            + ", oneMinuteAggregatedRangeDoesntApply:" + oneMinuteAggregatedRangeDoesntApply
                            + ", fifteenMinuteAggregatedRangeDoesntApply" + fifteenMinuteAggregatedRangeDoesntApply);
            return applyTimeDelayForDayData;

        }

        private boolean isMinutesFromCurrentTimeLessThanTimeDelay(final int timeDelay) {
            final boolean minutesFromCurrentTimeLessThanTimeDelay = this.minutesFromCurrentTime < timeDelay;
            return minutesFromCurrentTimeLessThanTimeDelay;
        }

        private void logDecision(final String methodName, final boolean isMinutesFromCurrentTimeLessThanTimeDelay,
                final boolean isTimeRangeApplicable, final boolean shouldApplyTimeDelayForRange) {
            ServicesLogger.detailed(this.getClass().getName(), methodName, "Applying time delay: "
                    + shouldApplyTimeDelayForRange
                    + " (due to isMinutesFromCurrentTimeLessThanTimeDelay evaluating as "
                    + isMinutesFromCurrentTimeLessThanTimeDelay + " AND isTimeRangeApplicable evaluating as "
                    + isTimeRangeApplicable);
        }

        @Override
        public Date getCurrentDateTime() {
            return currentDateTime;
        }
    }

    public static FormattedDateTimeRange getFormattedDateTimeRange(final String startDateTime,
            final String endDateTime, final int timeDelayForOneMinuteData, final int timeDelayForFifteenMinuteData,
            final int timeDelayForDayData) throws WebApplicationException {

        final long currentTime = System.currentTimeMillis();
        final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
        final Date now = new Date(currentTime - offSet);

        return getFormattedDateTimeRange(startDateTime, endDateTime, DATE_TIME_FORMAT, timeDelayForOneMinuteData,
                timeDelayForFifteenMinuteData, timeDelayForDayData, now);
    }

    public static FormattedDateTimeRange getFormattedDateTimeRangeWithOutOffset(final String startDateTime,
            final String endDateTime) throws WebApplicationException {
        try {
            return new FormattedTimeRange(startDateTime, endDateTime, DATE_TIME_FORMAT);
        } catch (final ParseException e) {
            throw new WebApplicationException(e);
        }
    }

    public static FormattedDateTimeRange getFormattedDateTimeRange(final String startDateTime,
            final String endDateTime, final String timestampFormat, final int timeDelayForOneMinuteData,
            final int timeDelayForFifteenMinuteData, final int timeDelayForDayData, final Date currentTime)
            throws WebApplicationException {
        try {
            return new FormattedTimeRangeWithDataDelayOffset(startDateTime, endDateTime, timestampFormat,
                    timeDelayForOneMinuteData, timeDelayForFifteenMinuteData, timeDelayForDayData, currentTime);
        } catch (final ParseException e) {
            throw new WebApplicationException(e);
        }
    }

    /**
     * Formatted date time for 15 minute aggregated data.
     * There are 4 conditions: <15; >=15 and <30; >=30 and <45; >=45
     * e.g. 4:13(before) ----> 4:00(after)
     *      4:19(before) ----> 4:15(after)
     *      4:38(before) ----> 4:30(after)
     *      4:55(before) ----> 4:45(after)
     *       
     * @param dateTime - dateTime need to be formatted
     * @param minutes  - input dateTime's minute value
     * @return formatted dateTime
     */
    public static String formattedDateTimeAgainst15MinsTable(final String dateTime, final int minutes) {

        if (minutes > 59 || minutes < 0) {
            throw new IllegalArgumentException("Invalid minutes" + minutes + " Minutes should be in range 0-59");
        }

        return DateTimeUtils.addMinutes(dateTime, getOffsetInMinutesFor15MinuteAggregatedData(minutes));
    }

    /**
     * There are 4 conditions: <15; >=15 and <30; >=30 and <45; >=45
     * <p/>
     * e.g. 4:13(before) ----> 4:00(after)
     *      4:19(before) ----> 4:15(after)
     *      4:38(before) ----> 4:30(after)
     *      4:55(before) ----> 4:45(after)
     *      
     * @param minutes
     * @return
     */
    private static int getOffsetInMinutesFor15MinuteAggregatedData(final int minutes) {
        int offset = 0;

        if (minutes < 15) {
            offset = -minutes;
        } else if (minutes >= 15 && minutes < 30) {
            offset = -(minutes - 15);
        } else if (minutes >= 30 && minutes < 45) {
            offset = -(minutes - 30);
        } else {
            offset = -(minutes - 45);
        }
        return offset;
    }

    /**
     * Formatted date time against day table.
     * No matter the minutes from the input dateTime, it will become 00 after formattion.
     * e.g.  4:46(before) ----> 4:00(after)
     * 
     * @param dateTime - dateTime need to be formatted
     * @param minutes  - input dateTime's minute value
     * @return formatted dateTime
     * @throws WebApplicationException - parse exception
     */
    public static String formattedDateTimeAgainstDayTable(final String dateTime, final int minutes)
            throws WebApplicationException {

        final Date oldDateTime = DateTimeUtils.parseDateTimeFormat(dateTime);
        final String formattedDateTime = DateTimeUtils.getDateTimeFormat().format(
                DateUtils.addMinutes(oldDateTime, -minutes));

        return formattedDateTime;
    }

    /**
     * Gets the formatted dateTime range for offset in days.
     * This method will also convert the local time to UTC time zone.
     * The dataTimeFrom and dataTimeTo parameters are returned in the FormattedDateTimeRange
     * 
     * @param tzOffset
     * @param offsetIndays
     */

    public static FormattedDateTimeRange getFormattedTimeRangeInDays(final String tzOffset, final String offsetIndays) {
        final long currentTime = System.currentTimeMillis();
        final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
        final Date now = new Date(currentTime - offSet);
        final String formattedStartDateTime = DateTimeUtils.getUTCTime(DateTimeUtils.getLocalTime(
                DateTimeUtils.formattedDateTimeInDays(now, offsetIndays), tzOffset, DATE_TIME_FORMAT), tzOffset,
                DATE_TIME_FORMAT);
        final String formattedEndDateTime = DateTimeUtils
                .getUTCTime(DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTimeInDays(now, "0"), tzOffset,
                        DATE_TIME_FORMAT), tzOffset, DATE_TIME_FORMAT);
        return DateTimeRange.getFormattedDateTimeRange(formattedStartDateTime, formattedEndDateTime, 0, 0, 0);
    }

    /**
     * Gets the formatted dateTime range.
     * This will process the time range parameters from RESTful service to generate the formatted dataTime range.
     * This method will also convert the local time to UTC time zone.
     * If the dataTimeFrom and dataTimeTo parameters are present, then these are returned in the FormattedDateTimeRange
     * If these aren't present, this method first checks for the presence of the time parameter
     * If the time parameter isn't present, the method finally checks for the timeFrom, timeTo, dateFrom and dateTo
     * parameters
     * <p/>
     * This order is required for CSV requests - when a CSV export is requested by the UI for data already presented
     * to the user in a Grid, the UI tacks on the dataTimeFrom and dataTimeTo contained in the original JSON Grid data
     * to the new URL for the CSV data - this is to ensure that the data received in CSV format is the same as the original 
     * JSON Grid data presented to the user.  In this case, both the dataTimeFrom and dataTimeTo, and the time parameters
     * could exist in the URL - the dataTimeFrom and dataTimeTo parameters should always take precedence
     * 
     * @param time - the minutes will go back from now.
     * @param timeFrom - start time
     * @param timeTo - end time
     * @param dateFrom - start date
     * @param dateTo - end date
     * @param dataTimeFrom 
     * @param dataTimeTo 
     * @param timeDelayOneMinuteData TODO
     * @param timeDelayFifteenMinuteData TODO
     * @param timeDelayDayData TODO
     * @return the formatted date time range
     * @throws WebApplicationException the parse exception
     */
    public static FormattedDateTimeRange getFormattedDateTimeRange(final String key, final String time,
            final String timeFrom, final String timeTo, final String dateFrom, final String dateTo,
            final String dataTimeFrom, final String dataTimeTo, final String tzOffset,
            final int timeDelayOneMinuteData, final int timeDelayFifteenMinuteData, final int timeDelayDayData)
            throws WebApplicationException {

        FormattedDateTimeRange dateTimeRange = null;

        if (StringUtils.isNotBlank(dataTimeFrom) && StringUtils.isNotBlank(dataTimeTo)) {

            String formattedStartDateTime;
            String formattedEndDateTime;
            final long rangeInMinutes = (Long.parseLong(dataTimeTo) - Long.parseLong(dataTimeFrom)) / 60000;
            /*
             * Below if block is to avoid converting time to UTC for all "RAW table CSV queries >1week 
             * and coming from Summary window", to match grid & csv query time params.
             * This is because UTC conversion is already done for this condition in the 
             * QueryUtils.mapRequestParameters method, no need to do again 
             */
            if (rangeInMinutes > MINUTES_IN_A_WEEK
                    && StringUtils.isNotBlank(key)
                    && (key.equals(KEY_TYPE_ERR) || key.equals(KEY_TYPE_TOTAL) || key.equals(KEY_TYPE_SUC) || key
                            .equals(KEY_TYPE_DV_DRILL_DOWN))) {
                formattedStartDateTime = DateTimeUtils.formattedDateTime(Long.parseLong(dataTimeFrom));
                formattedEndDateTime = DateTimeUtils.formattedDateTime(Long.parseLong(dataTimeTo));
            } else {
                formattedStartDateTime = DateTimeUtils.getUTCTime(
                        DateTimeUtils.formattedDateTime(Long.parseLong(dataTimeFrom)), tzOffset, DATE_TIME_FORMAT);

                formattedEndDateTime = DateTimeUtils.getUTCTime(
                        DateTimeUtils.formattedDateTime(Long.parseLong(dataTimeTo)), tzOffset, DATE_TIME_FORMAT);
            }

            //If the start and end time coming back is in long format then latency should have already been applied so set the time delays to zero
            dateTimeRange = getFormattedDateTimeRange(formattedStartDateTime, formattedEndDateTime, 0, 0, 0);

        } else if (StringUtils.isNotBlank(time)) {
            final long currentTime = System.currentTimeMillis();
            final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
            final Date now = new Date(currentTime - offSet);
            final String formattedStartDateTime = DateTimeUtils.getUTCTime(
                    DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, time), tzOffset, DATE_TIME_FORMAT),
                    tzOffset, DATE_TIME_FORMAT);
            final String formattedEndDateTime = DateTimeUtils.getUTCTime(
                    DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, "0"), tzOffset, DATE_TIME_FORMAT),
                    tzOffset, DATE_TIME_FORMAT);

            dateTimeRange = getFormattedDateTimeRange(formattedStartDateTime, formattedEndDateTime,
                    timeDelayOneMinuteData, timeDelayFifteenMinuteData, timeDelayDayData);

        } else if (StringUtils.isNotBlank(timeFrom) && StringUtils.isNotBlank(timeTo)
                && StringUtils.isNotBlank(dateFrom) && StringUtils.isNotBlank(dateTo)) {

            String formattedStartDateTime = DateTimeUtils.formattedDateTime(dateFrom, timeFrom);
            String formattedEndDateTime = DateTimeUtils.formattedDateTime(dateTo, timeTo);

            final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
            Date startDate;
            Date endDate;
            try {
                startDate = dateFormat.parse(formattedStartDateTime);
                endDate = dateFormat.parse(formattedEndDateTime);
            } catch (final ParseException e) {
                throw new WebApplicationException(e);
            }

            final long rangeInMinutes = (endDate.getTime() - startDate.getTime()) / 60000;

            //If the time range is a week or greater, then use the local time, otherwise use utc
            if (rangeInMinutes >= MINUTES_IN_A_WEEK) {

                dateTimeRange = getFormattedDateTimeRange(formattedStartDateTime, formattedEndDateTime,
                        timeDelayOneMinuteData, timeDelayFifteenMinuteData, timeDelayDayData);
            } else {
                formattedStartDateTime = DateTimeUtils.getUTCTime(DateTimeUtils.formattedDateTime(dateFrom, timeFrom),
                        tzOffset, DATE_TIME_FORMAT);

                formattedEndDateTime = DateTimeUtils.getUTCTime(DateTimeUtils.formattedDateTime(dateTo, timeTo),
                        tzOffset, DATE_TIME_FORMAT);

                dateTimeRange = getFormattedDateTimeRange(formattedStartDateTime, formattedEndDateTime,
                        timeDelayOneMinuteData, timeDelayFifteenMinuteData, timeDelayDayData);
            }
        }
        return dateTimeRange;
    }

    /**
     * Gets the formatted dateTime range without applying offsets.
     * This will process the time range parameters from RESTful service to generate the formatted dataTime range.
     * This method will also convert the local time to UTC time zone.
     * If the dataTimeFrom and dataTimeTo parameters are present, then these are returned in the FormattedDateTimeRange
     * If these aren't present, this method first checks for the presence of the time parameter
     * If the time parameter isn't present, the method finally checks for the timeFrom, timeTo, dateFrom and dateTo
     * parameters
     * <p/>
     * This order is required for CSV requests - when a CSV export is requested by the UI for data already presented
     * to the user in a Grid, the UI tacks on the dataTimeFrom and dataTimeTo contained in the original JSON Grid data
     * to the new URL for the CSV data - this is to ensure that the data received in CSV format is the same as the original 
     * JSON Grid data presented to the user.  In this case, both the dataTimeFrom and dataTimeTo, and the time parameters
     * could exist in the URL - the dataTimeFrom and dataTimeTo parameters should always take precedence
     * 
     * @param time - the minutes will go back from now.
     * @param timeFrom - start time
     * @param timeTo - end time
     * @param dateFrom - start date
     * @param dateTo - end date
     * @param dataTimeFrom 
     * @param dataTimeTo 
     * @return the formatted date time range
     * @throws WebApplicationException the parse exception
     */
    public static FormattedDateTimeRange getFormattedDateTimeRangeWithOutOffset(final String time,
            final String timeFrom, final String timeTo, final String dateFrom, final String dateTo,
            final String dataTimeFrom, final String dataTimeTo, final String tzOffset) throws WebApplicationException {

        FormattedDateTimeRange dateTimeRange = null;

        if (StringUtils.isNotBlank(dataTimeFrom) && StringUtils.isNotBlank(dataTimeTo)) {
            final String formattedStartDateTime = DateTimeUtils.getUTCTime(
                    DateTimeUtils.formattedDateTime(Long.parseLong(dataTimeFrom)), tzOffset, DATE_TIME_FORMAT);

            final String formattedEndDateTime = DateTimeUtils.getUTCTime(
                    DateTimeUtils.formattedDateTime(Long.parseLong(dataTimeTo)), tzOffset, DATE_TIME_FORMAT);

            dateTimeRange = getFormattedDateTimeRangeWithOutOffset(formattedStartDateTime, formattedEndDateTime);

        } else if (StringUtils.isNotBlank(time)) {
            final long currentTime = System.currentTimeMillis();
            final int offSet = Calendar.getInstance().getTimeZone().getOffset(currentTime);
            final Date now = new Date(currentTime - offSet);
            final String formattedStartDateTime = DateTimeUtils.getUTCTime(
                    DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, time), tzOffset, DATE_TIME_FORMAT),
                    tzOffset, DATE_TIME_FORMAT);
            final String formattedEndDateTime = DateTimeUtils.getUTCTime(
                    DateTimeUtils.getLocalTime(DateTimeUtils.formattedDateTime(now, "0"), tzOffset, DATE_TIME_FORMAT),
                    tzOffset, DATE_TIME_FORMAT);

            dateTimeRange = getFormattedDateTimeRangeWithOutOffset(formattedStartDateTime, formattedEndDateTime);

        } else if (StringUtils.isNotBlank(timeFrom) && StringUtils.isNotBlank(timeTo)
                && StringUtils.isNotBlank(dateFrom) && StringUtils.isNotBlank(dateTo)) {

            final String formattedStartDateTime = DateTimeUtils.getUTCTime(
                    DateTimeUtils.formattedDateTime(dateFrom, timeFrom), tzOffset, DATE_TIME_FORMAT);

            final String formattedEndDateTime = DateTimeUtils.getUTCTime(
                    DateTimeUtils.formattedDateTime(dateTo, timeTo), tzOffset, DATE_TIME_FORMAT);

            dateTimeRange = getFormattedDateTimeRangeWithOutOffset(formattedStartDateTime, formattedEndDateTime);

        }
        return dateTimeRange;
    }

    /**
     * Gets the daily aggregation time range by local time.
     *
     * @param time the time
     * @param timeDelayOneMinuteData TODO
     * @param timeDelayFifteenMinuteData TODO
     * @param timeDelayDayData TODO
     * @return the daily aggregation time range by local time
     */
    public static FormattedDateTimeRange getDailyAggregationTimeRangebyLocalTime(final String time,
            final int timeDelayOneMinuteData, final int timeDelayFifteenMinuteData, final int timeDelayDayData) {
        final Date now = new Date();
        final FormattedDateTimeRange dateTimeRange = getFormattedDateTimeRange(
                DateTimeUtils.formattedDateTime(now, time), DateTimeUtils.formattedDateTime(now, "0"),
                timeDelayOneMinuteData, timeDelayFifteenMinuteData, timeDelayDayData);
        return dateTimeRange;
    }

    /**
     * Gets the time range by local time.
     *
     * @param time the time
     * @return the time range by local time
     */
    public static FormattedDateTimeRange getTimeRangebyLocalTime(final String time) {
        final Date now = new Date();
        final FormattedDateTimeRange dateTimeRange = getFormattedDateTimeRangeWithOutOffset(
                DateTimeUtils.formattedDateTime(now, time), DateTimeUtils.formattedDateTime(now, "0"));
        return dateTimeRange;
    }

    /**
     * Gets the chart interval.
     *
     * @param dateTimeRange the date time range
     * @param timerange the time range
     * @return the chart interval
     * @throws WebApplicationException the parse exception
     */
    public static int getChartInterval(final FormattedDateTimeRange dateTimeRange, final String timerange)
            throws WebApplicationException {
        final int secondsBetweenTimerange = getTimewindowValue(dateTimeRange);
        int interval = 0;
        if (timerange.equals(EventDataSourceType.AGGREGATED_15MIN.getValue())) {
            interval = getInterval(secondsBetweenTimerange, SECONDS_IN_15_MINS);
        } else if (timerange.equals(EventDataSourceType.AGGREGATED_DAY.getValue())) {
            if (getChartSlotCount(dateTimeRange, SECONDS_IN_1_DAY) == CHART_SLOT_COUNT) {
                interval = getInterval(secondsBetweenTimerange, SECONDS_IN_1_DAY);
            } else {
                interval = SECONDS_IN_1_DAY;
            }
        } else if (timerange.equals(EventDataSourceType.AGGREGATED_1MIN.getValue())
                || timerange.equals(EventDataSourceType.RAW.getValue())) {
            interval = secondsBetweenTimerange / CHART_SLOT_COUNT;
        }
        return interval;
    }

    /**
     * Gets the day chart slot count.
     *
     * @param dateTimeRange the date time range
     * @return the day chart slot count
     * @throws WebApplicationException the parse exception
     */
    static int getChartSlotCount(final FormattedDateTimeRange dateTimeRange, final int secondsBeDivided)
            throws WebApplicationException {
        final int secondsBetweenTimerange = getTimewindowValue(dateTimeRange);
        int chartSlotCount = 0;
        final int count = secondsBetweenTimerange / secondsBeDivided;
        if (count < 30) {
            chartSlotCount = count;
        } else {
            chartSlotCount = CHART_SLOT_COUNT;
        }

        return chartSlotCount;
    }

    /**
     * Get 15 mins/Day interval to match the time range.
     *
     * @param secondsBetweenTimerange the seconds between time range
     * @param secondsBeDivided - the seconds be divided
     * @return the interval
     */
    private static int getInterval(int secondsBetweenTimerange, final int secondsBeDivided) {
        Integer interval = 0;
        for (; secondsBetweenTimerange > 0; secondsBetweenTimerange--) {
            final double divideResult = (secondsBetweenTimerange / CHART_SLOT_COUNT) / secondsBeDivided;
            final int remainder = (secondsBetweenTimerange / CHART_SLOT_COUNT) % secondsBeDivided;
            final double fraction = divideResult - (int) divideResult;
            if (fraction == 0d && remainder == 0) {
                interval = secondsBetweenTimerange / CHART_SLOT_COUNT;
                break;
            }
        }
        return interval;
    }

    /**
     * Gets the sampling time list.
     * This is used by sampling graph only, it will return 30 time slots based on time range and interval.
     * 
     * @param dateTimeRange the date time range
     * @param interval the interval
     * @return the KPI time list
     */
    public static String[] getSamplingTimeList(final FormattedDateTimeRange dateTimeRange, final int interval) {
        final String[] samplingTimeList = new String[CHART_SLOT_COUNT];
        final Date timeDateFrom = DateTimeUtils.parseDateTimeFormat(dateTimeRange.getStartDateTime());
        final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        final Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < samplingTimeList.length; i++) {
            calendar.setTimeInMillis(TimeUnit.MILLISECONDS.toMillis(timeDateFrom.getTime())
                    + (i * interval * MILLISECOND_IN_1_SECOND));
            samplingTimeList[i] = formatter.format(calendar.getTime());
        }
        return samplingTimeList;
    }

    /**
     * Return the date time with seconds as per the UTC Time zone
     *
     * @param dateTime the date and time
     * @return timestamp with milliseconds
     * @throws ParseException 
     */
    public static Date getUTCDateTimeWithSeconds(final String dateTime) throws ParseException {

        final String seconds = ":00";

        Date dateUTC = null;
        try {
            synchronized (dbUTCDateTimeFormat) {
                dateUTC = dbUTCDateTimeFormat.parse(dateTime + seconds);
            }
        } catch (final NumberFormatException nfe) {
            ServicesLogger.exception("DateTimeRange", "getUTCDateTimeWithSeconds", "Exception during Parsing", nfe);
            throw nfe;
        }
        return dateUTC;
    }

    public static int getTimewindowValue(final FormattedDateTimeRange dateTimeRange) {
        final Date timeDateFrom = DateTimeUtils.parseDateTimeFormat(dateTimeRange.getStartDateTime());
        final Date timeDateTo = DateTimeUtils.parseDateTimeFormat(dateTimeRange.getEndDateTime());
        return (int) (DateTimeUtils.timeInMinutesBetweenDates(timeDateFrom, timeDateTo)) * SECONDS_IN_1_MIN;
    }

    public static FormattedDateTimeRange getDataTieredFormattedDateTimeRange(
            final FormattedDateTimeRange dateTimeRange, final int dataTieringDelay) {
        return new DataTieredDateTimeRange(dateTimeRange, dataTieringDelay);
    }
}
