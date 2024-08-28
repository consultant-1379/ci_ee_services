package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrillOption complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DrillOption">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}CallbackOption">
 *       &lt;attribute name="FromHolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ToHolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DrillActionHolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FilterHolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BlockHolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="HierarchyHolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AmbiguousDrillCallbackScript" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrillOption")
public class DrillOption
        extends CallbackOption {

    @XmlAttribute(name = "FromHolder")
    protected String fromHolder;
    @XmlAttribute(name = "ToHolder")
    protected String toHolder;
    @XmlAttribute(name = "DrillActionHolder")
    protected String drillActionHolder;
    @XmlAttribute(name = "FilterHolder")
    protected String filterHolder;
    @XmlAttribute(name = "BlockHolder")
    protected String blockHolder;
    @XmlAttribute(name = "HierarchyHolder")
    protected String hierarchyHolder;
    @XmlAttribute(name = "AmbiguousDrillCallbackScript")
    protected String ambiguousDrillCallbackScript;

    /**
     * Gets the value of the fromHolder property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getFromHolder() {
        return fromHolder;
    }

    /**
     * Sets the value of the fromHolder property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFromHolder(String value) {
        this.fromHolder = value;
    }

    /**
     * Gets the value of the toHolder property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getToHolder() {
        return toHolder;
    }

    /**
     * Sets the value of the toHolder property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setToHolder(String value) {
        this.toHolder = value;
    }

    /**
     * Gets the value of the drillActionHolder property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDrillActionHolder() {
        return drillActionHolder;
    }

    /**
     * Sets the value of the drillActionHolder property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDrillActionHolder(String value) {
        this.drillActionHolder = value;
    }

    /**
     * Gets the value of the filterHolder property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getFilterHolder() {
        return filterHolder;
    }

    /**
     * Sets the value of the filterHolder property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFilterHolder(String value) {
        this.filterHolder = value;
    }

    /**
     * Gets the value of the blockHolder property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getBlockHolder() {
        return blockHolder;
    }

    /**
     * Sets the value of the blockHolder property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setBlockHolder(String value) {
        this.blockHolder = value;
    }

    /**
     * Gets the value of the hierarchyHolder property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getHierarchyHolder() {
        return hierarchyHolder;
    }

    /**
     * Sets the value of the hierarchyHolder property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHierarchyHolder(String value) {
        this.hierarchyHolder = value;
    }

    /**
     * Gets the value of the ambiguousDrillCallbackScript property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getAmbiguousDrillCallbackScript() {
        return ambiguousDrillCallbackScript;
    }

    /**
     * Sets the value of the ambiguousDrillCallbackScript property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAmbiguousDrillCallbackScript(String value) {
        this.ambiguousDrillCallbackScript = value;
    }

}
