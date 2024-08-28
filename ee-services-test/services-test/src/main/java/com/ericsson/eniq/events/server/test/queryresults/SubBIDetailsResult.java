/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author ericker
 * @since 2011
 *
 */
public class SubBIDetailsResult implements QueryResult {

    public static boolean isTypeIMSI = true;

    private String homeCountry;

    private String mobileNetworkOperator;

    private String roamingStatus;

    private String lastCellLocation;

    private String lastRoutingArea;

    private String lastObservedSGSN;

    private String firstObserved;

    private String lastObserved;

    private String lastObservedPTMSI;

    private String vipStatus;

    private String msisdn;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        int i = 1;
        if (isTypeIMSI) {
            vipStatus = jsonObject.getString(Integer.toString(i++));
            msisdn = jsonObject.getString(Integer.toString(i++));
        }
        homeCountry = jsonObject.getString(Integer.toString(i++));
        mobileNetworkOperator = jsonObject.getString(Integer.toString(i++));
        roamingStatus = jsonObject.getString(Integer.toString(i++));
        lastCellLocation = jsonObject.getString(Integer.toString(i++));
        lastRoutingArea = jsonObject.getString(Integer.toString(i++));
        lastObservedSGSN = jsonObject.getString(Integer.toString(i++));
        firstObserved = jsonObject.getString(Integer.toString(i++));
        lastObserved = jsonObject.getString(Integer.toString(i++));
        lastObservedPTMSI = jsonObject.getString(Integer.toString(i++));
    }

    /**
     * @return the vipStatus
     */
    public final String getVipStatus() {
        return vipStatus;
    }

    /**
     * @param vipStatus the vipStatus to set
     */
    public final void setVipStatus(final String vipStatus) {
        this.vipStatus = vipStatus;
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
        if (!(obj instanceof SubBIDetailsResult)) {
            return false;
        }
        final SubBIDetailsResult other = (SubBIDetailsResult) obj;
        if (firstObserved == null) {
            if (other.firstObserved != null) {
                return false;
            }
        } else if (!firstObserved.equals(other.firstObserved)) {
            return false;
        }
        if (homeCountry == null) {
            if (other.homeCountry != null) {
                return false;
            }
        } else if (!homeCountry.equals(other.homeCountry)) {
            return false;
        }
        if (lastCellLocation == null) {
            if (other.lastCellLocation != null) {
                return false;
            }
        } else if (!lastCellLocation.equals(other.lastCellLocation)) {
            return false;
        }
        if (lastObserved == null) {
            if (other.lastObserved != null) {
                return false;
            }
        } else if (!lastObserved.equals(other.lastObserved)) {
            return false;
        }
        if (lastObservedPTMSI == null) {
            if (other.lastObservedPTMSI != null) {
                return false;
            }
        } else if (!lastObservedPTMSI.equals(other.lastObservedPTMSI)) {
            return false;
        }
        if (lastObservedSGSN == null) {
            if (other.lastObservedSGSN != null) {
                return false;
            }
        } else if (!lastObservedSGSN.equals(other.lastObservedSGSN)) {
            return false;
        }
        if (lastRoutingArea == null) {
            if (other.lastRoutingArea != null) {
                return false;
            }
        } else if (!lastRoutingArea.equals(other.lastRoutingArea)) {
            return false;
        }
        if (mobileNetworkOperator == null) {
            if (other.mobileNetworkOperator != null) {
                return false;
            }
        } else if (!mobileNetworkOperator.equals(other.mobileNetworkOperator)) {
            return false;
        }
        if (roamingStatus == null) {
            if (other.roamingStatus != null) {
                return false;
            }
        } else if (!roamingStatus.equals(other.roamingStatus)) {
            return false;
        }
        if (vipStatus == null) {
            if (other.vipStatus != null) {
                return false;
            }
        } else if (!vipStatus.equals(other.vipStatus)) {
            return false;
        }
        if (msisdn == null) {
            if (other.msisdn != null) {
                return false;
            }
        } else if (!msisdn.equals(other.msisdn)) {
            return false;
        }
        return true;
    }

    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn the msisdn to set
     */
    public void setMsisdn(final String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return the firstObserved
     */
    public final String getFirstObserved() {
        return firstObserved;
    }

    /**
     * @return the homeCountry
     */
    public final String getHomeCountry() {
        return homeCountry;
    }

    /**
     * @return the lastCellLocation
     */
    public final String getLastCellLocation() {
        return lastCellLocation;
    }

    /**
     * @return the lastObserved
     */
    public final String getLastObserved() {
        return lastObserved;
    }

    /**
     * @return the lastObservedPTMSI
     */
    public final String getLastObservedPTMSI() {
        return lastObservedPTMSI;
    }

    /**
     * @return the lastObservedSGSN
     */
    public final String getLastObservedSGSN() {
        return lastObservedSGSN;
    }

    /**
     * @return the lastRoutingArea
     */
    public final String getLastRoutingArea() {
        return lastRoutingArea;
    }

    /**
     * @return the mobileNetworkOperator
     */
    public final String getMobileNetworkOperator() {
        return mobileNetworkOperator;
    }

    /**
     * @return the roamingStatus
     */
    public final String getRoamingStatus() {
        return roamingStatus;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstObserved == null) ? 0 : firstObserved.hashCode());
        result = prime * result + ((homeCountry == null) ? 0 : homeCountry.hashCode());
        result = prime * result + ((lastCellLocation == null) ? 0 : lastCellLocation.hashCode());
        result = prime * result + ((lastObserved == null) ? 0 : lastObserved.hashCode());
        result = prime * result + ((lastObservedPTMSI == null) ? 0 : lastObservedPTMSI.hashCode());
        result = prime * result + ((lastObservedSGSN == null) ? 0 : lastObservedSGSN.hashCode());
        result = prime * result + ((lastRoutingArea == null) ? 0 : lastRoutingArea.hashCode());
        result = prime * result + ((mobileNetworkOperator == null) ? 0 : mobileNetworkOperator.hashCode());
        result = prime * result + ((roamingStatus == null) ? 0 : roamingStatus.hashCode());
        result = prime * result + ((vipStatus == null) ? 0 : vipStatus.hashCode());
        result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
        return result;
    }

    /**
     * @param firstObserved the firstObserved to set
     */
    public final void setFirstObserved(final String firstObserved) {
        this.firstObserved = firstObserved;
    }

    /**
     * @param homeCountry the homeCountry to set
     */
    public final void setHomeCountry(final String homeCountry) {
        this.homeCountry = homeCountry;
    }

    /**
     * @param lastCellLocation the lastCellLocation to set
     */
    public final void setLastCellLocation(final String lastCellLocation) {
        this.lastCellLocation = lastCellLocation;
    }

    /**
     * @param lastObserved the lastObserved to set
     */
    public final void setLastObserved(final String lastObserved) {
        this.lastObserved = lastObserved;
    }

    /**
     * @param lastObservedPTMSI the lastObservedPTMSI to set
     */
    public final void setLastObservedPTMSI(final String lastObservedPTMSI) {
        this.lastObservedPTMSI = lastObservedPTMSI;
    }

    /**
     * @param lastObservedSGSN the lastObservedSGSN to set
     */
    public final void setLastObservedSGSN(final String lastObservedSGSN) {
        this.lastObservedSGSN = lastObservedSGSN;
    }

    /**
     * @param lastRoutingArea the lastRoutingArea to set
     */
    public final void setLastRoutingArea(final String lastRoutingArea) {
        this.lastRoutingArea = lastRoutingArea;
    }

    /**
     * @param mobileNetworkOperator the mobileNetworkOperator to set
     */
    public final void setMobileNetworkOperator(final String mobileNetworkOperator) {
        this.mobileNetworkOperator = mobileNetworkOperator;
    }

    /**
     * @param roamingStatus the roamingStatus to set
     */
    public final void setRoamingStatus(final String roamingStatus) {
        this.roamingStatus = roamingStatus;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("SubBIDetailsResult [homeCountry=").append(homeCountry).append(", mobileNetworkOperator=")
                .append(mobileNetworkOperator).append(", roamingStatus=").append(roamingStatus)
                .append(", lastCellLocation=").append(lastCellLocation).append(", lastRoutingArea=")
                .append(lastRoutingArea).append(", lastObservedSGSN=").append(lastObservedSGSN)
                .append(", firstObserved=").append(firstObserved).append(", lastObserved=").append(lastObserved)
                .append(", lastObservedPTMSI=").append(lastObservedPTMSI).append(", vipStatus=").append(vipStatus)
                .append(", msisdn=").append(msisdn).append("]");
        return builder.toString();
    }

}
