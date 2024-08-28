/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.MessageConstants.*;
import static com.ericsson.eniq.events.server.logging.performance.ServicesPerformanceThreadLocalHolder.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.ericsson.eniq.events.server.common.ApplicationConstants;
import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.common.Group;
import com.ericsson.eniq.events.server.common.GroupHashId;
import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.common.TechPackData;
import com.ericsson.eniq.events.server.common.exception.ServiceException;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.common.tablesandviews.TableType;
import com.ericsson.eniq.events.server.common.tablesandviews.TechPack;
import com.ericsson.eniq.events.server.common.tablesandviews.TechPackTables;
import com.ericsson.eniq.events.server.datasource.loadbalancing.LoadBalancingPolicy;
import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.logging.performance.ServicePerformanceTraceLogger;
import com.ericsson.eniq.events.server.logging.performance.ServicesPerformanceThreadLocalHolder;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.query.TimeRangeSelector;
import com.ericsson.eniq.events.server.services.DataService;
import com.ericsson.eniq.events.server.services.StreamingDataService;
import com.ericsson.eniq.events.server.services.datatiering.DataTieringHandler;
import com.ericsson.eniq.events.server.services.exclusivetacs.ExclusiveTACHandler;
import com.ericsson.eniq.events.server.templates.mappingengine.TemplateMappingEngine;
import com.ericsson.eniq.events.server.templates.utils.TemplateUtils;
import com.ericsson.eniq.events.server.utils.AuditService;
import com.ericsson.eniq.events.server.utils.CSVResponseBuilder;
import com.ericsson.eniq.events.server.utils.DateTimeRange;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.LoadBalancingPolicyService;
import com.ericsson.eniq.events.server.utils.MediaTypeHandler;
import com.ericsson.eniq.events.server.utils.QueryUtils;
import com.ericsson.eniq.events.server.utils.RequestParametersWrapper;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;
import com.ericsson.eniq.events.server.utils.datetime.DateTimeHelper;
import com.ericsson.eniq.events.server.utils.json.JSONUtils;
import com.ericsson.eniq.events.server.utils.techpacks.RawTableFetcher;
import com.ericsson.eniq.events.server.utils.techpacks.TechPackListFactory;
import com.ericsson.eniq.events.server.utils.techpacks.ViewTypeSelector;

/**
 * The Class BaseResource.
 *
 * @author eavidat
 * @author etomcor
 * @author ehaoswa
 * @since 2010
 * @deprecated There is a new Framework for Services. Resources should now extend
 *             com.ericsson.eniq.events.server.resources.AbstractResource. Example can be seen in
 *             com.ericsson.eniq.events.server.resources.MultipleRankingResource. Please also see the wiki for more details.
 *             http://atrclin2.athtem.eei.ericsson.se/wiki/index.php/ENIQ_Events_WCDMA_Services_Rework
 */
@Deprecated
public abstract class BaseResource {

    /**
     * The types restricted to one tech pack.
     */
    protected Map<String, String> typesRestrictedToOneTechPack = new HashMap<String, String>();

    /**
     * The aggregation views.
     */
    protected Map<String, AggregationTableInfo> aggregationViews = new HashMap<String, AggregationTableInfo>();

    /**
     * The uri info.
     */
    @Context
    protected UriInfo uriInfo;

    /**
     * The Performance Trace Logger.
     */
    @EJB
    protected ServicePerformanceTraceLogger performanceTrace;

    /**
     * The data service.
     */
    @EJB
    protected DataService dataService;

    /**
     * The streaming data service.
     */
    @EJB
    protected StreamingDataService streamingDataService;

    /**
     * The application config manager.
     */
    @EJB
    protected ApplicationConfigManager applicationConfigManager;

    /**
     * The response.
     */
    @Context
    protected HttpServletResponse response;

    /**
     * The query utils.
     */
    @EJB
    protected QueryUtils queryUtils;

    /**
     * The template utils.
     */
    @EJB
    protected TemplateUtils templateUtils;

    /**
     * The template mapping engine.
     */
    @EJB
    protected TemplateMappingEngine templateMappingEngine;

    /**
     * The http headers (used to determine the media type).
     */
    @Context
    protected HttpHeaders httpHeaders;

    @EJB
    private AuditService auditService;

    @EJB
    private MediaTypeHandler mediaTypeHandler;

    @EJB
    private CSVResponseBuilder csvResponseBuilder;

    @EJB
    private LoadBalancingPolicyService loadBalancingPolicyService;

    @EJB
    private TechPackListFactory techPackListFactory;

    @EJB
    protected RawTableFetcher rawTableFetcher;

    @EJB
    private ExclusiveTACHandler exclusiveTACHandler;

    @EJB
    protected TimeRangeSelector timeRangeSelector;

    @EJB
    protected DateTimeHelper dateTimeHelper;

    @EJB
    protected DataTieringHandler dataTieringHandler;

