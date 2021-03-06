package com.etashkinov.hubspot.companies;

import com.etashkinov.hubspot.HubspotProperties;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HubspotCompany extends HubspotProperties {

    private final String companyId;

    @JsonCreator
    public HubspotCompany(@JsonProperty("companyId") String companyId,
                          @JsonProperty("properties") Map<String, HubspotPropertyValue> properties) {
        super(properties);
        this.companyId = companyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getName() {
        return getProperty(HubspotCompanyProperty.NAME)
                .orElseThrow(() -> new IllegalStateException("No name found for company " + getCompanyId()));
    }
}
