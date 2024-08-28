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
public class SBrowserImsiRrcPopupTestDataProvider {
    public static Object[] provideTestData() {
        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_FROM_QUERY_PARAM, "0900").add(TIME_TO_QUERY_PARAM, "1000")
        //                .add(DATE_FROM_QUERY_PARAM, "23042012").add(DATE_TO_QUERY_PARAM, "23042012").add(TZ_OFFSET, "+0000")
        //                .add(IMSI_PARAM, "454061101273642").add(EVENT_TIME_FROM_PARAM, "1335173781000")
        //                .add(EVENT_TIME_TO_PARAM, "1335173781000").add(EVENT_ID_PARAM, "8").build();
        //        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());

        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "0245").add(TIME_TO_QUERY_PARAM, "0300")
                .add(DATE_FROM_QUERY_PARAM, "23032012").add(DATE_TO_QUERY_PARAM, "23032012").add(TZ_OFFSET, "+0100")
                .add(IMSI_PARAM, "454063391514041").add(EVENT_TIME_FROM_PARAM, "1332467845323")
                .add(EVENT_TIME_TO_PARAM, "1332467845324").add(EVENT_ID_PARAM, "8").add(ADDITIONAL_EVENT_ID, "1234")
                .build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
