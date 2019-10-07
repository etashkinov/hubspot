package com.etashkinov.hubspot;

import java.util.Map;

public class HubspotClient {

    public static final String ROOT_PATH = "https://api.hubapi.com";

    private static final String PATH_COMPANIES = "/companies/v2/companies/";
    private static final String PATH_CONTACT = "/contacts/v1/contact/";

    private final String hubspotApiKey;
    private final HttpClient httpClient;

    public HubspotClient(String rootPath, HttpClient httpClient, String hubspotApiKey) {
        this.httpClient =  new RootPathHttpClient(rootPath, httpClient);
        this.hubspotApiKey = hubspotApiKey;
    }

    public HubspotClient(HttpClient httpClient, String hubspotApiKey) {
        this(ROOT_PATH, httpClient, hubspotApiKey);
    }

    public HubspotCreatedContact createOrUpdateContact(String email, HubspotNewContact newContact) {
        String path = PATH_CONTACT + "createOrUpdate/email/" + email;
        return postForObject(path, newContact, HubspotCreatedContact.class);
    }

    public HubspotContactProfile getContact(String email) {
        return getForObject(PATH_CONTACT + "email/" + email + "/profile", HubspotContactProfile.class);
    }

    public HubspotCompany getCompany(String companyId) {
        return getForObject(PATH_COMPANIES + companyId, HubspotCompany.class);
    }

    public void updateCompany(String companyId, HubspotCompanyUpdate companyUpdate) {
        put(PATH_COMPANIES + companyId, companyUpdate);
    }

    private <T> T postForObject(String path, Object body, Class<T> clazz) {
        return httpClient.postForObject(getPathWithParam(path), body, clazz);
    }

    private void put(String path, Object body) {
        httpClient.put(getPathWithParam(path), body);
    }

    private <T> T  getForObject(String path, Class<T> clazz) {
        return httpClient.getForObject(getPathWithParam(path), clazz);
    }

    private String getPathWithParam(String path) {
        return path + "?hapikey=" + hubspotApiKey;
    }
}
