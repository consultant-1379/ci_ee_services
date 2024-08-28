/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.techpacks.timerangequeries.impl;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.MessageConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.datasource.loadbalancing.LoadBalancingPolicy;
import com.ericsson.eniq.events.server.datasource.loadbalancing.LoadBalancingPolicyFactory;
import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.query.DataServiceQueryExecutor;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.query.resultsettransformers.ResultSetTransformer;
import com.ericsson.eniq.events.server.query.resultsettransformers.ResultSetTransformerFactory;
import com.ericsson.eniq.events.server.templates.mappingengine.TemplateMappingEngine;
import com.ericsson.eniq.events.server.templates.utils.TemplateUtils;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.QueryUtils;
import com.ericsson.eniq.events.server.utils.config.latency.TechPackTechnologies;
import com.ericsson.eniq.events.server.utils.techpacks.timerangequeries.TimerangeQuerier;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Implementation of the TimerangeQuerier for the standard ENIQ Events tech packs.
 * All ENIQ Events tech packs will have corresponding time range views for the raw table partitions
 * Eg in the EVENT_E_RAN_CFA tech pack, there is the view EVENT_E_RAN_CFA_ERR_RAW view, and a corresponding
 * EVENT_E_RAN_CFA_ERR_RAW_TIMERANGE view
 * This class will query this view for the appropriate table names to use
 *
 * @author eemecoy
 *
 */
@Stateless
@Local(TimerangeQuerier.class)
public class EventsTechPackTimerangeQuerier implements TimerangeQuerier {

    @EJB
    private QueryUtils queryUtils;

    @EJB
    private TemplateUtils templateUtils;

    @EJB
    private TemplateMappingEngine templateMappingEngine;

    @EJB
    private DataServiceQueryExecutor dataServiceQueryExecutor;

    @EJB
    private LoadBalancingPolicyFactory loadBalancingPolicyFactory;

    @EJB
    private TechPackTechnologies techPackTechnologies;

    final static String GET_RAW_TABLES = "GET_RAW_TABLES";

    final static String GET_LATEST_RAW_TABLES = "GET_LATEST_RAW_TABLES";

    final static String GET_RAW_TABLES_NO_TIMERANGE = "GET_RAW_TABLES_NO_TIMERANGE";

