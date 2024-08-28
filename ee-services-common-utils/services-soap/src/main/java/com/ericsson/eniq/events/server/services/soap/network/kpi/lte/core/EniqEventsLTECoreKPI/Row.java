
package com.ericsson.eniq.events.server.services.soap.network.kpi.lte.core.EniqEventsLTECoreKPI;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Row complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Row">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="VS_MM_UnsuccAttach_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_EpsAttachFail_7_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_EpsAttachFail_8_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_EpsAttachFail_11_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_EpsAttachFail_12_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_EpsAttachFail_13_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_EpsAttachFail_15_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_EpsAttachFail_14_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_UnsuccAttachCC27_28_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_UnsuccAttachCC32_33_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_AttAttach_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_UnsuccServiceReq_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_AttServiceRequest_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_AttPsPaging_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_SuccPsPaging_E" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_NbrHomeSub_E__sum_" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_NbrVisitingForeign_E__sum_" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_NbrVisitingNatSub_E__sum_" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="VS_MM_NbrActAttachedSub_E__sum_" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Row", propOrder = {
    "date",
    "vsmmUnsuccAttachE",
    "vsmmEpsAttachFail7E",
    "vsmmEpsAttachFail8E",
    "vsmmEpsAttachFail11E",
    "vsmmEpsAttachFail12E",
    "vsmmEpsAttachFail13E",
    "vsmmEpsAttachFail15E",
    "vsmmEpsAttachFail14E",
    "vsmmUnsuccAttachCC2728E",
    "vsmmUnsuccAttachCC3233E",
    "vsmmAttAttachE",
    "vsmmUnsuccServiceReqE",
    "vsmmAttServiceRequestE",
    "vsmmAttPsPagingE",
    "vsmmSuccPsPagingE",
    "vsmmNbrHomeSubESum",
    "vsmmNbrVisitingForeignESum",
    "vsmmNbrVisitingNatSubESum",
    "vsmmNbrActAttachedSubESum"
})
public class Row {

