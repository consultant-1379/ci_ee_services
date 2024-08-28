/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.dataproviders.lte.cfa;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.convertToArrayOfMultivaluedMap;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

/**
 * @author echimma
 * @since 2011
 */
public class LTECFASubscriberERABEventAnalysisServiceDataProvider {

    private static final String TEST_VALUE_HIER3_ID = "4809532081614999117";

    private static final String TEST_VALUE_HIER321_ID = "7210756712490856540";

    private static final String TEST_VALUE_EVENT_TIME = "2011-09-20 08:12:00:1212";

    private static final String TEST_VALUE_IMSI = "460001789012345";

    private static final String TEST_VALUE_TAC = "55840581";

    private LTECFASubscriberERABEventAnalysisServiceDataProvider() {
    }

    public static Object[] provideTestData() {

        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(DISPLAY_PARAM, GRID_PARAM)
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, THIRTY_MINUTES, ONE_DAY, ONE_WEEK)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET)
                .add(MAX_ROWS, DEFAULT_MAX_ROWS)
                .add(HIER3_ID, TEST_VALUE_HIER3_ID)
                .add(HIER321_ID, TEST_VALUE_HIER321_ID)
                .add(EVENT_TIME, TEST_VALUE_EVENT_TIME)
                .add(EVENT_ID_PARAM, INTERNAL_PROC_ERAB_SETUP, INTERNAL_PROC_INITIAL_CTXT_SETUP,
                        INTERNAL_PROC_UE_CTXT_RELEASE).add(IMSI_PARAM, TEST_VALUE_IMSI).add(TAC, TEST_VALUE_TAC)
                .build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
