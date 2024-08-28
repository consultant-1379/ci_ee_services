/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.summary;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;

/**
 * @author eemecoy
 * @since 2012
 *
 */

@ContextConfiguration(locations = { "classpath:session-browser-context.xml" })
public class SBrowserRABThresholdServiceTest extends ServiceBaseTest {

    @Resource(name = "sessionBrowserRABThresholdService")
    private SBrowserRABThresholdService sessionBrowserRABThresholdService;

    @Test
    //test needs server upgrade
    public void testQuery() {
        // final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        //    runQuery(requestParameters, sessionBrowserRABThresholdService);
    }
}
