package com.etashkinov.hubspot.spring;

import com.etashkinov.hubspot.HubspotClient;
import org.springframework.web.client.RestTemplate;

public class HubspotSpringClient extends HubspotClient {

    public HubspotSpringClient(String rootPath, RestTemplate template, String hubspotApiKey) {
        super(rootPath, new HttpSpringClient(template), hubspotApiKey);
    }

    public HubspotSpringClient(RestTemplate template, String hubspotApiKey) {
        super(new HttpSpringClient(template), hubspotApiKey);
    }
}
