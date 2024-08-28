/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ewanggu
 * @since 2011
 *
 */
public class GSMCallFailureTerminalDetailedEventAnalysisResult implements QueryResult {

    private String eventTime;

    private String imsi;

    private int tac;

    private String eventType;

    private String releaseType;

    private String causeValue;

    private String extendedCauseValue;

    private String controller;

    private String accessArea;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        releaseType = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        causeValue = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        extendedCauseValue = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the eventTime
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * @param eventTime the eventTime to set
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
     * @param imsi the imsi to set
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
     * @param tac the tac to set
     */
    public void setTac(final int tac) {
        this.tac = tac;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
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
     * @param releaseType the releaseType to set
     */
    public void setReleaseType(final String releaseType) {
        this.releaseType = releaseType;
    }

    /**
     * @return the causeValue
     */
    public String getCauseValue() {
        return causeValue;
    }

    /**
     * @param causeValue the causeValue to set
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
     * @param extendedCauseValue the extendedCauseValue to set
     */
    public void setExtendedCauseValue(final String extendedCauseValue) {
        this.extendedCauseValue = extendedCauseValue;
    }

    /**
     * @return the controller
     */
    public String getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(final String controller) {
        this.controller = controller;
    }

    /**
     * @return the accessArea
     */
    public String getAccessArea() {
        return accessArea;
    }

    /**
     * @param accessArea the accessArea to set
     */
    public void setAccessArea(final String accessArea) {
        this.accessArea = accessArea;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ((eventTime == null) ? 0 : eventTime.hashCode());
        result = prime * result + ((imsi == null) ? 0 : imsi.hashCode());

        result = prime * result + tac;
        result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
        result = prime * result + ((releaseType == null) ? 0 : releaseType.hashCode());
        result = prime * result + ((causeValue == null) ? 0 : causeValue.hashCode());
        result = prime * result + ((extendedCauseValue == null) ? 0 : extendedCauseValue.hashCode());
        result = prime * result + ((controller == null) ? 0 : controller.hashCode());
        result = prime * result + ((accessArea == null) ? 0 : accessArea.hashCode());

        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof GSMCallFailureTerminalDetailedEventAnalysisResult)) {
            return false;
        }
        final GSMCallFailureTerminalDetailedEventAnalysisResult other = (GSMCallFailureTerminalDetailedEventAnalysisResult) obj;

        if (eventTime == null) {
            if (other.getEventTime() != null) {
                return false;
            }
        } else if (!eventTime.equals(other.getEventTime())) {
            return false;
        }
        if (imsi == null) {
            if (other.getImsi() != null) {
                return false;
            }
        } else if (!imsi.equals(other.getImsi())) {
            return false;
        }
        if (tac != other.getTac()) {
            return false;
        }
        if (eventType == null) {
            if (other.getEventType() != null) {
                return false;
            }
        } else if (!eventType.equals(other.getEventType())) {
            return false;
        }
        if (releaseType == null) {
            if (other.getReleaseType() != null) {
                return false;
            }
        } else if (!releaseType.equals(other.getReleaseType())) {
            return false;
        }
        if (causeValue == null) {
            if (other.getCauseValue() != null) {
                return false;
            }
        } else if (!causeValue.equals(other.getCauseValue())) {
            return false;
        }
        if (extendedCauseValue == null) {
            if (other.getExtendedCauseValue() != null) {
                return false;
            }
        } else if (!extendedCauseValue.equals(other.getExtendedCauseValue())) {
            return false;
        }
        if (controller == null) {
            if (other.getController() != null) {
                return false;
            }
        } else if (!controller.equals(other.getController())) {
            return false;
        }
        if (accessArea == null) {
            if (other.getAccessArea() != null) {
                return false;
            }
        } else if (!accessArea.equals(other.getAccessArea())) {
            return false;
        }

        return true;
    }

}
