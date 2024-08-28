/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import java.util.Date;

/**
 * Interface for FormattedDateTimeRange
 *
 * @author eemecoy
 */
public interface FormattedDateTimeRange {

    /**
     * Gets the start date time.
     *
     * @return the start date time
     */
    String getStartDateTime();

    /**
     * Gets the end date time.
     *
     * @return the end date time
     */
    String getEndDateTime();

    /**
     * Gets the start date
     *
     * @return the start date
     */
    Date getStartDate();

    /**
     * Gets the end date
     *
     * @return the end date
     */
    Date getEndDate();

    /**
     * Gets the minutes of start date time.
     *
     * @return the minutes of start date time
     */
    int getMinutesOfStartDateTime();

    /**
     * Gets the minutes of end date time.
     *
     * @return the minutes of end date time
     */
    int getMinutesOfEndDateTime();

    /**
     * Return range in minutes
     *
     * @return range in minutes
     */
    long getRangeInMinutes();

    /**
     * Returns current time when queries originally being requested   
     * @return
     */
    Date getCurrentDateTime();
}