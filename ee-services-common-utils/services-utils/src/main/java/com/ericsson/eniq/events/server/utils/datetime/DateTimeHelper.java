/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.datetime;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.utils.DateTimeUtils.*;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.utils.DateTimeRange;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

/**
 * 
 * New class, part of rework which will be carried out as part of the WCDMA feature
 * 
 * This class is responsible for handling the translation of the user's input parameters to the date/time ranges
 * used when querying the database
 * 
 * It is improtant to note that the TimeRange for different views is different.
 * Grid
 * 0 Min < t <= 5 Mins ==> Raw Tables
 * 5 Min < t < 2 Hours ==> 1 Min Aggregations
 * 2 Hours <= t < 1 Week ==> 15 Min Aggregations
 * 1 Week <= t < X ==> Day Aggregations.
 * 
 * Chart
 * 0 Min < t <= 5 Mins ==> Raw Tables
 * 5 Min < t <= 960 Mins (12 hours) ==> 1 Min Aggregations
 * 12 Hours < t < 2 Weeks ==> 15 Min Aggregations
 * 2 Weeks <= t < X ==> Day Aggregations.
 * 
 * Chart (without 1 min aggregations)
 * 0 Min < t <= 2 Hours ==> Raw Tables
 * 2 Hours < t < 1 Week ==> 15 Min Aggregations
 * 1 Week <= t < X ==> Day Aggregations.
 *
 * @author eeikonl
 *
 */
@Stateless
@Local
public class DateTimeHelper {

    @EJB
    private ApplicationConfigManager applicationConfigManager;

    /**
     * Based on the supplied request parameters, create the Formatted Date Time Range for the queries.
     * @param requestParameters the HTTPRequestParameters.
     * @param techPacks 
     * @return A FormattedDateTimeRange to be used for the queries
     */
    public FormattedDateTimeRange translateDateTimeParameters(final MultivaluedMap<String, String> requestParameters,
            final List<String> techPacks) {

        final FormattedDateTimeRange dateTimeRange = getAndCheckFormattedDateTimeRangeForDailyAggregation(
                requestParameters, techPacks);

        final FormattedDateTimeRange newDateTimeRange = getDateTimeRange(dateTimeRange,
                getEventDataSourceType(dateTimeRange));

        return newDateTimeRange;

    }

    public EventDataSourceType getEventDataSourceType(final FormattedDateTimeRange timeRange) {
        final EventDataSourceType dataSourceType = getEventDataSourceType(timeRange.getRangeInMinutes());

        return dataSourceType;

    }

    /**
     * Gets the event data source type for grid with one min aggregated.
     * The EventDataSourceType is based on these ranges
     * 
     * 0 Min < timeRangeInMinutes <= 5 Mins ==> Raw Tables
     * 5 Min < timeRangeInMinutes <= 2 Hours ==> 1 Min Aggregations
     * 2 Hours < timeRangeInMinutes <= 1 Week ==> 15 Min Aggregations
     * 1 Week < timeRangeInMinutes < X ==> Day Aggregations (where X is any number greater than 1 week).
     * 
     * @param timeRangeInMinutes
     * @return the event data source type for grid with one min aggregated
     */
    EventDataSourceType getEventDataSourceType(final long timeRangeInMinutes) {

        EventDataSourceType result;

        if (oneMinuteAggregationRange(timeRangeInMinutes) && applicationConfigManager.getOneMinuteAggregation()) {
            result = EventDataSourceType.AGGREGATED_1MIN;
        } else if (fifteenMinuteAggregatedRangeForGrid(timeRangeInMinutes)) {
            result = EventDataSourceType.AGGREGATED_15MIN;
        } else if (rawEventRange(timeRangeInMinutes)
                || (oneMinuteAggregationRange(timeRangeInMinutes) && !applicationConfigManager
                        .getOneMinuteAggregation())) {
            result = EventDataSourceType.RAW;
        } else {
            result = EventDataSourceType.AGGREGATED_DAY;
        }

        return result;
    }

    public FormattedDateTimeRange getDataTieredDateTimeRange(final FormattedDateTimeRange dateTimeRange) {
        final EventDataSourceType type = getEventDataSourceType(dateTimeRange);
        if (applicationConfigManager.isDataTieringEnabled()
                && ((type == EventDataSourceType.RAW) || (type == EventDataSourceType.AGGREGATED_1MIN))) {
            return DateTimeRange.getDataTieredFormattedDateTimeRange(dateTimeRange,
                    applicationConfigManager.getDataTieringDelay());
        }
        return dateTimeRange;
    }

