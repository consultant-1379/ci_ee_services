package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NavigateToPage complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="NavigateToPage">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}Navigate">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Page" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NavigateToPage")
public class NavigateToPage
        extends Navigate {

    @XmlAttribute(name = "Page", required = true)
    protected int page;

    /**
     * Gets the value of the page property.
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the value of the page property.
     */
    public void setPage(int value) {
        this.page = value;
    }

}
