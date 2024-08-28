package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.reports.client;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: eeidpar
 * Date: 08/03/12
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class ReportParamInfo {

    String reportName;

    Map<String, String> reportParams;

    public ReportParamInfo() {
    }

    public ReportParamInfo(final String reportName, final Map<String, String> reportParams) {
        this.reportName = reportName;
        this.reportParams = reportParams;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(final String reportName) {
        this.reportName = reportName;
    }

    public Map<String, String> getReportParams() {
        return reportParams;
    }

    public void setReportParams(final Map<String, String> reportParams) {
        this.reportParams = reportParams;
    }
}
