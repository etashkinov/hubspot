package com.etashkinov.hubspot;

public interface HttpClient {
    <T> T postForObject(String path, Object body, Class<T> clazz);
    void put(String path, Object body);
    <T> T getForObject(String path, Class<T> clazz);
}
