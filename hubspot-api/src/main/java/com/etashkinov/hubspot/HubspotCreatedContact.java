package com.etashkinov.hubspot;

public class HubspotCreatedContact {

    private final String vid;
    private final boolean isNew;

    public HubspotCreatedContact(String vid, boolean isNew) {
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
