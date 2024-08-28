/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConfigConstants.MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.AGGREGATION_VIEW;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISCONNECTION_CODE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.FILTER_TYPE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.FILTER_VALUE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.KEY_TYPE_ERR;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.NO_AGGREGATION_AVAILABLE;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.WCDMA_CFA_TERMINAL_DETAILED_EVENT_ANALYSIS_BY_FILTER;
import static com.ericsson.eniq.events.server.common.TechPackData.EVENT_E_RAN_CFA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.common.TechPackList;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericService;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eadrhyn
 *        
 */
@Stateless
@Local(Service.class)
public class TerminalDetailedEventAnalysisByFilterService extends GenericService {

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getServiceSpecificTemplateParameters(javax.ws.rs.core.MultivaluedMap, com.ericsson.eniq.events.server.utils.DateTimeRange.FormattedDateTimeRange)
     */
    @Override
    public Map<String, Object> getServiceSpecificTemplateParameters(
            final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange,
            final TechPackList techPackList) {
        final Map<String, Object> serviceSpecificTemplateParameters = new HashMap<String, Object>();
        serviceSpecificTemplateParameters.put(AGGREGATION_VIEW, techPackList.getTechPack(EVENT_E_RAN_CFA)
                .getErrAggregationView());
        updateTemplateIfQueryIncludeDisconnectionCode(requestParameters, serviceSpecificTemplateParameters);
        return serviceSpecificTemplateParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getTemplatePath()
     */
    @Override
    public String getTemplatePath() {
        return WCDMA_CFA_TERMINAL_DETAILED_EVENT_ANALYSIS_BY_FILTER;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getServiceSpecificDataServiceParameters(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public Map<String, Object> getServiceSpecificDataServiceParameters(
            final MultivaluedMap<String, String> requestParameters) {
        final Map<String, Object> dataServiceParameters = new HashMap<String, Object>();
        dataServiceParameters.put(TZ_OFFSET, requestParameters.getFirst(TZ_OFFSET));
        dataServiceParameters.put(FILTER_TYPE, requestParameters.getFirst(FILTER_TYPE));
        return dataServiceParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getRequiredParametersForQuery()
     */
    @Override
    public List<String> getRequiredParametersForQuery() {
        final List<String> requiredParameters = new ArrayList<String>();
        return requiredParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getValidDisplayParameters()
     */
    @Override
    public MultivaluedMap<String, String> getStaticParameters() {
        return new MultivaluedMapImpl();
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getDrillDownTypeForService()
     */
    @Override
    public String getDrillDownTypeForService(final MultivaluedMap<String, String> requestParameters) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getAggregationViews()
     */
    @Override
    public AggregationTableInfo getAggregationView(final String type) {
        return new AggregationTableInfo(NO_AGGREGATION_AVAILABLE);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getApplicableTechPacks(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public List<String> getApplicableTechPacks(final MultivaluedMap<String, String> requestParameters) {
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add(EVENT_E_RAN_CFA);
        return techPacks;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#areRawTablesRequiredForQuery()
     */
    @Override
    public boolean areRawTablesRequiredForAggregationQueries() {
        return false;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getMaxAllowableSize()
     */
    @Override
    public int getMaxAllowableSize() {
        return MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#requiredToCheckValidParameterValue(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public boolean requiredToCheckValidParameterValue(final MultivaluedMap<String, String> requestParameters) {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface
     * #getServiceSpecificQueryParameters(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public Map<String, QueryParameter> getServiceSpecificQueryParameters(
            final MultivaluedMap<String, String> requestParameters) {
        final Map<String, QueryParameter> queryParameters = new HashMap<String, QueryParameter>();

        queryParameters.put(TAC, QueryParameter.createStringParameter(requestParameters.getFirst(TAC)));

        final String filterValue = requestParameters.getFirst(FILTER_VALUE);
        if (("").equals(filterValue)) {
            if (!DISCONNECTION_CODE.equalsIgnoreCase(requestParameters.getFirst(FILTER_TYPE))) {
                queryParameters.put(FILTER_VALUE, QueryParameter.createDBNullParameter());
            }
        } else {
            queryParameters.put(FILTER_VALUE, QueryParameter.createStringParameter(filterValue));
        }

        return queryParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getTableSuffixKey()
     */
    @Override
    public String getTableSuffixKey() {
        return null;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getMeasurementTypes()
     */
    @Override
    public List<String> getMeasurementTypes() {
        return null;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getRawTableKeys()
     */
    @Override
    public List<String> getRawTableKeys() {
        final List<String> rawTableKeys = new ArrayList<String>();
        rawTableKeys.add(KEY_TYPE_ERR);
        return rawTableKeys;
    }

    /*
     * (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getTimeColumnIndices()
     */
    @Override
    public List<Integer> getTimeColumnIndices() {
        final List<Integer> columnIndices = new ArrayList<Integer>();
        columnIndices.add(1);
        return columnIndices;
    }

    /**
     * If the query is to include the disconnection code, there is a possibility in 3.2.6 that the disconnection code
     * is null/empty. We need to indicate in the template parameters if that is the case and it can take care of
     * that scenario then.
     * @param requestParameters
     * @param serviceSpecificTemplateParameters
     */
    private void updateTemplateIfQueryIncludeDisconnectionCode(final MultivaluedMap<String, String> requestParameters,
            final Map<String, Object> serviceSpecificTemplateParameters) {
        final String filterValue = requestParameters.getFirst(FILTER_VALUE);
        final String filertype = requestParameters.getFirst(FILTER_TYPE);
        if (("").equals(filterValue) && DISCONNECTION_CODE.equalsIgnoreCase(filertype)) {
            serviceSpecificTemplateParameters.put("NULL_DICCONECTION_CODE", "true");
        }
    }
}