    /**
     * Map requests to data service queries and return JSON encoded result for
     * relevant data.
     * <p/>
     * Request parameters are encapsulated in a UriInfo instance. Since there are
     * many potential parameters and these have certain relationships processing
     * is more easily done by accessing those which are relevant.
     *
     * @return JSON encoded results
     * @throws WebApplicationException the web application exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData() throws WebApplicationException {
        try {
            setRequestStartTime(Calendar.getInstance().getTimeInMillis());
            ServicesPerformanceThreadLocalHolder.setUriInfo(uriInfo.getRequestUri().toString());
            final String requestId = httpHeaders.getRequestHeaders().getFirst(REQUEST_ID);
            return getData(requestId, getDecodedQueryParameters());
        } finally {
            setRequestEndTime(Calendar.getInstance().getTimeInMillis());
            performanceTrace.detailed(Level.INFO, getContextInfo());
            releaseAllResources();
        }
    }

    /**
     * Returns the decoded query parameters.
     *
     * @return Decoded query parameters
     */
    protected MultivaluedMap<String, String> getDecodedQueryParameters() {
        return uriInfo.getQueryParameters(true);
    }

    /**
     * Map requests to data service queries and return JSON encoded result for
     * relevant data.
     * <p/>
     * Request parameters are encapsulated in a UriInfo instance. Since there are
     * many potential parameters and these have certain relationships processing
     * is more easily done by accessing those which are relevant.
     *
     * @return results in CSV format
     * @throws WebApplicationException the exception
     */
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getDataAsCSV() throws WebApplicationException {
        try {
            setRequestStartTime(Calendar.getInstance().getTimeInMillis());
            ServicesPerformanceThreadLocalHolder.setUriInfo(uriInfo.getRequestUri().toString());
            final String requestId = httpHeaders.getRequestHeaders().getFirst(REQUEST_ID);
            getData(requestId, getDecodedQueryParameters());
            return buildHttpResponseForCSVData();
        } finally {
            setRequestEndTime(Calendar.getInstance().getTimeInMillis());
            performanceTrace.detailed(Level.INFO, getContextInfo());
            releaseAllResources();
        }
    }

    /**
     * Checks if is valid value.
     *
     * @param requestParameters the request parameters
     * @return true, if checks if is valid value
     */
    protected abstract boolean isValidValue(MultivaluedMap<String, String> requestParameters);

    /**
     * Gets the template. Does not consider the timerange when choosing the
     * template As more and more templates are refactored into templates/queries
     * that use the raw tables and templates/queries that use the aggregation
     * tables, the method below should fade out of use, and be replaced with the
     * overloaded method below
     *
     * @param pathName          the path name
     * @param requestParameters the request parameters
     * @param drillType         the drill type
     * @return the template
     */
    protected String getTemplate(final String pathName, final MultivaluedMap<String, String> requestParameters,
            final String drillType) {
        return templateMappingEngine.getTemplate(pathName, requestParameters, drillType);
    }

    /**
     * Gets the template, and considers the timerange parameter when selecting the
     * template The timerange (which should be one of EventDataSourceType's
     * values) will be translated into the aggregate view type, and this key
     * passed to the template mapping file As more and more templates are
     * refactored into templates/queries that use the raw tables and
     * templates/queries that use the aggregation tables, this method should be
     * used exclusively
     *
     * @param pathName          the path name
     * @param requestParameters the request parameters
     * @param drillType         the drill type
     * @param timerange         timerange should be one of EventDataSourceType's values
     * @param isDataTiered      true will use data tiered view to select templates
     * @return
     */
    protected String getTemplate(final String pathName, final MultivaluedMap<String, String> requestParameters,
            final String drillType, final String timerange, final boolean isDataTiered) {
        String groupName = null;
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            groupName = requestParameters.getFirst(GROUP_NAME_PARAM);
        }

        String tacParam = null;
        if (requestParameters.containsKey(TAC_PARAM)) {
            tacParam = requestParameters.getFirst(TAC_PARAM);
        }

