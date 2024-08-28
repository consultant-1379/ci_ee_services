package com.ericsson.eniq.events.server.services.soap.ee_kpi_node_selection_query;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="table" type="{EE_KPI_Node_Selection_Query}Table"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creatorname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creationdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="creationdateformated" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="universe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="queryruntime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fetchedrows" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "table",
        "message",
        "creatorname",
        "creationdate",
        "creationdateformated",
        "description",
        "universe",
        "queryruntime",
        "fetchedrows"
})
@XmlRootElement(name = "runQueryAsAServiceExResponse")
public class RunQueryAsAServiceExResponse {

    @XmlElement(required = true)
    protected Table table;
    @XmlElement(required = true)
    protected String message;
    @XmlElement(required = true)
    protected String creatorname;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationdate;
    @XmlElement(required = true)
    protected String creationdateformated;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String universe;
    protected int queryruntime;
    protected int fetchedrows;

    /**
     * Gets the value of the table property.
     *
     * @return possible object is
     *         {@link Table }
     */
    public Table getTable() {
        return table;
    }

    /**
     * Sets the value of the table property.
     *
     * @param value allowed object is
     *              {@link Table }
     */
    public void setTable(Table value) {
        this.table = value;
    }

    /**
     * Gets the value of the message property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the creatorname property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getCreatorname() {
        return creatorname;
    }

    /**
     * Sets the value of the creatorname property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCreatorname(String value) {
        this.creatorname = value;
    }

    /**
     * Gets the value of the creationdate property.
     *
     * @return possible object is
     *         {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getCreationdate() {
        return creationdate;
    }

    /**
     * Sets the value of the creationdate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setCreationdate(XMLGregorianCalendar value) {
        this.creationdate = value;
    }

    /**
     * Gets the value of the creationdateformated property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getCreationdateformated() {
        return creationdateformated;
    }

    /**
     * Sets the value of the creationdateformated property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCreationdateformated(String value) {
        this.creationdateformated = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the universe property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUniverse() {
        return universe;
    }

    /**
     * Sets the value of the universe property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUniverse(String value) {
        this.universe = value;
    }

    /**
     * Gets the value of the queryruntime property.
     */
    public int getQueryruntime() {
        return queryruntime;
    }

    /**
     * Sets the value of the queryruntime property.
     */
    public void setQueryruntime(int value) {
        this.queryruntime = value;
    }

    /**
     * Gets the value of the fetchedrows property.
     */
    public int getFetchedrows() {
        return fetchedrows;
    }

    /**
     * Sets the value of the fetchedrows property.
     */
    public void setFetchedrows(int value) {
        this.fetchedrows = value;
    }

}
