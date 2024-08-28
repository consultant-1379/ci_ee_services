/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.summary;

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
public class SBrowserImsiRabSumTestDataProvider {
    public static Object[] provideTestData() {

        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, ONE_WEEK).add(TZ_OFFSET, "+0000").add(IMSI_PARAM, "454063306019097").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());

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
        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_FROM_QUERY_PARAM, "1100").add(TIME_TO_QUERY_PARAM, "1300")
        //                .add(DATE_FROM_QUERY_PARAM, "03102011").add(DATE_TO_QUERY_PARAM, "03102011").add(TZ_OFFSET, "+0100")
        //                .add(IMSI_PARAM, "454061101367379").build();
        //        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
