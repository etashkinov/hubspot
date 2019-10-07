package com.etashkinov.hubspot;

import java.util.Collections;
import java.util.List;

public class HubspotNewContact {

    public static Builder builder() {
        return new Builder();
    }

    private final List<HubspotContactPropertyValue> properties;

    private HubspotNewContact(List<HubspotContactPropertyValue> properties) {
        this.properties = Collections.unmodifiableList(properties);
    }

    public List<HubspotContactPropertyValue> getProperties() {
        return properties;
    }

    private static final class HubspotContactPropertyValue extends HubspotPropertyValue {
        private final String property;

        private HubspotContactPropertyValue(String property, String value) {
            super(value);
            this.property = property;
        }

        public String getProperty() {
            return property;
        }
    }

    public static final class Builder extends HubspotBuilder<Builder, HubspotContactPropertyValue> {

        @Override
        protected HubspotContactPropertyValue createPropertyValue(String property, String value) {
            return new HubspotContactPropertyValue(property, value);
        }

        public Builder firstName(String firstName) {
            return addValue(HubspotContactProperty.FIRST_NAME, firstName);
        }

        public Builder lastName(String lastName) {
            return addValue(HubspotContactProperty.LAST_NAME, lastName);
        }

        public Builder phone(String phone) {
            return addValue(HubspotContactProperty.PHONE, phone);
        }

        public Builder company(String company) {
            return addValue(HubspotContactProperty.COMPANY, company);
        }

        public Builder address(String address) {
            return addValue(HubspotContactProperty.ADDRESS, address);
        }

        public Builder city(String city) {
            return addValue(HubspotContactProperty.CITY, city);
        }

        public Builder state(String state) {
            return addValue(HubspotContactProperty.STATE, state);
        }

        public Builder country(String country) {
            return addValue(HubspotContactProperty.COUNTRY, country);
        }

        public Builder zip(String zip) {
            return addValue(HubspotContactProperty.ZIP, zip);
        }

        public HubspotNewContact build() {
            return new HubspotNewContact(getProperties());
        }
    }
}
