package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for FillDataSourceParameterValues complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="FillDataSourceParameterValues">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}Action">
 *       &lt;sequence>
 *         &lt;element name="DataSourceParameterValues" type="{http://datasourceparameter.businessobjects.com/2007/06/01}DataSourceParameterValue" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FillDataSourceParameterValues", namespace = "http://reportengine.dsws.businessobjects.com/2007/06/01", propOrder = {
        "dataSourceParameterValues"
})
public class FillDataSourceParameterValues
        extends Action {

    @XmlElement(name = "DataSourceParameterValues", nillable = true)
    protected List<DataSourceParameterValue> dataSourceParameterValues;

    /**
     * Gets the value of the dataSourceParameterValues property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSourceParameterValues property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSourceParameterValues().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSourceParameterValue }
     */
    public List<DataSourceParameterValue> getDataSourceParameterValues() {
        if (dataSourceParameterValues == null) {
            dataSourceParameterValues = new ArrayList<DataSourceParameterValue>();
        }
        return this.dataSourceParameterValues;
    }

}
