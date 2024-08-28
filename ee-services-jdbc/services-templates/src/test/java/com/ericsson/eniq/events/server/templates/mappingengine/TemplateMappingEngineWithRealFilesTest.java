/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.templates.mappingengine;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.common.exception.TemplateNotFoundException;
import com.ericsson.eniq.events.server.utils.file.FileOperations;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class TemplateMappingEngineWithRealFilesTest {

    private static final String[] MULTI_KEY_VALUES = { "EVENT_ANALYSIS", "APN", "false", "null", "false", "SUM", "_DAY" };

    private static final String MAPPED_VALUES = "common/q_apn_event_analysis_aggregation_summary.vm";

    private static MultiKey multiKey;

    TemplateMappingEngine templateMappingEngine = new TemplateMappingEngine();

    @Before
    public void setUp() {
        templateMappingEngine.setFileOperations(new FileOperations());
        templateMappingEngine.applicationStartup();
        multiKey = new MultiKey(MULTI_KEY_VALUES);
    }

    @After
    public void tearDown() {
        templateMappingEngine.applicationShutdown();
    }

    @Test
    public void testNoDuplicateKeysInCSVFiles() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        final String result = templateMappingEngine.getTemplate(NOTIFICATION_DATA, requestParameters, null);
        System.out.println(result);
    }

    @Test
    public void testGetTemplateReadsFeatureTemplateMappingFile() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        assertThat(templateMappingEngine.getTemplate(NOTIFICATION_DATA, requestParameters, null),
                is("kpi_notification/q_notification_data.vm"));
    }

    @Test(expected = TemplateNotFoundException.class)
    public void testGetTemplateThatDoesntExistWithoutTimerange() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_APN);
        requestParameters.putSingle(KEY_PARAM, KEY_TYPE_SUM);

        templateMappingEngine.getTemplate("NON_EXISTENT_RESOURCE", requestParameters, null);
    }

    @Test(expected = TemplateNotFoundException.class)
    public void testGetTemplateThatDoesntExistWithTimerange() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_APN);
        requestParameters.putSingle(KEY_PARAM, KEY_TYPE_SUM);

        templateMappingEngine.getTemplate("NON_EXISTENT_RESOURCE", requestParameters, null, "_15MIN");
    }

    @Test
    public void testGetTemplateWithTimerange() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_APN);
        requestParameters.putSingle(KEY_PARAM, KEY_TYPE_SUM);

        assertThat(templateMappingEngine.getTemplate(EVENT_ANALYSIS, requestParameters, null, "_15MIN"),
                is("common/q_event_analysis_aggregation_summary.vm"));
    }

    @Test
    public void testGetTemplateWithTimerange_ButWhereNoTimerangeSpecifiedInMappingFile() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_APN);

        assertThat(templateMappingEngine.getTemplate(KPI, requestParameters, null, "_15MIN"),
                is("network/q_network_analysis_KPI_sample.vm"));
    }

    @Test
    public void testGetTemplateWithoutTimerange() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_APN);

        assertThat(templateMappingEngine.getTemplate(QOS_STATISTICS, requestParameters, null),
                is("qos_statistics/q_qos_statistics_summary.vm"));
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.server.templates.mappingengine.TemplateMappingEngine#getTemplatesmap()}.
     */
    @Test
    public void testGetTemplate() {
        assertEquals(MAPPED_VALUES, templateMappingEngine.getTemplate(multiKey));
    }

}
