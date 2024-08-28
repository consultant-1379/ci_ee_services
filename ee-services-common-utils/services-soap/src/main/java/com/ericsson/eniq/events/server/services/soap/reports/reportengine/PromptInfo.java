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
 * <p>Java class for PromptInfo complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="PromptInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DefaultValues" type="{http://reportengine.dsws.businessobjects.com/2005}PromptValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PreviousValues" type="{http://reportengine.dsws.businessobjects.com/2005}PromptValue" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PromptValueFormat" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="MinValue" type="{http://reportengine.dsws.businessobjects.com/2005}PromptValue" minOccurs="0"/>
 *         &lt;element name="MaxValue" type="{http://reportengine.dsws.businessobjects.com/2005}PromptValue" minOccurs="0"/>
 *         &lt;element name="LOV" type="{http://reportengine.dsws.businessobjects.com/2005}LOV" minOccurs="0"/>
 *         &lt;element name="DataProviderIDs" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PromptToBeFilled" type="{http://reportengine.dsws.businessobjects.com/2005}PromptInfo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}LovDelegated use="required""/>
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}LocalizedQuestion use="required""/>
 *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Constrained" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="RequireAnswers" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="HasLOV" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AllowMultiValues" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AllowDiscreteValue" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AllowRangeValue" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="PromptType" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}PromptType" />
 *       &lt;attribute name="AllowNonLeafValues" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute ref="{http://reportengine.dsws.businessobjects.com/2007/06/01}Optional"/>
 *       &lt;attribute name="LovDisplayType" use="required" type="{http://reportengine.dsws.businessobjects.com/2005}LovDisplayType" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PromptInfo", propOrder = {
        "description",
        "defaultValues",
        "previousValues",
        "promptValueFormat",
        "minValue",
        "maxValue",
        "lov",
        "dataProviderIDs",
        "promptToBeFilled",
        "any"
})
public class PromptInfo {

