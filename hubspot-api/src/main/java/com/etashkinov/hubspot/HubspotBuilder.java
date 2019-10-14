package com.etashkinov.hubspot;

import java.util.LinkedList;
import java.util.List;

public abstract class HubspotBuilder<T extends HubspotBuilder<T, PV>, PV> {

    private final List<PV> properties;

    public HubspotBuilder() {
        this.properties = new LinkedList<>();
    }

    public T addValue(String property, String value) {
        if (value != null) {
            properties.add(createPropertyValue(property, value));
        }
        return (T) this;
    }

    protected abstract PV createPropertyValue(String property, String value);

    protected List<PV> getProperties() {
        return properties;
    }
}
