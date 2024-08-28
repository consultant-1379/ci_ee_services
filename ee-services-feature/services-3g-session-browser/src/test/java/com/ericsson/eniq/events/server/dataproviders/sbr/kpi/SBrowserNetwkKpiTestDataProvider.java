/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.dataproviders.sbr.kpi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserNetwkKpiTestDataProvider {
    public static Object[] provideTestData() {

        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIFTEEN_MINUTES_TEST, THIRTY_MINUTES, ONE_HOUR, TWO_HOURS, SIX_HOURS,
                        TWELVE_HOURS).add(TZ_OFFSET, "+0000").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_FROM_QUERY_PARAM, "1400").add(TIME_TO_QUERY_PARAM, "1600")
        //                .add(DATE_FROM_QUERY_PARAM, "19112012").add(DATE_TO_QUERY_PARAM, "19112012").add(TZ_OFFSET, "+0100")
        //                .build();
        //        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
