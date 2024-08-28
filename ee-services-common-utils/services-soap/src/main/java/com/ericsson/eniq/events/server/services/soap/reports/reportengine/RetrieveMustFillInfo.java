package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


/**
 * <p>Java class for RetrieveMustFillInfo complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="RetrieveMustFillInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RetrieveQueryContext" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveQueryContext" minOccurs="0"/>
 *         &lt;element name="RetrievePromptsInfo" type="{http://reportengine.dsws.businessobjects.com/2005}RetrievePromptsInfo" minOccurs="0"/>
 *         &lt;element name="RetrieveDBLogonInfo" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveDBLogonInfo" minOccurs="0"/>
 *         &lt;element ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}RetrieveTrackData" minOccurs="0"/>
 *         &lt;element ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}RetrieveDataSourceParameterValues" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveMustFillInfo", propOrder = {
        "retrieveQueryContext",
        "retrievePromptsInfo",
        "retrieveDBLogonInfo",
        "retrieveTrackData",
        "retrieveDataSourceParameterValues"
})
public class RetrieveMustFillInfo {

    @XmlElementRef(name = "RetrieveQueryContext", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<RetrieveQueryContext> retrieveQueryContext;
    @XmlElementRef(name = "RetrievePromptsInfo", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<RetrievePromptsInfo> retrievePromptsInfo;
    @XmlElementRef(name = "RetrieveDBLogonInfo", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<RetrieveDBLogonInfo> retrieveDBLogonInfo;
    @XmlElement(name = "RetrieveTrackData", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", nillable = true)
    protected RetrieveTrackData retrieveTrackData;
    @XmlElement(name = "RetrieveDataSourceParameterValues", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", nillable = true)
    protected RetrieveDataSourceParameterValues retrieveDataSourceParameterValues;

    /**
     * Gets the value of the retrieveQueryContext property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link RetrieveQueryContext }{@code >}
     */
    public JAXBElement<RetrieveQueryContext> getRetrieveQueryContext() {
        return retrieveQueryContext;
    }

    /**
     * Sets the value of the retrieveQueryContext property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link RetrieveQueryContext }{@code >}
     */
    public void setRetrieveQueryContext(JAXBElement<RetrieveQueryContext> value) {
        this.retrieveQueryContext = ((JAXBElement<RetrieveQueryContext>) value);
    }

    /**
     * Gets the value of the retrievePromptsInfo property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link RetrievePromptsInfo }{@code >}
     */
    public JAXBElement<RetrievePromptsInfo> getRetrievePromptsInfo() {
        return retrievePromptsInfo;
    }

    /**
     * Sets the value of the retrievePromptsInfo property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link RetrievePromptsInfo }{@code >}
     */
    public void setRetrievePromptsInfo(JAXBElement<RetrievePromptsInfo> value) {
        this.retrievePromptsInfo = ((JAXBElement<RetrievePromptsInfo>) value);
    }

    /**
     * Gets the value of the retrieveDBLogonInfo property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link RetrieveDBLogonInfo }{@code >}
     */
    public JAXBElement<RetrieveDBLogonInfo> getRetrieveDBLogonInfo() {
        return retrieveDBLogonInfo;
    }

    /**
     * Sets the value of the retrieveDBLogonInfo property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link RetrieveDBLogonInfo }{@code >}
     */
    public void setRetrieveDBLogonInfo(JAXBElement<RetrieveDBLogonInfo> value) {
        this.retrieveDBLogonInfo = ((JAXBElement<RetrieveDBLogonInfo>) value);
    }

    /**
     * Gets the value of the retrieveTrackData property.
     *
     * @return possible object is
     *         {@link RetrieveTrackData }
     */
    public RetrieveTrackData getRetrieveTrackData() {
        return retrieveTrackData;
    }

    /**
     * Sets the value of the retrieveTrackData property.
     *
     * @param value allowed object is
     *              {@link RetrieveTrackData }
     */
    public void setRetrieveTrackData(RetrieveTrackData value) {
        this.retrieveTrackData = value;
    }

    /**
     * Gets the value of the retrieveDataSourceParameterValues property.
     *
     * @return possible object is
     *         {@link RetrieveDataSourceParameterValues }
     */
    public RetrieveDataSourceParameterValues getRetrieveDataSourceParameterValues() {
        return retrieveDataSourceParameterValues;
    }

    /**
     * Sets the value of the retrieveDataSourceParameterValues property.
     *
     * @param value allowed object is
     *              {@link RetrieveDataSourceParameterValues }
     */
    public void setRetrieveDataSourceParameterValues(RetrieveDataSourceParameterValues value) {
        this.retrieveDataSourceParameterValues = value;
    }

}
