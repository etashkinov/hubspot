package com.etashkinov.hubspot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Optional;

public class HubspotProperties {

    private final Map<String, HubspotPropertyValue> properties;

    public HubspotProperties(Map<String, HubspotPropertyValue> properties) {
        this.properties = properties;
    }

    public Optional<String> getProperty(String name) {
        return Optional.ofNullable(properties.get(name)).map(HubspotPropertyValue::getValue);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class HubspotPropertyValue {
        private final String value;

        @JsonCreator
        HubspotPropertyValue(@JsonProperty("value") String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }
    }
}
