/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.datetime;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author eemecoy
 *
 */
public class DateTimeWhiteListTest {

    private DateTimeWhiteList dateTimeWhiteList;

    @Before
    public void setup() {
        dateTimeWhiteList = new DateTimeWhiteList();
        dateTimeWhiteList.applicationStartup();
    }

    @Test
    public void testForQueryThatDoesntRequireDateTimeParameters() {
        assertThat(dateTimeWhiteList.queryRequiresDateTimeParameters(WCDMA_CALL_FAILURE_SUBSCRIBER_DETAILS), is(false));
    }

    @Test
    public void testForQueryThatDoesRequireDateTimeParameters() {
        assertThat(dateTimeWhiteList.queryRequiresDateTimeParameters(WCDMA_CALL_FAILURE_EVENT_ANALYSIS), is(true));
    }

}
