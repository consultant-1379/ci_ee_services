/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.eventanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConfigConstants.*;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.*;

import java.util.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.ericsson.eniq.events.server.common.TechPackList;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericService;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.RequestParametersWrapper;
import com.ericsson.eniq.events.server.utils.wcdma.cfa.WcdmaCfaUtils;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eadrhyn
 *         
 */
@Stateless
@Local(Service.class)
public class NetworkDetailedEventAnalysisByFilterService extends GenericService {

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getServiceSpecificTemplateParameters(javax.ws.rs.core.MultivaluedMap, com.ericsson.eniq.events.server.utils.DateTimeRange.FormattedDateTimeRange)
     */
    @Override
    public Map<String, Object> getServiceSpecificTemplateParameters(
            final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange,
            final TechPackList techPackList) {
        final Map<String, Object> serviceSpecificTemplateParameters = WcdmaCfaUtils
                .getCategoryInformation(requestParameters);
        updateTemplateIfQueryIncludeDisconnectionCode(requestParameters, serviceSpecificTemplateParameters);
        return serviceSpecificTemplateParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getTemplatePath()
     */
    @Override
    public String getTemplatePath() {
        return WCDMA_CFA_NETWORK_DETAILED_EVENT_ANALYSIS_BY_FILTER;
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
        requiredParameters.add(TZ_OFFSET);
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
        final RequestParametersWrapper requestParametersWrapper = new RequestParametersWrapper(requestParameters);

        if (requestParametersWrapper.isController()) {
            if (requestParameters.containsKey(HIER3_ID)) {
                final String hier3Id = requestParameters.getFirst(HIER3_ID);
                queryParameters.put(HIER3_ID, QueryParameter.createLongParameter(Long.parseLong(hier3Id)));
            } else {
                final String controller = requestParameters.getFirst(RNC_FDN_PARAM);
                final String vendor = requestParameters.getFirst(RAN_VENDOR_PARAM);
                final long hashedIdForController = getQueryUtils().createHashIDForController(_3G, controller, vendor);
                queryParameters.put(HIER3_ID, QueryParameter.createLongParameter(hashedIdForController));
            }
        } else if (requestParametersWrapper.isAccessArea()) {
            if (requestParameters.containsKey(HIER3_CELL_ID_AGG)) {
                final String hier321Id = requestParameters.getFirst(HIER3_CELL_ID_AGG);
                queryParameters.put(HIER3_CELL_ID_AGG, QueryParameter.createLongParameter(Long.parseLong(hier321Id)));
            } else {
                final String controller = requestParameters.getFirst(RNC_FDN_PARAM);
                final String vendor = requestParameters.getFirst(RAN_VENDOR_PARAM);
                final String cell = requestParameters.getFirst(CELL_ID_PARAM);
                final long hashedIdForCell = getQueryUtils().createHashIDFor3GCell(_3G, controller, cell, vendor);
                queryParameters.put(HIER3_CELL_ID_AGG, QueryParameter.createLongParameter(hashedIdForCell));
            }

        }
        if (requestParameters.containsKey(FILTER_VALUE) && requestParameters.containsKey(FILTER_TYPE)) {
            final String filterValue = requestParameters.getFirst(FILTER_VALUE);
            if (StringUtils.isNotBlank(filterValue)) {
                if (filterValue.contains(DELIMITER)) {
                    //Disconnection Code drill use only
                    final String[] disconnectionCodes = filterValue.split(DELIMITER);
                    if (ArrayUtils.isNotEmpty(disconnectionCodes)) {
                        queryParameters.put(DISCONNECTION_CODE,
                                QueryParameter.createIntParameter(Integer.parseInt(disconnectionCodes[0])));
                        queryParameters.put(DISCONNECTION_SUB_CODE,
                                QueryParameter.createIntParameter(Integer.parseInt(disconnectionCodes[1])));
                    }
                } else {
                    queryParameters.put(FILTER_VALUE, QueryParameter.createIntParameter(Integer.parseInt(filterValue)));
                }

            } else {
                queryParameters.put(FILTER_VALUE, QueryParameter.createDBNullParameter());
            }
        }

        if (requestParameters.containsKey(EVENT_ID_PARAM)) {
            final String eventId = requestParameters.getFirst(EVENT_ID_PARAM);
            queryParameters.put(EVENT_ID_PARAM, QueryParameter.createIntParameter(Integer.parseInt(eventId)));
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
        if (StringUtils.isNotBlank(filterValue) && filterValue.contains(DELIMITER)
                && DISCONNECTION_CODE.equalsIgnoreCase(filertype)) {
            if (ArrayUtils.isEmpty(filterValue.split(DELIMITER))) {
                serviceSpecificTemplateParameters.put("NULL_DICCONECTION_CODE", "true");
            }

        }
    }
}
