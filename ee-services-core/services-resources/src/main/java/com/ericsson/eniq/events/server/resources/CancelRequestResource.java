/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.resources;

import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.query.NamedParameterStatement;
import com.ericsson.eniq.events.server.query.RequestIdMappingService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.sql.SQLException;
import java.util.logging.Level;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.REQUEST_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.SINGLE_SPACE;

/**
 * Resource for Cancel Request
 *
 * @author echchik
 */

@Stateless
public class CancelRequestResource {

    /**
     * The HttpHeader info
     */
    @Context
    protected HttpHeaders httpHeader;

    /**
     * This is used to fetch the prepared statement corresponding to the
     * cancel request id  from the cancel request
     */
    @EJB
    protected RequestIdMappingService requestIdMappings;

    /**
     * This method is invoked when ever a cancel request is received by services
     * * @throws WebApplicationException the web application exception
     */
    @GET
    public String cancelRequest() throws WebApplicationException {
        final String requestId = httpHeader.getRequestHeaders().getFirst(REQUEST_ID);
        ServicesLogger.detailed(Level.FINE, getClass().getName(), "cancleRequest()",
                "Cancel request received for RequestID=" + requestId);
        if (!requestIdMappings.containsKey(requestId)) {
            requestIdMappings.addFailedCancelReqId(requestId);
            ServicesLogger.detailed(Level.FINE, getClass().getName(), "cancleRequest()",
                    "Cancel request failed for RequestID=" + requestId);
            //@Get method can't be void
            return SINGLE_SPACE;
        }
        handleCancelRequest(requestId);
        //@Get method can't be void
        return SINGLE_SPACE;
    }

    /**
     * This method is invoked to cancel the query if there is a prepared
     * statement object in execution for the requestId supplied as part of
     * cancel request
     *
     * @param requestId : the Id of the request to be cancelled
     */
    private void handleCancelRequest(final String requestId) {
        final NamedParameterStatement pstmt = requestIdMappings.get(requestId);
        try {
            if (pstmt != null) {
                pstmt.cancelExecution();
                ServicesLogger.detailed(Level.FINE, getClass().getName(), "cancleRequest()",
                        "Cancel request successful for RequestID=" + requestId);
            }
        } catch (final SQLException sqlEx) {
            ServicesLogger.detailed(Level.FINE, getClass().getName(), "handleCancelledRequest()",
                    "Cancel request failed for RequestID=" + requestId, sqlEx.getStackTrace());
        } finally {
            requestIdMappings.remove(requestId);
        }

    }

}