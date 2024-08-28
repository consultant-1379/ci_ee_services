/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.detail;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserImsiRabDetailTestDataProvider {
    public static Object[] provideTestData() {

        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_QUERY_PARAM, FIVE_MINUTES, FIFTEEN_MINUTES_TEST, THIRTY_MINUTES, ONE_DAY, ONE_WEEK)
        //                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(IMSI_PARAM, "460010000757672").build();
        //        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());

        //data on 926 for EVENT_E_RAN_SESSION_SUC_HSDSCH_CELL_CHANGE_RAW table
        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_FROM_QUERY_PARAM, "0000").add(TIME_TO_QUERY_PARAM, "2300")
        //                .add(DATE_FROM_QUERY_PARAM, "15032012").add(DATE_TO_QUERY_PARAM, "15032012").add(TZ_OFFSET, "+0000")
        //                .add(IMSI_PARAM, "454063307182873").build();
        //        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());

        //data on 926 for EVENT_E_RAN_SESSION_INTER_OUT_HHO_FAIL_RAW table
        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_FROM_QUERY_PARAM, "0000").add(TIME_TO_QUERY_PARAM, "2300")
        //                .add(DATE_FROM_QUERY_PARAM, "15032012").add(DATE_TO_QUERY_PARAM, "15032012").add(TZ_OFFSET, "+0000")
        //                .add(IMSI_PARAM, "454061106124067").build();
        //        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());

        //data on 1127 for EVENT_E_RAN_SESSION_RAW table
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "1343").add(TIME_TO_QUERY_PARAM, "1343")
                .add(DATE_FROM_QUERY_PARAM, "13112012").add(DATE_TO_QUERY_PARAM, "20112012").add(TZ_OFFSET, "+0000")
                .add(IMSI_PARAM, "454064500551978").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
