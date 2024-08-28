/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.causecodeanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.WCDMA_CFA_CAUSE_CODE_TABLE;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericSimpleService;

/**
 * @author epesmit
 * @since 2011
 *
 */
@Stateless
@Local(Service.class)
public class ControllerCauseCodeTableService extends GenericSimpleService {

    @Override
    public String getTemplatePath() {
        return WCDMA_CFA_CAUSE_CODE_TABLE;
    }
}
