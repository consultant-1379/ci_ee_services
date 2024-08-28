package com.ericsson.eniq.events.server.dataproviders.lte.cfa;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISPLAY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.GRID_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.test.automation.util.CombinationUtils.convertToArrayOfMultivaluedMap;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.DEFAULT_MAX_ROWS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TEST_VALUE_TIMEZONE_OFFSET;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGenerator;
import com.ericsson.eniq.events.server.test.automation.util.CombinationGeneratorImpl;

public class LTECFACauseCodeListTestDataProvider {
	
	 private LTECFACauseCodeListTestDataProvider() {

	    }
	 
	 public static Object[] provideTestData() {
	        final CombinationGenerator<String> combinationGenerator = new CombinationGeneratorImpl.Builder<String>()
	                .add(DISPLAY_PARAM, GRID_PARAM).add(TZ_OFFSET, TEST_VALUE_TIMEZONE_OFFSET).add(MAX_ROWS, DEFAULT_MAX_ROWS).build();
	        return convertToArrayOfMultivaluedMap(combinationGenerator.getAllCombinations());
	    }

}
