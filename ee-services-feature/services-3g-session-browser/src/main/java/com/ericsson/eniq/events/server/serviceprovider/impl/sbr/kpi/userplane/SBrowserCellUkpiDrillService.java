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
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.kpi.userplane;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.*;

import java.util.*;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang.StringUtils;

import com.ericsson.eniq.events.server.common.TechPackList;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.datasource.loadbalancing.LoadBalancingPolicy;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericService;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.sbr.JsonResultSetTransformer;
import com.ericsson.eniq.events.server.utils.sbr.SbrowserUtil;
import com.ericsson.eniq.events.ui.shared.model.kpianalysis.IUserPlaneChartDrillKpiResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Stateless
@Local(Service.class)
public class SBrowserCellUkpiDrillService extends GenericService {

    private static final String START_HIER321_ID = "START_HIER321_ID";

    private String drillType;

    @Override
    public String getTemplatePath() {
        return USER_PLANE + PATH_SEPARATOR + KPI_CHART + PATH_SEPARATOR + KPI_DRILL;
    }

    @Override
    public Map<String, Object> getServiceSpecificTemplateParameters(
            final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange,
            final TechPackList techPackList) {
        return SbrowserUtil.getUserPlaneKPIDrillDownInformation(requestParameters);
    }

    @Override
    public Map<String, Object> getServiceSpecificDataServiceParameters(
            final MultivaluedMap<String, String> requestParameters) {
        final Map<String, Object> dataServiceParameters = new HashMap<String, Object>();
        dataServiceParameters.put(TZ_OFFSET, requestParameters.getFirst(TZ_OFFSET));
        return dataServiceParameters;
    }

    @Override
    public Map<String, QueryParameter> getServiceSpecificQueryParameters(
            final MultivaluedMap<String, String> requestParameters) {
        final String node = requestParameters.getFirst(NODE_PARAM);

        final Map<String, QueryParameter> queryParameters = new HashMap<String, QueryParameter>();

        if (StringUtils.isNotBlank(node)) {
            //generate hash id for SAC
            final long hashedIdForSAC = getQueryUtils().createHashIdForCell(node);
            queryParameters.put(HIER321_ID, QueryParameter.createLongParameter(hashedIdForSAC));
            queryParameters.put(START_HIER321_ID, QueryParameter.createLongParameter(hashedIdForSAC));
        }
        return queryParameters;
    }

    @Override
    public List<String> getRequiredParametersForQuery() {
        final List<String> requiredParameters = new ArrayList<String>();
        requiredParameters.add(TZ_OFFSET);
        return requiredParameters;
    }

    @Override
    public MultivaluedMap<String, String> getStaticParameters() {
        return new MultivaluedMapImpl();
    }

    @Override
    public String getDrillDownTypeForService(final MultivaluedMap<String, String> requestParameters) {
        drillType = requestParameters.getFirst(DRILLTYPE_PARAM);
        if (StringUtils.isNotBlank(drillType)) {
            return drillType;
        }
        throw new WebApplicationException(400);
    }

    @Override
    public AggregationTableInfo getAggregationView(final String type) {
        return new AggregationTableInfo(NO_AGGREGATION_AVAILABLE);
    }

    @Override
    public List<String> getApplicableTechPacks(final MultivaluedMap<String, String> requestParameters) {
        final String[] techPacksPerRequestType = new String[] { EVENT_E_RAN_SESSION, EVENT_E_USER_PLANE };

        return Arrays.asList(techPacksPerRequestType);
    }

    @Override
    public boolean areRawTablesRequiredForAggregationQueries() {
        return false;
    }

    @Override
    public int getMaxAllowableSize() {
        return applicationConfigManager.getSessionBrowserKpiDrillMaxCount();
    }

    @Override
    public boolean requiredToCheckValidParameterValue(final MultivaluedMap<String, String> requestParameters) {
        return false;
    }

    @Override
    public String getTableSuffixKey() {
        return null;
    }

    @Override
    public List<String> getMeasurementTypes() {
        final List<String> measurementTypes = new ArrayList<String>();
        measurementTypes.add(TCP_PERFORMANCE);
        return measurementTypes;
    }

    @Override
    public List<String> getRawTableKeys() {
        return null;
    }

    @Override
    public String runQuery(final String query, final String requestId,
            final Map<String, QueryParameter> queryParameters, final LoadBalancingPolicy loadBalancingPolicy,
            final Map<String, Object> serviceSpecificDataServiceParameters) {
        final String tzOffset = (String) serviceSpecificDataServiceParameters.get(TZ_OFFSET);
        final String timeStampFrom = queryParameters.get(DATE_FROM).getValue().toString();
        final String timeStampTo = queryParameters.get(DATE_TO).getValue().toString();
        final JsonResultSetTransformer transformer = new JsonResultSetTransformer(timeStampFrom, timeStampTo, tzOffset,
                IUserPlaneChartDrillKpiResult.class, null, drillType);
        return getDataService().getData(requestId, query, queryParameters, transformer);
    }
}
