/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConfigConstants.*;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.common.TechPackList;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.datasource.loadbalancing.LoadBalancingPolicy;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericService;
import com.ericsson.eniq.events.server.utils.DateTimeUtils;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.sbr.GenericDetailsPopupProcessor;
import com.ericsson.eniq.events.server.utils.sbr.JsonResultSetTransformer;
import com.ericsson.eniq.events.server.utils.sbr.SbrowserUtil;
import com.ericsson.eniq.events.ui.shared.model.sessionbrowser.popup.*;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2012
 */
@Stateless
@Local(Service.class)
public class SBrowserSgehPopupDetailService extends GenericService {

    public static final int ATTACH_ID = 0;

    public static final int ACTIVATE_ID = 1;

    public static final int RAU_ID = 2;

    public static final int ISRAU_ID = 3;

    public static final int DEACTIVATE_ID = 4;

    public static final int DETACH_ID = 14;

    public static final int SERVICE_REQUEST_ID = 15;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getTemplatePath()
     */
    @Override
    public String getTemplatePath() {
        return POPUP_DETAIL + PATH_SEPARATOR + CORE;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getServiceSpecificTemplateParameters(javax.ws.rs.core.MultivaluedMap, com.ericsson.eniq.events.server.utils.FormattedDateTimeRange, com.ericsson.eniq.events.server.common.TechPackList)
     */
    @Override
    public Map<String, Object> getServiceSpecificTemplateParameters(
            final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange,
            final TechPackList techPackList) {
        final Map<String, Object> serviceSpecificTemplateParameters = new HashMap<String, Object>();
        serviceSpecificTemplateParameters.put(SUBSCRIBER_TYPE, SbrowserUtil.getSubscriberType(requestParameters));
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
        final String eventTimeFromUTC = DateTimeUtils.formattedEventTimeUTC(requestParameters
                .getFirst(EVENT_TIME_FROM_PARAM));
        final String eventTimeToUTC = DateTimeUtils.formattedEventTimeUTC(requestParameters
                .getFirst(EVENT_TIME_TO_PARAM));
        queryParameters.put(EVENT_TIME_FROM_PARAM, QueryParameter.createStringParameter(eventTimeFromUTC));
        queryParameters.put(EVENT_TIME_TO_PARAM, QueryParameter.createStringParameter(eventTimeToUTC));
        queryParameters.put(EVENT_ID_PARAM,
                QueryParameter.createIntParameter(Integer.parseInt(requestParameters.getFirst(EVENT_ID_PARAM))));
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
        return new AggregationTableInfo(NO_AGGREGATION_AVAILABLE);
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
        return MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#requiredToCheckValidParameterValue(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public boolean requiredToCheckValidParameterValue(final MultivaluedMap<String, String> requestParameters) {
        // TODO Auto-generated method stub
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
    public String runQuery(final String query, final String requestId,
                           final Map<String, QueryParameter> queryParameters, final LoadBalancingPolicy loadBalancingPolicy,
                           final Map<String, Object> serviceSpecificDataServiceParameters) {
        final String tzOffset = (String) serviceSpecificDataServiceParameters.get(TZ_OFFSET);
        final String timeStampFrom = queryParameters.get(DATE_FROM).getValue().toString();
        final String timeStampTo = queryParameters.get(DATE_TO).getValue().toString();
        final int eventId = Integer.parseInt(queryParameters.get(EVENT_ID_PARAM).getValue().toString());
        JsonResultSetTransformer transformer = null;
        if (eventId == ATTACH_ID) {
            transformer = new JsonResultSetTransformer(timeStampFrom, timeStampTo, tzOffset,
                    ICorePopupDetailsEventAttach.class, new GenericDetailsPopupProcessor(
                    ICorePopupDetailsEventAttach.class, tzOffset));
        } else if (eventId == ACTIVATE_ID) {
            transformer = new JsonResultSetTransformer(timeStampFrom, timeStampTo, tzOffset,
                    ICorePopupDetailsEventActivate.class, new GenericDetailsPopupProcessor(
                    ICorePopupDetailsEventActivate.class, tzOffset));
        } else if (eventId == DEACTIVATE_ID) {
            transformer = new JsonResultSetTransformer(timeStampFrom, timeStampTo, tzOffset,
                    ICorePopupDetailsEventDeactivate.class, new GenericDetailsPopupProcessor(
                    ICorePopupDetailsEventDeactivate.class, tzOffset));
        } else if (eventId == RAU_ID) {
            transformer = new JsonResultSetTransformer(timeStampFrom, timeStampTo, tzOffset,
                    ICorePopupDetailsEventRau.class, new GenericDetailsPopupProcessor(
                    ICorePopupDetailsEventRau.class, tzOffset));
        } else if (eventId == DETACH_ID) {
            transformer = new JsonResultSetTransformer(timeStampFrom, timeStampTo, tzOffset,
                    ICorePopupDetailsEventDetach.class, new GenericDetailsPopupProcessor(
                    ICorePopupDetailsEventDetach.class, tzOffset));
        } else if (eventId == ISRAU_ID) {
            transformer = new JsonResultSetTransformer(timeStampFrom, timeStampTo, tzOffset,
                    ICorePopupDetailsEventIsRau.class, new GenericDetailsPopupProcessor(
                    ICorePopupDetailsEventIsRau.class, tzOffset));
        } else if (eventId == SERVICE_REQUEST_ID) {
            transformer = new JsonResultSetTransformer(timeStampFrom, timeStampTo, tzOffset,
                    ICorePopupDetailsEventServiceRequest.class, new GenericDetailsPopupProcessor(
                    ICorePopupDetailsEventServiceRequest.class, tzOffset));
        }
        return getDataService().getData(requestId, query, queryParameters, transformer);
    }
}