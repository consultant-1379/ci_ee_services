package com.ericsson.eniq.events.server.common;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * simple test class to make sure that the EVENT_E_GSN TechPack names are being created and used properly.
 *
 * @author eeikonl
 * @since 2011
 */

public class TechPackRepresentationSimpleTest {

    @Test
    public void testEventE_GSN_DT() {
        final TechPackRepresentation tpr = new TechPackRepresentation(EVENT_E_GSN_DT_TPNAME);
        assertThat(tpr.getName(), is(EVENT_E_GSN_DT_TPNAME));
        assertThat(tpr.getLicencedName(), is(EVENT_E_GSN_LICENCSED_TP_NAME));
    }

    @Test
    public void testEventE_GSN_DTPDP() {
        final TechPackRepresentation tpr = new TechPackRepresentation(EVENT_E_GSN_DTPDP_TPNAME);
        assertThat(tpr.getName(), is(EVENT_E_GSN_DTPDP_TPNAME));
        assertThat(tpr.getLicencedName(), is(EVENT_E_GSN_LICENCSED_TP_NAME));
    }

    @Test
    public void testEventE_SGEH() {
        final TechPackRepresentation tpr = new TechPackRepresentation(EVENT_E_SGEH_TPNAME);
        assertThat(tpr.getName(), is(EVENT_E_SGEH_TPNAME));
        assertThat(tpr.getLicencedName(), is(EVENT_E_SGEH_TPNAME));
    }

    @Test
    public void testEventE_LTE() {
        final TechPackRepresentation tpr = new TechPackRepresentation(EVENT_E_LTE_TPNAME);
        assertThat(tpr.getName(), is(EVENT_E_LTE_TPNAME));
        assertThat(tpr.getLicencedName(), is(EVENT_E_LTE_TPNAME));
    }

    @Test
    public void testEventE_LTE_CFA() {
        final TechPackRepresentation tpr = new TechPackRepresentation(EVENT_E_LTE_CFA_TP_NAME);
        assertThat(tpr.getName(), is(EVENT_E_LTE_CFA_TP_NAME));
        assertThat(tpr.getLicencedName(), is(EVENT_E_LTE_CFA_LICENCSED_TP_NAME));
    }

    @Test
    public void testEventE_LTE_CFA_ERAB() {
        final TechPackRepresentation tpr = new TechPackRepresentation(EVENT_E_LTE_CFA_ARRAY_ERAB_TP_NAME);
        assertThat(tpr.getName(), is(EVENT_E_LTE_CFA_ARRAY_ERAB_TP_NAME));
        assertThat(tpr.getLicencedName(), is(EVENT_E_LTE_CFA_LICENCSED_TP_NAME));
    }
}
