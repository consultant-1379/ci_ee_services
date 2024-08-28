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
public class SubBITerminalResult implements QueryResult {

    private String failureCount;

    private String successCount;

    private String imsi;

    private String manufacturer;

    private String marketingName;

    private int tac;

    private String capability;

    private String imeisv;

    private String totalEvents;

    private String firstSeen;

    private String lastSeen;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        imsi = (String) jsonObject.get("1");
        manufacturer = jsonObject.getString("2");
        marketingName = jsonObject.getString("3");
        tac = jsonObject.getInt("4");
        capability = jsonObject.getString("5");
        imeisv = jsonObject.getString("6");
        failureCount = (String) jsonObject.get("7");
        successCount = jsonObject.getString("8");
        totalEvents = jsonObject.getString("9");
        firstSeen = (String) jsonObject.get("10");
        lastSeen = jsonObject.getString("11");
    }

    /**
     * @param failureCount the failureCount to set
     */
    public final void setFailureCount(final String failureCount) {
        this.failureCount = failureCount;
    }

    /**
     * @param successCount the successCount to set
     */
    public final void setSuccessCount(final String successCount) {
        this.successCount = successCount;
    }

    /**
     * @param imsi the imsi to set
     */
    public final void setImsi(final String imsi) {
        this.imsi = imsi;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public final void setManufacturer(final String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * @param marketingName the marketingName to set
     */
    public final void setMarketingName(final String marketingName) {
        this.marketingName = marketingName;
    }

    /**
     * @param tac the tac to set
     */
    public final void setTac(final int tac) {
        this.tac = tac;
    }

    /**
     * @param capability the capability to set
     */
    public final void setCapability(final String capability) {
        this.capability = capability;
    }

    /**
     * @param imeisv the imeisv to set
     */
    public final void setImeisv(final String imeisv) {
        this.imeisv = imeisv;
    }

    /**
     * @param totalEvents the totalEvents to set
     */
    public final void setTotalEvents(final String totalEvents) {
        this.totalEvents = totalEvents;
    }

    /**
     * @param firstSeen the firstSeen to set
     */
    public final void setFirstSeen(final String firstSeen) {
        this.firstSeen = firstSeen;
    }

    /**
     * @param lastSeen the lastSeen to set
     */
    public final void setLastSeen(final String lastSeen) {
        this.lastSeen = lastSeen;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((capability == null) ? 0 : capability.hashCode());
        result = prime * result + ((failureCount == null) ? 0 : failureCount.hashCode());
        result = prime * result + ((firstSeen == null) ? 0 : firstSeen.hashCode());
        result = prime * result + ((imeisv == null) ? 0 : imeisv.hashCode());
        result = prime * result + ((imsi == null) ? 0 : imsi.hashCode());
        result = prime * result + ((lastSeen == null) ? 0 : lastSeen.hashCode());
        result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
        result = prime * result + ((marketingName == null) ? 0 : marketingName.hashCode());
        result = prime * result + ((successCount == null) ? 0 : successCount.hashCode());
        result = prime * result + tac;
        result = prime * result + ((totalEvents == null) ? 0 : totalEvents.hashCode());
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
        if (!(obj instanceof SubBITerminalResult)) {
            return false;
        }
        final SubBITerminalResult other = (SubBITerminalResult) obj;
        if (capability == null) {
            if (other.capability != null) {
                return false;
            }
        } else if (!capability.equals(other.capability)) {
            return false;
        }
        if (failureCount == null) {
            if (other.failureCount != null) {
                return false;
            }
        } else if (!failureCount.equals(other.failureCount)) {
            return false;
        }
        if (firstSeen == null) {
            if (other.firstSeen != null) {
                return false;
            }
        } else if (!firstSeen.equals(other.firstSeen)) {
            return false;
        }
        if (imeisv == null) {
            if (other.imeisv != null) {
                return false;
            }
        } else if (!imeisv.equals(other.imeisv)) {
            return false;
        }
        if (imsi == null) {
            if (other.imsi != null) {
                return false;
            }
        } else if (!imsi.equals(other.imsi)) {
            return false;
        }
        if (lastSeen == null) {
            if (other.lastSeen != null) {
                return false;
            }
        } else if (!lastSeen.equals(other.lastSeen)) {
            return false;
        }
        if (manufacturer == null) {
            if (other.manufacturer != null) {
                return false;
            }
        } else if (!manufacturer.equals(other.manufacturer)) {
            return false;
        }
        if (marketingName == null) {
            if (other.marketingName != null) {
                return false;
            }
        } else if (!marketingName.equals(other.marketingName)) {
            return false;
        }
        if (successCount == null) {
            if (other.successCount != null) {
                return false;
            }
        } else if (!successCount.equals(other.successCount)) {
            return false;
        }
        if (tac != other.tac) {
            return false;
        }

        if (totalEvents == null) {
            if (other.totalEvents != null) {
                return false;
            }
        } else if (!totalEvents.equals(other.totalEvents)) {
            return false;
        }
        return true;
    }

}
