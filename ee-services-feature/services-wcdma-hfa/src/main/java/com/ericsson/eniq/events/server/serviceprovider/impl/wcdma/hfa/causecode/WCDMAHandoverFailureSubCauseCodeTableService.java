/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.causecode;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericSimpleService;

/**
 * @author eromsza
 * @since 2012
 *
 */
@Stateless
@Local(Service.class)
public class WCDMAHandoverFailureSubCauseCodeTableService extends GenericSimpleService {

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericSimpleServiceInterface#getTemplatePath()
     */
    @Override
    public String getTemplatePath() {
        return WCDMA_HFA_SUB_CAUSE_CODE_TABLE;
    }
}
