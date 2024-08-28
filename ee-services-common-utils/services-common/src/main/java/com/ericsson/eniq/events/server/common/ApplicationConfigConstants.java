/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common;

/**
 * Constants used when accessing the ENIQ Events JNDI properties file. 
 * 
 * As services is moving away from JNDI, re-purposing this to hold configuration values. 
 * 
 * @author eemecoy
 *
 */
public final class ApplicationConfigConstants {

    public static final String ENIQ_EVENT_PROPERTIES = "Eniq_Event_Properties";

    /** default if no app config instance is unavailable */
    public static final int DEFAULT_EVENT_FAILURE_RANKING_COUNT = 50;

    public static final int MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE = 5000;

    public static final int DEFAULT_MAXIMUM_JSON_RESULT_SIZE = 500;

    public static final int DEFAULT_IMSI_EVENT_FAILURE_RANKING_COUNT = 50;

    public static final int DEFAULT_TERMINAL_EVENT_FAILURE_RANKING_COUNT = 50;

    public static final int DEFAULT_CELL_EVENT_FAILURE_RANKING_COUNT = 50;

    public static final int DEFAULT_BSC_EVENT_FAILURE_RANKING_COUNT = 50;

    public static final int DEFAULT_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT = 50;

    public static final int DEFAULT_APN_EVENT_FAILURE_RANKING_COUNT = 50;
    
    public static final int DEFAULT_ENODEB_EVENT_FAILURE_RANKING_COUNT = 50;

    public static final int DEFAULT_TERMINAL_COUNT = 30;

    public static final int DEFAULT_TERMINAL_GROUP_ANALYSIS_COUNT = 30;

    public static final int DEFAULT_TERMINAL_ANALYSIS_COUNT = 30;

    public static final int DEFAULT_ENIQ_EVENTS_LIVE_LOAD_COUNT = 100;

    public static final int MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_LIVE_LOAD_COUNT = 1000;

    //3G support
    public static final int DEFAULT_RNC_EVENT_FAILURE_RANKING_COUNT = 50;

    public static final int DEFAULT_SUBBI_COUNT = 30;

    public static final int DEFAULT_PDP_STATISTICS_COUNT = 50;

    //CR221 - 500MB default rollover size
    public static final int DEFAULT_ENIQ_EVENTS_AUDITLOG_ROLLOVER = 524288000;
    
    public static final int DEFAULT_ENIQ_EVENTS_PERFORMANCE_TRACE_ROLLOVER_IN_MB = 500;

    //HM82411 - The default maximum configurable value for JNDI values which do not already have one defined
    //HM89865 - Repurposed to limit the maxRows values being sent to the services layer
    public static final int MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS = 100;

    private ApplicationConfigConstants() {
        // Non instanciable
    }

}
