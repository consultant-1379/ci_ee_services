//
///*
// * -----------------------------------------------------------------------
// *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
// * -----------------------------------------------------------------------
// */
//
//package com.ericsson.eniq.events.server.services.soap.network.kpi.rrc_conn_succ_rate;
//
//import com.ericsson.eniq.events.server.config.ApplicationConfigManager;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import javax.annotation.PostConstruct;
//import javax.ejb.*;
//import javax.ws.rs.core.Application;
//import javax.xml.namespace.QName;
//import javax.xml.ws.Service;
//import javax.xml.ws.WebEndpoint;
//import javax.xml.ws.WebServiceClient;
//import javax.xml.ws.WebServiceFeature;
//
//
///**
// * This class was generated by the JAX-WS RI.
// * JAX-WS RI 2.1.1 in JDK 6
// * Generated source version: 2.1
// *
// */
//// Suppress PMD warnings as it's a generated class
//@SuppressWarnings("PMD")
//@WebServiceClient(name = "ENIQ_Events_Rrc_Conn_Succ_Rate", targetNamespace = "ENIQ_Events_Rrc_Conn_Succ_Rate")
//public class ENIQEventsRrcConnSuccRate extends Service
//{
//
//    @EJB
//    ApplicationConfigManager applicationConfigManager;
//
//    public ENIQEventsRrcConnSuccRate(URL wsdlLocation, QName serviceName) {
//        super(wsdlLocation, serviceName);
//    }
//
//    @PostConstruct
//    public void init() throws MalformedURLException {
//        this = (ENIQEventsRrcConnSuccRate)Service.create(new URL(applicationConfigManager.getBISServiceURL() + "/dswsbobje/qaawsservices/biws?WSDL=1&cuid=AfaUvd9T9I1BsxwJ_YeIohE"),new QName("ENIQ_Events_Rrc_Conn_Succ_Rate", "QueryAsAServiceSoap"));
//    }
//
//    /**
//     *
//     * @return
//     *     returns QueryAsAServiceSoap
//     */
//    @WebEndpoint(name = "QueryAsAServiceSoap")
//    public QueryAsAServiceSoap getQueryAsAServiceSoap() {
//        return (QueryAsAServiceSoap)super.getPort(new QName("ENIQ_Events_Rrc_Conn_Succ_Rate", "QueryAsAServiceSoap"), QueryAsAServiceSoap.class);
//    }
//
//    /**
//     *
//     * @param features
//     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
//     * @return
//     *     returns QueryAsAServiceSoap
//     */
//    @WebEndpoint(name = "QueryAsAServiceSoap")
//    public QueryAsAServiceSoap getQueryAsAServiceSoap(WebServiceFeature... features) {
//        return (QueryAsAServiceSoap)super.getPort(new QName("ENIQ_Events_Rrc_Conn_Succ_Rate", "QueryAsAServiceSoap"), QueryAsAServiceSoap.class, features);
//    }
//
//}
