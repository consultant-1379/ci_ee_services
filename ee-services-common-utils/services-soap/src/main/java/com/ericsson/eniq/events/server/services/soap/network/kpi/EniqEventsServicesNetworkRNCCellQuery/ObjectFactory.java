package com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QaaWSHeaderSessionID_QNAME = new QName("EniqEventsServicesNetworkRNCCellQuery", "sessionID");
    private final static QName _QaaWSHeaderSerializedSession_QNAME = new QName("EniqEventsServicesNetworkRNCCellQuery", "serializedSession");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LovValueIndex }
     */
    public LovValueIndex createLovValueIndex() {
        return new LovValueIndex();
    }

    /**
     * Create an instance of {@link Row }
     */
    public Row createRow() {
        return new Row();
    }

    /**
     * Create an instance of {@link QaaWSHeader }
     */
    public QaaWSHeader createQaaWSHeader() {
        return new QaaWSHeader();
    }

    /**
     * Create an instance of {@link ValuesOfUCellId }
     */
    public ValuesOfUCellId createValuesOfUCellId() {
        return new ValuesOfUCellId();
    }

    /**
     * Create an instance of {@link RunQueryAsAServiceEx }
     */
    public RunQueryAsAServiceEx createRunQueryAsAServiceEx() {
        return new RunQueryAsAServiceEx();
    }

    /**
     * Create an instance of {@link ValuesOfUCellIdResponse }
     */
    public ValuesOfUCellIdResponse createValuesOfUCellIdResponse() {
        return new ValuesOfUCellIdResponse();
    }

    /**
     * Create an instance of {@link ValuesOfRNCId }
     */
    public ValuesOfRNCId createValuesOfRNCId() {
        return new ValuesOfRNCId();
    }

    /**
     * Create an instance of {@link ValuesOfDateResponse }
     */
    public ValuesOfDateResponse createValuesOfDateResponse() {
        return new ValuesOfDateResponse();
    }

    /**
     * Create an instance of {@link Lov }
     */
    public Lov createLov() {
        return new Lov();
    }

    /**
     * Create an instance of {@link RunQueryAsAServiceResponse }
     */
    public RunQueryAsAServiceResponse createRunQueryAsAServiceResponse() {
        return new RunQueryAsAServiceResponse();
    }

    /**
     * Create an instance of {@link RunQueryAsAServiceExResponse }
     */
    public RunQueryAsAServiceExResponse createRunQueryAsAServiceExResponse() {
        return new RunQueryAsAServiceExResponse();
    }

    /**
     * Create an instance of {@link RunQueryAsAService }
     */
    public RunQueryAsAService createRunQueryAsAService() {
        return new RunQueryAsAService();
    }

    /**
     * Create an instance of {@link Table }
     */
    public Table createTable() {
        return new Table();
    }

    /**
     * Create an instance of {@link ValuesOfDate }
     */
    public ValuesOfDate createValuesOfDate() {
        return new ValuesOfDate();
    }

    /**
     * Create an instance of {@link ValuesOfRNCIdResponse }
     */
    public ValuesOfRNCIdResponse createValuesOfRNCIdResponse() {
        return new ValuesOfRNCIdResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "EniqEventsServicesNetworkRNCCellQuery", name = "sessionID", scope = QaaWSHeader.class)
    public JAXBElement<String> createQaaWSHeaderSessionID(String value) {
        return new JAXBElement<String>(_QaaWSHeaderSessionID_QNAME, String.class, QaaWSHeader.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "EniqEventsServicesNetworkRNCCellQuery", name = "serializedSession", scope = QaaWSHeader.class)
    public JAXBElement<String> createQaaWSHeaderSerializedSession(String value) {
        return new JAXBElement<String>(_QaaWSHeaderSerializedSession_QNAME, String.class, QaaWSHeader.class, value);
    }

}
