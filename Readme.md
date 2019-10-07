# Java API for Hubspot

Simple java wrapper on https://developers.hubspot.com/docs/overview

Just a couple of endpoints is implemented so far, but the lib is designed to be easily extended to your needs. 
Feel free to contribute.

## Usage

### Instantiating a client

####Without Spring web adapting any http client:
```java
...
HubspotClient client = new HubspotClient(httpClient, hubspotApiKey);
...
```

Implementing httpClient

 Simple
```java
HttpClient httpClient = new HttpClient() {
    @Override
     public <T> T postForObject(String path, Object body, Class<T> clazz) {
        return null;
    }

    @Override
    public void put(String path, Object body) {

    }

    @Override
    public <T> T getForObject(String path, Class<T> clazz) {
        return null;
    }
};
```

 With Jackson mapping
```java
HttpClient httpClient = new JacksonHttpClient() {
    @Override
    protected InputStream post(String path, InputStream body) {
        return null;
    }

    @Override
    protected void put(String path, InputStream body) {

    }

    @Override
    protected InputStream get(String path) {
        return null;
    }
};
```

#### With Spring Web
```java
client = new HubspotSpringClient(restTemplate, hubspotApiKey);
```

### Basic implemented endpoints

```java
...
String email = "new contact@email.com";

HubspotNewContact newContact =  HubspotNewContact.builder()
    .firstName("")
    .lastName("")
    .phone("")
    .company("")
    .addValue("Any custom property name", "property value")
    .build();

client.createOrUpdateContact(email, newContact);

HubspotContactProfile profile = client.getContact(email);

String companyId = profile.getAssociatedCompanyId();
HubspotCompany company = getCompany(companyId);

HubspotCompanyUpdate update = HubspotCompanyUpdate.builder()
    .addValue("property1", "value1")
    .addValue("property2", "value2")
    .addValue("property3", "value4")
    .build();
client.updateCompany(companyId, update);
...
```