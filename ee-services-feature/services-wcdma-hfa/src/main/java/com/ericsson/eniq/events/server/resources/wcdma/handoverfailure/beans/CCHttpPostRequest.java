package com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CCHttpPostRequest {

    private CCSelected[] selected;

    public CCSelected[] getSelected() {
        return this.selected;
    }

    public void setSelected(final CCSelected[] selected) {
        this.selected = selected;
    }
}