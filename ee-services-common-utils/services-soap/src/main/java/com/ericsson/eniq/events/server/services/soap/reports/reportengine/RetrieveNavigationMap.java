package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RetrieveNavigationMap complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="RetrieveNavigationMap">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Depth" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="Path" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RetrieveNavigationMap")
public class RetrieveNavigationMap {

    @XmlAttribute(name = "Depth", required = true)
    protected int depth;
    @XmlAttribute(name = "Path", required = true)
    protected String path;

    /**
     * Gets the value of the depth property.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the value of the depth property.
     */
    public void setDepth(int value) {
        this.depth = value;
    }

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
