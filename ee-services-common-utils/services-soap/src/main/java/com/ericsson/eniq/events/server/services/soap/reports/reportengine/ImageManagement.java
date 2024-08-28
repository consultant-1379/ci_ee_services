package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImageManagement complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ImageManagement">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}CallbackOption">
 *       &lt;attribute name="ImageManagementHolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImageManagement")
public class ImageManagement
        extends CallbackOption {

    @XmlAttribute(name = "ImageManagementHolder")
    protected String imageManagementHolder;

    /**
     * Gets the value of the imageManagementHolder property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getImageManagementHolder() {
        return imageManagementHolder;
    }

    /**
     * Sets the value of the imageManagementHolder property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setImageManagementHolder(String value) {
        this.imageManagementHolder = value;
    }

}
