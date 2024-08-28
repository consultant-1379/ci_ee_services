package com.ericsson.eniq.events.server.common.exception;

/**
 * User: ekurshi
 * Date: 15/12/11
 */
public class GroupDownloadException extends RuntimeException {
    /**
     * Constructor with a message
     *
     * @param message Error message
     */
    public GroupDownloadException(final String message) {
        super(message);
    }

    /**
     * Constructor with a message
     *
     * @param cause Original cause
     */
    public GroupDownloadException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
