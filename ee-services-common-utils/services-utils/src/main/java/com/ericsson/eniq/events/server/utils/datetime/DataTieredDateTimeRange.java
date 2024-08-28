package com.ericsson.eniq.events.server.utils.datetime;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.ericsson.eniq.events.server.utils.DateTimeUtils;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;

/**
 * This class is delegate of FormattedDateTimeRange for data tiered services.
 * Basically, it applies latency to round the end time stamp to the last quarter hour 
 * due to delays of data loading with 15min success table.
 * 
 * Note. If queries within SIX_MINUTE_MILLI_SECONDS after each ROP then we will step back
 * two periods of 15min of end time.  
 * 
 *  
 * @author eseuhon
 * @since 2012
 *
 */
public class DataTieredDateTimeRange implements FormattedDateTimeRange {

    private final int dataTieringDelaysInMin;

    private final FormattedDateTimeRange dateTimeRange;

    private final SimpleDateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);

    public DataTieredDateTimeRange(final FormattedDateTimeRange datetimeRange, final int dataTieringDelays) {
        this.dateTimeRange = datetimeRange;
        this.dataTieringDelaysInMin = dataTieringDelays;
    }

    @Override
    public String getStartDateTime() {
        return formatter.format(getStartDate());
    }

    @Override
    public String getEndDateTime() {
        return formatter.format(getEndDate());
    }

    @Override
    public Date getStartDate() {
        return DateUtils.addMinutes(getEndDate(), -((int) getRangeInMinutes()));
    }

    /**
     * round the end time stamp to the last quarter hour 
     */
    @Override
    public Date getEndDate() {

        final Date lastRop = getEndOfRopWhereLatestAggregatedDataAvailabe();
        if (dateTimeRange.getEndDate().getTime() < lastRop.getTime()) {
            return DateTimeUtils.roundToLastQuarterHour(dateTimeRange.getEndDate());
        }
        return lastRop;
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
        return dateTimeRange.getRangeInMinutes();
    }

    @Override
    public Date getCurrentDateTime() {
        return dateTimeRange.getCurrentDateTime();
    }

    protected Date getEndOfRopWhereLatestAggregatedDataAvailabe() {
        final Date lastQuaterHour = DateTimeUtils.roundToLastQuarterHour(getCurrentDateTime());
        final Date timeAggregatedDataAvailable = getDateLatest15minAggregatedDataIsAvailalbe(lastQuaterHour);

        if ((getCurrentDateTime().getTime() >= lastQuaterHour.getTime())
                && (getCurrentDateTime().getTime() <= timeAggregatedDataAvailable.getTime())) {
            return DateTimeUtils.stepBackTwoPeriodsOfQuarterHour(getCurrentDateTime());
        }
        return DateTimeUtils.roundToLastQuarterHour(getCurrentDateTime());
    }

    private Date getDateLatest15minAggregatedDataIsAvailalbe(final Date lastQuaterHour) {
        final Calendar c = Calendar.getInstance();
        c.setTime(lastQuaterHour);
        c.add(Calendar.MINUTE, dataTieringDelaysInMin);
        final Date timeAggregatedDataAvailable = c.getTime();
        return timeAggregatedDataAvailable;
    }

}
