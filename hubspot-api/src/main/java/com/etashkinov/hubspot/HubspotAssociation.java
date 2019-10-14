package com.etashkinov.hubspot;

public class HubspotAssociation {
    private static final String DEFAULT_CATEGORY = "HUBSPOT_DEFINED";

    private final String fromObjectId;
    private final String toObjectId;
    private final String category;
    private final int definitionId;

    public HubspotAssociation(String fromObjectId, String toObjectId, int definitionId) {
        this(fromObjectId, toObjectId, DEFAULT_CATEGORY, definitionId);
    }

    public HubspotAssociation(String fromObjectId, String toObjectId, String category, int definitionId) {
        this.fromObjectId = fromObjectId;
        this.toObjectId = toObjectId;
        this.category = category;
        this.definitionId = definitionId;
    }

    public String getFromObjectId() {
        return fromObjectId;
    }

    public String getToObjectId() {
        return toObjectId;
    }

    public String getCategory() {
        return category;
    }

    public int getDefinitionId() {
        return definitionId;
    }
}
