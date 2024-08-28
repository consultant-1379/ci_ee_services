/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2011
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.*;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceException;

import org.codehaus.jackson.map.ObjectMapper;

import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client.BICatalogObjectSoapClient;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client.BIObjectTreeDataType;
import com.ericsson.eniq.events.server.services.soap.reports.bicatalog.DSWSException_Exception;

/**
 * Describe DashboardReportsListService
 * This class encapsulated the Reports List service , this service needs the  BICatalogObjectSoapClient object
 * this class will do some error handling and convert the BIObjectTreeDataType datatype to corresponding JSON
 * String
 *
 * @author ezhelao
 */
@Stateless
@Local(Service.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class DashboardReportsListService implements Service {

    @EJB
    BICatalogObjectSoapClient biCatalogObjectSoapClient;

    static final String ERROR_JSON_MSG_TEMPLATE = "{\"success\":\"false\" , \"bisServiceSuccess\" :\"false\" ,\"errorDescription\" : \"json serialisation error\" }";

    static final String WRONG_URL_FORMAT_MSG = "Wrong URL format.";

    static final String INVALID_CREDENTIALS_MSG = "Invalid credentials.";

    static final String BIS_ERROR_MSG = "Business Intelligence Service is not available from the provided URL.";

    static final String BICATALOG_ERROR_MSG = "Failed to get reports.";

    static final String UNKNOWN_ERROR_MSG = "Unknown error.";

    static final String CAN_NOT_GET_DOC_LIST_ERROR_MSG="\"Can not get document list, Reason: ";

    //jackson objectmapper to use to serialise the json.
    private final ObjectMapper mapper = new ObjectMapper();

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.Service#getDataAsCSV(javax.ws.rs.core.MultivaluedMap, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Response getDataAsCSV(final MultivaluedMap<String, String> serviceProviderParameters,
                                 final HttpServletResponse response) {
        return null;
    }

    /*
    * connect to the BIS Soap server using the soap client and retrieve the reports list from the server as an
    * object . The object will be converted to JSON string using the Jackson objectmapper.
    *
    * @return the JSON string of the reports
    */
    @Override
    public String getData(final MultivaluedMap<String, String> serviceProviderParameters) {
        BIObjectTreeDataType biObjectTreeDataType = new BIObjectTreeDataType();
        final String successValue = "true";
        String bisSuccessValue = "false";
        String errorDescription = "";
        String resultString = ERROR_JSON_MSG_TEMPLATE;
        try {
            biObjectTreeDataType = biCatalogObjectSoapClient.getDocumentFolderList();
            bisSuccessValue = "true";
        } catch (final MalformedURLException ex) {
            errorDescription = WRONG_URL_FORMAT_MSG;
            ServicesLogger.warn(getClass().getName(), "getData",CAN_NOT_GET_DOC_LIST_ERROR_MSG + errorDescription);
            ServicesLogger.detailed(getClass().getName(), "getData", ex);
        } catch (final com.ericsson.eniq.events.server.services.soap.reports.session.DSWSException_Exception ex) {
            errorDescription = INVALID_CREDENTIALS_MSG;
            ServicesLogger.warn(getClass().getName(), "getData", CAN_NOT_GET_DOC_LIST_ERROR_MSG + errorDescription);
            ServicesLogger.detailed(getClass().getName(), "getData", ex);
        } catch (final DSWSException_Exception ex) {
            errorDescription = BICATALOG_ERROR_MSG;
            ServicesLogger.warn(getClass().getName(), "getData",CAN_NOT_GET_DOC_LIST_ERROR_MSG+ errorDescription);
            ServicesLogger.detailed(getClass().getName(), "getData", ex);
        } catch (final WebServiceException ex) {
            errorDescription = BIS_ERROR_MSG;
            ServicesLogger.warn(getClass().getName(), "getData", CAN_NOT_GET_DOC_LIST_ERROR_MSG + errorDescription);
            ServicesLogger.detailed(getClass().getName(), "getData", ex);
        } catch (final UnknownHostException ex) {
            errorDescription = ex.getMessage();
            ServicesLogger.warn(getClass().getName(), "getData", CAN_NOT_GET_DOC_LIST_ERROR_MSG + errorDescription);
            ServicesLogger.detailed(getClass().getName(), "getData", ex);
        } catch (final Exception ex) {
            errorDescription = UNKNOWN_ERROR_MSG;
            ServicesLogger.warn(getClass().getName(), "getData", CAN_NOT_GET_DOC_LIST_ERROR_MSG + errorDescription);
            ServicesLogger.detailed(getClass().getName(), "getData", ex);
        } finally {
            try {
                final List<BIObjectTreeDataType> listBIObjectDta = new ArrayList<BIObjectTreeDataType>();
                listBIObjectDta.add(biObjectTreeDataType);
                final ReportServiceResult result = new ReportServiceResult(successValue, bisSuccessValue,
                        errorDescription, listBIObjectDta);
                resultString = mapper.writeValueAsString(result);
            } catch (final Exception e) {
                ServicesLogger.warn(getClass().getName(), "getData", e);
            }
        }
        return resultString;
    }
}