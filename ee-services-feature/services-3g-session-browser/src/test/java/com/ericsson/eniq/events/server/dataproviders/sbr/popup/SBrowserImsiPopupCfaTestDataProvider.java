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
public class SBrowserImsiPopupCfaTestDataProvider {
    public static Object[] provideTestData() {

        //data for RAN CFA internal system release (438) on 1127
        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_FROM_QUERY_PARAM, "1100").add(TIME_TO_QUERY_PARAM, "1300")
        //                .add(DATE_FROM_QUERY_PARAM, "03102011").add(DATE_TO_QUERY_PARAM, "03102011").add(TZ_OFFSET, "+0100")
        //                .add(IMSI_PARAM, "454061101367379").add(EVENT_TIME_FROM_PARAM, "1317640796082")
        //                .add(EVENT_TIME_TO_PARAM, "1317640796083").add(EVENT_ID_PARAM, "438").build();

        //data for RAN CFA internal call setup failure (456) on 1127
                final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                        .add(TIME_FROM_QUERY_PARAM, "0000").add(TIME_TO_QUERY_PARAM, "0000")
                        .add(DATE_FROM_QUERY_PARAM, "16052012").add(DATE_TO_QUERY_PARAM, "17052012").add(TZ_OFFSET, "+0100")
                        .add(IMSI_PARAM, "454063302998991").add(EVENT_TIME_FROM_PARAM, "1337205769868")
                        .add(EVENT_TIME_TO_PARAM, "1337205769869").add(EVENT_ID_PARAM, "438").build();

        //data for RAN CFA internal system release (438) on 2134
        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_FROM_QUERY_PARAM, "1400").add(TIME_TO_QUERY_PARAM, "1500")
        //                .add(DATE_FROM_QUERY_PARAM, "18042012").add(DATE_TO_QUERY_PARAM, "18042012").add(TZ_OFFSET, "+0100")
        //                .add(IMSI_PARAM, "454061101395120").add(EVENT_TIME_FROM_PARAM, "1334760372530")
        //                .add(EVENT_TIME_TO_PARAM, "1334760372531").add(EVENT_ID_PARAM, "438").build();

        //data for RAN CFA internal system release (438) on 926
//        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
//                .add(TIME_FROM_QUERY_PARAM, "0000").add(TIME_TO_QUERY_PARAM, "0200")
//                .add(DATE_FROM_QUERY_PARAM, "23032012").add(DATE_TO_QUERY_PARAM, "23032012").add(TZ_OFFSET, "+0100")
//                .add(IMSI_PARAM, "454063302931991").add(EVENT_TIME_FROM_PARAM, "1332461284053")
//                .add(EVENT_TIME_TO_PARAM, "1332461284054").add(EVENT_ID_PARAM, "438").build();


        //data for RAN CFA internal call setup failure (456) on 2134
        //        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
        //                .add(TIME_FROM_QUERY_PARAM, "1100").add(TIME_TO_QUERY_PARAM, "1300")
        //                .add(DATE_FROM_QUERY_PARAM, "03102011").add(DATE_TO_QUERY_PARAM, "03102011").add(TZ_OFFSET, "+0100")
        //                .add(IMSI_PARAM, "454063302916329").add(EVENT_TIME_FROM_PARAM, "1317640085022")
        //                .add(EVENT_TIME_TO_PARAM, "1317640085023").add(EVENT_ID_PARAM, "456").build();

        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
