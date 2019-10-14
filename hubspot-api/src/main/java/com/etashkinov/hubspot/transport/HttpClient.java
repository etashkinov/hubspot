package com.etashkinov.hubspot.transport;

public interface HttpClient {
    <T> T getForObject(String path, Class<T> clazz);
    <T> T postForObject(String path, Object body, Class<T> clazz);
    void put(String path, Object body);
    void delete(String path);
}
