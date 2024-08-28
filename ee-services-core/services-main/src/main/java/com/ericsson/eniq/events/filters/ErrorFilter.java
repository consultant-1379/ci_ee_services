package com.ericsson.eniq.events.filters;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ErrorFilter implements ContainerResponseFilter {

    private static final int FILE_NOT_FOUND = 404;

    private static final int INTERNAL_SERVER_ERROR = 500;

    @Override
    public ContainerResponse filter(final ContainerRequest request, final ContainerResponse response) {

        if (response.getStatus() == FILE_NOT_FOUND || response.getStatus() == INTERNAL_SERVER_ERROR) {
            response.setResponse(Response
                    .status(response.getStatus())
                    .entity("{\"success\" : \"false\", \"errorDescription\" : \"HTTP " + response.getStatus()
                            + " status returned. Please refer to server.log for more details.\"}")
                    .type(MediaType.APPLICATION_JSON).build());
        }

        return response;
    }
}
/*LG Test*/