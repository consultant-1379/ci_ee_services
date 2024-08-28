package com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Row complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Row">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Req" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ReqSuccess" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="OSS_Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RNC_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RNC_Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UCell_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UCell_Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Row", propOrder = {
        "req",
        "reqSuccess",
        "ossId",
        "rncName",
        "rncId",
        "uCellName",
        "uCellId"
})
public class Row {

    @XmlElement(name = "Req", required = true, type = Double.class, nillable = true)
    protected Double req;
    @XmlElement(name = "ReqSuccess", required = true, type = Double.class, nillable = true)
    protected Double reqSuccess;
    @XmlElement(name = "OSS_Id", required = true, nillable = true)
    protected String ossId;
    @XmlElement(name = "RNC_Name", required = true, nillable = true)
    protected String rncName;
    @XmlElement(name = "RNC_Id", required = true, nillable = true)
    protected String rncId;
    @XmlElement(name = "UCell_Name", required = true, nillable = true)
    protected String uCellName;
    @XmlElement(name = "UCell_Id", required = true, nillable = true)
    protected String uCellId;

    /**
     * Gets the value of the req property.
     *
     * @return possible object is
     *         {@link Double }
     */
    public Double getReq() {
        return req;
    }

    /**
     * Sets the value of the req property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setReq(Double value) {
        this.req = value;
    }

    /**
     * Gets the value of the reqSuccess property.
     *
     * @return possible object is
     *         {@link Double }
     */
    public Double getReqSuccess() {
        return reqSuccess;
    }

    /**
     * Sets the value of the reqSuccess property.
     *
     * @param value allowed object is
     *              {@link Double }
     */
    public void setReqSuccess(Double value) {
        this.reqSuccess = value;
    }

    /**
     * Gets the value of the ossId property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getOSSId() {
        return ossId;
    }

    /**
     * Sets the value of the ossId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOSSId(String value) {
        this.ossId = value;
    }

    /**
     * Gets the value of the rncName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRNCName() {
        return rncName;
    }

    /**
     * Sets the value of the rncName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRNCName(String value) {
        this.rncName = value;
    }

    /**
     * Gets the value of the rncId property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRNCId() {
        return rncId;
    }

    /**
     * Sets the value of the rncId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRNCId(String value) {
        this.rncId = value;
    }

    /**
     * Gets the value of the uCellName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUCellName() {
        return uCellName;
    }

    /**
     * Sets the value of the uCellName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUCellName(String value) {
        this.uCellName = value;
    }

    /**
     * Gets the value of the uCellId property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUCellId() {
        return uCellId;
    }

    /**
     * Sets the value of the uCellId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUCellId(String value) {
        this.uCellId = value;
    }

}
