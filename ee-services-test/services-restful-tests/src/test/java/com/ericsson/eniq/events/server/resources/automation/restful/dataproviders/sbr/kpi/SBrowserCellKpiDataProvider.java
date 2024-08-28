/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.kpi;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserCellKpiDataProvider {
    public static Object[] provideTestData() {

        //use this one to get data
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "1100").add(TIME_TO_QUERY_PARAM, "1115")
                .add(DATE_FROM_QUERY_PARAM, "03102011").add(DATE_TO_QUERY_PARAM, "03102011").add(TZ_OFFSET, "+0000")
                .add(NODE_PARAM, "13021,,smartone_R:RNC10:RNC10,Ericsson,3G").add(TYPE_PARAM, TYPE_CELL).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());

        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_QUERY_PARAM, FIVE_MINUTES, FIFTEEN_MINUTES_TEST, THIRTY_MINUTES, ONE_DAY, ONE_WEEK)
        //                .add(TZ_OFFSET, "+0000").add(NODE_PARAM, "00,,ONRM_RootMo_R:RNC01:RNC01,Ericsson,3G")
        //                .add(TYPE_PARAM, TYPE_CELL).build();
        //        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
