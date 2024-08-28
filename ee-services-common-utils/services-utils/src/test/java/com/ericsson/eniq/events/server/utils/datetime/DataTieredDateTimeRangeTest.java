package com.ericsson.eniq.events.server.utils.datetime;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import com.ericsson.eniq.events.server.utils.DateTimeRange;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;

public class DataTieredDateTimeRangeTest {

    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm";

    public static final int TIME_DELAY_1MIN_DATA = 3;

    public static final int INCREASED_TIME_DELAY_1MIN_DATA = 30;

    public static final int TIME_DELAY_15MIN_DATA = 10;

    public static final int TIME_DELAY_DAY_DATA = 180;

    static final int DATA_TIERED_TIME_DELAY_IN_MIN = 6;

    @Test
    public void endTimeGoBackLastQuaterHourIfQuereisAfterDataTieredDelay() throws Exception {
        final int timeRangeInMinutes = 30;
        final SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
        final Date now = sdf.parse("2013-01-15 14:54");
        final String endTime = "2013-01-15 14:53";
        final String startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));

        final FormattedDateTimeRange formattedDateTimeRange = DateTimeRange.getFormattedDateTimeRange(startTime,
                endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA, TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        //Verify start and end time with FormmattedDateTimeRange before we apply additional delays due to dataTiering
        assertEquals(30, formattedDateTimeRange.getRangeInMinutes());
        assertEquals("2013-01-15 14:50", formattedDateTimeRange.getEndDateTime());
        assertEquals("2013-01-15 14:20", formattedDateTimeRange.getStartDateTime());
        assertEquals(50, formattedDateTimeRange.getMinutesOfEndDateTime());
        assertEquals(20, formattedDateTimeRange.getMinutesOfStartDateTime());

        //Verify start and end time with DataTieredDateTimeRange
        final DataTieredDateTimeRange dateTimeRange = new DataTieredDateTimeRange(formattedDateTimeRange,
                DATA_TIERED_TIME_DELAY_IN_MIN);
        assertEquals(30, dateTimeRange.getRangeInMinutes());
        assertEquals("2013-01-15 14:45", dateTimeRange.getEndDateTime());
        assertEquals("2013-01-15 14:15", dateTimeRange.getStartDateTime());
        assertEquals(45, dateTimeRange.getMinutesOfEndDateTime());
        assertEquals(15, dateTimeRange.getMinutesOfStartDateTime());
    }

    @Test
    public void endTimeGoBackLastButOneQuaterHourIfQuereisWithinDataTieredDelay() throws Exception {
        final int timeRangeInMinutes = 30;
        final SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
        final Date now = sdf.parse("2013-01-15 14:51");
        final String endTime = "2013-01-15 14:50";
        final String startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));

        final FormattedDateTimeRange formattedDateTimeRange = DateTimeRange.getFormattedDateTimeRange(startTime,
                endTime, TIMESTAMP_FORMAT, TIME_DELAY_1MIN_DATA, TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA, now);

        //Verify start and end time with FormmattedDateTimeRange before we apply additional delays due to dataTiering
        assertEquals(30, formattedDateTimeRange.getRangeInMinutes());
        assertEquals("2013-01-15 14:47", formattedDateTimeRange.getEndDateTime());
        assertEquals("2013-01-15 14:17", formattedDateTimeRange.getStartDateTime());
        assertEquals(47, formattedDateTimeRange.getMinutesOfEndDateTime());
        assertEquals(17, formattedDateTimeRange.getMinutesOfStartDateTime());

        //Verify start and end time with DataTieredDateTimeRange
        final DataTieredDateTimeRange dateTimeRange = new DataTieredDateTimeRange(formattedDateTimeRange,
                DATA_TIERED_TIME_DELAY_IN_MIN);
        assertEquals(30, dateTimeRange.getRangeInMinutes());
        assertEquals("2013-01-15 14:30", dateTimeRange.getEndDateTime());
        assertEquals("2013-01-15 14:00", dateTimeRange.getStartDateTime());
        assertEquals(30, dateTimeRange.getMinutesOfEndDateTime());
        assertEquals(00, dateTimeRange.getMinutesOfStartDateTime());
    }

    @Test
    public void endTimeGoBackLastQuaterFromEndTimeIfBeforeLastestTimeOfAggregatedDataIsAvailable() throws Exception {
        final int timeRangeInMinutes = 30;
        final SimpleDateFormat sdf = new SimpleDateFormat(TIMESTAMP_FORMAT);
        final Date now = sdf.parse("2013-01-15 17:49");
        final String endTime = "2013-01-15 17:49";
        final String startTime = sdf.format(DateUtils.addMinutes(sdf.parse(endTime), -timeRangeInMinutes));

        final FormattedDateTimeRange formattedDateTimeRange = DateTimeRange.getFormattedDateTimeRange(startTime,
                endTime, TIMESTAMP_FORMAT, INCREASED_TIME_DELAY_1MIN_DATA, TIME_DELAY_15MIN_DATA, TIME_DELAY_DAY_DATA,
                now);

        //Verify start and end time with FormmattedDateTimeRange before we apply additional delays due to dataTiering
        assertEquals(30, formattedDateTimeRange.getRangeInMinutes());
        assertEquals("2013-01-15 17:19", formattedDateTimeRange.getEndDateTime());
        assertEquals("2013-01-15 16:49", formattedDateTimeRange.getStartDateTime());
        assertEquals(19, formattedDateTimeRange.getMinutesOfEndDateTime());
        assertEquals(49, formattedDateTimeRange.getMinutesOfStartDateTime());

        //Verify start and end time with DataTieredDateTimeRange
        final DataTieredDateTimeRange dateTimeRange = new DataTieredDateTimeRange(formattedDateTimeRange,
                DATA_TIERED_TIME_DELAY_IN_MIN);
        assertEquals(30, dateTimeRange.getRangeInMinutes());
        assertEquals("2013-01-15 17:15", dateTimeRange.getEndDateTime());
        assertEquals("2013-01-15 16:45", dateTimeRange.getStartDateTime());
        assertEquals(15, dateTimeRange.getMinutesOfEndDateTime());
        assertEquals(45, dateTimeRange.getMinutesOfStartDateTime());
    }

}
