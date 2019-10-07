package com.etashkinov.hubspot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HubspotContactProfile extends HubspotProperties {

    private final String vid;

    @JsonCreator
    public HubspotContactProfile(@JsonProperty("vid") String vid,
                                 @JsonProperty("properties") Map<String, HubspotPropertyValue> properties) {
        super(properties);
        this.vid = vid;
    }

    public String getVid() {
        return vid;
    }

    public Optional<String> getAssociatedCompanyId() {
        return getProperty(HubspotContactProperty.ASSOCIATED_COMPANY_ID);
    }
}
