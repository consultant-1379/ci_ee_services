/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ericsson.eniq.events.server.services.MetaDataService;

/**
 * This is a sub-resource service called from the parent resource in: 
 * <br> com/ericsson/eniq/events/server/resources/EniqEventsDataService.java <br>
 * 
 * This stateless session bean will be used to return the JSON result set to the UI GUI.
 * 
 * 
 * @author edeccox
 * @author estepdu
 * @author ehaoswa
 * @author eromsza
 * 
 * @since Mar 2010
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class MetaDataResource {

    @EJB
    MetaDataService metaDataService;

    /**
     * Return meta data used to construct UI.
     *
     * @return Returns meta data structure to allow UI to build itself
     *                  with packet switched Menu Options (PS)
     * @throws IOException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(UI_PARAM)
    public String getUIMetaData() throws IOException {
        return metaDataService.getUIMetaData();
    }

    /**
     * Return meta data used to construct UI.
     *
     * @return Returns meta data structure to allow UI to build itself
     *                  with circuit switched Menu Options (CS)
     * @throws IOException
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path(UI_MSS_PARAM)
    public String getUIMetaData_MSS() throws IOException {
        return metaDataService.getUIMetaData();
    }

}