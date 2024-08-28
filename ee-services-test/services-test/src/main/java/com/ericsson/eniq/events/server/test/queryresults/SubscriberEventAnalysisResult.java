package com.ericsson.eniq.events.server.test.queryresults;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

public class SubscriberEventAnalysisResult implements QueryResult {
    private long imsi;

    private int tac;

    private String manufacturer;

    private String marketingName;

    private String procedureIndicatorDescription;

    private String eventType;

    private String evaluationCase;

    private String exceptionClass;

    private String causeValue;

    private String extendedCauseValue;

    private String severityIndicator;

    private String controller;

    private String source_conf;

    private double cpich_ec_no_cell_1;

    private double ul_int_cell1;

    private double rscp_cell_1;

    private String target_conf;

    private String c_id_serv_hsdsch_cell;

    private String crnc_id_serv_hsdsch_cell;

    private double rscp_cell_2;

    private double rscp_cell_3;

    private double rscp_cell_4;

    private double cpich_ec_no_cell_2;

    private double cpich_ec_no_cell_3;

    private double cpich_ec_no_cell_4;

    private double ul_int_cell2;

    private double ul_int_cell3;

    private double ul_int_cell4;

    private int scrambling_code_cell_1;

    private int scrambling_code_cell_2;

    private int scrambling_code_cell_3;

    private int scrambling_code_cell_4;

    private int gbr_ul;

    private int gbr_dl;

    private String utran_ranap_cause;

    private int data_in_dl_rlc_buffers;

    public String getMarketingName() {
        return marketingName;
    }

    public String getController() {
        return controller;
    }

    public String getAccessArea() {
        return accessArea;
    }

    private String accessArea;

    private String eventTime;

