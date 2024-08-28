/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.kpi.core;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;

import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.common.TechPackList;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.datasource.loadbalancing.LoadBalancingPolicy;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericService;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.sbr.MapKpiResultSetTransformer;
import com.ericsson.eniq.events.ui.shared.model.kpianalysis.ISGEHNetworkMapKpiResult;
import com.ericsson.eniq.events.ui.shared.model.kpianalysis.ISGEHNonNwrkMapKpiResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
@Stateless
@Local(Service.class)
public class SBrowserCoreKpiMapService extends GenericService {

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getTemplatePath()
     */
    @Override
    public String getTemplatePath() {
        return CORE + PATH_SEPARATOR + KPI_MAP;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getServiceSpecificTemplateParameters(javax.ws.rs.core.MultivaluedMap, com.ericsson.eniq.events.server.utils.FormattedDateTimeRange, com.ericsson.eniq.events.server.common.TechPackList)
     */
    @Override
    public Map<String, Object> getServiceSpecificTemplateParameters(
            final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange,
            final TechPackList techPackList) {
        final Map<String, Object> serviceSpecificTemplateParameters = new HashMap<String, Object>();
        serviceSpecificTemplateParameters.put(ERR_AGGREGATION_VIEW, techPackList.getTechPack(EVENT_E_SGEH)
                .getErrAggregationView());
        serviceSpecificTemplateParameters.put(SUC_AGGREGATION_TABLES, techPackList.getTechPack(EVENT_E_SGEH)
                .getSucAggregationView());
        final String type = requestParameters.getFirst(TYPE_PARAM);
        if (StringUtils.isNotBlank(type)) {
            serviceSpecificTemplateParameters.put(TYPE_PARAM, type);
        }
        return serviceSpecificTemplateParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getServiceSpecificDataServiceParameters(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public Map<String, Object> getServiceSpecificDataServiceParameters(
            final MultivaluedMap<String, String> requestParameters) {
        final Map<String, Object> dataServiceParameters = new HashMap<String, Object>();
        dataServiceParameters.put(TZ_OFFSET, requestParameters.getFirst(TZ_OFFSET));
        return dataServiceParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getServiceSpecificQueryParameters(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public Map<String, QueryParameter> getServiceSpecificQueryParameters(
            final MultivaluedMap<String, String> requestParameters) {
        final Map<String, QueryParameter> queryParameters = new HashMap<String, QueryParameter>();

        final String node = requestParameters.getFirst(NODE_PARAM);
        final String type = requestParameters.getFirst(TYPE_PARAM);
        if (StringUtils.isNotBlank(type)) {

            if (StringUtils.isNotBlank(node)) {
                final String[] allData = node.split(DELIMITER);
                if (TYPE_CELL.equals(type)) {
                    queryParameters.put(CELL_SQL_NAME, QueryParameter.createStringParameter(allData[0]));
                    queryParameters.put(VENDOR_PARAM_UPPER_CASE, QueryParameter.createStringParameter(allData[3]));
                    queryParameters.put(RAT_PARAM, QueryParameter.createStringParameter(getQueryUtils()
                            .getRatDescriptionMappingUtils().getRATIntegerValue(allData[4])));
                    queryParameters.put(BSC_SQL_NAME, QueryParameter.createStringParameter(allData[2]));
                } else {
                    queryParameters.put(VENDOR_PARAM_UPPER_CASE, QueryParameter.createStringParameter(allData[1]));
                    queryParameters.put(RAT_PARAM, QueryParameter.createStringParameter(getQueryUtils()
                            .getRatDescriptionMappingUtils().getRATIntegerValue(allData[2])));
                    queryParameters.put(BSC_SQL_NAME, QueryParameter.createStringParameter(allData[0]));
                }

            }
        }

        return queryParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getRequiredParametersForQuery()
     */
    @Override
    public List<String> getRequiredParametersForQuery() {
        final List<String> requiredParameters = new ArrayList<String>();
        requiredParameters.add(TZ_OFFSET);
        return requiredParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getStaticParameters()
     */
    @Override
    public MultivaluedMap<String, String> getStaticParameters() {
        return new MultivaluedMapImpl();
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getDrillDownTypeForService(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public String getDrillDownTypeForService(final MultivaluedMap<String, String> requestParameters) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getAggregationView(java.lang.String)
     */
    @Override
    public AggregationTableInfo getAggregationView(final String type) {
        if (StringUtils.isNotBlank(type)) {
            return new AggregationTableInfo(VEND_HIER321_EVENTID, EventDataSourceType.AGGREGATED_15MIN,
                    EventDataSourceType.AGGREGATED_DAY);
        }
        return new AggregationTableInfo(VEND_HIER3_EVENTID, EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);

    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getApplicableTechPacks(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public List<String> getApplicableTechPacks(final MultivaluedMap<String, String> requestParameters) {
        final List<String> techPackList = new ArrayList<String>();
        techPackList.add(EVENT_E_SGEH);
        return techPackList;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#areRawTablesRequiredForAggregationQueries()
     */
    @Override
    public boolean areRawTablesRequiredForAggregationQueries() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getMaxAllowableSize()
     */
    @Override
    public int getMaxAllowableSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#requiredToCheckValidParameterValue(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public boolean requiredToCheckValidParameterValue(final MultivaluedMap<String, String> requestParameters) {
        if (StringUtils.isNotBlank(requestParameters.getFirst(TYPE_PARAM))
                && (TYPE_BSC.equals(requestParameters.getFirst(TYPE_PARAM)) || TYPE_CELL.equals(requestParameters
                        .getFirst(TYPE_PARAM)))) {
            return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getTableSuffixKey()
     */
    @Override
    public String getTableSuffixKey() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getMeasurementTypes()
     */
    @Override
    public List<String> getMeasurementTypes() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getRawTableKeys()
     */
    @Override
    public List<String> getRawTableKeys() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isDataTieredService(final MultivaluedMap<String, String> parameters) {
        return true;
    }

    @Override
    public String runQuery(final String query, final String requestId,
            final Map<String, QueryParameter> queryParameters, final LoadBalancingPolicy loadBalancingPolicy,
            final Map<String, Object> serviceSpecificDataServiceParameters) {
        final String tzOffset = (String) serviceSpecificDataServiceParameters.get(TZ_OFFSET);
        final String timeStampFrom = queryParameters.get(DATE_FROM).getValue().toString();
        final String timeStampTo = queryParameters.get(DATE_TO).getValue().toString();
        MapKpiResultSetTransformer transformer;
        if (queryParameters.containsKey(BSC_SQL_NAME) || queryParameters.containsKey(CELL_SQL_NAME)) {
            //call for rnc level
            transformer = new MapKpiResultSetTransformer(timeStampFrom, timeStampTo, tzOffset,
                    ISGEHNonNwrkMapKpiResult.class, null, false);
        } else {
            //call on network level
            transformer = new MapKpiResultSetTransformer(timeStampFrom, timeStampTo, tzOffset,
                    ISGEHNetworkMapKpiResult.class, null, true);
        }
        return getDataService().getData(requestId, query, queryParameters, transformer);
    }
}
