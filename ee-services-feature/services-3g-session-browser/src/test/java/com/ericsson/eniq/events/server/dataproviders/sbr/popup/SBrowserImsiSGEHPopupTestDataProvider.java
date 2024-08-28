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
public class SBrowserImsiSGEHPopupTestDataProvider {
    public static Object[] provideTestData() {

        //data on 926 in EVENT_E_SGEH_ERR_RAW
//        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
//                .add(TIME_FROM_QUERY_PARAM, "0000").add(TIME_TO_QUERY_PARAM, "2300")
//                .add(DATE_FROM_QUERY_PARAM, "15032012").add(DATE_TO_QUERY_PARAM, "15032012").add(TZ_OFFSET, "+0000")
//                .add(IMSI_PARAM, "454063302898296").add(EVENT_TIME_FROM_PARAM, "1331784300000")
//                .add(EVENT_TIME_TO_PARAM, "1331784300000").add(EVENT_ID_PARAM, "1").build();
//        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());

        //data on 926 in EVENT_E_SGEH_ERR_RAW
        //"eventTime":"1332471594000","eventType":"ACTIVATE","cId":null,"cellId":null,"duration":null,"tac":1303600,"errFlag":"true","eventClassType":"CORE"
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_FROM_QUERY_PARAM, "2300").add(TIME_TO_QUERY_PARAM, "2300")
                .add(DATE_FROM_QUERY_PARAM, "21032012").add(DATE_TO_QUERY_PARAM, "23032012").add(TZ_OFFSET, "+0000")
                .add(IMSI_PARAM, "454063899810676").add(EVENT_TIME_FROM_PARAM, "1332471594000")
                .add(EVENT_TIME_TO_PARAM, "1332471594000").add(EVENT_ID_PARAM, "1").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
