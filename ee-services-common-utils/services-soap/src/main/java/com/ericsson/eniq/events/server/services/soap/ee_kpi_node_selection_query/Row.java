package com.ericsson.eniq.events.server.services.soap.ee_kpi_node_selection_query;

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
        "ossId"
})
public class Row {

    @XmlElement(name = "Req", required = true, type = Double.class, nillable = true)
    protected Double req;
    @XmlElement(name = "ReqSuccess", required = true, type = Double.class, nillable = true)
    protected Double reqSuccess;
    @XmlElement(name = "OSS_Id", required = true, nillable = true)
    protected String ossId;

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

}
