/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.utils.test.wcdma.cfa;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.*;

import com.ericsson.eniq.events.server.utils.wcdma.cfa.WcdmaCfaUtils;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2013
 *
 */
public class WcdmaCfaUtilsTest {

    private MultivaluedMap<String, String> requestParameters;

    private Map<String, Object> serviceSpecificTemplateParameters;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        requestParameters = new MultivaluedMapImpl();
        serviceSpecificTemplateParameters = new HashMap<String, Object>();

    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        requestParameters.clear();
        serviceSpecificTemplateParameters.clear();
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.server.utils.wcdma.cfa.WcdmaCfaUtils#getTemplateParameters(javax.ws.rs.core.MultivaluedMap)}.
     */
    @Test
    public void testGetTemplateParametersWithoutRequiredParameters() {
        serviceSpecificTemplateParameters = WcdmaCfaUtils.getCategoryInformation(requestParameters);
        assertFalse(serviceSpecificTemplateParameters.containsKey(WCDMA_CFA_DRILL_CATEGORY));
        assertFalse(serviceSpecificTemplateParameters.containsKey(CATEGORY_ID_PARAM));
        assertTrue(serviceSpecificTemplateParameters.isEmpty());
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.server.utils.wcdma.cfa.WcdmaCfaUtils#getTemplateParameters(javax.ws.rs.core.MultivaluedMap)}.
     */
    @Test
    public void testGetTemplateParametersWithRequiredParameters() {
        requestParameters.putSingle(WCDMA_CFA_DRILL_CATEGORY, "0");
        requestParameters.putSingle(CATEGORY_ID_PARAM, "0");
        serviceSpecificTemplateParameters = WcdmaCfaUtils.getCategoryInformation(requestParameters);
        assertTrue(serviceSpecificTemplateParameters.containsKey(WCDMA_CFA_DRILL_CATEGORY));
        assertTrue(serviceSpecificTemplateParameters.containsKey(CATEGORY_ID_PARAM));
        assertFalse(serviceSpecificTemplateParameters.isEmpty());
    }
}
