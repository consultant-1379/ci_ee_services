package com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

public class TerminalFailedEventAnalysisDataProvider {

    public static Object[] provideTestData() {

        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK)
                .add(TAC, "11223344")
                .add(EVENT_ID_PARAM, CALL_SETUP_FAILURE_EVENT_ID, CALL_DROP_EVENT_ID)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET)
                .add(MAX_ROWS, DEFAULT_MAX_ROWS, NO_MAX_ROWS)
                .build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
