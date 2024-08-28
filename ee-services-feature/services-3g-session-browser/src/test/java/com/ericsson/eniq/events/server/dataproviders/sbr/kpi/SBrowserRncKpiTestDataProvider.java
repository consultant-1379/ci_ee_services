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
 * @author ejoegaf
 * @since 2012
 *
 */
public class SBrowserRncKpiTestDataProvider {
    public static Object[] provideTestData() {

        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIFTEEN_MINUTES_TEST, THIRTY_MINUTES, ONE_HOUR, TWO_HOURS, SIX_HOURS,
                        TWELVE_HOURS).add(TZ_OFFSET, "+0000").add(NODE_PARAM, "smartone_R:RNC10:RNC10,Ericsson,3G")
                .add(TYPE_PARAM, TYPE_BSC).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_FROM_QUERY_PARAM, "1100").add(TIME_TO_QUERY_PARAM, "1115")
        //                .add(DATE_FROM_QUERY_PARAM, "03102011").add(DATE_TO_QUERY_PARAM, "03102011").add(TZ_OFFSET, "+0000")
        //                .add(NODE_PARAM, "smartone_R:RNC10:RNC10,Ericsson,3G").add(TYPE_PARAM, TYPE_BSC).build();
        //return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