        final String view = timeRangeSelector.getTimeRangeType(timerange,
                queryIsExclusiveTacRelated(groupName, tacParam), isDataTiered);
        return templateMappingEngine.getTemplate(pathName, requestParameters, drillType, view);
    }

    /**
     * Normally the timerange will specify which table (raw or one of the aggregations) is used.  This depends on the individual
     * resource types.
     * However, if the group is the exclusive TAC group, or if the request is for a TAC that is in the EXCLUSIVE_TAC group then the
     * request will be redirected to RAW as the aggregations have removed the events related to TACS in the EXCLUSIVE_TAC group.
     *
     * @param timerange
     * @param groupName
     * @param tacParam
     * @return
     */
    //        private String getDataViewType(final String timerange, final String groupName, final String tacParam) {
    //            if (queryIsExclusiveTacRelated(groupName, tacParam)) {
    //                return RAW_VIEW;
    //            }
    //            return returnAggregateViewType(timerange);
    //        }

    /**
     * Put together the response (including the http headers) for the csv data
     * This includes headers to ensure the data isn't cached.
     *
     * @return the response
     */
    protected Response buildHttpResponseForCSVData() {
        return csvResponseBuilder.buildHttpResponseForCSVData();
    }

    /**
     * Map requests to data service queries and return JSON encoded result for
     * relevant data.
     *
     * @param requestId         corresponds to this request
     * @param requestParameters the request parameters
     * @return JSON encoded results
     * @throws WebApplicationException the exception
     */
    protected abstract String getData(final String requestId, final MultivaluedMap<String, String> requestParameters)
            throws WebApplicationException;

    /**
     * Checks for presence mandatory parameters. If they are not there an error
     * list is updated with the missing parameter.
     *
     * @param requestParameters input request map
     * @return List of missing parameters
     */
    protected abstract List<String> checkParameters(final MultivaluedMap<String, String> requestParameters);

    /**
     * Gets the no such display error response.
     *
     * @param displayType the display type
     * @return the no such display error response
     */
    protected String getNoSuchDisplayErrorResponse(final String displayType) {
        return getErrorResponse(E_NO_SUCH_DISPLAY_TYPE + " : " + displayType);
    }

    /**
     * General error response handler.
     *
     * @param message general error description
     * @param errors  detailed errors
     * @return JSON error result object
     */
    protected String getErrorResponse(final String message, final List<String> errors) {
        final StringBuilder sb = new StringBuilder();
        if (errors != null) {
            for (final String error : errors) {
                sb.append(error);
            }
        }
        return JSONUtils.createJSONErrorResult(message
                + (errors == null || errors.isEmpty() ? "" : " : " + sb.toString()));
    }

    /**
     * Build JSON error result with specified message.
     *
     * @param message - error description
     * @return JSON encoded error result
     */
    protected String getErrorResponse(final String message) {
        return JSONUtils.createJSONErrorResult(message);
    }

    /**
     * Pull out the time/date parameters from the URL parameters in
     * requestParameters, and pass these along to DateTimeRange to be converted
     * into a FormattedDateTimeRange.
     *
     * @param requestParameters the request parameters
     * @param techPacks
     * @return the formatted date time range
     */
    protected FormattedDateTimeRange getFormattedDateTimeRange(final MultivaluedMap<String, String> requestParameters,
            final List<String> techPacks) {
        final FormattedDateTimeRange dateTimeRange = DateTimeRange.getFormattedDateTimeRange(
                requestParameters.getFirst(KEY_PARAM), requestParameters.getFirst(TIME_QUERY_PARAM),
                requestParameters.getFirst(TIME_FROM_QUERY_PARAM), requestParameters.getFirst(TIME_TO_QUERY_PARAM),
                requestParameters.getFirst(DATE_FROM_QUERY_PARAM), requestParameters.getFirst(DATE_TO_QUERY_PARAM),
                requestParameters.getFirst(DATA_TIME_FROM_QUERY_PARAM),
                requestParameters.getFirst(DATA_TIME_TO_QUERY_PARAM), requestParameters.getFirst(TZ_OFFSET),
                applicationConfigManager.getTimeDelayOneMinuteData(techPacks),
                applicationConfigManager.getTimeDelayFifteenMinuteData(techPacks),
                applicationConfigManager.getTimeDelayDayData(techPacks));
        return dateTimeRange;
    }

    /**
     * Gets the formatted date time range.
     *
     * @param requestParameters the request parameters
     * @param techPacks
     * @return the formatted date time range
     */
    protected FormattedDateTimeRange getAndCheckFormattedDateTimeRangeForDailyAggregation(
            final MultivaluedMap<String, String> requestParameters, final List<String> techPacks) {
        FormattedDateTimeRange timerange = getFormattedDateTimeRange(requestParameters, techPacks);
        if (requestParameters.containsKey(TIME_QUERY_PARAM)
                && queryUtils.getEventDataSourceType(timerange).equals(EventDataSourceType.AGGREGATED_DAY.getValue())) {
            timerange = DateTimeRange.getDailyAggregationTimeRangebyLocalTime(
                    requestParameters.getFirst(TIME_QUERY_PARAM),
                    applicationConfigManager.getTimeDelayOneMinuteData(techPacks),
                    applicationConfigManager.getTimeDelayFifteenMinuteData(techPacks),
                    applicationConfigManager.getTimeDelayDayData(techPacks));

        }
        return timerange;
    }

    /**
     * Gets the date time range of chart and summary grid.
     *
     * @param dateTimeRange - FormattedDateTimeRange
     * @param viewName      - aggregation view name
     * @param techPacks
     * @return the formatted dateTime range of chart and summary grid
     * @throws WebApplicationException the parse exception
     */
    public FormattedDateTimeRange getDateTimeRangeOfChartAndSummaryGrid(final FormattedDateTimeRange dateTimeRange,
            final String viewName, final List<String> techPacks) throws WebApplicationException {
        FormattedDateTimeRange newDateTimeRange = null;
        if (viewName.equals(EventDataSourceType.AGGREGATED_15MIN.getValue())) {
            newDateTimeRange = DateTimeRange.getFormattedDateTimeRange(
                    DateTimeRange.formattedDateTimeAgainst15MinsTable(dateTimeRange.getStartDateTime(),
                            dateTimeRange.getMinutesOfStartDateTime()),
                    DateTimeRange.formattedDateTimeAgainst15MinsTable(dateTimeRange.getEndDateTime(),
                            dateTimeRange.getMinutesOfEndDateTime()),
                    applicationConfigManager.getTimeDelayOneMinuteData(techPacks),
                    applicationConfigManager.getTimeDelayFifteenMinuteData(techPacks),
                    applicationConfigManager.getTimeDelayDayData(techPacks));
        } else if (viewName.equals(EventDataSourceType.AGGREGATED_DAY.getValue())) {
            newDateTimeRange = DateTimeRange.getFormattedDateTimeRange(
                    DateTimeRange.formattedDateTimeAgainstDayTable(dateTimeRange.getStartDateTime(),
                            dateTimeRange.getMinutesOfStartDateTime()),
                    DateTimeRange.formattedDateTimeAgainstDayTable(dateTimeRange.getEndDateTime(),
                            dateTimeRange.getMinutesOfEndDateTime()), 0, 0, 0);
        } else {
            newDateTimeRange = dateTimeRange;
        }
        return newDateTimeRange;
    }

    /**
     * Fetch the appropriate LoadBalancingPolicy If the property
     * ENIQ_EVENTS_USE_LOAD_BALANCING_POLICIES is set to false, then no load
     * balancing policy is used
     * <p/>
     * Otherwise this is determined based on the type of query - if the query is
     * IMSI based, then a specific IMSILoadBalancingPolicy is returned Otherwise
     * the default policy (round robin) is returned.
     *
     * @param requestParameters requestParameters from URL
     * @return the load balancing policy
     */
    protected LoadBalancingPolicy getLoadBalancingPolicy(final MultivaluedMap<String, String> requestParameters) {
        return loadBalancingPolicyService.getLoadBalancingPolicy(requestParameters);
    }

    /**
     * Returns true if the first media type defined in the http headers is of type
     * If the acceptableMediaTypes in httpHeaders is null, then false is returned
     * text/csv.
     *
     * @return true, if checks if is media type application csv
     */
    protected boolean isMediaTypeApplicationCSV() {
        return mediaTypeHandler.isMediaTypeApplicationCSV(httpHeaders);
    }

    /**
     * exposed for unit test.
     *
     * @param httpHeaders the httpHeaders to set
     */
    public void setHttpHeaders(final HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    /*
     * (non-Javadoc)
     * 
       * @see java.lang.Object#toString()
       */
    @Override
    public String toString() {
        return "BaseResource{" + "uriInfo=" + uriInfo.getRequestUri() + '}';
    }

    /**
     * Update templateParameters with group definition.
     *
     * @param templateParameters - template parameters
     * @param requestParameters  - URL query parameters
     * @return void
     */
    protected void updateTemplateParametersWithGroupDefinition(final Map<String, Object> templateParameters,
            final MultivaluedMap<String, String> requestParameters) {
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            final Map<String, Group> templateGroupDefs = dataService.getGroupsForTemplates();
            templateParameters.put(GROUP_DEFINITIONS, templateGroupDefs);
            final String groupName = requestParameters.getFirst(GROUP_NAME_PARAM);
            if (groupName == null || groupName.length() == 0) {
                throw new ServiceException(GROUP_NAME_PARAM + " undefined");
            }
        }
    }

    /**
     * Update templateParameters with group definition with HashID.
     *
     * @param templateParameters - template parameters
     * @param requestParameters  - URL query parameters
     * @return void
     */
    protected void updateTemplateParametersWithGroupDefinitionForHashId(final Map<String, Object> templateParameters,
            final MultivaluedMap<String, String> requestParameters) {
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            final Map<String, GroupHashId> templateGroupDefs = dataService.getGroupsForTemplatesForHashId();
            templateParameters.put(GROUP_DEFINITIONS, templateGroupDefs);
            final String groupName = requestParameters.getFirst(GROUP_NAME_PARAM);
            if (groupName == null || groupName.length() == 0) {
                throw new ServiceException(GROUP_NAME_PARAM + " undefined");
            }
        }
    }

    /**
     * Check that the required parameters for a URL query have been provided.
     *
     * @param requestParameters  the request parameters
     * @param requiredParameters the required parameters
     * @return error string if there are missing parameters, null if all required
     *         parameters are present
     */
    protected String checkRequiredParametersExistAndReturnErrorMessage(
            final MultivaluedMap<String, String> requestParameters, final String[] requiredParameters) {
        final List<String> errors = checkRequiredParametersExist(requestParameters, requiredParameters);
        if (!errors.isEmpty()) {
            return getErrorResponse(E_INVALID_OR_MISSING_PARAMS, errors);
        }
        return null;
    }

    /**
     * Check that the required parameters for a URL query have been provided.
     *
     * @param requestParameters the request parameters
     * @param requiredParameter the required parameter
     * @return list of errors if there are missing parameters, null if all
     *         required parameters are present
     */
    protected List<String> checkRequiredParametersExist(final MultivaluedMap<String, String> requestParameters,
            final String requiredParam) {
        return checkRequiredParametersExist(requestParameters, new String[] { requiredParam });
    }

    /**
     * Check that the required parameters for a URL query have been provided.
     *
     * @param requestParameters  the request parameters
     * @param requiredParameters the required parameters
     * @return list of errors if there are missing parameters, null if all
     *         required parameters are present
     */
    protected List<String> checkRequiredParametersExist(final MultivaluedMap<String, String> requestParameters,
            final String[] requiredParameters) {

        // removed temporarily
        //return parameterChecker.checkRequiredParametersIfExists(requestParameters,requiredParameters);

        final List<String> errors = new ArrayList<String>();
        for (final String requiredParameter : requiredParameters) {
            if (!requestParameters.containsKey(requiredParameter)) {
                errors.add(requiredParameter);
            }
        }
        return errors;
    }

    /**
     * This function is used for getting the raw tables which could replace a raw
     * view for a particular time range The function first uses the RMI engine, if
     * it fails then it uses the SQL query
     * <p/>
     * Currently it has been used for timeout issues in
     * Ranking(#MultipleRankingResource) and Event
     * Analysis(#EventAnalysisResource).
     *
     * @param newDateTimeRange the new date time range
     * @param key              to differentiate which RAW tables we need (e.g. error, success)
     * @param templateKey      key passed to template eg rawAllErrTables
     * @return the raw table names
     */
    protected List<String> getRAWTables(final FormattedDateTimeRange newDateTimeRange, final String key,
            final String templateKey) {
        return rawTableFetcher.getRAWTables(newDateTimeRange, key, templateKey);

    }

    /**
     * This function is used for updating the template parameters with the raw
     * table names which could replace a raw view in the template (SQL query) for
     * a particular time range.
     *
     * @param templateParameters the parameters used inside the query template
     * @param dateTimeRange      the date and time range for the query
     * @param key                the key parameter
     * @param templateKey        the key used in velocity template for accessing the RAW table name
     *                           list
     * @return true if raw tables are found; otherwise false
     */
    protected boolean updateTemplateWithRAWTables(final Map<String, Object> templateParameters,
            final FormattedDateTimeRange dateTimeRange, final String key, final String... templateKeys) {

        final List<String> totalRawtables = new ArrayList<String>();

        for (final String templateKey : templateKeys) {
            final List<String> rawtables = getRAWTables(dateTimeRange, key, templateKey);
            totalRawtables.addAll(rawtables);
            templateParameters.put(templateKey, rawtables);
        }

        return !totalRawtables.isEmpty();
    }

    /**
     * This method gets the maxRows value from the parameter map and parses it to
     * an int, including error handling.
     *
     * @param requestParameters the request parameters
     * @param maxAllowableSize  The maximum allowable size for the result set returned
     * @return the value to be put into the count on the query ( <=0 for all
     *         results)
     */
    protected int getCountValue(final MultivaluedMap<String, String> requestParameters, final int maxAllowableSize) {
        return new RequestParametersWrapper(requestParameters).getCountValue(maxAllowableSize);
    }

    /**
     * This method sets up the appropriate headers etc for and executes streaming
     * the csv data into the response.
     *
     * @param requestParameters the request parameters
     * @param tzOffset          the tz offset
     * @param timeColumn        the time column
     * @param query             the query
     * @param newDateTimeRange  the new date time range
     */
    protected void streamDataAsCSV(final MultivaluedMap<String, String> requestParameters, final String tzOffset,
            final String timeColumn, final String query, final FormattedDateTimeRange newDateTimeRange) {
        response.setContentType("application/csv");
        response.setHeader("Content-disposition", "attachment; filename=export.csv");
        try {
            this.streamingDataService.streamDataAsCsv(query, mapQueryParameters(requestParameters, newDateTimeRange),
                    timeColumn, tzOffset, getLoadBalancingPolicy(requestParameters), response.getOutputStream());
        } catch (final IOException e) {
            ServicesLogger.error(getClass().getName(), "streamDataAsCSV", e);
        }
    }

    /**
     * maps the required parameters for queries, this method is overridden in subclass if specific action is required.
     *
     * @param requestParameters
     * @param newDateTimeRange
     * @return
     */
    protected Map<String, QueryParameter> mapQueryParameters(final MultivaluedMap<String, String> requestParameters,
            final FormattedDateTimeRange newDateTimeRange) {
        return this.queryUtils.mapRequestParameters(requestParameters, newDateTimeRange);
    }

    /**
     * If conditions exist for creating an audit log entry, then create a FINE
     * audit log entry
     *
     * @param requestParameters
     * @param query
     * @param newDateTimeRange
     */
    protected void checkAndCreateFineAuditLogEntryForQuery(final MultivaluedMap<String, String> requestParameters,
            final String query, final FormattedDateTimeRange newDateTimeRange) {
        final Map<String, QueryParameter> queryParameters = this.queryUtils.mapRequestParameters(requestParameters,
                newDateTimeRange);
        auditService.logAuditEntryForQuery(uriInfo, requestParameters, query, queryParameters, httpHeaders);
    }

    /**
     * If conditions exist for creating an audit log entry, then create a FINE
     * audit log entry
     *
     * @param requestParameters
     * @param queries
     * @param newDateTimeRange
     */
    protected void checkAndCreateFineAuditLogEntryForQuery(final MultivaluedMap<String, String> requestParameters,
            final List<String> queries, final FormattedDateTimeRange newDateTimeRange) {
        final Map<String, QueryParameter> queryParameters = this.queryUtils.mapRequestParameters(requestParameters,
                newDateTimeRange);
        auditService.logAuditEntryForQuery(uriInfo, requestParameters, queries, queryParameters, httpHeaders);

    }

    /**
     * Check and create info audit log entry for uri.
     *
     * @param requestParameters the request parameters
     */
    protected void checkAndCreateINFOAuditLogEntryForURI(final MultivaluedMap<String, String> requestParameters) {
        auditService.logAuditEntryForURI(uriInfo, requestParameters, httpHeaders);
    }

    /**
     * used for test purposes.
     *
     * @param dataService the data service
     */
    public void setDataService(final DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * used for test purposes.
     *
     * @param applicationConfigManager the application config manager
     */
    public void setApplicationConfigManager(final ApplicationConfigManager applicationConfigManager) {
        this.applicationConfigManager = applicationConfigManager;
    }

    /**
     * used for test purposes.
     *
     * @param queryUtils the query utils
     */
    public void setQueryUtils(final QueryUtils queryUtils) {
        this.queryUtils = queryUtils;
    }

    /**
     * used for test purposes.
     *
     * @param templateUtils the template utils
     */
    public void setTemplateUtils(final TemplateUtils templateUtils) {
        this.templateUtils = templateUtils;

    }

    /**
     * Gets the matching dim techpack.
     *
     * @param techPackName the tech pack name
     * @return the matching dim techpack
     */
    protected String getMatchingDIMTechpack(final String techPackName) {
        return techPackListFactory.getMatchingDIMTechPack(techPackName);
    }

    /**
     * Get aggregation views to use in a query.  Will include all techpacks as per
     * TechPackData.completeTechPackList
     * <p/>
     * Depends on the sub resource sub class populating the aggregationViews map
     * with the correct information for the query/resource
     *
     * @param type      the type
     * @param timerange the timerange
     * @return the aggregation tables
     */
    protected TechPackTables getAggregationTables(final String type, final String timerange) {
        return getAggregationTables(type, timerange, TechPackData.completeSGEHTechPackList);
    }

    /**
     * Gets the dtput aggregation tables.
     *
     * @param type      the type
     * @param timerange the timerange
     * @return the DT put aggregation tables
     */
    protected TechPackTables getDTPutAggregationTables(final String type, final String timerange) {
        return getDTPutAggregationTables(type, timerange, TechPackData.completeGSNTechPackList);
    }

    /**
     * Get aggregation views to use in a query.
     * <p/>
     * Depends on the sub resource sub class populating the aggregationViews map
     * with the correct information for the query/resource
     *
     * @param type            the type
     * @param timerange       the timerange
     * @param listOfTechPacks the list of tech packs
     * @return the aggregation tables
     */
    protected TechPackTables getAggregationTables(final String type, final String timerange,
            final List<String> listOfTechPacks) {
        final TechPackTables techPackTables = new TechPackTables(TableType.AGGREGATION);
        final String errTime = ApplicationConstants.returnAggregateViewType(timerange);
        for (final String techPackName : listOfTechPacks) {
            if (isTechPackApplicableForType(type, techPackName)) {
                final TechPack techPack = new TechPack(techPackName, TableType.AGGREGATION,
                        getMatchingDIMTechpack(techPackName));
                techPack.setErrAggregationView(techPackListFactory.getErrorAggregationView(type, errTime, techPackName,
                        aggregationViews));
                final String sucTime = ViewTypeSelector.returnSuccessAggregateViewType(
                        EventDataSourceType.getEventDataSourceType(timerange), techPackName);
                techPack.setSucAggregationView(techPackListFactory.getSuccessAggregationView(type, sucTime,
                        techPackName, aggregationViews));
                techPackTables.addTechPack(techPack);
            }
        }
        return techPackTables;
    }

    /**
     * Gets the dtput aggregation tables.
     *
     * @param type            the type
     * @param timerange       the timerange
     * @param listOfTechPacks the list of tech packs
     * @return the DT put aggregation tables
     */
    protected TechPackTables getDTPutAggregationTables(final String type, final String timerange,
            final List<String> listOfTechPacks) {
        final TechPackTables techPackTables = new TechPackTables(TableType.AGGREGATION);

        final String aggregationViewForQueryType = aggregationViews.get(type).getAggregationView();
        final String time = ApplicationConstants.returnAggregateViewType(timerange);

        for (final String techPackName : listOfTechPacks) {
            if (isTechPackApplicableForType(type, techPackName)) {
                final TechPack techPack = new TechPack(techPackName, TableType.AGGREGATION,
                        getMatchingDIMTechpack(techPackName));
                if (techPackName.equals(EVENT_E_GSN_DT_TPNAME)) {
                    techPack.setDtAggregationView(techPackName + UNDERSCORE + aggregationViewForQueryType + time);
                } else {
                    techPack.setDtPdpAggregationView(techPackName + UNDERSCORE + aggregationViewForQueryType + time);
                }
                techPackTables.addTechPack(techPack);
            }
        }
        return techPackTables;
    }

    /**
     * Gets the template key matching tech pack.
     *
     * @param techPack the tech pack
     * @return the template key matching tech pack
     */
    protected String getTemplateKeyMatchingTechPack(final String techPack) {
        if (techPack.equals(EVENT_E_SGEH_TPNAME)) {
            return RAW_NON_LTE_TABLES;
        } else if (techPack.equals(EVENT_E_GSN_DTPDP_TPNAME)) {
            return RAW_DTPDP_TABLES;
        } else if (techPack.equals(EVENT_E_GSN_DT_TPNAME)) {
            return RAW_DT_TABLES;
        }
        return RAW_LTE_TABLES;
    }

    /**
     * Get raw tables to use in a query. This method will look up the engine or
     * query the time range views to determine which raw tables are applicable for
     * this time range. Method will include all techpacks in
     * TechPackData.completeTechPackList Depends on the sub resource sub class
     * populating the aggregationViews map with the correct information for the
     * query/resource
     *
     * @param type          one of TYPE_APN, TYPE_SGSN etc
     * @param dateTimeRange
     */
    protected TechPackTables getRawTables(final String type, final FormattedDateTimeRange dateTimeRange) {
        return getRawTables(type, dateTimeRange, TechPackData.completeSGEHTechPackList);
    }

    /**
     * Get DTPUT tables to use in a query.
     * This method will look up the engine or query the time range views to determine which raw tables are applicable
     * for this time range.
     * Method will include all techpacks in TechPackData.completeTechPackList
     * Depends on the sub resource sub class populating the aggregationViews map
     * with the correct information for the query/resource
     *
     * @param type          the type
     * @param dateTimeRange the date time range
     * @return the raw tables
     */
    protected TechPackTables getDTPutRawTables(final String type, final FormattedDateTimeRange dateTimeRange) {
        return getDTPutRawTables(type, dateTimeRange, TechPackData.completeGSNTechPackList);
    }

    /**
     * Get raw tables to use in a query.
     * This method will look up the engine or query the time range views to determine which raw tables are applicable
     * for this time range.
     * Depends on the sub resource sub class populating the aggregationViews map
     * with the correct information for the query/resource
     *
     * @param type            the type
     * @param dateTimeRange   the date time range
     * @param listOfTechPacks the list of tech packs
     * @return the raw tables
     */
    protected TechPackTables getRawTables(final String type, final FormattedDateTimeRange dateTimeRange,
            final List<String> listOfTechPacks) {
        final TechPackTables techPackTables = new TechPackTables(TableType.RAW);
        for (final String techPackName : listOfTechPacks) {
            if (isTechPackApplicableForType(type, techPackName)) {
                final TechPack techPack = new TechPack(techPackName, TableType.RAW,
                        getMatchingDIMTechpack(techPackName));
                final List<String> errTables = rawTableFetcher.getRawErrTables(dateTimeRange, techPackName);
                if (errTables.size() > 0) {
                    techPack.setErrRawTables(errTables);
                }
                final List<String> sucTables = rawTableFetcher.getRawSucTables(dateTimeRange, techPackName);
                if (sucTables.size() > 0) {
                    techPack.setSucRawTables(sucTables);
                }
                if (techPack.hasAnyTables()) {
                    techPackTables.addTechPack(techPack);
                }
            }
        }
        return techPackTables;
    }

    /**
     * If a particular type is restricted to only certain tech pack(s) (eg ECELl
     * is only applicable for the EVENT_E_LTE tech pack) then this method checks
     * that the tech pack in question is the allowed for this type Otherwise just
     * return true - the particular type (eg APN) is applicable to all tech packs
     * <p/>
     * Otherwise just return true - the particular type (eg APN) is applicable to
     * all tech packs.
     * <p/>
     * This method has public access for backward compatibility only - it is used by the BaseResource class, which
     * is to be obsoleted
     *
     * @param type                           the type
     * @param techPack                       the tech pack
     * @param inTypesRestrictedToOneTechPack
     * @return true, if checks if is tech pack applicable for type
     */
    public boolean isTechPackApplicableForType(final String type, final String techPack) {

        if (typesRestrictedToOneTechPack.containsKey(type)) {
            if (typesRestrictedToOneTechPack.get(type).equals(techPack)) {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * Get DTPUT raw tables to use in a query.
     * This method will look up the engine or query the time range views to determine which raw tables are applicable
     * for this time range.
     * Depends on the sub resource sub class populating the aggregationViews map
     * with the correct information for the query/resource
     *
     * @param type            the type
     * @param dateTimeRange   the date time range
     * @param listOfTechPacks the list of tech packs
     * @return the raw tables
     */
    protected TechPackTables getDTPutRawTables(final String type, final FormattedDateTimeRange dateTimeRange,
            final List<String> listOfTechPacks) {
        final TechPackTables techPackTables = new TechPackTables(TableType.RAW);
        for (final String techPackName : listOfTechPacks) {
            if (isTechPackApplicableForType(type, techPackName)) {
                final TechPack techPack = new TechPack(techPackName, TableType.RAW,
                        getMatchingDIMTechpack(techPackName));
                if (techPackName.equals(EVENT_E_GSN_DT_TPNAME)) {
                    final List<String> dtTables = getRAWTables(dateTimeRange, KEY_TYPE_DT,
                            getTemplateKeyMatchingTechPack(techPackName));
                    if (dtTables.size() > 0) {
                        techPack.setDtRawTables(dtTables);
                    }
                } else {
                    final List<String> dtPdpTables = getRAWTables(dateTimeRange, KEY_TYPE_DT,
                            getTemplateKeyMatchingTechPack(techPackName));
                    if (dtPdpTables.size() > 0) {
                        techPack.setDtPdpRawTables(dtPdpTables);
                    }
                }
                if (techPack.hasAnyDTPutTables()) {
                    techPackTables.addTechPack(techPack);
                }
            }
        }
        return techPackTables;
    }

    /**
     * update template parameters with the columns and tables to use in the ranking query.
     *
     * @param type               the type
     * @param templateParameters the template parameters
     */
    protected void addColumnsForQueries(final String type, final Map<String, Object> templateParameters) {
        templateParameters.put(COLUMNS_FOR_QUERY, TechPackData.aggregationColumns.get(type));
        templateParameters.put(COLUMNS_FOR_DTPUT_RANKING_QUERY, TechPackData.dtAggregationColumns.get(type));
    }

    /**
     * Returns true if there is an aggregation view present for this timerange and
     * type Currently depends on the sub resource sub class populating the
     * aggregationViews map with this information
     *
     * @param type      one of TYPE_APN, TYPE_SGSN etc
     * @param timerange one of EventDataSourceType's enums as a string (eg the string
     *                  value of the _1DAY enum is TR_4)
     */
    protected boolean shouldQueryUseAggregationView(final String type, final String timerange) {
        return techPackListFactory.shouldQueryUseAggregationView(type, timerange, aggregationViews);
    }

    /**
     * Returns true if the groupName is not null, and is not the EXCLUSIVE_TAC group, and
     * if there is an aggregation view present for this timerange and type
     * Currently depends on the sub resource sub class populating the aggregationViews map
     * with this information
     *
     * @param type      one of TYPE_APN, TYPE_SGSN etc
     * @param timerange one of EventDataSourceType's enums as a string (eg the string value of the _1DAY enum is TR_4)
     * @param groupName groupName if provided in query, null otherwise
     */
    boolean shouldQueryUseAggregationView(final String type, final String timerange, final String groupName) {
        return shouldQueryUseAggregationView(type, timerange, groupName, null);
    }

    /**
     * Returns true if
     * a. the groupName is not the EXCLUSIVE_TAC group,
     * and
     * b. the tac is not a member of the  EXCLUSIVE_TAC group,
     * and
     * c. if there is an aggregation view present for this timerange and type
     * <p/>
     * Currently depends on the sub resource sub class populating the aggregationViews map
     * with this information
     *
     * @param type      one of TYPE_APN, TYPE_SGSN etc
     * @param timerange one of EventDataSourceType's enums as a string (eg the string value of the _1DAY enum is TR_4)
     * @param groupName groupName if provided in query, null otherwise
     * @param tac       tac if provided in query, null otherwise
     */
    boolean shouldQueryUseAggregationView(final String type, final String timerange, final String groupName,
            final String tac) {
        if (queryIsExclusiveTacRelated(groupName, tac)) {
            return false;
        }
        return shouldQueryUseAggregationView(type, timerange);
    }

    protected boolean queryIsExclusiveTacRelated(final String groupName, final String tac) {
        return exclusiveTACHandler.queryIsExclusiveTacRelated(groupName, tac);

    }

    public boolean isExclusiveTacGroup(final String groupName) {
        return exclusiveTACHandler.isExclusiveTacGroup(groupName);
    }

    /**
     * is this TAC a member of the EXCLUSIVE_TAC group?
     *
     * @param tac
     * @return true if the TAC is a member of the EXCLUSIVE_TAC group, false otherwise
     */
    protected boolean isTacInExclusiveTacGroup(final String tac) {
        return exclusiveTACHandler.isTacInExclusiveTacGroup(tac);
    }

    /**
     * Get the tech pack tables or views that should be used for this query This
     * method relies on the sub resource populating the aggregationViews field in
     * this class in order to determine whether the query should use raw or
     * aggregation views
     *
     * @param dateTimeRange
     * @param type            one of TYPE_APN, TYPE_SGSN etc
     * @param timerange       one of EventDataSourceType's enums as a string (eg the string
     *                        value of the _1DAY enum is TR_4)
     * @param listOfTechPacks list of tech packs that should be considered for this query
     */
    protected TechPackTables getTechPackTablesOrViews(final FormattedDateTimeRange dateTimeRange,
            final String timerange, final String type, final List<String> listOfTechPacks) {
        if (shouldQueryUseAggregationView(type, timerange)) {
            return getAggregationTables(type, timerange, listOfTechPacks);
        }
        return getRawTables(type, dateTimeRange, listOfTechPacks);

    }

    /**
     * @param uriInfo the uriInfo to set
     */
    public void setUriInfo(final UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    /**
     * @param templateMappingEngine the templateMappingEngine to set
     */
    public void setTemplateMappingEngine(final TemplateMappingEngine templateMappingEngine) {
        this.templateMappingEngine = templateMappingEngine;
    }

    public void setAuditService(final AuditService auditService) {
        this.auditService = auditService;
    }

    protected boolean isImsiOrImsiGroupQuery(final MultivaluedMap<String, String> requestParameters) {
        return requestParameters.getFirst(TYPE_PARAM).equals(TYPE_IMSI);
    }

    /**
     * @param performanceTrace the performanceTrace to set
     */
    public void setPerformanceTrace(final ServicePerformanceTraceLogger performanceTrace) {
        this.performanceTrace = performanceTrace;
    }

    public void setMediaTypeHandler(final MediaTypeHandler mediaTypeHandler) {
        this.mediaTypeHandler = mediaTypeHandler;
    }

    public void setLoadBalancingPolicyService(final LoadBalancingPolicyService loadBalancingPolicyService) {
        this.loadBalancingPolicyService = loadBalancingPolicyService;
    }

    public void setCsvResponseBuilder(final CSVResponseBuilder csvResponseBuilder) {
        this.csvResponseBuilder = csvResponseBuilder;
    }

    public void setTechPackListFactory(final TechPackListFactory techPackListFactory) {
        this.techPackListFactory = techPackListFactory;
    }

    public void setRawTableFetcher(final RawTableFetcher rawTableFetcher) {
        this.rawTableFetcher = rawTableFetcher;
    }

    /**
     * @param exclusiveTACHandler the exclusiveTACHandler to set
     */
    public void setExclusiveTACHandler(final ExclusiveTACHandler exclusiveTACHandler) {
        this.exclusiveTACHandler = exclusiveTACHandler;
    }

    public void setTimeRangeSelector(final TimeRangeSelector timeRangeSelector) {
        this.timeRangeSelector = timeRangeSelector;
    }

    public void setDateTimeHelper(final DateTimeHelper dateTimeHelper) {
        this.dateTimeHelper = dateTimeHelper;
    }

    public void setDataTieringHandler(final DataTieringHandler dataTieringHandler) {
        this.dataTieringHandler = dataTieringHandler;
    }
}
