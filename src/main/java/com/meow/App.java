package com.meow;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class App {
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    protected static void postReq(String url) throws Exception {

        String json = new StringBuilder()
                .append("{")
                .append("\"name\":\"meow\",")
                .append("\"age\":\"15\"")
                .append("}").toString();

        var request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode()); // Status Code
        System.out.println(response.body()); // Body
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            postReq("your URL");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}