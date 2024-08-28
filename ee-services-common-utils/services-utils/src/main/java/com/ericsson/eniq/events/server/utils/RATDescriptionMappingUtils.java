/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.utils;

import java.util.Collections;
import java.util.Map;
import java.util.Observable;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

/**
 * @author ericker
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class RATDescriptionMappingUtils extends Observable {
    /**
     * Map of rat integer values to rat (string) descriptions eg 0->GSM
     */
    private Map<String, String> ratMappings;

    public void setRatMappings(final Map<String, String> ratMappings) {
        this.ratMappings = Collections.unmodifiableMap(ratMappings);
    }

    /**
     * Will return true if the integer contained in the ratIntegerValue parameter is a valid RAT
     * in the table DIM_E_SGEH_RAT
     *
     * @param ratIntegerValue eg "0" or "1"
     * @return
     */
    public boolean isaRATValue(final String ratIntegerValue) {
        return ratMappings.containsKey(ratIntegerValue);
    }

    /**
     * Get corresponding RAT description as specified in the DIM_E_SGEH_RAT table
     * If RAT value not present in table, then the inputted ratIntegerValue is returned
     *
     * @param ratIntegerValue eg "0" or "1"
     * @return eg GSM or WCDMA
     */
    public String getRATDescription(final String ratIntegerValue) {
        if (ratMappings.containsKey(ratIntegerValue)) {
            return ratMappings.get(ratIntegerValue);
        }
        return ratIntegerValue;
    }

    /**
     * Get corresponding RAT integer value for RAT description as specified in the DIM_E_SGEH_RAT table
     * If RAT description not present in table, then the inputted ratDescription is returned
     *
     * @param ratDescription eg GSM or WCDMA
     * @return eg 0 or 1
     */
    public String getRATIntegerValue(final String ratDescription) {
        for (final String ratIntegerValue : ratMappings.keySet()) {
            if (ratMappings.get(ratIntegerValue).equals(ratDescription)) {
                return ratIntegerValue;
            }
        }
        return ratDescription;
    }

    @Lock(LockType.READ)
    public Map<String, String> getRatDescriptionMapping() {
        return ratMappings;
    }

}
