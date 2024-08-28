/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserImsiServerDistTestDataProvider {
    public static Object[] provideTestData() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "1100").add(TIME_TO_QUERY_PARAM, "1115")
                .add(DATE_FROM_QUERY_PARAM, "03102011").add(DATE_TO_QUERY_PARAM, "03102011").add(TZ_OFFSET, "+0000")
                .add(IMSI_PARAM, "454063302871333").add(EVENT_TIME_FROM_PARAM, "1317636248000")
                .add(EVENT_TIME_TO_PARAM, "1317636300087").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
