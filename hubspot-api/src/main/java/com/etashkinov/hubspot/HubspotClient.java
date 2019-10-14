package com.etashkinov.hubspot;

import com.etashkinov.hubspot.companies.CompaniesClient;
import com.etashkinov.hubspot.contacts.ContactsClient;
import com.etashkinov.hubspot.transport.HttpClient;
import com.etashkinov.hubspot.transport.RootPathHttpClient;
import com.etashkinov.hubspot.transport.SimpleHttpClient;

public class HubspotClient {

    public static final String ROOT_PATH = "https://api.hubapi.com";

    private final String hubspotApiKey;
    private final HttpClient httpClient;

    public HubspotClient(String rootPath, HttpClient httpClient, String hubspotApiKey) {
        this.httpClient =  new RootPathHttpClient(rootPath, httpClient);
        this.hubspotApiKey = hubspotApiKey;
    }

    public HubspotClient(String hubspotApiKey) {
        this(new SimpleHttpClient(), hubspotApiKey);
    }

    public HubspotClient(HttpClient httpClient, String hubspotApiKey) {
        this(ROOT_PATH, httpClient, hubspotApiKey);
    }

    public void createAssociation(String from, String to, int definition) {
        put("/crm-associations/v1/associations", new HubspotAssociation(from, to, definition));
    }

    public <T> T postForObject(String path, Object body, Class<T> clazz) {
        return httpClient.postForObject(getPathWithParam(path), body, clazz);
    }

    public void put(String path, Object body) {
        httpClient.put(getPathWithParam(path), body);
    }

    public void delete(String path) {
        httpClient.delete(getPathWithParam(path));
    }

    public <T> T  getForObject(String path, Class<T> clazz) {
        return httpClient.getForObject(getPathWithParam(path), clazz);
    }

    private String getPathWithParam(String path) {
        return path + "?hapikey=" + hubspotApiKey;
    }

    public ContactsClient getContacts() {
        return new ContactsClient(this);
    }

    public CompaniesClient getCompanies() {
        return new CompaniesClient(this);
    }
}
