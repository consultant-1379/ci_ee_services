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
package com.ericsson.eniq.events.server.utils.sbr;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;
import static org.junit.Assert.*;

import java.util.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.*;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SbrowserUtilTest {

    private MultivaluedMap<String, String> map;

    private String drilltype;

    private List<String> mappedColumns;

    private Map<String, Object> serviceSpecificTemplateParameters;

    @Before
    public void setUp() {
        map = new MultivaluedMapImpl();
        mappedColumns = new ArrayList<String>();
        serviceSpecificTemplateParameters = new HashMap<String, Object>();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        map.clear();
        serviceSpecificTemplateParameters.clear();
    }

    @Test
    public void testGetPopupDetailOfEvent_INTERNAL_SUCCESSFUL_HSDSCH_CELL_CHANGE_Is_SOHO() {

        map.putSingle(EVENT_ID_PARAM, Integer.toString(INTERNAL_SUCCESSFUL_HSDSCH_CELL_CHANGE));
        assertEquals(HFA_HSDSCH_SUC, SbrowserUtil.getPopupDetailOfEvent(map));
    }

    @Test
    public void testGetPopupDetailOfEvent_RANHFA_INTERNAL_SOFT_HANDOVER_EXECUTION_Is_SOHO() {

        map.putSingle(EVENT_ID_PARAM, Integer.toString(INTERNAL_SOFT_HANDOVER_EXECUTION));
        assertEquals(SOHO, SbrowserUtil.getPopupDetailOfEvent(map));
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.server.utils.sbr.SbrowserUtil#getSubscriberType(javax.ws.rs.core.MultivaluedMap)}.
     */
    @Test
    public void testGetSubscriberType_IMSI() {

        map.putSingle(IMSI_PARAM, "123456");
        assertEquals(TYPE_IMSI, SbrowserUtil.getSubscriberType(map));
    }

    @Test
    public void testGetSubscriberType_MSISDN() {

        map.putSingle(MSISDN_PARAM, "123456");
        assertEquals(TYPE_MSISDN, SbrowserUtil.getSubscriberType(map));
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.server.utils.sbr.SbrowserUtil#getPopupDetailOfEvent(javax.ws.rs.core.MultivaluedMap)}.
     */
    @Test
    public void testGetPopupDetailOfEvent_CORE() {

        map.putSingle(EVENT_ID_PARAM, "0");
        assertEquals(CORE, SbrowserUtil.getPopupDetailOfEvent(map));
    }

    @Test
    public void testGetPopupDetailOfEvent_RANCFA() {

        map.putSingle(EVENT_ID_PARAM, "438");
        assertEquals(RAN_CFA, SbrowserUtil.getPopupDetailOfEvent(map));
    }

    @Test
    public void testGetPopupDetailOfEvent_RANHFA_HHO() {

        map.putSingle(EVENT_ID_PARAM, Integer.toString(INTERNAL_OUT_HARD_HANDOVER_FAILURE));
        assertEquals(IRAT, SbrowserUtil.getPopupDetailOfEvent(map));
    }

    @Test
    public void testGetPopupDetailOfEvent_RAB() {

        map.putSingle(EVENT_ID_PARAM, "20000");
        assertEquals(RAB, SbrowserUtil.getPopupDetailOfEvent(map));
    }

    /**
     * Test method for {@link com.ericsson.eniq.events.server.utils.sbr.SbrowserUtil#getMappedColumnsForDrilltype(java.lang.String)}.
     */
    @Test
    public void testGetMappedColumnsForDrilltype() {
        drilltype = HS_CATEGORY;
        mappedColumns.add("HSDPA_UE_CATEGORY");
        assertEquals(mappedColumns, SbrowserUtil.getMappedColumnsForDrilltype(drilltype));
    }

    @Test
    public void testGetTemplateParametersWithoutRequiredParameters() {
        serviceSpecificTemplateParameters = SbrowserUtil.getUserPlaneKPIDrillDownInformation(map);
        assertFalse(serviceSpecificTemplateParameters.containsKey(drilltype));
        assertFalse(serviceSpecificTemplateParameters.containsKey(KPI));
        assertTrue(serviceSpecificTemplateParameters.isEmpty());
    }

    @Test
    public void testGetTemplateParametersWithRequiredParameters() {
        map.putSingle(DRILLTYPE_PARAM, ECNO);
        map.putSingle(KPI_ID, "0");
        map.putSingle(KPI_END_TIME, "1337230800000");
        serviceSpecificTemplateParameters = SbrowserUtil.getUserPlaneKPIDrillDownInformation(map);
        assertTrue(serviceSpecificTemplateParameters.containsKey(DRILLTYPE_PARAM));
        assertTrue(serviceSpecificTemplateParameters.containsKey(KPI));
        assertFalse(serviceSpecificTemplateParameters.isEmpty());
    }
}
