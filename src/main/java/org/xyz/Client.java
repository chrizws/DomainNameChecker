package org.xyz;

import java.io.IOException;
import java.net.CookieManager;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Client {

    private HttpClient client;
    private HttpRequest request;
    private String url;

    public Client setParameter(String domain) {
        url = "https://api.ca.fury.ca/api/domain/validate?domain=" + domain;
        return this;
    }

    public Client createClient() {

        client = HttpClient.newBuilder()
                .cookieHandler(new CookieManager())
                .build();

        return this;
    }

    public void createRequest() {

        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public HttpResponse<String> getResponse() {

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        client.close();
    }
}
