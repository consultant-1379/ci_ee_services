/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.datetime;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Class to manage queries that don't require the usual date time parameters
 * If a query doesn't require these parameters, its template path should be added to the whiteList below (otherwise
 * the query exeuction will fail )
 * @author eemecoy
 *
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class DateTimeWhiteList {

    private List<String> whiteList;

    @PostConstruct
    public void applicationStartup() {
        whiteList = new ArrayList<String>();
        whiteList.add(WCDMA_CALL_FAILURE_SUBSCRIBER_DETAILS);
    }

    @PreDestroy
    public void applicationDestroy() {
        whiteList = null;

    }

    /**
     * Returns true if the query defined by the templatePath parameter requires the dateFrom and dateTo SQL parameters, 
     * false otherwise
     * 
     * @param templatePath              template path as defined in the templateMap.csv file
     * @return boolean - see above
     */
    public boolean queryRequiresDateTimeParameters(final String templatePath) {
        return !whiteList.contains(templatePath);
    }

}
