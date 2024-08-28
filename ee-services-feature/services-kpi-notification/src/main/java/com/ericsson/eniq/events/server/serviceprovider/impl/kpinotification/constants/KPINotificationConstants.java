/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification.constants;

/**
 * @author eemecoy
 *
 */
public abstract class KPINotificationConstants {

    public static final String PERCEIVED_SEVERITY = "perceivedSeverity";

    public static final int WARNING_INTEGER_VALUE = 6;

    public static final int MINOR_INTEGER_VALUE = 5;

    public static final int MAJOR_INTEGER_VALUE = 4;

    public static final int CRITICAL_INTEGER_VALUE = 3;

    public final static String CRITICAL = "critical";

    public final static String MAJOR = "major";

    public final static String WARNING = "warning";

    public final static String MINOR = "minor";

    private final static SeverityTextDescriptions severityTextDescriptions = new SeverityTextDescriptions();

    public static int getPerceivedSeverityValue(final String perceivedSeverityText) {
        return severityTextDescriptions.getIntegerValue(perceivedSeverityText);
    }

}
