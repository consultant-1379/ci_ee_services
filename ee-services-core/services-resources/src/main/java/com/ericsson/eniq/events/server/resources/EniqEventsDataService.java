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

import com.ericsson.eniq.events.server.resources.mss.MssServiceResource;
import com.ericsson.eniq.events.server.resources.user.UserPreferencesResource;

/**
 * The Class EniqEventsDataService. The root resource of RESTful service.
 *
 * @author ehaoswa
 * @since Feb 2010
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
@Path("/")
public class EniqEventsDataService {

    @EJB
    protected MetaDataResource metaDataResource;

    @EJB
    protected SubscriberServiceResource subscriberServiceResource;

    @EJB
    protected NetworkServiceResource networkServiceResource;

    @EJB
    protected TerminalServiceResource terminalServiceResource;

    @EJB
    protected RankingServiceResource rankingServiceResource;

    @EJB
    protected LiveLoadResource liveLoadResource;

    @EJB
    protected CancelRequestResource cancelRequestResource;

    @EJB
    protected MssServiceResource mssServiceResource;

    @EJB
    protected ImsiMsisdnMappingResource imsiMsisdnMappingResource;

    @EJB
    protected DashboardServiceResource dashboardServiceResource;

    @EJB
    protected UserPreferencesResource userPreferencesResource;

    @EJB
    protected ConfigurationResource configurationResource;

    @EJB
    protected SessionBrowserResource sessionBrowserResource;

    /**
     * Subroot-resource locator method for UI_META_DATA Retrieves representation
     * of an instance of MetaDataResource.
     *
     * @return an instance of MetaDataResource
     */
    @Path(METADATA_SERVICE)
    public MetaDataResource getMetaDataResource() {
        return this.metaDataResource;
    }

    @Path(USER_SERVICES)
    public UserPreferencesResource getUserPreferencesResource() {
        return this.userPreferencesResource;
    }

    /**
     * Subroot-resource locator method for SUBSCRIBER service Retrieves
     * representation of an instance of SubscriberServiceResource.
     *
     * @return an instance of SubscriberServiceResource
     */
    @Path(SUBSCRIBER_SERVICES)
    public SubscriberServiceResource getSubscriberServiceResource() {
        return this.subscriberServiceResource;
    }

    /**
     * Subroot-resource locator method for NETWORK service Retrieves
     * representation of an instance of NetworkServiceResource.
     *
     * @return an instance of NetworkServiceResource
     */
    @Path(NETWORK_SERVICES)
    public NetworkServiceResource getNetworkServiceResource() {
        return this.networkServiceResource;
    }

    /**
     * Subroot-resource locator method for TERMINAL service Retrieves
     * representation of an instance of TerminalServiceResource.
     *
     * @return an instance of TerminalServiceResource
     */
    @Path(TERMINAL_SERVICES)
    public TerminalServiceResource getTerminalServiceResource() {
        return this.terminalServiceResource;
    }

    /**
     * Subroot-resource locator method for RANKING service Retrieves
     * representation of an instance of RankingServiceResource.
     *
     * @return an instance of RankingServiceResource
     */
    @Path(RANKING_SERVICES)
    public RankingServiceResource getRankingServiceResource() {
        return this.rankingServiceResource;
    }

    /**
     * Subroot-resource locator method for DASHBOARD service Retrieves
     * representation of an instance of DashboardServiceResource.
     *
     * @return an instance of DashboardServiceResource
     */
    @Path(DASHBOARD_SERVICES)
    public DashboardServiceResource getDashboardServiceResource() {
        return this.dashboardServiceResource;
    }

    /**
     * Subroot-resource locator method for LiveLoad service Retrieves
     * representation of an instance of LiveLoadResource.
     *
     * @return an instance of LiveLoadResource
     */
    @Path(LIVELOAD)
    public LiveLoadResource getLiveLoadResource() {
        return this.liveLoadResource;
    }

    /**
     * Subroot-resource locator method for Cancel service Retrieves
     * representation of an instance of LiveLoadResource.
     *
     * @return an instance of CancelRequestResource
     */
    @Path(CANCEL)
    public CancelRequestResource getCancelRequestResource() {
        return this.cancelRequestResource;
    }

    /**
     * Subroot-resource for MSS
     *
     * @return an instance of MssServiceResource
     */
    @Path(MSS)
    public MssServiceResource getMssServiceResource() {
        return this.mssServiceResource;
    }

    /**
     * Subroot-resource locator method for IMSI-MSISDN mapping service Retrieves
     * representation of an instance of ImsiMsisdnMappingResource.
     *
     * @return an instance of ImsiMsisdnMappingResource
     */
    @Path(IMSI_MSISDN_MAPPING)
    public ImsiMsisdnMappingResource getImsiMsisdnMappingResource() {
        return this.imsiMsisdnMappingResource;
    }

    @Path(CONFIGURATION)
    public ConfigurationResource getConfigurationResource() {
        return this.configurationResource;
    }

    @Path(SESSION_BROWSER)
    public SessionBrowserResource getSessionBrowserResource() {
        return this.sessionBrowserResource;
    }

}
