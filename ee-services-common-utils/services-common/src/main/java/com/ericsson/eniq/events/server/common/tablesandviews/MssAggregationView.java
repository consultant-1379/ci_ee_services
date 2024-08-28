/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.tablesandviews;

import java.util.ArrayList;
import java.util.List;

/**
 * This enum hold the aggregation view name for different node type
 * @author echchik
 *
 */
public enum MssAggregationView {

    TAC_MANUFACTURER_SUMMARY("EVENT_E_MSS_VOICE_MANUF_TAC_EVENTID_SUC",
            "EVENT_E_MSS_VOICE_MANUF_TAC_EVENTID_DROP_CALL", "EVENT_E_MSS_VOICE_MANUF_TAC_EVENTID_ERR"),

    EVENTSRC_SUMMARY("EVENT_E_MSS_VOICE_EVNTSRC_EVENTID_SUC", "EVENT_E_MSS_VOICE_EVNTSRC_EVENTID_DROP_CALL",
            "EVENT_E_MSS_VOICE_EVNTSRC_EVENTID_ERR"),

    CONTROLLER_CELL_SUMMARY("EVENT_E_MSS_VOICE_VEND_HIER321_EVENTID_SUC",
            "EVENT_E_MSS_VOICE_VEND_HIER321_EVENTID_DROP_CALL", "EVENT_E_MSS_VOICE_VEND_HIER321_EVENTID_ERR"),

    LOC_SERVICE_TAC_MANUFACTURER_SUMMARY("EVENT_E_MSS_LOC_SERVICE_MANUF_TAC_SUC", "",
            "EVENT_E_MSS_LOC_SERVICE_MANUF_TAC_ERR"),

    LOC_SERVICE_EVENTSRC_SUMMARY("EVENT_E_MSS_LOC_SERVICE_EVNTSRC_SUC", "", "EVENT_E_MSS_LOC_SERVICE_EVNTSRC_ERR"),

    LOC_SERVICE_CONTROLLER_CELL_SUMMARY("EVENT_E_MSS_LOC_SERVICE_VEND_HIER321_SUC", "",
            "EVENT_E_MSS_LOC_SERVICE_VEND_HIER321_ERR"),

    SMS_TAC_MANUFACTURER_SUMMARY("EVENT_E_MSS_SMS_MANUF_TAC_EVENTID_SUC", "", "EVENT_E_MSS_SMS_MANUF_TAC_EVENTID_ERR"),

    SMS_EVENTSRC_SUMMARY("EVENT_E_MSS_SMS_EVNTSRC_EVENTID_SUC", "", "EVENT_E_MSS_SMS_EVNTSRC_EVENTID_ERR"),

    SMS_CONTROLLER_CELL_SUMMARY("EVENT_E_MSS_SMS_VEND_HIER321_EVENTID_SUC", "",
            "EVENT_E_MSS_SMS_VEND_HIER321_EVENTID_ERR");

    private MssAggregationView(final String successView, final String droppedView, final String errView) {
        this.sucessView = successView;
        this.droppedView = droppedView;
        this.errView = errView;
    }

    public List<String> getSucessView() {
        final List<String> sucViewLst = new ArrayList<String>();
        sucViewLst.add(sucessView);
        return sucViewLst;
    }

    public List<String> getDroppedView() {
        final List<String> droppedViewLst = new ArrayList<String>();
        droppedViewLst.add(droppedView);
        return droppedViewLst;
    }

    public List<String> getErrorView() {
        final List<String> errorViewLst = new ArrayList<String>();
        errorViewLst.add(errView);
        return errorViewLst;
    }

    private String sucessView;

    private String droppedView;

    private String errView;
}
