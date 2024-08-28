package com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.beans;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CCSelected {
    private String label;

    private String description;

    private String groupId;

    public String get1() {
        return label;
    }

    public void set1(final String label) {
        this.label = label;
    }

    public String get2() {
        return description;
    }

    public void set2(final String description) {
        this.description = description;
    }

    public String get3() {
        return groupId;
    }

    public void set3(final String groupId) {
        this.groupId = groupId;
    }
}