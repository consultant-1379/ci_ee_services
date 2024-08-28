/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.terminalgroupanalysis;

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
public class TGAFailureServiceIntegrationTest extends ServiceBaseTest {

    @Resource(name = "terminalGroupAnalysisSOHOFailureService")
    private TerminalGASOHOFailureService terminalGroupAnalysisSOHOFailureService;

    @Resource(name = "terminalGroupAnalysisHSDSCHFailureService")
    private TerminalGAHSDSCHFailureService terminalGroupAnalysisHSDSCHFailureService;

    @Resource(name = "terminalGroupAnalysisIFHOFailureService")
    private TerminalGAIFHOFailureService terminalGroupAnalysisIFHOFailureService;

    @Resource(name = "terminalGroupAnalysisIRATFailureService")
    private TerminalGAIRATFailureService terminalGroupAnalysisIRATFailureService;

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataForSOHO(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalGroupAnalysisSOHOFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataAsCSVForSOHO(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, terminalGroupAnalysisSOHOFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataForHSDSCH(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalGroupAnalysisHSDSCHFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataAsCSVForHSDSCH(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, terminalGroupAnalysisHSDSCHFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataForIFHO(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalGroupAnalysisIFHOFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataAsCSVForIFHO(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, terminalGroupAnalysisIFHOFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataForIRAT(final MultivaluedMap<String, String> requestParameters) {
        runQuery(requestParameters, terminalGroupAnalysisIRATFailureService);
    }

    @Test
    @Parameters(source = HFATATestDataProvider.class)
    public void getDataAsCSVForIRAT(final MultivaluedMap<String, String> requestParameters) {
        runQueryForCSV(requestParameters, terminalGroupAnalysisIRATFailureService);
    }

}
