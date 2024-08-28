/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.dataproviders.sbr.detail;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserImsiRanHfaDetailTestDataProvider {
    public static Object[] provideTestData() {

        //data on 2134 for EVENT_E_RAN_SESSION_RRC_MEAS_RAW table
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "0000").add(TIME_TO_QUERY_PARAM, "2300")
                .add(DATE_FROM_QUERY_PARAM, "30032012").add(DATE_TO_QUERY_PARAM, "30032012").add(TZ_OFFSET, "+0100")
                .add(IMSI_PARAM, "454063307073257").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
