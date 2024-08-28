package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 */
@WebFault(name = "DSWSException", targetNamespace = "http://dsws.businessobjects.com/2007/06/01")
public class DSWSException_Exception
        extends Exception {

    /**
     * Java type that goes as soapenv:Fault detail element.
     */
    private DSWSException faultInfo;

    /**
     * @param message
     * @param faultInfo
     */
    public DSWSException_Exception(String message, DSWSException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * @param message
     * @param faultInfo
     * @param cause
     */
    public DSWSException_Exception(String message, DSWSException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * @return returns fault bean: com.ericsson.eniq.events.server.services.soap.reports.bicatalog.DSWSException
     */
    public DSWSException getFaultInfo() {
        return faultInfo;
    }

}
