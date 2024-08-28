/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.terminalanalysis;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.eniq.events.server.resources.automation.ServiceBaseTest;
import com.ericsson.eniq.events.server.dataproviders.wcdma.hfa.HFATATestDataProvider;
import org.springframework.test.context.ContextConfiguration;
/**
 * @author eseuhon
 * @since 2011
 *
 */
@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"classpath:wcdma-hfa-service-context.xml"})
public class TAFailureServiceIntegrationTest extends ServiceBaseTest {

    @Resource(name = "terminalAnalysisSOHOFailureService")
    private TerminalAnalysisSOHOFailureService terminalAnalysisSOHOFailureService;

    @Resource(name = "terminalAnalysisHSDSCHFailureService")
    private TerminalAnalysisHSDSCHFailureService terminalAnalysisHSDSCHFailureService;

    @Resource(name = "terminalAnalysisIFHOFailureService")
    private TerminalAnalysisIFHOFailureService terminalAnalysisIFHOFailureService;

    @Resource(name = "terminalAnalysisIRATFailureService")
    private TerminalAnalysisIRATFailureService terminalAnalysisIRATFailureService;

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataForSOHO(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalAnalysisSOHOFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataAsCSVForSOHO(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, terminalAnalysisSOHOFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataForHSDSCH(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalAnalysisHSDSCHFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataAsCSVForHSDSCH(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, terminalAnalysisHSDSCHFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataForIFHO(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalAnalysisIFHOFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataAsCSVForIFHO(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, terminalAnalysisIFHOFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataForIRAT(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalAnalysisIRATFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataAsCSVForIRAT(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, terminalAnalysisIRATFailureService);
    }

}
