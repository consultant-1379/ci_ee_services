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
public class SBrowserImsiRabPopupTestDataProvider {
    public static Object[] provideTestData() {

        //data for RAN Session table on 1127
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "0000").add(TIME_TO_QUERY_PARAM, "0000")
                .add(DATE_FROM_QUERY_PARAM, "13112012").add(DATE_TO_QUERY_PARAM, "14112012").add(TZ_OFFSET, "+0000")
                .add(IMSI_PARAM, "454063307080447").add(EVENT_TIME_FROM_PARAM, "1352800800000")
                .add(EVENT_TIME_TO_PARAM, "1352800800000").add(EVENT_ID_PARAM, "20000").build();

        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
