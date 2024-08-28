/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.templates.utils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.FieldMethodizer;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.ericsson.eniq.events.server.common.ApplicationConstants;
import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.templates.exception.ResourceInitializationException;

/**
 * Class providing utilities for loading velocity templates
 * 
 * @author eemecoy
 * 
 */
@Singleton
//@TransactionManagement(TransactionManagementType.BEAN)
 @LocalBean
@Startup
public class TemplateUtils {

    /** The props. */
    private final Properties props = new Properties();

    /** The Constant EMPTY_PARAMETER_MAP. */
    private static final Map<String, ?> EMPTY_PARAMETER_MAP = new HashMap<String, Object>();

    private static final String VM_MACRO = "VM_query_macros.vm";

    @PostConstruct
    public void applicationStartup() throws Exception { // NOPMD (eemecoy)
                                                        // 1/6/10, Exception is
                                                        // thrown from
                                                        // Velocity.init, a 3pp)
        props.setProperty("velocimacro.library", VM_MACRO);
        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.description", "Classpath Loader");
        props.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        props.setProperty("velocimacro.permissions.allow.inline.local.scope", "true");
        Velocity.init(props);
    }

    @PreDestroy
    public void applicationShutdown() {
        props.clear();
    }

    /**
     * Gets the query from template.
     * 
     * @param templateFile
     *            the template file
     * @return the query from template
     */
    public String getQueryFromTemplate(final String templateFile) {
        return getQueryFromTemplate(templateFile, EMPTY_PARAMETER_MAP);
    }

    /**
     * Gets the query from template.
     * 
     * @param templateFile
     *            the template file
     * @param parameters
     *            the parameters
     * @return the query from template
     * @throws ResourceInitializationException
     *             the resource initialization exception
     */
    public String getQueryFromTemplate(final String templateFile, final Map<String, ?> parameters)
            throws ResourceInitializationException {

        ServicesLogger.detailed(Level.FINE, getClass().getName(), "getQueryFromTemplate", templateFile, parameters);

        final VelocityContext context = new VelocityContext();

        // allows access to ApplicationConstants _constants_ in velocity
        // templates, e.g. $ApplicationConstants.COUNT_PARAM
        context.put("ApplicationConstants", new FieldMethodizer(
                "com.ericsson.eniq.events.server.common.ApplicationConstants"));

        // allows access to ApplicationConstants _methods_ in velocity templates
        context.put("ApplicationMethods", ApplicationConstants.getInstance());

        if (parameters != null && parameters.size() > 0) {
            final Set<String> keys = parameters.keySet();
            for (final String key : keys) {
                context.put(key, parameters.get(key));
            }
        }

        try {
            final Template template = Velocity.getTemplate(templateFile);
            final StringWriter sw = new StringWriter();
            template.merge(context, sw);
            return sw.toString();
        } catch (final ResourceNotFoundException e) {
            throw new ResourceInitializationException("Can not find query template", e);
        } catch (final ParseErrorException e) {
            throw new ResourceInitializationException("Error parsing query template", e);
        } catch (final Exception e) {
            throw new ResourceInitializationException("Error in template initialization", e);
        }
    }

}