    /**
     * Gets the date time range of chart and summary grid.
     * 
     * @param dateTimeRange FormattedDateTimeRange
     * @param eventDataSourceType aggregation view name
     * @return the formatted dateTime range of chart and summary grid
     */

    FormattedDateTimeRange getDateTimeRange(final FormattedDateTimeRange dateTimeRange,
            final EventDataSourceType eventDataSourceType) {
        FormattedDateTimeRange newDateTimeRange = null;

        FormattedDateTimeRange tempDateTimeRange;
        switch (eventDataSourceType) {
        case AGGREGATED_15MIN:
            tempDateTimeRange = DateTimeRange.getFormattedDateTimeRange(
                    DateTimeRange.formattedDateTimeAgainst15MinsTable(dateTimeRange.getStartDateTime(),
                            dateTimeRange.getMinutesOfStartDateTime()),
                    DateTimeRange.formattedDateTimeAgainst15MinsTable(dateTimeRange.getEndDateTime(),
                            dateTimeRange.getMinutesOfEndDateTime()), 0, 0, 0);
            newDateTimeRange = tempDateTimeRange;
            break;
        case AGGREGATED_DAY:
            tempDateTimeRange = DateTimeRange.getFormattedDateTimeRange(
                    DateTimeRange.formattedDateTimeAgainstDayTable(dateTimeRange.getStartDateTime(),
                            dateTimeRange.getMinutesOfStartDateTime()),
                    DateTimeRange.formattedDateTimeAgainstDayTable(dateTimeRange.getEndDateTime(),
                            dateTimeRange.getMinutesOfEndDateTime()), 0, 0, 0);
            newDateTimeRange = tempDateTimeRange;
            break;
        case AGGREGATED_1MIN:
        case RAW:
            newDateTimeRange = dateTimeRange;
        }

        return newDateTimeRange;
    }

    FormattedDateTimeRange getFormattedDateTimeRange(final MultivaluedMap<String, String> requestParameters,
            final List<String> techPacks) {
        final FormattedDateTimeRange dateTimeRange = DateTimeRange.getFormattedDateTimeRange(
                requestParameters.getFirst(KEY_PARAM), requestParameters.getFirst(TIME_QUERY_PARAM),
                requestParameters.getFirst(TIME_FROM_QUERY_PARAM), requestParameters.getFirst(TIME_TO_QUERY_PARAM),
                requestParameters.getFirst(DATE_FROM_QUERY_PARAM), requestParameters.getFirst(DATE_TO_QUERY_PARAM),
                requestParameters.getFirst(DATA_TIME_FROM_QUERY_PARAM),
                requestParameters.getFirst(DATA_TIME_TO_QUERY_PARAM), requestParameters.getFirst(TZ_OFFSET),
                applicationConfigManager.getTimeDelayOneMinuteData(techPacks),
                applicationConfigManager.getTimeDelayFifteenMinuteData(techPacks),
                applicationConfigManager.getTimeDelayDayData(techPacks));
        return dateTimeRange;
    }

    /**
     * Gets the formatted date time range.
     *
    * @param requestParameters the request parameters
     * @param techPacks 
    * @return the formatted date time range
    */
    FormattedDateTimeRange getAndCheckFormattedDateTimeRangeForDailyAggregation(
            final MultivaluedMap<String, String> requestParameters, final List<String> techPacks) {

        FormattedDateTimeRange timerange = getFormattedDateTimeRange(requestParameters, techPacks);

        if (requestParameters.containsKey(TIME_QUERY_PARAM)) {
            if (isDayAggregation(timerange)) {
                timerange = DateTimeRange.getDailyAggregationTimeRangebyLocalTime(
                        requestParameters.getFirst(TIME_QUERY_PARAM),
                        applicationConfigManager.getTimeDelayOneMinuteData(techPacks),
                        applicationConfigManager.getTimeDelayFifteenMinuteData(techPacks),
                        applicationConfigManager.getTimeDelayDayData(techPacks));
            }
        }
        return timerange;
    }

    /**
     * Works out if we should use Day Aggregation level for the given date time range.
     * @param timerange
     * @return
     */
    boolean isDayAggregation(final FormattedDateTimeRange timerange) {
        return (getEventDataSourceType(timerange).equals(EventDataSourceType.AGGREGATED_DAY));
    }

    /**
     * This is to allow us to inject the applicationConfi manager for JUnit tests
     * @param applicationConfigManager
     */
    public void setApplicationConfigManager(final ApplicationConfigManager applicationConfigManager) {
        this.applicationConfigManager = applicationConfigManager;
    }

}