    @XmlElement(name = "Date", required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "VS_MM_UnsuccAttach_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmUnsuccAttachE;
    @XmlElement(name = "VS_MM_EpsAttachFail_7_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmEpsAttachFail7E;
    @XmlElement(name = "VS_MM_EpsAttachFail_8_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmEpsAttachFail8E;
    @XmlElement(name = "VS_MM_EpsAttachFail_11_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmEpsAttachFail11E;
    @XmlElement(name = "VS_MM_EpsAttachFail_12_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmEpsAttachFail12E;
    @XmlElement(name = "VS_MM_EpsAttachFail_13_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmEpsAttachFail13E;
    @XmlElement(name = "VS_MM_EpsAttachFail_15_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmEpsAttachFail15E;
    @XmlElement(name = "VS_MM_EpsAttachFail_14_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmEpsAttachFail14E;
    @XmlElement(name = "VS_MM_UnsuccAttachCC27_28_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmUnsuccAttachCC2728E;
    @XmlElement(name = "VS_MM_UnsuccAttachCC32_33_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmUnsuccAttachCC3233E;
    @XmlElement(name = "VS_MM_AttAttach_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmAttAttachE;
    @XmlElement(name = "VS_MM_UnsuccServiceReq_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmUnsuccServiceReqE;
    @XmlElement(name = "VS_MM_AttServiceRequest_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmAttServiceRequestE;
    @XmlElement(name = "VS_MM_AttPsPaging_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmAttPsPagingE;
    @XmlElement(name = "VS_MM_SuccPsPaging_E", required = true, type = Double.class, nillable = true)
    protected Double vsmmSuccPsPagingE;
    @XmlElement(name = "VS_MM_NbrHomeSub_E__sum_", required = true, type = Double.class, nillable = true)
    protected Double vsmmNbrHomeSubESum;
    @XmlElement(name = "VS_MM_NbrVisitingForeign_E__sum_", required = true, type = Double.class, nillable = true)
    protected Double vsmmNbrVisitingForeignESum;
    @XmlElement(name = "VS_MM_NbrVisitingNatSub_E__sum_", required = true, type = Double.class, nillable = true)
    protected Double vsmmNbrVisitingNatSubESum;
    @XmlElement(name = "VS_MM_NbrActAttachedSub_E__sum_", required = true, type = Double.class, nillable = true)
    protected Double vsmmNbrActAttachedSubESum;

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the vsmmUnsuccAttachE property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMUnsuccAttachE() {
        return vsmmUnsuccAttachE;
    }

    /**
     * Sets the value of the vsmmUnsuccAttachE property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMUnsuccAttachE(Double value) {
        this.vsmmUnsuccAttachE = value;
    }

    /**
     * Gets the value of the vsmmEpsAttachFail7E property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMEpsAttachFail7E() {
        return vsmmEpsAttachFail7E;
    }

    /**
     * Sets the value of the vsmmEpsAttachFail7E property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMEpsAttachFail7E(Double value) {
        this.vsmmEpsAttachFail7E = value;
    }

    /**
     * Gets the value of the vsmmEpsAttachFail8E property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMEpsAttachFail8E() {
        return vsmmEpsAttachFail8E;
    }

    /**
     * Sets the value of the vsmmEpsAttachFail8E property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMEpsAttachFail8E(Double value) {
        this.vsmmEpsAttachFail8E = value;
    }

    /**
     * Gets the value of the vsmmEpsAttachFail11E property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMEpsAttachFail11E() {
        return vsmmEpsAttachFail11E;
    }

    /**
     * Sets the value of the vsmmEpsAttachFail11E property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMEpsAttachFail11E(Double value) {
        this.vsmmEpsAttachFail11E = value;
    }

    /**
     * Gets the value of the vsmmEpsAttachFail12E property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMEpsAttachFail12E() {
        return vsmmEpsAttachFail12E;
    }

    /**
     * Sets the value of the vsmmEpsAttachFail12E property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMEpsAttachFail12E(Double value) {
        this.vsmmEpsAttachFail12E = value;
    }

    /**
     * Gets the value of the vsmmEpsAttachFail13E property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMEpsAttachFail13E() {
        return vsmmEpsAttachFail13E;
    }

    /**
     * Sets the value of the vsmmEpsAttachFail13E property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMEpsAttachFail13E(Double value) {
        this.vsmmEpsAttachFail13E = value;
    }

    /**
     * Gets the value of the vsmmEpsAttachFail15E property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMEpsAttachFail15E() {
        return vsmmEpsAttachFail15E;
    }

    /**
     * Sets the value of the vsmmEpsAttachFail15E property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMEpsAttachFail15E(Double value) {
        this.vsmmEpsAttachFail15E = value;
    }

    /**
     * Gets the value of the vsmmEpsAttachFail14E property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMEpsAttachFail14E() {
        return vsmmEpsAttachFail14E;
    }

    /**
     * Sets the value of the vsmmEpsAttachFail14E property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMEpsAttachFail14E(Double value) {
        this.vsmmEpsAttachFail14E = value;
    }

    /**
     * Gets the value of the vsmmUnsuccAttachCC2728E property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMUnsuccAttachCC2728E() {
        return vsmmUnsuccAttachCC2728E;
    }

    /**
     * Sets the value of the vsmmUnsuccAttachCC2728E property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMUnsuccAttachCC2728E(Double value) {
        this.vsmmUnsuccAttachCC2728E = value;
    }

    /**
     * Gets the value of the vsmmUnsuccAttachCC3233E property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMUnsuccAttachCC3233E() {
        return vsmmUnsuccAttachCC3233E;
    }

    /**
     * Sets the value of the vsmmUnsuccAttachCC3233E property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMUnsuccAttachCC3233E(Double value) {
        this.vsmmUnsuccAttachCC3233E = value;
    }

    /**
     * Gets the value of the vsmmAttAttachE property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMAttAttachE() {
        return vsmmAttAttachE;
    }

    /**
     * Sets the value of the vsmmAttAttachE property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMAttAttachE(Double value) {
        this.vsmmAttAttachE = value;
    }

    /**
     * Gets the value of the vsmmUnsuccServiceReqE property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMUnsuccServiceReqE() {
        return vsmmUnsuccServiceReqE;
    }

    /**
     * Sets the value of the vsmmUnsuccServiceReqE property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMUnsuccServiceReqE(Double value) {
        this.vsmmUnsuccServiceReqE = value;
    }

    /**
     * Gets the value of the vsmmAttServiceRequestE property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMAttServiceRequestE() {
        return vsmmAttServiceRequestE;
    }

    /**
     * Sets the value of the vsmmAttServiceRequestE property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMAttServiceRequestE(Double value) {
        this.vsmmAttServiceRequestE = value;
    }

    /**
     * Gets the value of the vsmmAttPsPagingE property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMAttPsPagingE() {
        return vsmmAttPsPagingE;
    }

    /**
     * Sets the value of the vsmmAttPsPagingE property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMAttPsPagingE(Double value) {
        this.vsmmAttPsPagingE = value;
    }

    /**
     * Gets the value of the vsmmSuccPsPagingE property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMSuccPsPagingE() {
        return vsmmSuccPsPagingE;
    }

    /**
     * Sets the value of the vsmmSuccPsPagingE property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMSuccPsPagingE(Double value) {
        this.vsmmSuccPsPagingE = value;
    }

    /**
     * Gets the value of the vsmmNbrHomeSubESum property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMNbrHomeSubESum() {
        return vsmmNbrHomeSubESum;
    }

    /**
     * Sets the value of the vsmmNbrHomeSubESum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMNbrHomeSubESum(Double value) {
        this.vsmmNbrHomeSubESum = value;
    }

    /**
     * Gets the value of the vsmmNbrVisitingForeignESum property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMNbrVisitingForeignESum() {
        return vsmmNbrVisitingForeignESum;
    }

    /**
     * Sets the value of the vsmmNbrVisitingForeignESum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMNbrVisitingForeignESum(Double value) {
        this.vsmmNbrVisitingForeignESum = value;
    }

    /**
     * Gets the value of the vsmmNbrVisitingNatSubESum property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMNbrVisitingNatSubESum() {
        return vsmmNbrVisitingNatSubESum;
    }

    /**
     * Sets the value of the vsmmNbrVisitingNatSubESum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMNbrVisitingNatSubESum(Double value) {
        this.vsmmNbrVisitingNatSubESum = value;
    }

    /**
     * Gets the value of the vsmmNbrActAttachedSubESum property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getVSMMNbrActAttachedSubESum() {
        return vsmmNbrActAttachedSubESum;
    }

    /**
     * Sets the value of the vsmmNbrActAttachedSubESum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setVSMMNbrActAttachedSubESum(Double value) {
        this.vsmmNbrActAttachedSubESum = value;
    }

}
