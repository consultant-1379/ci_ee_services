package com.ericsson.eniq.events.server.services.soap.reports.session;

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
 *         &lt;element name="session" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dictionary" type="{http://session.dsws.businessobjects.com/2007/06/01}DictionaryType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "session",
        "dictionary"
})
@XmlRootElement(name = "getDictionary")
public class GetDictionary {

    @XmlElement(required = true)
    protected String session;
    @XmlElement(required = true)
    protected DictionaryType dictionary;

    /**
     * Gets the value of the session property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSession(String value) {
        this.session = value;
    }

    /**
     * Gets the value of the dictionary property.
     *
     * @return possible object is
     *         {@link DictionaryType }
     */
    public DictionaryType getDictionary() {
        return dictionary;
    }

    /**
     * Sets the value of the dictionary property.
     *
     * @param value allowed object is
     *              {@link DictionaryType }
     */
    public void setDictionary(DictionaryType value) {
        this.dictionary = value;
    }

}
