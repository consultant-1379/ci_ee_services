/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.dataproviders.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserImsiPopupHfaIRatTestDataProvider {
    public static Object[] provideTestData() {

        //data for RAN HFA IRAT table on 926
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "0000").add(TIME_TO_QUERY_PARAM, "2300")
                .add(DATE_FROM_QUERY_PARAM, "19032012").add(DATE_TO_QUERY_PARAM, "19032012").add(TZ_OFFSET, "+0000")
                .add(IMSI_PARAM, "454063306038779").add(EVENT_TIME_FROM_PARAM, "1332130011030")
                .add(EVENT_TIME_TO_PARAM, "1332130011031").add(EVENT_ID_PARAM, "458").build();

        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
