/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import java.text.DecimalFormat;

/**
 * calculate the expected success ratio for use when verifying results of queries
 * @author eemecoy
 *
 */
public class SuccessRatioCalculator {

    public static double calculateSuccessRatio(final double occurrences, final double noSuccesses) {
        final double result = noSuccesses / occurrences * 100;
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return Double.valueOf(decimalFormat.format(result));
    }

}
