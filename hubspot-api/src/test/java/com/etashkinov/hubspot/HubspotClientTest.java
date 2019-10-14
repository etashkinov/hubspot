package com.etashkinov.hubspot;

import com.etashkinov.hubspot.companies.HubspotCompany;
import com.etashkinov.hubspot.companies.HubspotCompanyUpdate;
import com.etashkinov.hubspot.contacts.HubspotContactProfile;
import com.etashkinov.hubspot.contacts.HubspotContactUpdate;
import com.etashkinov.hubspot.contacts.HubspotCreatedContact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HubspotClientTest {

    private static final String CONTACT_EMAIL = "test@gmail.com";
    private HubspotClient client;


    @Before
    public void setup() {
        client = new HubspotClient(System.getenv("hubspot.api.key"));
        cleanupContact();
    }

    private void cleanupContact() {
        HubspotContactProfile profile = getContact();
        if (profile != null) {
            client.getContacts().delete(profile.getVid());
        }
    }

    @Test
    public void shouldCreateContact() {
        HubspotCreatedContact contact = createContact();
        assertThat(contact.getVid()).isNotNull();
        assertThat(contact.isNew()).isTrue();

        HubspotContactProfile profile = getContact();
        assertThat(profile.getVid()).isEqualTo(contact.getVid());
    }

    private HubspotCreatedContact createContact() {
        return client.getContacts().createOrUpdate(CONTACT_EMAIL, HubspotContactUpdate.builder().build());
    }

    @Test
    public void shouldAddCompany() {
        HubspotCreatedContact contact = createContact();

        HubspotContactProfile profile = getContact();
        assertThat(profile.getAssociatedCompanyId()).isEmpty();

        client.getContacts().update(contact.getVid(), HubspotContactUpdate.builder().company(CONTACT_EMAIL).build());

        HubspotCompany company = client.getCompanies().create(HubspotCompanyUpdate.builder().name(CONTACT_EMAIL).build());

        try {
            assertThat(company.getName()).isEqualTo(CONTACT_EMAIL);

            client.getContacts().addToCompany(contact.getVid(), company.getCompanyId());
        } finally {
            //cleanup
            client.getCompanies().delete(company.getCompanyId());
        }
    }

    private HubspotContactProfile getContact() {
        return client.getContacts().getByEmail(CONTACT_EMAIL);
    }

    @After
    public void tearDown() {
        cleanupContact();
    }
}