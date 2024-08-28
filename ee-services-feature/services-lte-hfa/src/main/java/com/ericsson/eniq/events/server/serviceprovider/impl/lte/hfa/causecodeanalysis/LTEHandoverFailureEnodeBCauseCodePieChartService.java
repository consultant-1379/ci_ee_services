/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.lte.hfa.causecodeanalysis;

import static com.ericsson.eniq.events.server.common.ApplicationConfigConstants.*;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.*;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ericsson.eniq.events.server.utils.LTEHandoverFailureCauseCodeEventIdDataHolder;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eeiyspn
 */
@Stateless
@Local(Service.class)
public class LTEHandoverFailureEnodeBCauseCodePieChartService extends GenericService {

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
        final Map<String, Object> serviceSpecificTemplateParameters = new HashMap<String, Object>();
        serviceSpecificTemplateParameters.put(LTE_HFA_CAUSE_CODE_DATA, getCauseCodeIdsWhereClause(requestParameters));
        serviceSpecificTemplateParameters.put(AGGREGATION_VIEW, techPackList.getTechPack(EVENT_E_LTE_HFA)
                .getErrAggregationView());
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
        return LTE_HFA_ENODEB_CAUSE_CODE_ANALYSIS_BY_PIE_CHART;
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
        return new AggregationTableInfo(LTE_HFA_HIER3_EVENTID_CC, EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
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
        techPacks.add(EVENT_E_LTE_HFA);
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
        if (requestParameters.containsKey(GROUP_NAME_PARAM) || requestParameters.containsKey(NODE_PARAM)) {
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
        long hashedIdForController = 0;
        final String node = requestParameters.getFirst(NODE_PARAM);
        if (node != null) {
            final String[] allData = node.split(DELIMITER);
            hashedIdForController = getQueryUtils().createHashIDForController(allData[2], allData[0], allData[1]);
            queryParameters.put(NODE, QueryParameter.createStringParameter(requestParameters.getFirst(NODE_PARAM)));
            if (hashedIdForController != 0) {
                queryParameters.put(HIER3_ID, QueryParameter.createLongParameter(hashedIdForController));
            }
        }
        return queryParameters;
    }

    @Override
    public List<String> getRawTableKeys() {
        final List<String> rawTableKeys = new ArrayList<String>();
        rawTableKeys.add(KEY_TYPE_ERR);
        return rawTableKeys;
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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
     * getTimeColumnIndices()
     */
    @Override
    public List<Integer> getTimeColumnIndices() {
        return new ArrayList<Integer>();
    }

    private List<LTEHandoverFailureCauseCodeEventIdDataHolder> getCauseCodeIdsWhereClause(
            final MultivaluedMap<String, String> requestParameters) {
        final String cclist = requestParameters.getFirst(CAUSE_CODE_ID_LIST);
        if (cclist == null) {
            return null;
        }
        final String[] ccEvntIds = cclist.split(COMMA);
        final List<String> lstOfCcEvntId = Arrays.asList(ccEvntIds);
        final List<LTEHandoverFailureCauseCodeEventIdDataHolder> allCauseCodeEvntIdList = new ArrayList<LTEHandoverFailureCauseCodeEventIdDataHolder>();
        for (final String ccEvntId : lstOfCcEvntId) {
            final String[] ccAndEvntId = ccEvntId.split(HYPHEN);
            final LTEHandoverFailureCauseCodeEventIdDataHolder ccEvntIdData = new LTEHandoverFailureCauseCodeEventIdDataHolder(
                    ccAndEvntId[0], ccAndEvntId[1]);
            allCauseCodeEvntIdList.add(ccEvntIdData);
        }

        return allCauseCodeEvntIdList;
    }
}
