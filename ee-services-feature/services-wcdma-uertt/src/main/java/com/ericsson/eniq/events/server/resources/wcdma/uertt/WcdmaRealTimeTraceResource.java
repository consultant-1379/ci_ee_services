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
package com.ericsson.eniq.events.server.resources.wcdma.uertt;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.UERTT;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author xmonsou
 * @since 2013
 * 
 */
@Stateless
@LocalBean
public class WcdmaRealTimeTraceResource extends AbstractResource {
	@EJB(beanName = "SubscriberUERTTService")
	private Service SubscriberUERTTService;

	@Override
	protected Service getService() {
		throw new UnsupportedOperationException();
	}

	/*
	 * @see com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.uertt.
	 * SubscriberUERTTService#getData
	 */
	@Path(UERTT)
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getSubscriberUERTTService() {
		return SubscriberUERTTService.getData(mapResourceLayerParameters());
	}
}
