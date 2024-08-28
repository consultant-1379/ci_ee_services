/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.kpi.userplane;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import java.util.Date;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

public class SBrowserRncUkpiDrillTestDataProvider {
    public static Object[] provideTestData() {

        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIFTEEN_MINUTES_TEST, THIRTY_MINUTES, ONE_HOUR, TWO_HOURS, SIX_HOURS,
                        TWELVE_HOURS).add(KPI_END_TIME, String.valueOf(new Date().getTime() - 1000000))
                .add(TZ_OFFSET, "+0000").add(NODE_PARAM, "smartone_R:RNC10:RNC10,Ericsson,3G")
                .add(TYPE_PARAM, TYPE_BSC).add(DRILLTYPE_PARAM, SBrowserUserPlaneKpiDrillTypes.getDrillTypes())
                .add(KPI_ID, "0", "1", "2", "3", "4", "5").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
