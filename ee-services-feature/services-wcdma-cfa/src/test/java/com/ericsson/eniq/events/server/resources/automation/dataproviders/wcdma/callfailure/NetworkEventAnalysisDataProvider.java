package com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

public class NetworkEventAnalysisDataProvider {

    public static Object[] provideTestDataForBsc() {

        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, BSC)
                .add(NODE_PARAM, "ONRM_ROOT_MO_R:RNC01:RNC01,Ericsson,3G").add(DISPLAY_PARAM, GRID)
                //node=<ALTERNATE_FDN>,<VENDOR>,<TECHNOLOGY>
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS).add(EVENT_ID_PARAM, "438")
                .add(CATEGORY_ID_PARAM, "0").add(WCDMA_CFA_DRILL_CATEGORY, "312").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForControllerGroup() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, BSC).add(DISPLAY_PARAM, GRID)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS, NO_MAX_ROWS)
                .add(GROUP_NAME_PARAM, SAMPLE_BSC_GROUP).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForCell() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, TYPE_CELL)
                .add(DISPLAY_PARAM, GRID).add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET)
                .add(MAX_ROWS, DEFAULT_MAX_ROWS, NO_MAX_ROWS).add(GROUP_NAME_PARAM, SAMPLE_ECELL_GROUP).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideDataForAccessArea() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, TYPE_CELL)
                .add(DISPLAY_PARAM, GRID).add(NODE_PARAM, TEST_VALUE_ACCESS_AREA_NODE).add(EVENT_ID_PARAM, "438")
                .add(CATEGORY_ID_PARAM, "0").add(WCDMA_CFA_DRILL_CATEGORY, "312")
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
