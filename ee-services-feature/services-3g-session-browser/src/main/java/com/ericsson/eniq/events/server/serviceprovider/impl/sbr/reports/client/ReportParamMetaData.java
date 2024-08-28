package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.reports.client;


/**
 * Created by IntelliJ IDEA.
 * User: eeidpar
 * Date: 08/03/12
 * Time: 18:45
 * To change this template use File | Settings | File Templates.
 */
public class ReportParamMetaData {
    private ReportParamInfo[] reportParamMetaData;

    public ReportParamMetaData() {
    }

    public ReportParamMetaData(final ReportParamInfo[] reportParamMetaData) {
        this.reportParamMetaData = reportParamMetaData;
    }

    public ReportParamInfo[] getReportParamMetaData() {
        return reportParamMetaData;
    }

    public void setReportParamMetaData(final ReportParamInfo[] reportParamMetaData) {
        this.reportParamMetaData = reportParamMetaData;
    }

}