    public String getEventTime() {
        return eventTime;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        imsi = object.getLong(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        manufacturer = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        marketingName = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        procedureIndicatorDescription = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        evaluationCase = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        exceptionClass = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        causeValue = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        extendedCauseValue = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        severityIndicator = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);

        source_conf = object.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
        cpich_ec_no_cell_1 = object.getDouble(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        ul_int_cell1 = object.getDouble(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        rscp_cell_1 = object.getDouble(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
        target_conf = object.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);
        c_id_serv_hsdsch_cell = object.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        crnc_id_serv_hsdsch_cell = object.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        rscp_cell_2 = object.getDouble(INDEX_OF_TWENTY_SECOND_COLUMN_IN_JSON_RESULT);
        rscp_cell_3 = object.getDouble(INDEX_OF_TWENTY_THIRD_COLUMN_IN_JSON_RESULT);
        rscp_cell_4 = object.getDouble(INDEX_OF_TWENTY_FOURTH_COLUMN_IN_JSON_RESULT);
        cpich_ec_no_cell_2 = object.getDouble(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
        cpich_ec_no_cell_3 = object.getDouble(INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT);
        cpich_ec_no_cell_4 = object.getDouble(INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT);
        ul_int_cell2 = object.getDouble(INDEX_OF_TWENTY_EIGHT_COLUMN_IN_JSON_RESULT);
        ul_int_cell3 = object.getDouble(INDEX_OF_TWENTY_NINTH_COLUMN_IN_JSON_RESULT);
        ul_int_cell4 = object.getDouble(INDEX_OF_THIRTIETH_COLUMN_IN_JSON_RESULT);
        scrambling_code_cell_1 = object.getInt(INDEX_OF_THIRTY_FIRST_COLUMN_IN_JSON_RESULT);
        scrambling_code_cell_2 = object.getInt(INDEX_OF_THIRTY_SECOND_COLUMN_IN_JSON_RESULT);
        scrambling_code_cell_3 = object.getInt(INDEX_OF_THIRTY_THIRD_COLUMN_IN_JSON_RESULT);
        scrambling_code_cell_4 = object.getInt(INDEX_OF_THIRTY_FOURTH_COLUMN_IN_JSON_RESULT);
        gbr_ul = object.getInt(INDEX_OF_THIRTY_FIFTH_COLUMN_IN_JSON_RESULT);
        gbr_dl = object.getInt(INDEX_OF_THIRTY_SIXTH_COLUMN_IN_JSON_RESULT);
        utran_ranap_cause = object.getString(INDEX_OF_THIRTY_SEVENTH_COLUMN_IN_JSON_RESULT);
        data_in_dl_rlc_buffers = object.getInt(INDEX_OF_THIRTY_EIGHT_COLUMN_IN_JSON_RESULT);        
        c_id_serv_hsdsch_cell = object.getString(INDEX_OF_TWENTIETH_COLUMN_IN_JSON_RESULT);
        crnc_id_serv_hsdsch_cell = object.getString(INDEX_OF_TWENTY_FIRST_COLUMN_IN_JSON_RESULT);
        ul_int_cell3 = object.getDouble(INDEX_OF_TWENTY_EIGHT_COLUMN_IN_JSON_RESULT);
        ul_int_cell4 = object.getDouble(INDEX_OF_TWENTY_NINTH_COLUMN_IN_JSON_RESULT);
    }

    public String getProcedureIndicatorDescription() {
        return procedureIndicatorDescription;
    }

    public long getIMSI() {
        return imsi;
    }

    public int getTAC() {
        return tac;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEvaluationCase() {
        return evaluationCase;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public String getCauseValue() {
        return causeValue;
    }

    public String getExtendedCauseValue() {
        return extendedCauseValue;
    }

    public String getSeverityIndicator() {
        return severityIndicator;
    }

    /**
     * @return the imsi
     */
    public long getImsi() {
        return imsi;
    }

    /**
     * @return the tac
     */
    public int getTac() {
        return tac;
    }

    /**
     * @return the source_conf
     */
    public String getSourceConf() {
        return source_conf;
    }

    /**
     * @return the cpich_ec_no_cell_1
     */
    public double getCpich_ec_no_cell_1() {
        return cpich_ec_no_cell_1;
    }

    /**
     * @return the ul_int_cell1
     */
    public double getUl_int_cell1() {
        return ul_int_cell1;
    }

    /**
     * @return the rscp_cell_1
     */
    public double getRscp_cell_1() {
        return rscp_cell_1;
    }

    /**
     * @return the target_conf
     */
    public String getTarget_conf() {
        return target_conf;
    }

    /**
     * @return the c_id_serv_hsdsch_cell
     */
    public String getC_id_serv_hsdsch_cell() {
        return c_id_serv_hsdsch_cell;
    }

    /**
     * @return the crnc_id_serv_hsdsch_cell
     */
    public String getCrnc_id_serv_hsdsch_cell() {
        return crnc_id_serv_hsdsch_cell;
    }

    /**
     * @return the rscp_cell_2
     */
    public double getRscp_cell_2() {
        return rscp_cell_2;
    }

    /**
     * @return the rscp_cell_3
     */
    public double getRscp_cell_3() {
        return rscp_cell_3;
    }

    /**
     * @return the rscp_cell_4
     */
    public double getRscp_cell_4() {
        return rscp_cell_4;
    }

    /**
     * @return the cpich_ec_no_cell_2
     */
    public double getCpich_ec_no_cell_2() {
        return cpich_ec_no_cell_2;
    }

    /**
     * @return the cpich_ec_no_cell_3
     */
    public double getCpich_ec_no_cell_3() {
        return cpich_ec_no_cell_3;
    }

    /**
     * @return the cpich_ec_no_cell_4
     */
    public double getCpich_ec_no_cell_4() {
        return cpich_ec_no_cell_4;
    }

    /**
     * @return the ul_int_cell2
     */
    public double getUl_int_cell2() {
        return ul_int_cell2;
    }

    /**
     * @return the ul_int_cell3
     */
    public double getUl_int_cell3() {
        return ul_int_cell3;
    }

    /**
     * @return the ul_int_cell4
     */
    public double getUl_int_cell4() {
        return ul_int_cell4;
    }

    /**
     * @return the scrambling_code_cell_1
     */
    public int getScrambling_code_cell_1() {
        return scrambling_code_cell_1;
    }

    /**
     * @return the scrambling_code_cell_2
     */
    public int getScrambling_code_cell_2() {
        return scrambling_code_cell_2;
    }

    /**
     * @return the scrambling_code_cell_3
     */
    public int getScrambling_code_cell_3() {
        return scrambling_code_cell_3;
    }

    /**
     * @return the scrambling_code_cell_4
     */
    public int getScrambling_code_cell_4() {
        return scrambling_code_cell_4;
    }

    /**
     * @return the gbr_ul
     */
    public int getGbr_ul() {
        return gbr_ul;
    }

    /**
     * @return the gbr_dl
     */
    public int getGbr_dl() {
        return gbr_dl;
    }

    /**
     * @return the utran_ranap_cause
     */
    public String getUtran_ranap_cause() {
        return utran_ranap_cause;
    }

    /**
     * @return the data_in_dl_rlc_buffers
     */
    public int getData_in_dl_rlc_buffers() {
        return data_in_dl_rlc_buffers;
    }

}
