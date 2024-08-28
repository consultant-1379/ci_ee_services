package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EmbeddedAction complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="EmbeddedAction">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}Action">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="ActionString" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmbeddedAction")
public class EmbeddedAction
        extends Action {

    @XmlAttribute(name = "ActionString", required = true)
    protected String actionString;

    /**
     * Gets the value of the actionString property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getActionString() {
        return actionString;
    }

    /**
     * Sets the value of the actionString property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setActionString(String value) {
        this.actionString = value;
    }

}
