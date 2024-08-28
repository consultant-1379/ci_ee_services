package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Category complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Category">
 *   &lt;complexContent>
 *     &lt;extension base="{http://bicatalog.dsws.businessobjects.com/2007/06/01}BICatalogObject">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Alias" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;anyAttribute namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Category")
public class Category
        extends BICatalogObject {

    @XmlAttribute(name = "Alias", required = true)
    protected boolean alias;

    /**
     * Gets the value of the alias property.
     */
    public boolean isAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     */
    public void setAlias(boolean value) {
        this.alias = value;
    }

}
