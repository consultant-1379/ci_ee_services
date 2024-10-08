
package com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "QueryAsAServiceSoap", targetNamespace = "EniqEventsLTENetworkRanKpi")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface QueryAsAServiceSoap {


    /**
     * Get Web Service Provider server info
     * 
     * @param requestHeader
     * @param parameters
     * @return
     *     returns com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi.RunQueryAsAServiceResponse
     */
    @WebMethod(action = "EniqEventsLTENetworkRanKpi/runQueryAsAService")
    @WebResult(name = "runQueryAsAServiceResponse", targetNamespace = "EniqEventsLTENetworkRanKpi", partName = "parameters")
    public RunQueryAsAServiceResponse runQueryAsAService(
        @WebParam(name = "runQueryAsAService", targetNamespace = "EniqEventsLTENetworkRanKpi", partName = "parameters")
        RunQueryAsAService parameters,
        @WebParam(name = "QaaWSHeader", targetNamespace = "EniqEventsLTENetworkRanKpi", header = true, partName = "request_header")
        QaaWSHeader requestHeader);

    /**
     * Get Web Service Provider server info
     * 
     * @param requestHeader
     * @param parameters
     * @return
     *     returns com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi.RunQueryAsAServiceExResponse
     */
    @WebMethod(action = "EniqEventsLTENetworkRanKpi/runQueryAsAServiceEx")
    @WebResult(name = "runQueryAsAServiceExResponse", targetNamespace = "EniqEventsLTENetworkRanKpi", partName = "parameters")
    public RunQueryAsAServiceExResponse runQueryAsAServiceEx(
        @WebParam(name = "runQueryAsAServiceEx", targetNamespace = "EniqEventsLTENetworkRanKpi", partName = "parameters")
        RunQueryAsAServiceEx parameters,
        @WebParam(name = "QaaWSHeader", targetNamespace = "EniqEventsLTENetworkRanKpi", header = true, partName = "request_header")
        QaaWSHeader requestHeader);

    /**
     * Get Web Service Provider server info
     * 
     * @param requestHeader
     * @param parameters
     * @return
     *     returns com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi.ValuesOfDateResponse
     */
    @WebMethod(operationName = "valuesOf_Date", action = "EniqEventsLTENetworkRanKpi/valuesOf_Date")
    @WebResult(name = "valuesOf_DateResponse", targetNamespace = "EniqEventsLTENetworkRanKpi", partName = "parameters")
    public ValuesOfDateResponse valuesOfDate(
        @WebParam(name = "valuesOf_Date", targetNamespace = "EniqEventsLTENetworkRanKpi", partName = "parameters")
        ValuesOfDate parameters,
        @WebParam(name = "QaaWSHeader", targetNamespace = "EniqEventsLTENetworkRanKpi", header = true, partName = "request_header")
        QaaWSHeader requestHeader);

}
