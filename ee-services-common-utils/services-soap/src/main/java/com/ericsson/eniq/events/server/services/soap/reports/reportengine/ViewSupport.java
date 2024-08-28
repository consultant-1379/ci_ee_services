package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import org.w3c.dom.Element;

import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Java class for ViewSupport complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ViewSupport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ViewMode" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}ViewModeType" />
 *       &lt;attribute name="OutputFormat" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}OutputFormatType" />
 *       &lt;attribute name="ViewType" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}ViewType" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewSupport", propOrder = {
        "any"
})
public class ViewSupport {

    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "ViewMode", required = true)
    protected ViewModeType viewMode;
    @XmlAttribute(name = "OutputFormat", required = true)
    protected OutputFormatType outputFormat;
    @XmlAttribute(name = "ViewType", required = true)
    protected ViewType viewType;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the any property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link Element }
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * Gets the value of the viewMode property.
     *
     * @return possible object is
     *         {@link ViewModeType }
     */
    public ViewModeType getViewMode() {
        return viewMode;
    }

    /**
     * Sets the value of the viewMode property.
     *
     * @param value allowed object is
     *              {@link ViewModeType }
     */
    public void setViewMode(ViewModeType value) {
        this.viewMode = value;
    }

    /**
     * Gets the value of the outputFormat property.
     *
     * @return possible object is
     *         {@link OutputFormatType }
     */
    public OutputFormatType getOutputFormat() {
        return outputFormat;
    }

    /**
     * Sets the value of the outputFormat property.
     *
     * @param value allowed object is
     *              {@link OutputFormatType }
     */
    public void setOutputFormat(OutputFormatType value) {
        this.outputFormat = value;
    }

    /**
     * Gets the value of the viewType property.
     *
     * @return possible object is
     *         {@link ViewType }
     */
    public ViewType getViewType() {
        return viewType;
    }

    /**
     * Sets the value of the viewType property.
     *
     * @param value allowed object is
     *              {@link ViewType }
     */
    public void setViewType(ViewType value) {
        this.viewType = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * <p/>
     * <p/>
     * the map is keyed by the name of the attribute and
     * the value is the string value of the attribute.
     * <p/>
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     *
     * @return always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