    final static String GET_LATEST_RAW_TABLES_NO_TIMERANGE = "GET_LATEST_RAW_TABLES_NO_TIMERANGE";

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.utils.techpacks.timerangequeries.TimerangeQuerier#getLatestTablesUsingQuery(java.lang.String)
     */
    @Override
    public List<String> getLatestTablesUsingQuery(final String view) {
        final Map<String, Object> templateParametersForRawTables = new HashMap<String, Object>();
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        String queryForLatestTable = null;
        if (techPackTechnologies.usesVolumeBasedRawPartitions(view)) {
            templateParametersForRawTables.put(SELECT_BOTH_LATEST_ERR_AND_SUC_TABLES,
                    getViewsThatShouldReturnBothErrAndSucTables().contains(view));
            templateParametersForRawTables.put(RAW_TIMERANGE_VIEW, getRawTimeRangeView(view));
            queryForLatestTable = this.templateUtils.getQueryFromTemplate(
                    templateMappingEngine.getTemplate(GET_LATEST_RAW_TABLES, requestParameters, null),
                    templateParametersForRawTables);
            return getRawTables(queryForLatestTable, null);
        }

        templateParametersForRawTables.put(RAW_TYPE, view);
        queryForLatestTable = this.templateUtils.getQueryFromTemplate(
                templateMappingEngine.getTemplate(GET_LATEST_RAW_TABLES_NO_TIMERANGE, requestParameters, null),
                templateParametersForRawTables);
        final List<List<String>> rows = dataServiceQueryExecutor.getDataFromRepdb(CANCEL_REQ_NOT_SUPPORTED,
                queryForLatestTable, null, ResultSetTransformerFactory.getStringResultsTransformer());
        final List<String> rawTableNames = new ArrayList<String>();
        String tableName;
        for (final List<String> row : rows) {
            tableName = row.get(0);
            rawTableNames.add(tableName);
            ServicesLogger.detailed(Level.FINE, getClass().getName(), "getLatestTablesUsingQuery", "Table " + tableName
                    + " added to the list");
        }
        return rawTableNames;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.utils.techpacks.timerangequeries.TimerangeQuerier#getLatestTablesUsingQuery(com.ericsson.eniq.events.server.utils.FormattedDatetimeRange, java.lang.String)
     */
    @Override
    public List<String> getRAWTablesUsingQuery(final FormattedDateTimeRange newDateTimeRange, final String view) {
        ServicesLogger.warn(getClass().getName(), "getRAWTablesUsingQuery", E_RMI_FAILURE);

        final String drillType = null;
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        final Map<String, Object> templateParametersForRawTables = new HashMap<String, Object>();
        String query = null;

        if (techPackTechnologies.usesVolumeBasedRawPartitions(view)) {
            templateParametersForRawTables.put(RAW_TIMERANGE_VIEW, getRawTimeRangeView(view));
            query = this.templateUtils.getQueryFromTemplate(
                    templateMappingEngine.getTemplate(GET_RAW_TABLES, requestParameters, drillType),
                    templateParametersForRawTables);
            return getRawTables(query, this.queryUtils.mapDateParameters(newDateTimeRange));
        }

        templateParametersForRawTables.put(RAW_TYPE, view);
        query = this.templateUtils.getQueryFromTemplate(
                templateMappingEngine.getTemplate(GET_RAW_TABLES_NO_TIMERANGE, requestParameters, drillType),
                templateParametersForRawTables);
        final List<List<String>> rows = dataServiceQueryExecutor.getDataFromRepdb(CANCEL_REQ_NOT_SUPPORTED, query,
                this.queryUtils.mapDateParameters(newDateTimeRange),
                ResultSetTransformerFactory.getStringResultsTransformer());
        final List<String> rawTableNames = new ArrayList<String>();
        String tableName;
        for (final List<String> row : rows) {
            tableName = row.get(0);
            rawTableNames.add(tableName);
            ServicesLogger.detailed(Level.FINE, getClass().getName(), "getRAWTablesUsingQuery", "Table " + tableName
                    + " added to the list");
        }
        return rawTableNames;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.services.DataService#getRawTables(java.lang.String, java.util.Map)
     */
    private List<String> getRawTables(final String query, final Map<String, QueryParameter> queryParameters) {
        final ResultSetTransformer<List<String>> transformer = ResultSetTransformerFactory
                .getRawTableNamesTransformer();
        final LoadBalancingPolicy loadBalancingPolicy = loadBalancingPolicyFactory.getDefaultLoadBalancingPolicy();
        return dataServiceQueryExecutor.getData(CANCEL_REQ_NOT_SUPPORTED, query, queryParameters, transformer,
                loadBalancingPolicy);
    }

    private String getRawTimeRangeView(final String view) {
        return view + "_TIMERANGE";
    }

    private List<String> getViewsThatShouldReturnBothErrAndSucTables() {
        final List<String> listOfViewsWhereQueryShouldFetchBothLatestErrAndSucTables = new ArrayList<String>();
        listOfViewsWhereQueryShouldFetchBothLatestErrAndSucTables.add(EVENT_E_SGEH_RAW);
        listOfViewsWhereQueryShouldFetchBothLatestErrAndSucTables.add(EVENT_E_LTE_RAW);
        return listOfViewsWhereQueryShouldFetchBothLatestErrAndSucTables;

    }

    void setQueryUtils(final QueryUtils queryUtils) {
        this.queryUtils = queryUtils;

    }

    void setTemplateMappingEngine(final TemplateMappingEngine templateMappingEngine) {
        this.templateMappingEngine = templateMappingEngine;
    }

    void setTemplateUtils(final TemplateUtils templateUtils) {
        this.templateUtils = templateUtils;
    }

    /**
     * Exposed for testing
     * @param dataServiceQueryExecutor
     */
    public void setDataServiceQueryExecutor(final DataServiceQueryExecutor dataServiceQueryExecutor) {
        this.dataServiceQueryExecutor = dataServiceQueryExecutor;
    }

    /**
     * @param loadBalancingPolicyFactory the loadBalancingPolicyFactory to set
     */
    public void setLoadBalancingPolicyFactory(final LoadBalancingPolicyFactory loadBalancingPolicyFactory) {
        this.loadBalancingPolicyFactory = loadBalancingPolicyFactory;
    }

    /**
     * @param techPackTechnologies the techPackDefinitions to set
     */
    public void setTechPackTechnologies(final TechPackTechnologies techPackTechnologies) {
        this.techPackTechnologies = techPackTechnologies;
    }

}
