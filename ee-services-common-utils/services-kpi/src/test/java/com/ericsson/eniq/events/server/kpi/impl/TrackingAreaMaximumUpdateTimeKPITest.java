/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.kpi.impl;

import static com.ericsson.eniq.events.server.kpi.KPIConstants.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TrackingAreaMaximumUpdateTimeKPITest extends KPIBaseTestClass {

    private TrackingAreaAverageUpdateTimeKPI objUnderTest;
    @Before
    public void onSetUp(){
        objUnderTest = new TrackingAreaAverageUpdateTimeKPI();
        objUnderTest.kpiUtilities = this.kpiUtilities;
    }
    
    @Test
    public void testgetEventID() {
        final String expected = "8";
        final String actual = objUnderTest.getEventID();

        assertEquals(expected, actual);
    }

    @Test
    public void testgetSuccessColumnsAgg() {
        final Map<String, String> expected = new HashMap<String, String>();
        expected.put(NO_OF_SUCCESSES, NO_OF_SUCCESSES_AGG);

        final Map<String, String> actual = objUnderTest.getSuccessColumnsAgg();

        assertEquals(expected, actual);
    }

    @Test
    public void testgetSuccessColumnsRaw() {
        final Map<String, String> expected = null;

        final Map<String, String> actual = objUnderTest.getSuccessColumnsRaw();

        assertEquals(expected, actual);
    }

    @Test
    public void testgetErrorColumnsAgg() {
    	final Map<String, String> expected = new HashMap<String, String>();
        expected.put(NO_OF_ERRORS, NO_OF_ERRORS_AGG);
        final Map<String, String> actual = objUnderTest.getErrorColumnsAgg();

        assertEquals(expected, actual);
    }

    @Test
    public void testgetErrorColumnsRaw() {
        final Map<String, String> expected = null;

        final Map<String, String> actual = objUnderTest.getErrorColumnsRaw();

        assertEquals(expected, actual);
    }

    @Test
    public void testgetKPICalculation() {
        final String expected = "max(isnull((case when isnull(suc.EVENT_ID, err.EVENT_ID)=8 then (cast (round((isnull((isnull(TOTAL_DURATION,0) )/cast(isnull(NO_OF_SUCCESSES,0) as float),0)), 2) as numeric(5,2))) end),0)) as 'Tracking Area Average Update Time'\r\n";
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(SUCCESS_TABLE_ALIAS_VARIABLE, SUC_AS_TABLE);
        parameters.put(ERROR_TABLE_ALIAS_VARIABLE, ERR_AS_TABLE);

        final String actual = objUnderTest.getKPICalculation(parameters);

        Assert.assertThat(actual, Matchers.equalToIgnoringWhiteSpace(expected));
    }
}