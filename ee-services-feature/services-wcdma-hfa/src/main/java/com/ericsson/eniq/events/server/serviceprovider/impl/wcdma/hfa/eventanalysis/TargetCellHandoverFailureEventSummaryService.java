/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.hfa.eventanalysis;

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

import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.common.TechPackList;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericService;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 *
 * @author eromsza
 * @since 2011
 *
 */
@Stateless
@Local(Service.class)
public class TargetCellHandoverFailureEventSummaryService extends GenericService {

    private static final String TARGET_AGG_VIEW_TYPE = "THIER3_CELL";

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getServiceSpecificTemplateParameters(javax.ws.rs.core.MultivaluedMap, com.ericsson.eniq.events.server.utils.DateTimeRange.FormattedDateTimeRange, com.ericsson.eniq.events.server.utils.techpacks.TechPackList)
     */
    @Override
    public Map<String, Object> getServiceSpecificTemplateParameters(
            final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange,
            final TechPackList techPackList) {
        final Map<String, Object> serviceSpecificTemplateParameters = new HashMap<String, Object>();
        final List<String> otherMeasurementTypes = new ArrayList<String>();
        otherMeasurementTypes.add(HFA_SOHO);
        otherMeasurementTypes.add(HFA_IFHO);
        otherMeasurementTypes.add(HFA_HSDSCH);
        serviceSpecificTemplateParameters.put(AGGREGATION_VEIW_LIST, techPackList
                .getSpecificAggregationViewsWithMeasurementType(EVENT_E_RAN_HFA, otherMeasurementTypes,
                        TARGET_AGG_VIEW_TYPE));
        final List<String> specificMeasurementTypes = new ArrayList<String>();
        specificMeasurementTypes.add(HFA_IRAT);
        serviceSpecificTemplateParameters.put(AGGREGATION_VIEW_LIST_SPECIFIC, techPackList
                .getSpecificAggregationViewsWithMeasurementType(EVENT_E_RAN_HFA, specificMeasurementTypes,
                        TARGET_AGG_VIEW_TYPE));
        return serviceSpecificTemplateParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getTemplatePath()
     */
    @Override
    public String getTemplatePath() {
        return WCDMA_HFA_EVENT_SUMMARY + "/" + TYPE_TARGET_CELL;
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
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getRequiredParametersForQuery()
     */
    @Override
    public List<String> getRequiredParametersForQuery() {
        final List<String> requiredParameters = new ArrayList<String>();
        requiredParameters.add(TZ_OFFSET);
        requiredParameters.add(RAT_PARAM);
        requiredParameters.add(RNC_FDN_PARAM);
        requiredParameters.add(RAN_VENDOR_PARAM);
        requiredParameters.add(CELL_ID_PARAM);
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
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getAggregationView(java.lang.String)
     */
    @Override
    public AggregationTableInfo getAggregationView(final String type) {
        return new AggregationTableInfo("THIER3_CELL", EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getApplicableTechPacks(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public List<String> getApplicableTechPacks(final MultivaluedMap<String, String> requestParameters) {
        final List<String> techPackList = new ArrayList<String>();
        techPackList.add(EVENT_E_RAN_HFA);
        return techPackList;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#areRawTablesRequiredForAggregationQueries()
     */
    @Override
    public boolean areRawTablesRequiredForAggregationQueries() {
        return true;
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
        return false;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getServiceSpecificQueryParameters(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public Map<String, QueryParameter> getServiceSpecificQueryParameters(
            final MultivaluedMap<String, String> requestParameters) {
        final Map<String, QueryParameter> queryParameters = new HashMap<String, QueryParameter>();
        final String rat = requestParameters.getFirst(RAT_PARAM);
        final String controller = requestParameters.getFirst(RNC_FDN_PARAM);
        final String cellOrSAC = requestParameters.getFirst(CELL_ID_PARAM);
        final String vendor = requestParameters.getFirst(RAN_VENDOR_PARAM);
        final long hashedIdFor2GCell;
        final long hashedIdFor3GCellOrSAC;
        if (rat.equals("0")) {
            hashedIdFor2GCell = getQueryUtils().createHashIDForCell(GSM, controller, "", cellOrSAC, vendor);
            hashedIdFor3GCellOrSAC = -1;
        } else {
            hashedIdFor2GCell = -1;
            hashedIdFor3GCellOrSAC = getQueryUtils().createHashIDFor3GCell(_3G, controller, cellOrSAC, vendor);
        }

        queryParameters.put(THIER321_HASHID, QueryParameter.createLongParameter(hashedIdFor2GCell));
        queryParameters.put(THIER3_CELL_HASHID, QueryParameter.createLongParameter(hashedIdFor3GCellOrSAC));
        return queryParameters;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getDrillDownTypeForService(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public String getDrillDownTypeForService(final MultivaluedMap<String, String> requestParameters) {
        return null;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getMeasurementTypes()
     */
    @Override
    public List<String> getMeasurementTypes() {
        final List<String> measurementTypes = new ArrayList<String>();
        measurementTypes.add(HFA_IFHO);
        measurementTypes.add(HFA_SOHO);
        measurementTypes.add(HFA_IRAT);
        measurementTypes.add(HFA_HSDSCH);
        return measurementTypes;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getMeasurementTypes()
     */
    @Override
    public String getTableSuffixKey() {
        return KEY_TYPE_ERR;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getRawTableKeys()
     */
    @Override
    public List<String> getRawTableKeys() {
        return null;
    }
}