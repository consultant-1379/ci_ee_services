/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.templates.exception.ResourceInitializationException;
import com.ericsson.eniq.events.server.templates.utils.TemplateUtils;

/**
 * @author eemecoy
 *
 */
public class TemplateUtilsIntegrationTest {

    private static final String TEST_TEMPLATE = "q_test_template.vm";

    private final TemplateUtils templateUtils = new TemplateUtils();

    @Before
    public void setUp() throws Exception {
        templateUtils.applicationStartup();

    }

    @After
    public void tearDown() {
        templateUtils.applicationShutdown();
    }

    @Test
    public void testGetTemplate() throws Exception {
        final Map<String, String> parameters = new HashMap<String, String>();

        parameters.put("count", "10");
        parameters.put("type", "cell");

        final String sql = templateUtils.getQueryFromTemplate(TEST_TEMPLATE, parameters);
        assertNotNull(sql);
    }

    @Test
    public void testGetQueryFromTemplate() throws Exception {
        final Map<String, String> parameters = new HashMap<String, String>();

        parameters.put("type", "IMSI");
        parameters.put("key", "ERR");
        final String sql = templateUtils.getQueryFromTemplate(TEST_TEMPLATE, parameters);
        assertNotNull(sql);

        parameters.clear();
        parameters.put("type", "SGSN");
        parameters.put("timerange", "TR_2");
        templateUtils.getQueryFromTemplate(TEST_TEMPLATE, parameters);
        assertNotNull(sql);
    }

    @Test
    public void testGetDifferentQueriesFromTemplate() throws Exception {
        final Map<String, String> parameters = new HashMap<String, String>();

        parameters.put("type", "SGSN");
        parameters.put("key", "ERR");
        final String sql = templateUtils.getQueryFromTemplate(TEST_TEMPLATE, parameters);
        assertNotNull(sql);

        parameters.clear();
        parameters.put("type", "SGSN");
        parameters.put("timerange", "TR_1");

        templateUtils.getQueryFromTemplate(TEST_TEMPLATE, parameters);
        assertNotNull(sql);

    }

    @Test(expected = ResourceInitializationException.class)
    public void testGetQueryFromTemplateResourceNotFound() throws Exception {
        templateUtils.getQueryFromTemplate("bogus_template.vm", null);
    }

    @Test
    public void testGetQueryNoParams() throws Exception {
        final String sql = templateUtils.getQueryFromTemplate(TEST_TEMPLATE, null);
        assertNotNull(sql);
    }

}
