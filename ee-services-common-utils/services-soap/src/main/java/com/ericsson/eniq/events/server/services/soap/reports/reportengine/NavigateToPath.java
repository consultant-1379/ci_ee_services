package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NavigateToPath complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="NavigateToPath">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}Navigate">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NavigateToPath")
public class NavigateToPath
        extends Navigate {

    @XmlAttribute(name = "Path", required = true)
    protected String path;

    /**
     * Gets the value of the path property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPath(String value) {
        this.path = value;
    }

}
