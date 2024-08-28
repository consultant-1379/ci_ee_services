package com.ericsson.eniq.events.server.utils.config;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ericsson.eniq.events.server.utils.config.latency.LatencySettings;

@Stateless
public class ApplicationConfigManagerImpl implements ApplicationConfigManager {

    @EJB
    private PropertyStore propertyStore;

    @EJB
    private LatencySettings latencySettings;

    @Override
    public int getMaxJsonResultSize() {
        final Property maxJsonResultSizeProperty = new Property(ENIQ_EVENTS_MAX_JSON_RESULT_SIZE);
        maxJsonResultSizeProperty.setDefaultValue(DEFAULT_MAXIMUM_JSON_RESULT_SIZE);
        maxJsonResultSizeProperty.setMaxConfigurableValue(MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE);
        return propertyStore.getIntegerPropertyValue(maxJsonResultSizeProperty);
    }

    @Override
    public int getMscEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_MSC_EVENT_FAILURE_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_MSC_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getBscEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_BSC_EVENT_FAILURE_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_BSC_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getCauseCodeEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getCellEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_CELL_EVENT_FAILURE_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_CELL_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getImsiEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_IMSI_EVENT_FAILURE_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_IMSI_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getTerminalEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_TERMINAL_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        property.setDefaultValue(DEFAULT_TERMINAL_EVENT_FAILURE_RANKING_COUNT);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getUnansweredCallsEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_UNANSWERED_CALLS_EVENT_FAILURE_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_UNANSWERED_CALLS_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getDurationCallsEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_DURATION_CALLS_EVENT_FAILURE_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_DURATION_CALLS_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getApnEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_APN_EVENT_FAILURE_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_APN_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    //LTE CFA Support
    @Override
    public int getLTECallFailureSubscriberCallSetupRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_LTE_CFA_IMSI_CALL_SETUP_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_LTE_CFA_IMSI_CALL_SETUP_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getLTECallFailureSubscriberCallDropRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_LTE_CFA_IMSI_CALL_DROP_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_LTE_CFA_IMSI_CALL_DROP_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getEnodeBEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_ENODEB_EVENT_FAILURE_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_ENODEB_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getTerminalAnalysisCount() {
        final Property property = new Property(ENIQ_EVENTS_TERMINAL_ANALYSIS_COUNT);
        property.setDefaultValue(DEFAULT_TERMINAL_ANALYSIS_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getTerminalGroupAnalysisCount() {
        final Property property = new Property(ENIQ_EVENTS_TERMINAL_GROUP_ANALYSIS_COUNT);
        property.setDefaultValue(DEFAULT_TERMINAL_GROUP_ANALYSIS_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getRncEventFailureRankingCount() {
        final Property property = new Property(ENIQ_EVENTS_RNC_EVENT_FAILURE_RANKING_COUNT);
        property.setDefaultValue(DEFAULT_RNC_EVENT_FAILURE_RANKING_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getSubBICount() {
        final Property property = new Property(ENIQ_EVENTS_SUBBI_COUNT);
        property.setDefaultValue(DEFAULT_SUBBI_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getPDPStatisticsCount() {
        final Property property = new Property(ENIQ_EVENTS_PDP_STATISTICS_COUNT);
        property.setDefaultValue(DEFAULT_PDP_STATISTICS_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getLiveLoadCount() {
        final Property property = new Property(ENIQ_EVENTS_LIVE_LOAD_COUNT);
        property.setDefaultValue(DEFAULT_ENIQ_EVENTS_LIVE_LOAD_COUNT);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_LIVE_LOAD_COUNT);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public boolean getOneMinuteAggregation() {
        return propertyStore
                .getBooleanPropertyValue(ENIQ_EVENTS_ONE_MINUTE_AGGREGATION, DEFAULT_ONE_MINUTE_AGGREGATION);

    }

    @Override
    public int getTimeDelayOneMinuteData(final List<String> techPacks) {
        return latencySettings.getTimeDelayLeastOffset(techPacks);
    }

    @Override
    public int getTimeDelayFifteenMinuteData(final List<String> techPacks) {
        return latencySettings.getTimeDelayMediumOffset(techPacks);
    }

    @Override
    public int getTimeDelayDayData(final List<String> techPacks) {
        return latencySettings.getTimeDelayLargeOffset(techPacks);

    }

    @Override
    public int getAPNRetention() {
        final Property property = new Property(ENIQ_EVENTS_APN_RETENTION_IN_DAYS);
        property.setDefaultValue(DEFAULT_APN_RETENTION_IN_DAYS);
        property.setMaxConfigurableValue(MAXIMUM_POSSIBLE_APN_RETENTION_IN_DAYS);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getCancelRequestTimeOut() {
        final Property property = new Property(ENIQ_EVENTS_CANCEL_REQUEST_TIMEOUT_TIME_IN_MIN);
        property.setDefaultValue(DEFAULT_ENIQ_EVENTS_CANCEL_REQUEST_TIMEOUT_TIME_IN_MIN);
        property.setMaxConfigurableValue(MAXIMUM_CANCEL_REQUEST_TIMEOUT_TIME_IN_MIN);
        return propertyStore.getIntegerPropertyValue(property);
    }

    //MSS aggregation time offsets

    @Override
    public int getMSSTimeDelayOneMinuteData() {
        final Property property = new Property(ENIQ_EVENTS_MSS_TIME_DELAY_1MIN_DATA);
        property.setDefaultValue(ENIQ_EVENTS_MSS_TIME_DELAY_1MIN_DATA_DEFAULT_MINUTES);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getMSSTimeDelayFifteenMinuteData() {
        final Property property = new Property(ENIQ_EVENTS_MSS_TIME_DELAY_15MIN_DATA);
        property.setDefaultValue(ENIQ_EVENTS_MSS_TIME_DELAY_15MIN_DATA_DEFAULT_MINUTES);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public int getMSSTimeDelayDayData() {
        final Property property = new Property(ENIQ_EVENTS_MSS_TIME_DELAY_DAY_DATA);
        property.setDefaultValue(ENIQ_EVENTS_MSS_TIME_DELAY_DAY_DATA_DEFAULT_MINUTES);
        return propertyStore.getIntegerPropertyValue(property);
    }

    public void setPropertyStore(final PropertyStore propertyStore) {
        this.propertyStore = propertyStore;
    }

    /**
     * return the complete url to the bis soap service 
     */
    @Override
    public String getBISServiceURL() {
        return propertyStore.getStringPropertyValue(BIS_SOAP_SERVICE_URL, BIS_SOAP_SERVICE_URL_DEFAULT_VALUE);
    }

    /**
     * return a open doc url formatter with protocol ,hostname and port and the opendoc path
     */
    @Override
    public String getBISServiceOpenDocCompleteURLWithFormatter() {
        final String baseUrl = propertyStore.getStringPropertyValue(BIS_SOAP_SERVICE_URL,
                BIS_SOAP_SERVICE_URL_DEFAULT_VALUE);
        final String openDocFormatter = propertyStore.getStringPropertyValue(BIS_OPENDOC_URL_FORMATTER,
                BIS_OPENDOC_URL_FORMATTER_DEFAULT_VALUE);
        return baseUrl + openDocFormatter;
    }

    @Override
    public String getBISReportsRootObjectName() {
        return propertyStore.getStringPropertyValue(BIS_REPORTS_ROOT_OBJECT_NAME,
                BIS_REPORTS_ROOT_OBJECT_NAME_DEFAULT_VALUE);
    }

    @Override
    public String getBISSessionBrowserReportsRootObjectName() {
        return propertyStore.getStringPropertyValue(BIS_SB_REPORTS_ROOT_OBJECT_NAME,
                BIS_SESSION_BROWSER_REPORTS_ROOT_OBJECT_NAME_DEFAULT_VALUE);
    }

    @Override
    public int getSessionBrowserKpiDrillMaxCount() {
        final Property property = new Property(ENIQ_EVENTS_SESSION_BROWSER_KPI_DRILL_MAX_COUNT);
        property.setDefaultValue(ENIQ_EVENTS_SESSION_BROWSER_KPI_DRILL_MAX_COUNT_DEFAULT);
        return propertyStore.getIntegerPropertyValue(property);
    }

    @Override
    public String getBISUsername() {
        return propertyStore.getStringPropertyValue(BIS_SOAP_SERVICE_USERNAME, BIS_SOAP_SERVICE_USERNAME_DEFAULT_VALUE);
    }

    @Override
    public String getBISPassword() {
        String password = propertyStore.getStringPropertyValue(BIS_SOAP_SERVICE_PASSWORD,
                BIS_SOAP_SERVICE_PASSWORD_DEFAULT_VALUE);
        if (password.equals("/")) {
            password = "";
        }
        return password;
    }

    /**
     * @param latencySettings the latencySettings to set
     */
    public void setLatencySettings(final LatencySettings latencySettings) {
        this.latencySettings = latencySettings;
    }

    @Override
    public boolean isDataTieringEnabled() {
        return propertyStore.getBooleanPropertyValue(ENIQ_EVENTS_DATA_TIERING, DEFAULT_DATA_TIERING_ENABLED);
    }

    @Override
    public int getDataTieringDelay() {
        final Property property = new Property(ENIQ_EVENTS_DATA_TIERING_DELAY);
        property.setDefaultValue(ENIQ_EVENTS_DATA_TIERING_DELAY_DEFAULTS_MINUTES);
        return propertyStore.getIntegerPropertyValue(property);
    }

}
