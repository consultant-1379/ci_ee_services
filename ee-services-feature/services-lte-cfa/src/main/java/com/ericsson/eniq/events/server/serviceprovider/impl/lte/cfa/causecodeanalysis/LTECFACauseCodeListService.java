/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.cfa.causecodeanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.LTE_CALL_FAILURE_CAUSE_CODE_TABLE_CC;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericSimpleService;

/**
 * @author emohasu
 * @since 2012
 */
@Stateless
@Local(Service.class)
public class LTECFACauseCodeListService extends GenericSimpleService {

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericSimpleServiceInterface#getTemplatePath()
     */
    @Override
    public String getTemplatePath() {
        return LTE_CALL_FAILURE_CAUSE_CODE_TABLE_CC;
    }
}
