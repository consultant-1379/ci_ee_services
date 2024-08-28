/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.kpinotification.constants;

import org.junit.Before;

import com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification.constants.SeverityTextDescriptions;

/**
 * @author eemecoy
 *
 */
public class SeverityTextDescriptionsTest {

    private SeverityTextDescriptions severityTextDescriptions;

    @Before
    public void setup() {
        severityTextDescriptions = new SeverityTextDescriptions();
    }

}
