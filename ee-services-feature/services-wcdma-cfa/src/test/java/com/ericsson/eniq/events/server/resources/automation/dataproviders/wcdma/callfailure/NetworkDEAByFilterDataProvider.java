package com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.BSC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CATEGORY_ID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CELL;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CELL_ID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISPLAY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.FILTER_TYPE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.FILTER_VALUE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_CELL_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RAN_VENDOR_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.RNC_FDN_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.WCDMA_CFA_DRILL_CATEGORY;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.convertToArrayOfMultivaluedMap;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DEFAULT_MAX_ROWS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ERICSSON;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.FIVE_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.GRID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ONE_DAY;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ONE_WEEK;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.RNC01_ALTERNATIVE_FDN;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_VALUE_TIMEZONE_OFFSET;

import com.ericsson.eniq.events.server.common.ApplicationConstants;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

public class NetworkDEAByFilterDataProvider {

    public static Object[] provideTestDataForSummmaryToRaw_Controller_withEventID() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, BSC)
                .add(HIER3_ID, "1234567890123").add(DISPLAY_PARAM, GRID).add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET)
                .add(MAX_ROWS, DEFAULT_MAX_ROWS).add(EVENT_ID_PARAM, "438", "456")
                .add(CATEGORY_ID_PARAM, "0", "1", "2", "3").add(WCDMA_CFA_DRILL_CATEGORY, "312").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForSummmaryToRaw_Cell_withEventID() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, CELL)
                .add(HIER3_CELL_ID, "1234567890123").add(DISPLAY_PARAM, GRID)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS)
                .add(EVENT_ID_PARAM, "438", "456").add(CATEGORY_ID_PARAM, "0", "1", "2", "3")
                .add(WCDMA_CFA_DRILL_CATEGORY, "312").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForSummmaryToRaw_Controller_withoutEventID() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, BSC)
                .add(RAN_VENDOR_PARAM, ERICSSON).add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN).add(DISPLAY_PARAM, GRID)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForSummmaryToRaw_Cell_withoutEventID() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, CELL).add(CELL_ID_PARAM, "1")
                .add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN).add(DISPLAY_PARAM, GRID).add(RAN_VENDOR_PARAM, ERICSSON)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForDisconnectionToRaw_Controller_withEventID() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, BSC)
                .add(HIER3_ID, "1234567890123").add(DISPLAY_PARAM, GRID).add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET)
                .add(MAX_ROWS, DEFAULT_MAX_ROWS).add(EVENT_ID_PARAM, "438", "456")
                .add(FILTER_TYPE, ApplicationConstants.DISCONNECTION_CODE).add(FILTER_VALUE, "2,1")
                .add(CATEGORY_ID_PARAM, "0", "1", "2", "3").add(WCDMA_CFA_DRILL_CATEGORY, "312").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForDisconnectionToRaw_Cell_withEventID() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, CELL)
                .add(HIER3_CELL_ID, "1234567890123").add(DISPLAY_PARAM, GRID)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS)
                .add(EVENT_ID_PARAM, "438", "456").add(CATEGORY_ID_PARAM, "0", "1", "2", "3")
                .add(FILTER_TYPE, ApplicationConstants.DISCONNECTION_CODE).add(FILTER_VALUE, "2,1")
                .add(WCDMA_CFA_DRILL_CATEGORY, "312").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForDisconnectionToRaw_Controller_withoutEventID() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, BSC)
                .add(RAN_VENDOR_PARAM, ERICSSON).add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN).add(DISPLAY_PARAM, GRID)
                .add(FILTER_TYPE, ApplicationConstants.DISCONNECTION_CODE).add(FILTER_VALUE, "2,1")
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForDisconnectionToRaw_Cell_withoutEventID() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, CELL).add(CELL_ID_PARAM, "1")
                .add(RNC_FDN_PARAM, RNC01_ALTERNATIVE_FDN).add(DISPLAY_PARAM, GRID).add(RAN_VENDOR_PARAM, ERICSSON)
                .add(FILTER_TYPE, ApplicationConstants.DISCONNECTION_CODE).add(FILTER_VALUE, "2,1")
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS).build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForRabToRaw_Controller_withEventID() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, BSC)
                .add(HIER3_ID, "1234567890123").add(DISPLAY_PARAM, GRID).add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET)
                .add(MAX_ROWS, DEFAULT_MAX_ROWS).add(EVENT_ID_PARAM, "438", "456")
                .add(FILTER_TYPE, ApplicationConstants.RAB_TYPE).add(FILTER_VALUE, "2")
                .add(CATEGORY_ID_PARAM, "0", "1", "2", "3").add(WCDMA_CFA_DRILL_CATEGORY, "312").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

    public static Object[] provideTestDataForRabToRaw_Cell_withEventID() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(TIME_QUERY_PARAM, FIVE_MINUTES, ONE_DAY, ONE_WEEK).add(TYPE_PARAM, CELL)
                .add(HIER3_CELL_ID, "1234567890123").add(DISPLAY_PARAM, GRID)
                .add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS)
                .add(EVENT_ID_PARAM, "438", "456").add(CATEGORY_ID_PARAM, "0", "1", "2", "3")
                .add(FILTER_TYPE, ApplicationConstants.RAB_TYPE).add(FILTER_VALUE, "2")
                .add(WCDMA_CFA_DRILL_CATEGORY, "312").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }

}
