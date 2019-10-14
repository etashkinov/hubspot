package com.etashkinov.hubspot.contacts;

import com.etashkinov.hubspot.HubspotClient;

import java.util.Map;

public class ContactsClient {

    private static final String PATH_CONTACT = "/contacts/v1/contact/";
    private static final String PATH_CONTACT_VID = PATH_CONTACT + "vid/";
    private static final String PATH_CONTACT_EMAIL = PATH_CONTACT + "email/";
    private static final int ASSOCIATION_COMPANY = 1;

    private final HubspotClient client;

    public ContactsClient(HubspotClient client) {
        this.client = client;
    }

    public HubspotCreatedContact createOrUpdate(String email, HubspotContactUpdate newContact) {
        String path = PATH_CONTACT + "createOrUpdate/email/" + email;
        return client.postForObject(path, newContact, HubspotCreatedContact.class);
    }

    public HubspotContactProfile getByEmail(String email) {
        return client.getForObject(PATH_CONTACT_EMAIL + email + "/profile", HubspotContactProfile.class);
    }

    public HubspotContactProfile getById(String vid) {
        return client.getForObject(PATH_CONTACT_VID + vid + "/profile", HubspotContactProfile.class);
    }

    public void delete(String vid) {
        client.delete(PATH_CONTACT_VID + vid);
    }

    public void addToCompany(String vid, String companyId) {
        client.createAssociation(vid, companyId, ASSOCIATION_COMPANY);
    }

    public void update(String vid, HubspotContactUpdate update) {
        client.postForObject(PATH_CONTACT_VID + vid, update, Map.class);
    }
}
