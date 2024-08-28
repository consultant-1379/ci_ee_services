package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RetrieveData complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="RetrieveData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RetrieveNavigationMap" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveNavigationMap" minOccurs="0"/>
 *         &lt;element name="RetrieveView" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveView" minOccurs="0"/>
 *         &lt;element name="RetrieveDataProvidersInfo" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveDataProvidersInfo" minOccurs="0"/>
 *         &lt;element name="RetrieveCurrentReportState" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveCurrentReportState" minOccurs="0"/>
 *         &lt;element name="RetrieveReportList" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveReportList" minOccurs="0"/>
 *         &lt;element name="RetrieveViewSupport" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveViewSupport" minOccurs="0"/>
 *         &lt;element name="RetrieveDrillInfo" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveDrillInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveData", propOrder = {
        "retrieveNavigationMap",
        "retrieveView",
        "retrieveDataProvidersInfo",
        "retrieveCurrentReportState",
        "retrieveReportList",
        "retrieveViewSupport",
        "retrieveDrillInfo"
})
public class RetrieveData {

    @XmlElementRef(name = "RetrieveNavigationMap", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<RetrieveNavigationMap> retrieveNavigationMap;
    @XmlElementRef(name = "RetrieveView", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<RetrieveView> retrieveView;
    @XmlElementRef(name = "RetrieveDataProvidersInfo", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<RetrieveDataProvidersInfo> retrieveDataProvidersInfo;
    @XmlElementRef(name = "RetrieveCurrentReportState", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<RetrieveCurrentReportState> retrieveCurrentReportState;
    @XmlElementRef(name = "RetrieveReportList", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<RetrieveReportList> retrieveReportList;
    @XmlElementRef(name = "RetrieveViewSupport", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<RetrieveViewSupport> retrieveViewSupport;
    @XmlElementRef(name = "RetrieveDrillInfo", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<RetrieveDrillInfo> retrieveDrillInfo;

    /**
     * Gets the value of the retrieveNavigationMap property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link RetrieveNavigationMap }{@code >}
     */
    public JAXBElement<RetrieveNavigationMap> getRetrieveNavigationMap() {
        return retrieveNavigationMap;
    }

    /**
     * Sets the value of the retrieveNavigationMap property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link RetrieveNavigationMap }{@code >}
     */
    public void setRetrieveNavigationMap(JAXBElement<RetrieveNavigationMap> value) {
        this.retrieveNavigationMap = ((JAXBElement<RetrieveNavigationMap>) value);
    }

    /**
     * Gets the value of the retrieveView property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link RetrieveView }{@code >}
     */
    public JAXBElement<RetrieveView> getRetrieveView() {
        return retrieveView;
    }

    /**
     * Sets the value of the retrieveView property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link RetrieveView }{@code >}
     */
    public void setRetrieveView(JAXBElement<RetrieveView> value) {
        this.retrieveView = ((JAXBElement<RetrieveView>) value);
    }

    /**
     * Gets the value of the retrieveDataProvidersInfo property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link RetrieveDataProvidersInfo }{@code >}
     */
    public JAXBElement<RetrieveDataProvidersInfo> getRetrieveDataProvidersInfo() {
        return retrieveDataProvidersInfo;
    }

    /**
     * Sets the value of the retrieveDataProvidersInfo property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link RetrieveDataProvidersInfo }{@code >}
     */
    public void setRetrieveDataProvidersInfo(JAXBElement<RetrieveDataProvidersInfo> value) {
        this.retrieveDataProvidersInfo = ((JAXBElement<RetrieveDataProvidersInfo>) value);
    }

    /**
     * Gets the value of the retrieveCurrentReportState property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link RetrieveCurrentReportState }{@code >}
     */
    public JAXBElement<RetrieveCurrentReportState> getRetrieveCurrentReportState() {
        return retrieveCurrentReportState;
    }

    /**
     * Sets the value of the retrieveCurrentReportState property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link RetrieveCurrentReportState }{@code >}
     */
    public void setRetrieveCurrentReportState(JAXBElement<RetrieveCurrentReportState> value) {
        this.retrieveCurrentReportState = ((JAXBElement<RetrieveCurrentReportState>) value);
    }

    /**
     * Gets the value of the retrieveReportList property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link RetrieveReportList }{@code >}
     */
    public JAXBElement<RetrieveReportList> getRetrieveReportList() {
        return retrieveReportList;
    }

    /**
     * Sets the value of the retrieveReportList property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link RetrieveReportList }{@code >}
     */
    public void setRetrieveReportList(JAXBElement<RetrieveReportList> value) {
        this.retrieveReportList = ((JAXBElement<RetrieveReportList>) value);
    }

    /**
     * Gets the value of the retrieveViewSupport property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link RetrieveViewSupport }{@code >}
     */
    public JAXBElement<RetrieveViewSupport> getRetrieveViewSupport() {
        return retrieveViewSupport;
    }

    /**
     * Sets the value of the retrieveViewSupport property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link RetrieveViewSupport }{@code >}
     */
    public void setRetrieveViewSupport(JAXBElement<RetrieveViewSupport> value) {
        this.retrieveViewSupport = ((JAXBElement<RetrieveViewSupport>) value);
    }

    /**
     * Gets the value of the retrieveDrillInfo property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link RetrieveDrillInfo }{@code >}
     */
    public JAXBElement<RetrieveDrillInfo> getRetrieveDrillInfo() {
        return retrieveDrillInfo;
    }

    /**
     * Sets the value of the retrieveDrillInfo property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link RetrieveDrillInfo }{@code >}
     */
    public void setRetrieveDrillInfo(JAXBElement<RetrieveDrillInfo> value) {
        this.retrieveDrillInfo = ((JAXBElement<RetrieveDrillInfo>) value);
    }

}
