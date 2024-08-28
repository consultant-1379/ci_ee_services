/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.automation.dataproviders.wcdma.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CATEGORY_ID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.HIER3_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_BSC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.WCDMA_CFA_DRILL_CATEGORY;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.convertToArrayOfMultivaluedMap;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DEFAULT_MAX_ROWS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.FIVE_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.NO_MAX_ROWS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ONE_DAY;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ONE_WEEK;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_VALUE_TIMEZONE_OFFSET;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.THIRTY_MINUTES;

import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

/**
 * @author etonayr
 * @since 2011
 *
 */
public class RNCByRabTypeDescDataProvider {

    public static Object[] provideTestDataWithEventId() {
        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
                .add(HIER3_ID, "1234567890123").add(WCDMA_CFA_DRILL_CATEGORY, "1").add(TYPE_PARAM, TYPE_BSC)
                .add(TIME_QUERY_PARAM, THIRTY_MINUTES, FIVE_MINUTES, ONE_DAY, ONE_WEEK)
                .add(EVENT_ID_PARAM, "438", "456").add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET)
                .add(MAX_ROWS, DEFAULT_MAX_ROWS, NO_MAX_ROWS).add(CATEGORY_ID_PARAM, "0", "1", "2").build();
        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
    }
}
