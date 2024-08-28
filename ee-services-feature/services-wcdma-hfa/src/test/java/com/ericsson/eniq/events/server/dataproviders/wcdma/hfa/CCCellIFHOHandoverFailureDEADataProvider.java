/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.dataproviders.wcdma.hfa;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author eromsza
 * @since 2012
 *
 */
public class CCCellIFHOHandoverFailureDEADataProvider {

    public static Object[] provideTestDataChart() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TYPE_PARAM, CELL).add(CAUSE_CODE_PARAM, "1").add(SUB_CAUSE_CODE_PARAM, "1")
                .add(EVENT_ID_PARAM, "423").add(NODE_PARAM, TEST_VALUE_ACCESS_AREA_NODE)
                .add(DISPLAY_PARAM, CHART_PARAM).add(TIME_QUERY_PARAM, THIRTY_MINUTES)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS, NO_MAX_ROWS)
                .add(WCDMA_HFA_STATE_PARAM, WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataGrid() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TYPE_PARAM, CELL).add(CAUSE_CODE_PARAM, "1").add(SUB_CAUSE_CODE_PARAM, "1")
                .add(EVENT_ID_PARAM, "423").add(NODE_PARAM, TEST_VALUE_ACCESS_AREA_NODE).add(DISPLAY_PARAM, GRID_PARAM)
                .add(TIME_QUERY_PARAM, THIRTY_MINUTES).add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET)
                .add(MAX_ROWS, DEFAULT_MAX_ROWS, NO_MAX_ROWS)
                .add(WCDMA_HFA_STATE_PARAM, WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataChartGroup() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TYPE_PARAM, CELL).add(CAUSE_CODE_PARAM, "1").add(SUB_CAUSE_CODE_PARAM, "1")
                .add(EVENT_ID_PARAM, "423").add(GROUP_NAME_PARAM, "test").add(DISPLAY_PARAM, CHART_PARAM)
                .add(TIME_QUERY_PARAM, THIRTY_MINUTES).add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET)
                .add(MAX_ROWS, DEFAULT_MAX_ROWS, NO_MAX_ROWS)
                .add(WCDMA_HFA_STATE_PARAM, WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataGridGroup() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TYPE_PARAM, CELL).add(CAUSE_CODE_PARAM, "1").add(SUB_CAUSE_CODE_PARAM, "1")
                .add(EVENT_ID_PARAM, "423").add(GROUP_NAME_PARAM, "test").add(DISPLAY_PARAM, GRID_PARAM)
                .add(TIME_QUERY_PARAM, THIRTY_MINUTES).add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET)
                .add(MAX_ROWS, DEFAULT_MAX_ROWS, NO_MAX_ROWS)
                .add(WCDMA_HFA_STATE_PARAM, WCDMA_HFA_SOURCE_AND_TARGET_GROUP_ID).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
