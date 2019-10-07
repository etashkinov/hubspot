package com.etashkinov.hubspot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HubspotCreatedContact {

    private final String vid;
    private final boolean isNew;

    @JsonCreator
    public HubspotCreatedContact(@JsonProperty("isNew") String vid, @JsonProperty("isNew") boolean isNew) {
        this.vid = vid;
        this.isNew = isNew;
    }

    public String getVid() {
        return vid;
    }

    public boolean isNew() {
        return isNew;
    }
}
