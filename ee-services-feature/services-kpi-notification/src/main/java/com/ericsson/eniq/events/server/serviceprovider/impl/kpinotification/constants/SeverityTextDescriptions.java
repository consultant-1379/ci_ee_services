/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification.constants;

import static com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification.constants.KPINotificationConstants.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Hold information on the severity text descriptions for this feature
 * Normally, this information would be held in a static table, but its not possible to edit the tech pack for 
 * this feature.
 * 
 * This class is used when querying for a specific severity value, and also for counting the number of severity values
 * 
 * @author eemecoy
 *
 */
public class SeverityTextDescriptions {

    private static Map<String, Integer> severityValues = new HashMap<String, Integer>();

    static {
        severityValues.put(CRITICAL, CRITICAL_INTEGER_VALUE);
        severityValues.put(MAJOR, MAJOR_INTEGER_VALUE);
        severityValues.put(MINOR, MINOR_INTEGER_VALUE);
        severityValues.put(WARNING, WARNING_INTEGER_VALUE);
    }

    /**
     * Get the integer value for a specific perceivedSeverityText
     * 
     * @param perceivedSeverityText
     * @return corresponding integer value
     */
    public int getIntegerValue(final String perceivedSeverityText) {
        return severityValues.get(perceivedSeverityText);
    }

}
