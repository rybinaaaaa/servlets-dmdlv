package org.rybina;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientExample {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

//        Либо клиент с дефолтными настройками
//        HttpClient.newHttpClient();

        HttpRequest request1 = HttpRequest.newBuilder(URI.create("https://www.google.com")).GET().build();
        HttpRequest request2 = HttpRequest.newBuilder(URI.create("https://www.google.com")).POST(BodyPublishers.ofString("Hello")).build();


        HttpResponse<String> response = httpClient.send(request1, BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response.headers());
    }
}
