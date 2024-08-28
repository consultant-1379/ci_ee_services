package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NavigateToReportPart complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="NavigateToReportPart">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}Navigate">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="ReportPartReference" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NavigateToReportPart")
public class NavigateToReportPart
        extends Navigate {

    @XmlAttribute(name = "ReportPartReference", required = true)
    protected String reportPartReference;

    /**
     * Gets the value of the reportPartReference property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getReportPartReference() {
        return reportPartReference;
    }

    /**
     * Sets the value of the reportPartReference property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReportPartReference(String value) {
        this.reportPartReference = value;
    }

}
