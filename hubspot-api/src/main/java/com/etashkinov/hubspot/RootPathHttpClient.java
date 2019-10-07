package com.etashkinov.hubspot;

public class RootPathHttpClient implements HttpClient {

    private final String rootPath;
    private final HttpClient httpClient;

    public RootPathHttpClient(String rootPath, HttpClient httpClient) {
        this.httpClient = httpClient;
        this.rootPath = rootPath;
    }

    @Override
    public <T> T postForObject(String path, Object body, Class<T> clazz) {
        return httpClient.postForObject(getAbsolutePath(path), body, clazz);
    }

    private String getAbsolutePath(String path) {
        return rootPath + path;
    }

    @Override
    public void put(String path, Object body) {
        httpClient.put(getAbsolutePath(path), body);
    }

    @Override
    public <T> T getForObject(String path, Class<T> clazz) {
        return httpClient.getForObject(getAbsolutePath(path), clazz);
    }
}
