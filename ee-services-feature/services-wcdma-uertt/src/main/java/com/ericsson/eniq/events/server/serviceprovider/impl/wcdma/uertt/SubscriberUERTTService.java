/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.uertt;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.common.TechPackList;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericService;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.log.util.LoggerOutputStream;
/**
 * @author xmonsou
 * @since 2013
 */
@Stateless
@Local(Service.class)
public class SubscriberUERTTService extends GenericService {

	/*
	 * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#
	 * getData() : 
	 * This Method is supposed to return the eventPojo object to UI from JMS queue.
	 * JMS Consumer integration will be introduced here,currently we are testing with
	 * single eventPojo object.
	 */
	@Override
    public String getData(final MultivaluedMap<String, String> parameters){	
		String imsi = "46000000001234567";	
		String pojoData="";			
		if(parameters!=null && parameters.get("imsi")!=null && parameters.get("imsi").toString().substring(1,parameters.get("imsi").toString().length()-1).equals(imsi)){
	    pojoData = "{\"eventId\": \"My_customized event\",\"protocol_type\": \"RRC\",\"direction\": \"To\",\"timestamp\": \"2008-04-19 5:09.912\"}";
	    String data= "{\"success\":\"true\",\"errorDescription\":\"\",\"dataTimeFrom\":\"1371476160000\",\"dataTimeTo\":\"1371477960000\",\"timeZone\":\"+0530\",\"data\":"+pojoData+"}";    	 
	    return data;
		}	
		pojoData = "No Data To Display";
	    String data= "{\"success\":\"true\",\"errorDescription\":\"\",\"dataTimeFrom\":\"1371476160000\",\"dataTimeTo\":\"1371477960000\",\"timeZone\":\"+0530\",\"data\":"+pojoData+"}";    	 
	    return data;				        	    
    }
	@Override
	public String getTemplatePath() {
		//TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean areRawTablesRequiredForAggregationQueries() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public AggregationTableInfo getAggregationView(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> getApplicableTechPacks(
			MultivaluedMap<String, String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getDrillDownTypeForService(MultivaluedMap<String, String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getMaxAllowableSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<String> getMeasurementTypes() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> getRawTableKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> getRequiredParametersForQuery() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Object> getServiceSpecificDataServiceParameters(
			MultivaluedMap<String, String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, QueryParameter> getServiceSpecificQueryParameters(
			MultivaluedMap<String, String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<String, Object> getServiceSpecificTemplateParameters(
			MultivaluedMap<String, String> arg0, FormattedDateTimeRange arg1,
			TechPackList arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public MultivaluedMap<String, String> getStaticParameters() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTableSuffixKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean requiredToCheckValidParameterValue(
			MultivaluedMap<String, String> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
