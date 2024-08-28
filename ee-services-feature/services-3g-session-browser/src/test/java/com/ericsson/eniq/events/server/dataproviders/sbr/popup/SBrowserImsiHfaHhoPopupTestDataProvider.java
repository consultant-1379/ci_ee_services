/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.dataproviders.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserImsiHfaHhoPopupTestDataProvider {
    public static Object[] provideTestData() {

        //data for RAN HFA HHO table on 926 and for EVENT_E_RAN_SESSION_INTER_OUT_HHO_FAIL_RAW table on 926
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "1325").add(TIME_TO_QUERY_PARAM, "1325")
                .add(DATE_FROM_QUERY_PARAM, "18042012").add(DATE_TO_QUERY_PARAM, "25042012").add(TZ_OFFSET, "+0100")
                .add(IMSI_PARAM, "454063302610319").add(EVENT_TIME_FROM_PARAM, "1335174236453")
                .add(EVENT_TIME_TO_PARAM, "1335174236454").add(EVENT_ID_PARAM, "458").build();

        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());

    }
}
