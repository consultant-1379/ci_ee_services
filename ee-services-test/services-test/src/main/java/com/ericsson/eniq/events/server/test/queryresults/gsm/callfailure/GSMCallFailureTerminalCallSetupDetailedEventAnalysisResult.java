/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ewanggu
 * @since 2011
 * 
 */
public class GSMCallFailureTerminalCallSetupDetailedEventAnalysisResult
		implements QueryResult {

	private String eventTime;

	private String imsi;

	private int tac;

	private String terminalMake;

	private String terminalModel;

	private String controller;

	private String eventType;

	private String releaseType;

	private String vendor;

	private String vamos;

	private String rsai;

	private String channelType;

	private String urgencyCondition;

	private String accessArea;

	private String causeValue;

	private String extendedCauseValue;

	private String afcause;

	private String hier321_id;

	private String hier3_id;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject
	 * (com.ericsson.eniq.events.server.json.JSONObject)
	 */
	@Override
	public void parseJSONObject(final JSONObject object) throws JSONException {

		eventTime = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
		eventType = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
		vendor = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
		controller = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
		accessArea = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
		imsi = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
		tac = object.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
		terminalMake = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
		terminalModel = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
		releaseType = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
		urgencyCondition = object
				.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
		causeValue = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
		extendedCauseValue = object
				.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
		hier3_id = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
		hier321_id = object.getString(INDEX_OF_FIFTEENTH_COLUMN_IN_JSON_RESULT);
		afcause = object.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
		channelType = object
				.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
		vamos = object.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);
		rsai = object.getString(INDEX_OF_NINETEENTH_COLUMN_IN_JSON_RESULT);

	}

	/**
	 * @return the eventTime
	 */
	public String getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime
	 *            the eventTime to set
	 */
	public void setEventTime(final String eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}

	/**
	 * @param imsi
	 *            the imsi to set
	 */
	public void setImsi(final String imsi) {
		this.imsi = imsi;
	}

	/**
	 * @return the tac
	 */
	public int getTac() {
		return tac;
	}

	/**
	 * @param tac
	 *            the tac to set
	 */
	public void setTac(final int tac) {
		this.tac = tac;
	}

	/**
	 * @return the terminalMake
	 */
	public String getTerminalMake() {
		return terminalMake;
	}

	/**
	 * @param terminalMake
	 *            the terminalMake to set
	 */
	public void setTerminalMake(final String terminalMake) {
		this.terminalMake = terminalMake;
	}

	/**
	 * @return the terminalModel
	 */
	public String getTerminalModel() {
		return terminalModel;
	}

	/**
	 * @param terminalModel
	 *            the terminalModel to set
	 */
	public void setTerminalModel(final String terminalModel) {
		this.terminalModel = terminalModel;
	}

	/**
	 * @return the controller
	 */
	public String getController() {
		return controller;
	}

	/**
	 * @param controller
	 *            the controller to set
	 */
	public void setController(final String controller) {
		this.controller = controller;

	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(final String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the releaseType
	 */
	public String getReleaseType() {
		return releaseType;
	}

	/**
	 * @param releaseType
	 *            the releaseType to set
	 */
	public void setReleaseType(final String releaseType) {
		this.releaseType = releaseType;
	}

	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(final String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the vamos
	 */
	public String getVamos() {
		return vamos;
	}

	/**
	 * @param vamos
	 *            the vamos to set
	 */
	public void setVamos(final String vamos) {
		this.vamos = vamos;
	}

	/**
	 * @return the channelType
	 */
	public String getChannelType() {
		return channelType;
	}

	/**
	 * @param channelType
	 *            the channelType to set
	 */
	public void setChannelType(final String channelType) {
		this.channelType = channelType;
	}

	/**
	 * @return the rsai
	 */
	public String getRsai() {
		return rsai;
	}

	/**
	 * @param rsai
	 *            the rsai to set
	 */
	public void setRsai(final String rsai) {
		this.rsai = rsai;
	}

	/**
	 * @return the causeValue
	 */
	public String getCauseValue() {
		return causeValue;
	}

	/**
	 * @param causeValue
	 *            the causeValue to set
	 */
	public void setCauseValue(final String causeValue) {
		this.causeValue = causeValue;
	}

	/**
	 * @return the extendedCauseValue
	 */
	public String getExtendedCauseValue() {
		return extendedCauseValue;
	}

	/**
	 * @param extendedCauseValue
	 *            the extendedCauseValue to set
	 */
	public void setExtendedCauseValue(final String extendedCauseValue) {
		this.extendedCauseValue = extendedCauseValue;
	}

	/**
	 * @return the urgencyCondition
	 */
	public String getUrgencyCondition() {
		return urgencyCondition;
	}

	/**
	 * @param urgencyCondition
	 *            the urgencyCondition to set
	 */
	public void setUrgencyCondition(final String urgencyCondition) {
		this.urgencyCondition = urgencyCondition;
	}

	/**
	 * @return the accessArea
	 */
	public String getAccessArea() {
		return accessArea;
	}

	/**
	 * @param accessArea
	 *            the accessArea to set
	 */
	public void setAccessArea(final String accessArea) {
		this.accessArea = accessArea;
	}

	/**
	 * @param afcause
	 *            the afcause to set
	 */
	public void setAfcause(final String afcause) {
		this.afcause = afcause;
	}

	/**
	 * @return the afcause
	 */
	public String getAfcause() {
		return afcause;
	}

	/**
	 * @param hier3_id
	 *            the hier3_id to set
	 */
	public void setHier3Id(final String hier3_id) {
		this.hier3_id = hier3_id;
	}

	/**
	 * @return the hier3_id
	 */
	public String getHier3Id() {
		return hier3_id;
	}

	/**
	 * @param hier321_id
	 *            the hier321_id to set
	 */
	public void setHier321Id(final String hier321_id) {
		this.hier321_id = hier321_id;
	}

	/**
	 * @return the hier321_id
	 */
	public String gethier321_id() {
		return hier321_id;
	}

}
