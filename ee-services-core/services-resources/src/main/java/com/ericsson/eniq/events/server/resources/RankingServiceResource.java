/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.ericsson.eniq.events.server.resources.dtput.DatavolRankingResource;
import com.ericsson.eniq.events.server.resources.lte.handoverfailure.LTEHandoverFailureRankingResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.WCDMAHandoverFailureRankingResource;

/**
 * The Class RankingServiceResource.
 * Sub-root resource of RESTful service.
 *
 * @author ehaoswa
 * @since  May 2010
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class RankingServiceResource {

    @EJB
    protected EventAnalysisResource eventAnalysisResource;

    @EJB
    protected KPIResource kpiResource;

    @EJB
    protected MultipleRankingResource multipleRankingResource;

    @EJB
    protected DatavolRankingResource datavolRankingResource;

    @EJB
    protected WCDMAHandoverFailureRankingResource wcdmaHandoverFailureRankingResource;

    @EJB
    protected LTEHandoverFailureRankingResource lteHandoverFailureRankingResource;

    /**
     * Gets the event analysis resource.
     *
     * @return the event analysis resource
     */
    @Path(EVENT_ANALYSIS)
    public EventAnalysisResource getEventAnalysisResource() {
        return this.eventAnalysisResource;
    }

    /**
     * Gets the kpi resource.
     *
     * @return the kpi resource
     */
    @Path(KPI)
    public KPIResource getKpiResource() {
        return this.kpiResource;
    }

    /**
     * Gets the multiple ranking resource.
     *
     * @return the multiple ranking resource
     */
    @Path(RANKING_ANALYSIS)
    public MultipleRankingResource getMultipleRankingResource() {
        return this.multipleRankingResource;
    }

    /**
     * Gets the data volume ranking resource.
     *
     * @return the data volume ranking resource
     */
    @Path(DATAVOL_RANKING_ANALYSIS)
    public DatavolRankingResource getDatavolRankingResource() {
        return this.datavolRankingResource;
    }

    /**
     * Gets the data volume group ranking resource.
     *
     * @return the data volume group ranking resource
     */
    @Path(DATAVOL_GROUP_RANKING_ANALYSIS)
    public DatavolRankingResource getDatavolGroupRankingResource() {
        return this.datavolRankingResource;
    }

    @Path(WCDMA_HFA_RANKING)
    public WCDMAHandoverFailureRankingResource getWcdmaHandoverFailureRankingResource() {
        return this.wcdmaHandoverFailureRankingResource;

    }

    @Path(LTE_HFA_RANKING)
    public LTEHandoverFailureRankingResource getLteHandoverFailureRankingResource() {
        return this.lteHandoverFailureRankingResource;
    }

}
