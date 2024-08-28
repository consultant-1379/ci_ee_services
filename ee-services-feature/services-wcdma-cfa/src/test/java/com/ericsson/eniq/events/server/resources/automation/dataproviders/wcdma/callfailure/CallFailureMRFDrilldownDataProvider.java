package com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

public class CallFailureMRFDrilldownDataProvider {

    public static Object[] provideTestData() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(RNC_FDN_PARAM, "RNC01").add(CELL_ID, "1").add(IMSI_PARAM, "123456789012345").add(CAUSE_VALUE, "1")
                .add(EXTENDED_CAUSE_VALUE, "2").add(TIME_QUERY_PARAM, THIRTY_MINUTES).add(RAN_VENDOR_PARAM, "Ericsson")
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS, NO_MAX_ROWS).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());

    }
}
