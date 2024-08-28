package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import org.w3c.dom.Element;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Java class for DrillDimension complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DrillDimension">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LOV" type="{http://reportengine.dsws.businessobjects.com/2005}LOV" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Filter" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="InScope" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Ambiguous" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrillDimension", propOrder = {
        "lov",
        "description",
        "any"
})
public class DrillDimension {

    @XmlElementRef(name = "LOV", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<LOV> lov;
    @XmlElementRef(name = "Description", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<String> description;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "ID", required = true)
    protected String id;
    @XmlAttribute(name = "Filter")
    protected String filter;
    @XmlAttribute(name = "InScope", required = true)
    protected boolean inScope;
    @XmlAttribute(name = "Ambiguous", required = true)
    protected boolean ambiguous;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the lov property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link LOV }{@code >}
     */
    public JAXBElement<LOV> getLOV() {
        return lov;
    }

    /**
     * Sets the value of the lov property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link LOV }{@code >}
     */
    public void setLOV(JAXBElement<LOV> value) {
        this.lov = ((JAXBElement<LOV>) value);
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setDescription(JAXBElement<String> value) {
        this.description = ((JAXBElement<String>) value);
    }

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
     * Gets the value of the name property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the filter property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFilter(String value) {
        this.filter = value;
    }

    /**
     * Gets the value of the inScope property.
     */
    public boolean isInScope() {
        return inScope;
    }

    /**
     * Sets the value of the inScope property.
     */
    public void setInScope(boolean value) {
        this.inScope = value;
    }

    /**
     * Gets the value of the ambiguous property.
     */
    public boolean isAmbiguous() {
        return ambiguous;
    }

    /**
     * Sets the value of the ambiguous property.
     */
    public void setAmbiguous(boolean value) {
        this.ambiguous = value;
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
