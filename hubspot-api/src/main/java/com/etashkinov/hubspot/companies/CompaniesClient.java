package com.etashkinov.hubspot.companies;

import com.etashkinov.hubspot.HubspotClient;

public class CompaniesClient {

    private static final String PATH_COMPANIES = "/companies/v2/companies/";

    private final HubspotClient client;

    public CompaniesClient(HubspotClient client) {
        this.client = client;
    }

    public HubspotCompany create(HubspotCompanyUpdate update) {
        return client.postForObject(PATH_COMPANIES, update, HubspotCompany.class);
    }

    public void updateCompany(String companyId, HubspotCompanyUpdate companyUpdate) {
        client.put(PATH_COMPANIES + companyId, companyUpdate);
    }

    public HubspotCompany getCompany(String companyId) {
        return client.getForObject(PATH_COMPANIES + companyId, HubspotCompany.class);
    }

    public void delete(String companyId) {
        client.delete(PATH_COMPANIES + companyId);
    }

}
