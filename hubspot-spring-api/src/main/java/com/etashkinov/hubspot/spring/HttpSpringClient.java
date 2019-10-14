package com.etashkinov.hubspot.spring;

import com.etashkinov.hubspot.transport.HttpClient;
import org.springframework.web.client.RestTemplate;

public class HttpSpringClient implements HttpClient {

    private final RestTemplate template;

    public HttpSpringClient(RestTemplate template) {
        this.template = template;
    }

    @Override
    public <T> T postForObject(String path, Object body, Class<T> clazz) {
        return template.postForObject(path, body, clazz);
    }

    @Override
    public void put(String path, Object body) {
        template.put(path, body);
    }

    @Override
    public void delete(String path) {
        template.delete(path);
    }

    @Override
    public <T> T getForObject(String path, Class<T> clazz) {
        return template.getForObject(path, clazz);
    }
}
