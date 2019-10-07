package com.etashkinov.hubspot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class JacksonHttpClient implements HttpClient {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public <T> T postForObject(String path, Object body, Class<T> clazz) {
        return toObject(post(path, fromObject(body)), clazz);
    }

    @Override
    public void put(String path, Object body) {
        put(path, fromObject(body));
    }
    
    @Override
    public <T> T getForObject(String path, Class<T> clazz) {
        return toObject(get(path), clazz);
    }

    protected abstract InputStream post(String path, InputStream body);
    protected abstract void put(String path, InputStream body);
    protected abstract InputStream get(String path);

    private InputStream fromObject(Object object) {
        try {
            return new ByteArrayInputStream(MAPPER.writeValueAsBytes(object));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to write " + object, e);
        }
    }

    private <T> T toObject(InputStream is, Class<T> clazz) {
        try {
            return MAPPER.readValue(is, clazz);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to read " + clazz, e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
