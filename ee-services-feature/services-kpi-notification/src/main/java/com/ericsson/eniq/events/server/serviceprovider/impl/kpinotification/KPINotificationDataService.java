package com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification;

import static com.ericsson.eniq.events.server.common.ApplicationConfigConstants.*;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification.constants.KPINotificationConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification.constants.KPINotificationConstants;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Stateless
@Local(Service.class)
/**
 *
 * @author echavch
 * @since 2012
 *
 */
public class KPINotificationDataService extends GenericService {

    //all date columns that needs to be converted to user local time
    //UTC_DATETIME_ID is the first column in the resulting query that needs to be converted to local time
    private static final List<Integer> dateColumns = Arrays.asList(1);

    @Override
    public String getTemplatePath() {
        return NOTIFICATION_DATA;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> getServiceSpecificTemplateParameters(
            final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange,
            final TechPackList techPackList) {
        return new HashMap<String, Object>();
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

        final Map<String, QueryParameter> queryParameters = new HashMap<String, QueryParameter>();

        final String notificationSeverityParam = requestParameters.getFirst(NOTIFICATION_SEVERITY_PARAM);
        final int perceivedSeverityValue = KPINotificationConstants
                .getPerceivedSeverityValue(notificationSeverityParam);
        queryParameters.put(PERCEIVED_SEVERITY, QueryParameter.createIntParameter(perceivedSeverityValue));
        return queryParameters;
    }

    @Override
    public List<String> getRequiredParametersForQuery() {
        final List<String> requiredParameters = new ArrayList<String>();
        requiredParameters.add(NOTIFICATION_SEVERITY_PARAM);
        return requiredParameters;
    }

    @Override
    public MultivaluedMap<String, String> getStaticParameters() {
        return new MultivaluedMapImpl();
    }

    @Override
    public String getDrillDownTypeForService(final MultivaluedMap<String, String> requestParameters) {
        return null;
    }

    @Override
    public AggregationTableInfo getAggregationView(final String type) {
        return new AggregationTableInfo(NO_AGGREGATION_AVAILABLE);
    }

    @Override
    public List<String> getApplicableTechPacks(final MultivaluedMap<String, String> requestParameters) {
        return Arrays.asList(DC_Z_ALARM);

    }

    @Override
    public boolean areRawTablesRequiredForAggregationQueries() {
        return false;
    }

    @Override
    public int getMaxAllowableSize() {
        return MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE;
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
        return null;
    }

    @Override
    public List<String> getRawTableKeys() {
        final List<String> rawTableKeys = new ArrayList<String>();
        rawTableKeys.add(INFO);
        return rawTableKeys;
    }

    @Override
    public List<Integer> getTimeColumnIndices() {
        return dateColumns;
    }
}