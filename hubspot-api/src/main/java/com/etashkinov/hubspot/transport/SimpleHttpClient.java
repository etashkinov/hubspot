package com.etashkinov.hubspot.transport;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class SimpleHttpClient extends JacksonHttpClient {


    @Override
    protected InputStream post(String path, InputStream body) {
        return send(path, "POST", body);
    }

    @Override
    protected void put(String path, InputStream body) {
        send(path, "PUT", body);
    }

    @Override
    protected InputStream get(String path) {
        return send(path, "GET", null);
    }

    private InputStream send(String path, String method, InputStream input) {
        try {
            URL url = new URL(path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);

            if (input != null) {
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type", "application/json");
                try (InputStream in = input; OutputStream out = con.getOutputStream()) {
                    transferTo(in, out);
                }
            }

            con.connect();

            int status = con.getResponseCode();

            if (status == 404) {
                return null;
            } else if (status > 299) {
                try (InputStream is = con.getErrorStream()) {
                    throw new IllegalStateException("Http request to " + path +
                            " returned an error " + status + " (" + con.getResponseMessage() + "): " + toString(is));
                }
            } else {
                return con.getInputStream();
            }
        } catch (Exception e) {
            throw new IllegalStateException("Http request to " + path + " failed", e);
        }
    }

    private String toString(InputStream inputStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    private void transferTo(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        while (len != -1) {
            out.write(buffer, 0, len);
            len = in.read(buffer);
        }
    }

    @Override
    public void delete(String path) {
        send(path, "DELETE", null);
    }
}