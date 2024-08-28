/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config.latency;

/**
 * The different offset options that the user can select for the parameter ENIQ_EVENTS_TIME_DELAY_TO_BE_USED
 * @author eemecoy
 *
 */
public enum OffsetOptions {

    SGEH(0), DT(1), WCDMA(2), BEST_EFFORT(3), GSM(4), LTE(5), KPI_NOTIFICATION(6);

    private int integerValue;

    /**
     * Constructor for enum option
     * 
     * @param integerValue              the integer value for this offset option - this is what the user will 
     *                                  specify in glassfish
     */
    OffsetOptions(final int integerValue) {
        this.integerValue = integerValue;
    }

    public int getIntegerValue() {
        return integerValue;
    }

}
