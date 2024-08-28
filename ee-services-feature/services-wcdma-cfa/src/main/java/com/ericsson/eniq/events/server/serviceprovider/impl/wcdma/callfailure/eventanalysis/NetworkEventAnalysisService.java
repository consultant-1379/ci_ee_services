/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
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

import com.ericsson.eniq.events.server.common.EventDataSourceType;
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
 * @author eflatib
 *         <p/>
 *         This is a hybrid Service to support NODE-SUMMARY and GROUP-SUMMARY
 *         hence the mod in getServiceSpecificQueryParameters()
 */
@Stateless
@Local(Service.class)
public class NetworkEventAnalysisService extends GenericService {

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * getServiceSpecificTemplateParameters(javax.ws.rs.core.MultivaluedMap,
     * com.
     * ericsson.eniq.events.server.utils.DateTimeRange.FormattedDateTimeRange)
     */
    @Override
    public Map<String, Object> getServiceSpecificTemplateParameters(
            final MultivaluedMap<String, String> requestParameters, final FormattedDateTimeRange dateTimeRange,
            final TechPackList techPackList) {
        final Map<String, Object> serviceSpecificTemplateParameters = WcdmaCfaUtils
                .getCategoryInformation(requestParameters);

        serviceSpecificTemplateParameters.put(AGGREGATION_VIEW, techPackList.getTechPack(EVENT_E_RAN_CFA)
                .getErrAggregationView());

        serviceSpecificTemplateParameters.put(ALL_CALLS_AGGREGATION_VIEW, techPackList.getTechPack(EVENT_E_RAN_CFA)
                .getAllCallsAggregationView());

        return serviceSpecificTemplateParameters;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * getTemplatePath()
     */
    @Override
    public String getTemplatePath() {
        return WCDMA_CFA_NETWORK_EVENT_ANALYSIS;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * getServiceSpecificDataServiceParameters(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public Map<String, Object> getServiceSpecificDataServiceParameters(
            final MultivaluedMap<String, String> requestParameters) {
        final Map<String, Object> dataServiceParameters = new HashMap<String, Object>();
        dataServiceParameters.put(TZ_OFFSET, requestParameters.getFirst(TZ_OFFSET));
        return dataServiceParameters;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * getRequiredParametersForQuery()
     */
    @Override
    public List<String> getRequiredParametersForQuery() {
        final List<String> requiredParameters = new ArrayList<String>();
        requiredParameters.add(TZ_OFFSET);
        //        requiredParameters.add(EVENT_ID_PARAM);
        //        requiredParameters.add(CATEGORY_ID_PARAM);
        return requiredParameters;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * getValidDisplayParameters()
     */
    @Override
    public MultivaluedMap<String, String> getStaticParameters() {
        return new MultivaluedMapImpl();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * getDrillDownTypeForService()
     */
    @Override
    public String getDrillDownTypeForService(final MultivaluedMap<String, String> requestParameters) {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * getAggregationViews()
     */
    @Override
    public AggregationTableInfo getAggregationView(final String type) {

        if (type.equals(CELL)) {
            return new AggregationTableInfo(HIER3_CELL_ID_AGG_EVENT_ID, HIER3_CELL_ID,
                    EventDataSourceType.AGGREGATED_15MIN, EventDataSourceType.AGGREGATED_DAY);
        } else if (type.equals(BSC)) {
            return new AggregationTableInfo(HIER3_EVENTID, "HIER3", EventDataSourceType.AGGREGATED_15MIN,
                    EventDataSourceType.AGGREGATED_DAY);
        }

        return new AggregationTableInfo(NO_AGGREGATION_AVAILABLE);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * getApplicableTechPacks(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public List<String> getApplicableTechPacks(final MultivaluedMap<String, String> requestParameters) {
        final List<String> techPacks = new ArrayList<String>();
        techPacks.add(EVENT_E_RAN_CFA);
        return techPacks;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * areRawTablesRequiredForQuery()
     */
    @Override
    public boolean areRawTablesRequiredForAggregationQueries() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * getMaxAllowableSize()
     */
    @Override
    public int getMaxAllowableSize() {
        return MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * requiredToCheckValidParameterValue(javax.ws.rs.core.MultivaluedMap)
     */
    @Override
    public boolean requiredToCheckValidParameterValue(final MultivaluedMap<String, String> requestParameters) {
        if (requestParameters.containsKey(NODE_PARAM) || requestParameters.containsKey(GROUP_NAME_PARAM)) {
            return true;
        }
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
            final long hashedIdForController = getHashedIDForController(requestParameters);
            queryParameters.put(HIER3_ID, QueryParameter.createLongParameter(hashedIdForController));
        } else if (requestParametersWrapper.isAccessArea()) {
            final long hashedIdForCell = getHashedIDForAccessArea(requestParameters);
            queryParameters.put(HIER3_CELL_ID_AGG, QueryParameter.createLongParameter(hashedIdForCell));
        }
        if (requestParameters.containsKey(EVENT_ID_PARAM)) {
            final String eventId = requestParameters.getFirst(EVENT_ID_PARAM);
            queryParameters.put(EVENT_ID_PARAM, QueryParameter.createIntParameter(Integer.parseInt(eventId)));
        }

        return queryParameters;
    }

    private long getHashedIDForController(final MultivaluedMap<String, String> requestParameters) {
        final String hashedIdForControllerAsString = requestParameters.getFirst(HIER3_ID); // Controller
        long hashedIdForController = 0;
        if (hashedIdForControllerAsString == null) {
            final String node = requestParameters.getFirst(NODE_PARAM);
            if (node != null) {
                final String[] allData = node.split(DELIMITER);
                hashedIdForController = getQueryUtils().createHashIDForController(allData[2], allData[0], allData[1]);
            }
        } else {
            hashedIdForController = Long.parseLong(hashedIdForControllerAsString);
        }

        return hashedIdForController;
    }

    private long getHashedIDForAccessArea(final MultivaluedMap<String, String> requestParameters) {
        final String hashedIdForCellAsString = requestParameters.getFirst(HIER3_CELL_ID_AGG);
        long hashedIdForCell = 0;
        if (hashedIdForCellAsString == null) {
            final String node = requestParameters.getFirst(NODE_PARAM);
            if (node != null) {
                final String[] allData = node.split(DELIMITER);
                hashedIdForCell = getQueryUtils().createHashIDFor3GCell(allData[4], allData[2], allData[0], allData[3]);
            }
        } else {
            hashedIdForCell = Long.parseLong(hashedIdForCellAsString);
        }
        return hashedIdForCell;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface
     * #getTableSuffixKey()
     */
    @Override
    public String getTableSuffixKey() {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface
     * #getMeasurementTypes()
     */
    @Override
    public List<String> getMeasurementTypes() {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface
     * #getRawTableKeys()
     */
    @Override
    public List<String> getRawTableKeys() {
        final List<String> rawTableKeys = new ArrayList<String>();
        rawTableKeys.add(KEY_TYPE_ERR);
        return rawTableKeys;
    }

    @Override
    public boolean isDataTieredService(final MultivaluedMap<String, String> parameters) {
        return (parameters.getFirst(TYPE_PARAM).equalsIgnoreCase(TYPE_BSC) || parameters.getFirst(TYPE_PARAM)
                .equalsIgnoreCase(TYPE_CELL)) && !parameters.containsKey(GROUP_NAME_PARAM);
    }

}
