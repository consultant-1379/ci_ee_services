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
 *         &lt;element name="Image" type="{http://reportengine.dsws.businessobjects.com/2005}Image"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "image"
})
@XmlRootElement(name = "getImageResponse")
public class GetImageResponse {

    @XmlElement(name = "Image", required = true, nillable = true)
    protected Image image;

    /**
     * Gets the value of the image property.
     *
     * @return possible object is
     *         {@link Image }
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     *
     * @param value allowed object is
     *              {@link Image }
     */
    public void setImage(Image value) {
        this.image = value;
    }

}
