/**
 * 
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author evijred
 *
 */
public class LTEHFAEventVolumeResult implements QueryResult {

	private int INTERNAL_PROC_HO_PREP_X2_IN_FailCount;
	private int INTERNAL_PROC_HO_PREP_X2_OUT_FailCount;
	private int INTERNAL_PROC_HO_EXEC_X2_IN_FailCount;
	private int INTERNAL_PROC_HO_EXEC_X2_OUT_FailCount;
	private int INTERNAL_PROC_HO_PREP_S1_IN_FailCount;
	private int INTERNAL_PROC_HO_PREP_S1_OUT_FailCount;
	private int INTERNAL_PROC_HO_EXEC_S1_IN_FailCount;
	private int INTERNAL_PROC_HO_EXEC_S1_OUT_FailCount;
	private int impactedSubscribers;
	private String time;
	
	public int getINTERNAL_PROC_HO_PREP_X2_IN_FailCount() {
        return INTERNAL_PROC_HO_PREP_X2_IN_FailCount;
    }
	
	public int getINTERNAL_PROC_HO_PREP_X2_OUT_FailCount() {
        return INTERNAL_PROC_HO_PREP_X2_OUT_FailCount;
    }
	
	public int getINTERNAL_PROC_HO_EXEC_X2_IN_FailCount() {
        return INTERNAL_PROC_HO_EXEC_X2_IN_FailCount;
    }
	
	public int getINTERNAL_PROC_HO_EXEC_X2_OUT_FailCount() {
        return INTERNAL_PROC_HO_EXEC_X2_OUT_FailCount;
    }
	
	public int getINTERNAL_PROC_HO_PREP_S1_IN_FailCount() {
        return INTERNAL_PROC_HO_PREP_S1_IN_FailCount;
    }
	
	public int getINTERNAL_PROC_HO_PREP_S1_OUT_FailCount() {
        return INTERNAL_PROC_HO_PREP_S1_OUT_FailCount;
    }
	
	public int getINTERNAL_PROC_HO_EXEC_S1_IN_FailCount() {
        return INTERNAL_PROC_HO_EXEC_S1_IN_FailCount;
    }
	
	public int getINTERNAL_PROC_HO_EXEC_S1_OUT_FailCount() {
        return INTERNAL_PROC_HO_EXEC_S1_OUT_FailCount;
    }
	
	public int getImpactedSubscribers() {
        return impactedSubscribers;
    }
	
	public String getTime() {
        return time;
    }
	
	

	@Override
	public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
		INTERNAL_PROC_HO_PREP_X2_IN_FailCount = jsonObject.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
		INTERNAL_PROC_HO_PREP_X2_OUT_FailCount = jsonObject.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
		INTERNAL_PROC_HO_EXEC_X2_IN_FailCount = jsonObject.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
		INTERNAL_PROC_HO_EXEC_X2_OUT_FailCount = jsonObject.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
		INTERNAL_PROC_HO_PREP_S1_IN_FailCount = jsonObject.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
		INTERNAL_PROC_HO_PREP_S1_OUT_FailCount = jsonObject.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
		INTERNAL_PROC_HO_EXEC_S1_IN_FailCount = jsonObject.getInt(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
		INTERNAL_PROC_HO_EXEC_S1_OUT_FailCount = jsonObject.getInt(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
		impactedSubscribers = jsonObject.getInt(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
		time = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
	}

}
