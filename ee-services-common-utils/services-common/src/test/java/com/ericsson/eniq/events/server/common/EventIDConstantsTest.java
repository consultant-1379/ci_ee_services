/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author eemecoy
 *
 */
public class EventIDConstantsTest {

    @Test
    public void testgetEventIdForEventDesc_CallDropsEvent() {
        final int eventId = EventIDConstants.getEventId(CALL_DROPS);
        assertThat(eventId, is(CALL_DROP_EVENT_ID_AS_INTEGER));
    }

    @Test
    public void testgetEventIdForEventDesc_CallSetupFailureEvent() {
        final int eventId = EventIDConstants.getEventId(CALL_SETUP_FAILURES_EVENT_DESC);
        assertThat(eventId, is(CALL_SETUP_FAILURE_EVENT_ID_AS_INTEGER));
    }

    @Test(expected = RuntimeException.class)
    public void testgetEventIdForEventDesc_UnknownEvent() {
        final int dummyEventId = 999;
        EventIDConstants.getEventId(Integer.toString(dummyEventId));
    }

}
