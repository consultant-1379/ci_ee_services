/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.dataproviders.sbr.kpi.core;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author ejoegaf
 * @since 2012
 *
 */
public class SBrowserRncCoreKpiDrillTestDataProvider {
    public static Object[] provideTestData() {

        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "1300").add(TIME_TO_QUERY_PARAM, "1500")
                .add(DATE_FROM_QUERY_PARAM, "13052013").add(DATE_TO_QUERY_PARAM, "13052013").add(TZ_OFFSET, "+0100")
                .add(NODE_PARAM, "smartone_R:RNC10:RNC10,Ericsson,3G").add(TYPE_PARAM, TYPE_BSC)
                .add(DRILLTYPE_PARAM, TYPE_MAN, TAC, MAKE_MODEL).add(KPI_END_TIME, "1368448200000")
                .add(KPI_ID, "0", "1", "2", "3", "4", "5", "6", "7").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
