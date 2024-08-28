/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.piechart;

import static com.ericsson.eniq.events.server.integritytests.piechart.PieChartCauseCodePopulator.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ericsson.eniq.events.server.test.queryresults.PieChartCauseCodeAnalysisResult;
import com.ericsson.eniq.events.server.test.queryresults.PieChartCauseCodeListResult;
import com.ericsson.eniq.events.server.test.queryresults.PieChartSubCauseCodeAnalysisResult;

/**
 * @author eavidat
 * @since 2011
 *
 */
public class PieChartCauseCodeDataValidator {

    private final static Map<Integer, String> causeCodeMappingSGEH = new HashMap<Integer, String>();

    private final static Map<Integer, String> causeCodeMappingLTE = new HashMap<Integer, String>();

    private final static Map<Integer, String> subCauseCodeMappingSGEH = new HashMap<Integer, String>();

    private final static Map<Integer, String> subCauseCodeMappingLTE = new HashMap<Integer, String>();

    private final static Map<Integer, String> cptMappingSGEH = new HashMap<Integer, String>();

    private final static Map<Integer, String> cptMappingLTE = new HashMap<Integer, String>();

    static {
        // Populate SGEH cause codes/descriptions
        causeCodeMappingSGEH.put(causeCode_1_SGEH, causeCode_1_desc_SGEH);
        causeCodeMappingSGEH.put(causeCode_2_SGEH, causeCode_2_desc_SGEH);
        causeCodeMappingSGEH.put(causeCode_3_SGEH, causeCode_2_desc_SGEH);

        // Populate LTE cause codes/descriptions
        causeCodeMappingLTE.put(causeCode_1_LTE, causeCode_1_desc_LTE);
        causeCodeMappingLTE.put(causeCode_2_LTE, causeCode_2_desc_LTE);
        causeCodeMappingLTE.put(causeCode_3_LTE, causeCode_2_desc_LTE);

        // Populate SGEH sub cause codes/descriptions
        subCauseCodeMappingSGEH.put(subCauseCode_1_SGEH, subCauseCode_1_desc_SGEH);
        subCauseCodeMappingSGEH.put(subCauseCode_2_SGEH, subCauseCode_2_desc_SGEH);

        // Populate LTE sub cause codes/descriptions
        subCauseCodeMappingLTE.put(subCauseCode_1_LTE, subCauseCode_1_desc_LTE);
        subCauseCodeMappingLTE.put(subCauseCode_2_LTE, subCauseCode_2_desc_LTE);

        // Populate SGEH sub cause prototypes /descriptions
        cptMappingSGEH.put(causeProtType, causeProtType_desc_SGEH);

        // Populate LTE sub cause prototypes /descriptions
        cptMappingLTE.put(causeProtType, causeProtType_desc_LTE);
    }

    public void makeAssertionsCCList(final List<PieChartCauseCodeListResult> summaryResult) {
        assertThat(summaryResult.size(), is(4));

        final PieChartCauseCodeListResult firstResult = summaryResult.get(0);
        assertThat(firstResult.getCauseCodeID(), is(Integer.toString(causeCode_1_SGEH)));
        assertThat(firstResult.getCauseCode(), is(causeCodeMappingSGEH.get(causeCode_1_SGEH)));

        final PieChartCauseCodeListResult secondResult = summaryResult.get(1);
        assertThat(secondResult.getCauseCodeID(), is(Integer.toString(causeCode_2_SGEH)));
        assertThat(secondResult.getCauseCode(), is(causeCodeMappingSGEH.get(causeCode_2_SGEH)));

        final PieChartCauseCodeListResult thirdResult = summaryResult.get(2);
        assertThat(thirdResult.getCauseCodeID(), is(Integer.toString(causeCode_1_LTE)));
        assertThat(thirdResult.getCauseCode(), is(causeCodeMappingLTE.get(causeCode_1_LTE)));

        final PieChartCauseCodeListResult fourthResult = summaryResult.get(3);
        assertThat(fourthResult.getCauseCodeID(), is(Integer.toString(causeCode_2_LTE)));
        assertThat(fourthResult.getCauseCode(), is(causeCodeMappingLTE.get(causeCode_2_LTE)));
    }

    public void makeAssertionsCCAnaylsis(final List<PieChartCauseCodeAnalysisResult> summaryResult) {
        assertThat(summaryResult.size(), is(2));

        final PieChartCauseCodeAnalysisResult firstResult = summaryResult.get(0);
        assertThat(firstResult.getCauseCodeID(), is(Integer.toString(causeCode_1_SGEH)));
        assertThat(firstResult.getCauseCode(), is(causeCodeMappingSGEH.get(causeCode_1_SGEH)));
        assertThat(firstResult.getNumberOfErrors(), is(2));
        assertThat(firstResult.getNumberOfOccurences(), is(2));

        final PieChartCauseCodeAnalysisResult secondResult = summaryResult.get(1);
        assertThat(secondResult.getCauseCodeID(), is(Integer.toString(causeCode_1_LTE)));
        assertThat(secondResult.getCauseCode(), is(causeCodeMappingLTE.get(causeCode_1_LTE)));
        assertThat(secondResult.getNumberOfErrors(), is(2));
        assertThat(secondResult.getNumberOfOccurences(), is(1));
    }

    public void makeAssertionsSCCAnaylsis(final List<PieChartSubCauseCodeAnalysisResult> summaryResult) {
        assertThat(summaryResult.size(), is(1));

        final PieChartSubCauseCodeAnalysisResult firstResult = summaryResult.get(0);
        assertThat(firstResult.getCauseCodeID(), is(causeCode_1_SGEH));
        assertThat(firstResult.getSubCauseCodeID(), is(subCauseCode_1_SGEH));
        assertThat(firstResult.getSubCauseCode(), is(subCauseCodeMappingSGEH.get(subCauseCode_1_SGEH) + " ("
                + cptMappingSGEH.get(causeProtType) + ")"));
        assertThat(firstResult.getNumberOfErrors(), is(2));
        assertThat(firstResult.getNumberOfOccurences(), is(2));
    }
}