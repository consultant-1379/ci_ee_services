/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.restfulservicetests.roaming;

import com.ericsson.eniq.events.server.resources.restfulservicetests.BaseRESTfulServiceTest;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

/**
 * Desrible RoamingDrillByCountryRESTfulTest
 *
 * @author ezhelao
 * @since 02/2012
 */
public class RoamingDrillByOperatorDetailRESTfulTest extends BaseRESTfulServiceTest{

    @Rule
        public ExpectedException thrown= ExpectedException.none();

    @Test
    public void testGetDrillByCountryDetailData ()
    {
        final MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();
        queryParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        queryParameters.putSingle(TIME_QUERY_PARAM, "5");
        queryParameters.putSingle(TZ_OFFSET, "+0000");
        queryParameters.putSingle(COUNTRY_OPERATOR_NAME_PARAM,"Telenor,Norway");
        queryParameters.putSingle(EVENT_ID_PARAM,"1");
        runQuery(queryParameters, MediaType.APPLICATION_JSON, NETWORK_SERVICES, ROAMING_ANALYSIS, ROAMING_COUNTRY_DRILL_DETAIL);

    }


}
