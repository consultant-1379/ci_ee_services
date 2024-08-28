package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EmbeddedCallbackOption complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="EmbeddedCallbackOption">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}CallbackOption">
 *       &lt;attribute name="ActionHolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="NameHolder" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmbeddedCallbackOption")
public class EmbeddedCallbackOption
        extends CallbackOption {

    @XmlAttribute(name = "ActionHolder")
    protected String actionHolder;
    @XmlAttribute(name = "NameHolder")
    protected String nameHolder;

    /**
     * Gets the value of the actionHolder property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getActionHolder() {
        return actionHolder;
    }

    /**
     * Sets the value of the actionHolder property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setActionHolder(String value) {
        this.actionHolder = value;
    }

    /**
     * Gets the value of the nameHolder property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getNameHolder() {
        return nameHolder;
    }

    /**
     * Sets the value of the nameHolder property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNameHolder(String value) {
        this.nameHolder = value;
    }

}
