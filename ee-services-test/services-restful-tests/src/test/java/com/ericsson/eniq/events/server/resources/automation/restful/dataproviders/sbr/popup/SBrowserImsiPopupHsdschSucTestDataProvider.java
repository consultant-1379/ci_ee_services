/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.restful.dataproviders.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserImsiPopupHsdschSucTestDataProvider {
    public static Object[] provideTestData() {

        //data for EVENT_E_RAN_SESSION_SUC_HSDSCH_CELL_CHANGE_RAW Table on 2134
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "0000").add(TIME_TO_QUERY_PARAM, "2300")
                .add(DATE_FROM_QUERY_PARAM, "15032012").add(DATE_TO_QUERY_PARAM, "15032012").add(TZ_OFFSET, "+0000")
                .add(IMSI_PARAM, "454063307052126").add(EVENT_TIME_FROM_PARAM, "1331789491000")
                .add(EVENT_TIME_TO_PARAM, "1331789491001")
                .add(EVENT_ID_PARAM, Integer.toString(INTERNAL_SUCCESSFUL_HSDSCH_CELL_CHANGE)).build();

        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
