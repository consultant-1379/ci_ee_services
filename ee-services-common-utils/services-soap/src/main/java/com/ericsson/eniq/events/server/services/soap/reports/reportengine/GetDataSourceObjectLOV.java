package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="session" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataSourceUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataSourceObjectKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="retrieveMustFillInfo" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveMustFillInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "session",
        "dataSourceUID",
        "dataSourceObjectKey",
        "retrieveMustFillInfo"
})
@XmlRootElement(name = "getDataSourceObjectLOV")
public class GetDataSourceObjectLOV {

    @XmlElement(required = true)
    protected String session;
    @XmlElement(required = true)
    protected String dataSourceUID;
    @XmlElement(required = true)
    protected String dataSourceObjectKey;
    protected RetrieveMustFillInfo retrieveMustFillInfo;

    /**
     * Gets the value of the session property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSession(String value) {
        this.session = value;
    }

    /**
     * Gets the value of the dataSourceUID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDataSourceUID() {
        return dataSourceUID;
    }

    /**
     * Sets the value of the dataSourceUID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDataSourceUID(String value) {
        this.dataSourceUID = value;
    }

    /**
     * Gets the value of the dataSourceObjectKey property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDataSourceObjectKey() {
        return dataSourceObjectKey;
    }

    /**
     * Sets the value of the dataSourceObjectKey property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDataSourceObjectKey(String value) {
        this.dataSourceObjectKey = value;
    }

    /**
     * Gets the value of the retrieveMustFillInfo property.
     *
     * @return possible object is
     *         {@link RetrieveMustFillInfo }
     */
    public RetrieveMustFillInfo getRetrieveMustFillInfo() {
        return retrieveMustFillInfo;
    }

    /**
     * Sets the value of the retrieveMustFillInfo property.
     *
     * @param value allowed object is
     *              {@link RetrieveMustFillInfo }
     */
    public void setRetrieveMustFillInfo(RetrieveMustFillInfo value) {
        this.retrieveMustFillInfo = value;
    }

}
