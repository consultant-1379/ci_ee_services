package com.ericsson.eniq.events.server.services.soap.ee_kpi_node_selection_query;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="login" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Enter_value_s__for__RNC_Name_" type="{EE_KPI_Node_Selection_Query}LovValueIndex" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Enter_value_s__for__UCell_Name_" type="{EE_KPI_Node_Selection_Query}LovValueIndex" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Date_" type="{EE_KPI_Node_Selection_Query}LovValueIndex"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "login",
        "password",
        "enterValueSForRNCName",
        "enterValueSForUCellName",
        "date"
})
@XmlRootElement(name = "runQueryAsAServiceEx")
public class RunQueryAsAServiceEx {

    @XmlElement(required = true)
    protected String login;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(name = "Enter_value_s__for__RNC_Name_", nillable = true)
    protected List<LovValueIndex> enterValueSForRNCName;
    @XmlElement(name = "Enter_value_s__for__UCell_Name_", nillable = true)
    protected List<LovValueIndex> enterValueSForUCellName;
    @XmlElement(name = "Date_", required = true, nillable = true)
    protected LovValueIndex date;

    /**
     * Gets the value of the login property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the value of the login property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Gets the value of the password property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the enterValueSForRNCName property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enterValueSForRNCName property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnterValueSForRNCName().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link LovValueIndex }
     */
    public List<LovValueIndex> getEnterValueSForRNCName() {
        if (enterValueSForRNCName == null) {
            enterValueSForRNCName = new ArrayList<LovValueIndex>();
        }
        return this.enterValueSForRNCName;
    }

    /**
     * Gets the value of the enterValueSForUCellName property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enterValueSForUCellName property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnterValueSForUCellName().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link LovValueIndex }
     */
    public List<LovValueIndex> getEnterValueSForUCellName() {
        if (enterValueSForUCellName == null) {
            enterValueSForUCellName = new ArrayList<LovValueIndex>();
        }
        return this.enterValueSForUCellName;
    }

    /**
     * Gets the value of the date property.
     *
     * @return possible object is
     *         {@link LovValueIndex }
     */
    public LovValueIndex getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param value allowed object is
     *              {@link LovValueIndex }
     */
    public void setDate(LovValueIndex value) {
        this.date = value;
    }

}
