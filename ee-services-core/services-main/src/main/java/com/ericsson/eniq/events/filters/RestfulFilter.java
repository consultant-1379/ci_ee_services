package com.ericsson.eniq.events.filters;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static javax.ws.rs.core.MediaType.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.header.InBoundHeaders;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class RestfulFilter implements ContainerRequestFilter {

    private static final List<MediaType> textHTMLMediaTypeAsList = new ArrayList<MediaType>();

    static {
        textHTMLMediaTypeAsList.add(TEXT_HTML_TYPE);
    }

    @Override
    public ContainerRequest filter(final ContainerRequest request) {
        if (request.getHeaderValue(REQUEST_ID) == null
                && TEXT_HTML_TYPE.equals(request.getAcceptableMediaType(textHTMLMediaTypeAsList))) {
            final InBoundHeaders inboundHeaders = new InBoundHeaders();
            inboundHeaders.add(REQUEST_ID, Math.random() + "_" + new Date().getTime());
            request.setHeaders(inboundHeaders);
        }

        return request;
    }
}
