/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author edarbla
 */

/*
 * This class represents an object with attributes that
 * 1. describe an error message, 
 * 2. report a success or failure and,
 * 3. shows the relevant data. 
 * 
 */
public class KPIDataType {

    private String success = "false";
    private String errorDescription = "";
    final private List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

    // Declare a constructor to initialise different instances of class DataObj
    public KPIDataType(final Boolean succ, final String errorDesc) {
        this.success = succ.toString();
        this.errorDescription = errorDesc;
    }


    // create a success datatype
    public KPIDataType() {
        this(true, "");
    }

    /*
      * General getters and setters below
      */

    public String getSuccess() {
        return success;
    }

    public void setSuccess(final Boolean success) {
        this.success = success.toString();
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(final String desc) {
        this.errorDescription = desc;
    }

    public List<HashMap<String, String>> getData() {
        return data;
    }
}