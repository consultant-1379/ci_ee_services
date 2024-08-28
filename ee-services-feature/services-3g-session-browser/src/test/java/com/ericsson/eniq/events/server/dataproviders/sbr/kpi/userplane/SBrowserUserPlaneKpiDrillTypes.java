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
package com.ericsson.eniq.events.server.dataproviders.sbr.kpi.userplane;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

public class SBrowserUserPlaneKpiDrillTypes {

    public static String[] getDrillTypes() {
        return new String[] { ECNO, RSCP, UL_INER, HS_USER };
    }

}
