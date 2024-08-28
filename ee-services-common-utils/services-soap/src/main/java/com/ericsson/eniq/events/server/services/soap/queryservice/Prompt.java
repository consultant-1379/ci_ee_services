package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for Prompt complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Prompt">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}Operand">
 *       &lt;sequence>
 *         &lt;element name="Question" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DefaultValues" type="{http://query.businessobjects.com/2005}Values" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="KeepLastValues" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Constrained" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="HasLov" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="Order" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute ref="{http://queryservice.dsws.businessobjects.com/2007/06/01}Optional use="required""/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Prompt", propOrder = {
        "question",
        "defaultValues"
})
public class Prompt
        extends Operand {

    @XmlElement(name = "Question", required = true)
    protected String question;
    @XmlElement(name = "DefaultValues")
    protected Values defaultValues;
    @XmlAttribute(name = "KeepLastValues", required = true)
    protected boolean keepLastValues;
    @XmlAttribute(name = "Constrained", required = true)
    protected boolean constrained;
    @XmlAttribute(name = "HasLov", required = true)
    protected boolean hasLov;
    @XmlAttribute(name = "Order", required = true)
    protected int order;
    @XmlAttribute(name = "Optional", namespace = "http://queryservice.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean optional;

    /**
     * Gets the value of the question property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the value of the question property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setQuestion(String value) {
        this.question = value;
    }

    /**
     * Gets the value of the defaultValues property.
     *
     * @return possible object is
     *         {@link Values }
     */
    public Values getDefaultValues() {
        return defaultValues;
    }

    /**
     * Sets the value of the defaultValues property.
     *
     * @param value allowed object is
     *              {@link Values }
     */
    public void setDefaultValues(Values value) {
        this.defaultValues = value;
    }

    /**
     * Gets the value of the keepLastValues property.
     */
    public boolean isKeepLastValues() {
        return keepLastValues;
    }

    /**
     * Sets the value of the keepLastValues property.
     */
    public void setKeepLastValues(boolean value) {
        this.keepLastValues = value;
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
     * Gets the value of the hasLov property.
     */
    public boolean isHasLov() {
        return hasLov;
    }

    /**
     * Sets the value of the hasLov property.
     */
    public void setHasLov(boolean value) {
        this.hasLov = value;
    }

    /**
     * Gets the value of the order property.
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     */
    public void setOrder(int value) {
        this.order = value;
    }

    /**
     * Gets the value of the optional property.
     */
    public boolean isOptional() {
        return optional;
    }

    /**
     * Sets the value of the optional property.
     */
    public void setOptional(boolean value) {
        this.optional = value;
    }

}