    @XmlElementRef(name = "Description", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<String> description;
    @XmlElement(name = "DefaultValues", nillable = true)
    protected List<PromptValue> defaultValues;
    @XmlElement(name = "PreviousValues", nillable = true)
    protected List<PromptValue> previousValues;
    @XmlElement(name = "PromptValueFormat", nillable = true)
    protected List<String> promptValueFormat;
    @XmlElementRef(name = "MinValue", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<PromptValue> minValue;
    @XmlElementRef(name = "MaxValue", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<PromptValue> maxValue;
    @XmlElementRef(name = "LOV", namespace = "http://reportengine.dsws.businessobjects.com/2005", type = JAXBElement.class)
    protected JAXBElement<LOV> lov;
    @XmlElement(name = "DataProviderIDs", nillable = true)
    protected List<String> dataProviderIDs;
    @XmlElement(name = "PromptToBeFilled", nillable = true)
    protected List<PromptInfo> promptToBeFilled;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "LovDelegated", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean lovDelegated;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "LocalizedQuestion", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", required = true)
    protected String localizedQuestion;
    @XmlAttribute(name = "ID", required = true)
    protected String id;
    @XmlAttribute(name = "Constrained", required = true)
    protected boolean constrained;
    @XmlAttribute(name = "RequireAnswers", required = true)
    protected boolean requireAnswers;
    @XmlAttribute(name = "HasLOV", required = true)
    protected boolean hasLOV;
    @XmlAttribute(name = "AllowMultiValues", required = true)
    protected boolean allowMultiValues;
    @XmlAttribute(name = "AllowDiscreteValue", required = true)
    protected boolean allowDiscreteValue;
    @XmlAttribute(name = "AllowRangeValue", required = true)
    protected boolean allowRangeValue;
    @XmlAttribute(name = "PromptType", required = true)
    protected PromptType promptType;
    @XmlAttribute(name = "AllowNonLeafValues", required = true)
    protected boolean allowNonLeafValues;
    @XmlAttribute(name = "Optional", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01")
    protected Boolean optional;
    @XmlAttribute(name = "LovDisplayType", required = true)
    protected LovDisplayType lovDisplayType;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

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
     * Gets the value of the defaultValues property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the defaultValues property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDefaultValues().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link PromptValue }
     */
    public List<PromptValue> getDefaultValues() {
        if (defaultValues == null) {
            defaultValues = new ArrayList<PromptValue>();
        }
        return this.defaultValues;
    }

    /**
     * Gets the value of the previousValues property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the previousValues property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreviousValues().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link PromptValue }
     */
    public List<PromptValue> getPreviousValues() {
        if (previousValues == null) {
            previousValues = new ArrayList<PromptValue>();
        }
        return this.previousValues;
    }

    /**
     * Gets the value of the promptValueFormat property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the promptValueFormat property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPromptValueFormat().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getPromptValueFormat() {
        if (promptValueFormat == null) {
            promptValueFormat = new ArrayList<String>();
        }
        return this.promptValueFormat;
    }

    /**
     * Gets the value of the minValue property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link PromptValue }{@code >}
     */
    public JAXBElement<PromptValue> getMinValue() {
        return minValue;
    }

    /**
     * Sets the value of the minValue property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link PromptValue }{@code >}
     */
    public void setMinValue(JAXBElement<PromptValue> value) {
        this.minValue = ((JAXBElement<PromptValue>) value);
    }

    /**
     * Gets the value of the maxValue property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link PromptValue }{@code >}
     */
    public JAXBElement<PromptValue> getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the value of the maxValue property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link PromptValue }{@code >}
     */
    public void setMaxValue(JAXBElement<PromptValue> value) {
        this.maxValue = ((JAXBElement<PromptValue>) value);
    }

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
     * Gets the value of the dataProviderIDs property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataProviderIDs property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataProviderIDs().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getDataProviderIDs() {
        if (dataProviderIDs == null) {
            dataProviderIDs = new ArrayList<String>();
        }
        return this.dataProviderIDs;
    }

    /**
     * Gets the value of the promptToBeFilled property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the promptToBeFilled property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPromptToBeFilled().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link PromptInfo }
     */
    public List<PromptInfo> getPromptToBeFilled() {
        if (promptToBeFilled == null) {
            promptToBeFilled = new ArrayList<PromptInfo>();
        }
        return this.promptToBeFilled;
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
     * Gets the value of the lovDelegated property.
     */
    public boolean isLovDelegated() {
        return lovDelegated;
    }

    /**
     * Sets the value of the lovDelegated property.
     */
    public void setLovDelegated(boolean value) {
        this.lovDelegated = value;
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
     * Gets the value of the localizedQuestion property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLocalizedQuestion() {
        return localizedQuestion;
    }

    /**
     * Sets the value of the localizedQuestion property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLocalizedQuestion(String value) {
        this.localizedQuestion = value;
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
     * Gets the value of the constrained property.
     */
    public boolean isConstrained() {
        return constrained;
    }

    /**
     * Sets the value of the constrained property.
     */
    public void setConstrained(boolean value) {
        this.constrained = value;
    }

    /**
     * Gets the value of the requireAnswers property.
     */
    public boolean isRequireAnswers() {
        return requireAnswers;
    }

    /**
     * Sets the value of the requireAnswers property.
     */
    public void setRequireAnswers(boolean value) {
        this.requireAnswers = value;
    }

    /**
     * Gets the value of the hasLOV property.
     */
    public boolean isHasLOV() {
        return hasLOV;
    }

    /**
     * Sets the value of the hasLOV property.
     */
    public void setHasLOV(boolean value) {
        this.hasLOV = value;
    }

    /**
     * Gets the value of the allowMultiValues property.
     */
    public boolean isAllowMultiValues() {
        return allowMultiValues;
    }

    /**
     * Sets the value of the allowMultiValues property.
     */
    public void setAllowMultiValues(boolean value) {
        this.allowMultiValues = value;
    }

    /**
     * Gets the value of the allowDiscreteValue property.
     */
    public boolean isAllowDiscreteValue() {
        return allowDiscreteValue;
    }

    /**
     * Sets the value of the allowDiscreteValue property.
     */
    public void setAllowDiscreteValue(boolean value) {
        this.allowDiscreteValue = value;
    }

    /**
     * Gets the value of the allowRangeValue property.
     */
    public boolean isAllowRangeValue() {
        return allowRangeValue;
    }

    /**
     * Sets the value of the allowRangeValue property.
     */
    public void setAllowRangeValue(boolean value) {
        this.allowRangeValue = value;
    }

    /**
     * Gets the value of the promptType property.
     *
     * @return possible object is
     *         {@link PromptType }
     */
    public PromptType getPromptType() {
        return promptType;
    }

    /**
     * Sets the value of the promptType property.
     *
     * @param value allowed object is
     *              {@link PromptType }
     */
    public void setPromptType(PromptType value) {
        this.promptType = value;
    }

    /**
     * Gets the value of the allowNonLeafValues property.
     */
    public boolean isAllowNonLeafValues() {
        return allowNonLeafValues;
    }

    /**
     * Sets the value of the allowNonLeafValues property.
     */
    public void setAllowNonLeafValues(boolean value) {
        this.allowNonLeafValues = value;
    }

    /**
     * Gets the value of the optional property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public Boolean isOptional() {
        return optional;
    }

    /**
     * Sets the value of the optional property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setOptional(Boolean value) {
        this.optional = value;
    }

    /**
     * Gets the value of the lovDisplayType property.
     *
     * @return possible object is
     *         {@link LovDisplayType }
     */
    public LovDisplayType getLovDisplayType() {
        return lovDisplayType;
    }

    /**
     * Sets the value of the lovDisplayType property.
     *
     * @param value allowed object is
     *              {@link LovDisplayType }
     */
    public void setLovDisplayType(LovDisplayType value) {
        this.lovDisplayType = value;
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
