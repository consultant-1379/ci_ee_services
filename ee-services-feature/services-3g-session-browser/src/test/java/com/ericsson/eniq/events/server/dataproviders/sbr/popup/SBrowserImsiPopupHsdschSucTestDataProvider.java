/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.dataproviders.sbr.popup;

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
                .add(TIME_FROM_QUERY_PARAM, "0350").add(TIME_TO_QUERY_PARAM, "1550")
                .add(DATE_FROM_QUERY_PARAM, "02122012").add(DATE_TO_QUERY_PARAM, "02122012").add(TZ_OFFSET, "+0000")
                .add(IMSI_PARAM, "454063306019097").add(EVENT_TIME_FROM_PARAM, "1354424411304")
                .add(EVENT_TIME_TO_PARAM, "1354424411304")
                .add(EVENT_ID_PARAM, Integer.toString(INTERNAL_SUCCESSFUL_HSDSCH_CELL_CHANGE)).build();

        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
