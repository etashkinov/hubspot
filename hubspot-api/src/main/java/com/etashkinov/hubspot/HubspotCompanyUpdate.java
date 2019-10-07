package com.etashkinov.hubspot;

import java.util.Collections;
import java.util.List;

public class HubspotCompanyUpdate {

    public static Builder builder() {
        return new Builder();
    }

    private final List<HubspotCompanyPropertyValue> properties;

    private HubspotCompanyUpdate(List<HubspotCompanyPropertyValue> properties) {
        this.properties = Collections.unmodifiableList(properties);
    }

    public List<HubspotCompanyPropertyValue> getProperties() {
        return properties;
    }

    private static final class HubspotCompanyPropertyValue extends HubspotPropertyValue {
        private final String name;

        private HubspotCompanyPropertyValue(String name, String value) {
            super(value);
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static final class Builder extends HubspotBuilder<Builder, HubspotCompanyPropertyValue> {

        @Override
        protected HubspotCompanyPropertyValue createPropertyValue(String name, String value) {
            return new HubspotCompanyPropertyValue(name, value);
        }

        public HubspotCompanyUpdate build() {
            return new HubspotCompanyUpdate(getProperties());
        }
    }
}
