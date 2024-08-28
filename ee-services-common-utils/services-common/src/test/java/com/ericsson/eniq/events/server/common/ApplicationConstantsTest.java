/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author etomcor
 * @since 2010
 *
 */
public class ApplicationConstantsTest {

    private static ApplicationConstants it;

    @BeforeClass
    public static void setUp() {
        it = ApplicationConstants.getInstance();
    }

    @Test
    public void testReturnAggregateViewType() throws Exception {
        assertEquals(it.returnAggregateViewType(it.ONE_MINUTE), it.ONE_MINUTE_VIEW);
        assertEquals(it.returnAggregateViewType(it.FIFTEEN_MINUTES), it.FIFTEEN_MINUTES_VIEW);
        assertEquals(it.returnAggregateViewType(it.DAY), it.DAY_VIEW);
        assertEquals(it.returnAggregateViewType(it.RAW), it.RAW_VIEW);
    }
}
